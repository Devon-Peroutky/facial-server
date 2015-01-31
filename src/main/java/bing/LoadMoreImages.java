package bing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import jsonObjects.Star;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import code.HttpRequestService;
import code.QueryService;

public class LoadMoreImages {
	private static ObjectMapper mapper = new ObjectMapper();
	public static String accountKey = "wFMmKZpkWhdPqNdgZ2ftq8MFItXWgZ1jNZa5/+RIs20";
	public static String bingImageBaseUrl = "https://api.datamarket.azure.com/Data.ashx/Bing/Search/v1/Composite?Adult=%27moderate%27&Sources=%27image%27&$top=10&$format=JSON&Query=";

	public static void main(String[] args) throws IOException {
		
		
		byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
		String accountKeyEnc = new String(accountKeyBytes);

		ArrayList<Star> stars = QueryService.loadStars();
		for (Star s : stars) {
			URL url = new URL(bingImageBaseUrl + URLEncoder.encode("'" + s.name + "'"));
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
			
			String response = HttpRequestService.readResponse(urlConnection);
			ArrayList<String> urls = getUrls(response);
			QueryService.createRecognitionImages(s.name, urls);
		}
		
		
		
		
	}
	
	public static ArrayList<String> getUrls(String response) throws JsonParseException, JsonMappingException, IOException {
		ArrayList<String> ret = Lists.newArrayList();
		JsonNode rNode = mapper.readValue(response, JsonNode.class).get("d").get("results").get(0).get("Image");
		for (int i = 0; rNode.get(i) != null; i++) {
			ret.add(rNode.get(i).get("MediaUrl").asText());
		}
		return ret;
	}
}
