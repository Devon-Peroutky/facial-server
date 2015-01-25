package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import code.QueryService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import jsonObjects.RecognitionSearchResult;
import jsonObjects.SearchResponse;
import jsonObjects.SearchResponse.Candidate;
import jsonObjects.Star;
import jsonObjects.StarImage;

import org.jooq.Record;
import org.jooq.Result;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;

import static jooq.generated.Tables.IMAGES;
import static jooq.generated.Tables.STARS;

@Path("faceplusplus/")
@Produces(MediaType.APPLICATION_JSON)
public class FacePlusPlusResource {
	public static String[] ethnicityGroups = {"Black", "White", "Latina", "Asian"};
	public static String apiUrl = "http://apius.faceplusplus.com/";
	public static String apiKeyAndSecret = "?api_key=30b670da0c0cacf3741a7471d81324c3&api_secret=HwnpB8K-rhHzDx8dA-GyX3FhN0ahaILN&";
	public static String groupNameUrl = "group_name=Stars&";
	public static String apiKey = "30b670da0c0cacf3741a7471d81324c3";
	public static String apiSecret = "HwnpB8K-rhHzDx8dA-GyX3FhN0ahaILN";
	public static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Tries up to four times to make a request to the given url string. Keeps trying until it gets a 200 response.
	 */
	public static HttpURLConnection makeRequest(String urlString) {
		try {
			HttpURLConnection conn = null;
			int responseCode = 0;
			int tries = 0;
			while (responseCode != 200 && tries < 4) {
				URL url = new URL(urlString);
				conn = (HttpURLConnection) url.openConnection();
				tries++;
				if (conn.getResponseCode() == 200)
					return conn;
				
				System.err.println("Recieved response code "
							+ conn.getResponseCode()
							+ ", trying again in two seconds");
				Thread.sleep(2000);
			}
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getFullResponse(HttpURLConnection conn) {
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
		
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine + "\n");
			}
			in.close();
	
			// print result
			return response.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "400 ERROR IN READING RESPONSE";
	}

	@GET
	@Path("detection/detect")
	public Response detect(@QueryParam("url") String img) {
		try {
			System.out.println("received detect request");
			String urlString = apiUrl + "detection/detect" + apiKeyAndSecret
					+ "url=" + URLEncoder.encode(img);
			System.out.println(urlString);
			HttpURLConnection conn = makeRequest(urlString);
			String response = getFullResponse(conn);
			return Response.ok().entity(response).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.noContent().build();
		}

			
	}
	
	@GET
	@Path("recognition/identify")
	public Response identify(@QueryParam("key_face_id") String faceId) {
		String urlString = apiUrl + "recognition/identify" + apiKeyAndSecret
				+ groupNameUrl + "key_face_id=" + faceId;
		HttpURLConnection conn = makeRequest(urlString);
		String response = getFullResponse(conn);
		return Response.ok().entity(response).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@GET
	@Path("recognition/search")
	public Response search(@QueryParam("key_face_id") String faceId) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("recieved recogtion/search request");
		try {
			// 1. Find average match with ethnicity
			double bestAvgMatch = 0;
			SearchResponse bestCategory = null;
			String bestGroup = "";
			for (String ethnicityGroup : ethnicityGroups) {
				String urlString = apiUrl + "recognition/search" + apiKeyAndSecret + "faceset_name=" + ethnicityGroup + "&key_face_id=" + faceId + "&count=300";
				String response = getFullResponse(makeRequest(urlString));
				SearchResponse searchResponse = mapper.readValue(response, SearchResponse.class);
				double medianMatch = searchResponse.getMedianMatch();
				System.out.println(ethnicityGroup + ": " + medianMatch);
				if (medianMatch > bestAvgMatch) {
					bestAvgMatch = medianMatch;
					bestCategory = searchResponse;
					bestGroup = ethnicityGroup;
				}
			}
			System.out.println("Best group: " + bestGroup);
			// 2. Find best matches within that ethnicity
			ListMultimap<Star, Candidate> starToCandidate = ArrayListMultimap.create();
			for (Candidate candidate : bestCategory.candidate) {
				starToCandidate.put(Main.faceIdToStarMap.get(candidate.face_id), candidate);
			}
			
			Star bestMatch = null;
			double bestSimilarity = 0;
			List<Candidate> topCandidates = null;
			for (Star s : starToCandidate.keys()) {
				List<Candidate> candidates = starToCandidate.get(s);
				double total = 0;
				for (Candidate c : candidates) {
					total += c.similarity;
				}
				double average = total / candidates.size();
				if (average > bestSimilarity) {
					bestSimilarity = average;
					bestMatch = s;
					topCandidates = candidates;
				}
			}
			
			// 3. Find best matching url for person
			double topSimilarity = 0;
			String topUrl = "";
			for (Candidate c : topCandidates) {
				if (c.similarity > topSimilarity) {
					StarImage s = QueryService.getStarImage(c.face_id);
					topSimilarity = c.similarity;
					topUrl = s.url;
				}
			}
		
		// return {star: {star}, bestURl: {url}
		return Response.ok().entity(new RecognitionSearchResult(bestMatch, topUrl)).header("Access-Control-Allow-Origin", "*").build();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.noContent().build();
		}
	}
	
	
	@GET
	@Path("findbestmatch")
	@Produces("text/plain")
	public Response findBestMatch(@QueryParam("starname") String starName,
			@QueryParam("normalfaceid") String normalFaceId) throws JsonParseException, JsonMappingException, IOException {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
		
			
			String urlString = apiUrl + "person/get_info" + apiKeyAndSecret + "person_name=" + URLEncoder.encode(starName);
			String result = getFullResponse(makeRequest(urlString));
			JsonNode faces = mapper.readValue(result, JsonNode.class).get("face");
			System.out.println(faces);
			String bestFaceId = "";
			int bestMatch = 0;
			for (int i = 0; faces.get(i) != null; i++) {
				String starFaceId = faces.get(i).get("face_id").asText();
				String compareUrl = apiUrl + "recognition/compare" + apiKeyAndSecret + "face_id1=" + normalFaceId + "&face_id2=" + starFaceId;
				String compareResult = getFullResponse(makeRequest(compareUrl));
				JsonNode compare = mapper.readValue(compareResult, JsonNode.class);
				int similarity = compare.get("similarity").asInt();
				if (similarity > bestMatch) {
					bestFaceId = starFaceId;
					bestMatch = similarity;
				}
			}
			System.out.println("best face: " + bestFaceId);
			// get the url associated with it
			Result<Record> records = QueryService.create.select().from(IMAGES).where(IMAGES.FACE_ID.equal(bestFaceId)).fetch();
			String bestImg = records.get(0).getValue(IMAGES.IMG);
			
			
			
			
			
			return Response.ok().entity(bestImg).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.noContent().build();
		}
	}
}
