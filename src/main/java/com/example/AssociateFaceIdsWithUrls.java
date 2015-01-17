package com.example;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import code.QueryService;

import static com.example.FacePlusPlusResource.*;
import static jooq.generated.Tables.IMAGES;
import static jooq.generated.Tables.STARS;

/**
 * Class that gets each person in api, gets all of their face_ids, finds the url
 * of their face_ids, then updates db with that info
 * 
 * @author Student
 * 
 */
public class AssociateFaceIdsWithUrls {
	public static void main(String[] args) throws JsonParseException,
			IOException, InterruptedException {
		Scanner scan = new Scanner(new File("names.txt"));
		ObjectMapper mapper = new ObjectMapper();
		while (scan.hasNextLine()) {
			Thread.sleep(2000);
			String name = scan.nextLine().trim();
			String result = getFullResponse(makeRequest(apiUrl + "person/get_info" + apiKeyAndSecret + "person_name=" + URLEncoder.encode(name)));
			
			JsonNode rootNode = mapper.readValue(result, JsonNode.class);
			JsonNode faces = rootNode.get("face");
			//cfb501f03a0fcfca9550b52bd89927b6
			int i = 0;
			while (faces.get(i) != null) {
				String face_id = faces.get(i).get("face_id").asText();
				Thread.sleep(2000);
				String face_result = getFullResponse(makeRequest(apiUrl + "info/get_face" + apiKeyAndSecret + "face_id=" + face_id));
				JsonNode faceReader = mapper.readValue(face_result, JsonNode.class);
				String url = faceReader.get("face_info").get(0).get("url").asText();
				System.out.println("update " + name + " " + face_id + " url " + url);
				
				QueryService.create.update(IMAGES).set(IMAGES.FACE_ID, face_id)
				.where(IMAGES.IMG.equal(url)).execute();
				
				i++;
			}
			

		}
	}
}
