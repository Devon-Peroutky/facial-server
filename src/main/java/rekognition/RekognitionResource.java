package rekognition;

import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import code.HttpRequestService;

@Path("rekognition/")
@Produces(MediaType.APPLICATION_JSON)
public class RekognitionResource {
	
	public static String apiKey = "gfU32Hud7vlEj06S";
	public static String apiSecret = "gJcngjDjcuTk2WG9";
	public static String apiKeyUrl = "api_key=" + apiKey;
	public static String apiSecretUrl = "&api_secret=" + apiSecret;
	public static String nameSpaceUrl = "&name_space=Stars7";
	public static String apiUrl = "https://rekognition.com/func/api/?";
	public static String baseUrl = apiUrl + apiKeyUrl + apiSecretUrl + nameSpaceUrl + "&jobs=";
	
	@Path("recognize")
	@GET
	public Response recognize(@QueryParam("url") String url) {
		System.out.println("Recieved recognize request");
		try {
			String requestUrl = baseUrl + "face_recognize&urls=" + URLEncoder.encode(url) + "&num_return=5";
			String response = HttpRequestService.makeRequest(requestUrl);
			return Response.ok().entity(response).header("Access-Control-Allow-Origin", "*").build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.ok().entity("fail").build();
		}
	}
}
