package rekognition;

import java.net.URLEncoder;
import java.util.Set;

import jsonObjects.Star;
import code.HttpRequestService;
import code.QueryService;

public class loadStarsIntoRekognition {
	public static String apiKey = "gfU32Hud7vlEj06S";
	public static String apiSecret = "gJcngjDjcuTk2WG9";
	public static String apiKeyUrl = "api_key=" + apiKey;
	public static String apiSecretUrl = "&api_secret=" + apiSecret;
	public static String nameSpaceUrl = "&name_space=Stars";
	public static String apiUrl = "https://rekognition.com/func/api/?";
	
	public static void main (String[] args) throws InterruptedException {
		Set<Star> stars = QueryService.loadStars();
		String addFaceBaseUrl = apiUrl + apiKeyUrl + apiSecretUrl + nameSpaceUrl + "&jobs=face_add";
		for (Star s : stars) {
			for (String img : s.images) {
				String url = addFaceBaseUrl + "&tag=" + URLEncoder.encode(s.name) + "&urls=" + URLEncoder.encode(img);
				System.out.println(HttpRequestService.makeRequest(url));;
				Thread.sleep(2000);
			}
		}
	}
}
