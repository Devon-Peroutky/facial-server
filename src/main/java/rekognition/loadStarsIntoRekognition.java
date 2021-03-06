package rekognition;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jsonObjects.Star;
import code.HttpRequestService;
import code.QueryService;

public class loadStarsIntoRekognition {
	// start at Rihanna rines
	public static String apiKey = "gfU32Hud7vlEj06S";
	public static String apiSecret = "gJcngjDjcuTk2WG9";
	public static String apiKeyUrl = "api_key=" + apiKey;
	public static String apiSecretUrl = "&api_secret=" + apiSecret;
	public static String nameSpaceUrl = "&name_space=Stars7";
	public static String apiUrl = "https://rekognition.com/func/api/?";
	private static String startName = "Rihanna Rimes";
	
	public static void main (String[] args) throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		ArrayList<Star> stars = QueryService.loadStars();
		ObjectMapper mapper = new ObjectMapper();
		String addFaceBaseUrl = apiUrl + apiKeyUrl + apiSecretUrl + nameSpaceUrl + "&jobs=face_add";
		int count = 0;
		boolean start = false;
		for (Star s : stars) {
			if (s.name.equals(startName)) {
				start = true;
			}
			if (!start)
				continue;
			System.out.println(s.name);
			ArrayList<String> images = QueryService.getRecognitionImages(s.name);
			for (String img : images) {
				try {
					String url = addFaceBaseUrl + "&tag=" + URLEncoder.encode(s.name.replace(' ', '_')) + "&urls=" + URLEncoder.encode(img);
					String result = HttpRequestService.makeRequest(url);
					JsonNode r = mapper.readValue(result, JsonNode.class);
					if (r.get("face_detection").size() == 0) {
						System.out.println("FNF;" + s.name+";"+img);
					} else if (r.get("face_detection").size() > 1) {
						System.out.println("MFF;" + s.name+";"+img);
					}
					count++;
					if (count % 40 == 0) {
						System.out.println("COUNT;" + count);
					}
				} catch(Exception e) {
					
				}
			}
		}
	}
}
