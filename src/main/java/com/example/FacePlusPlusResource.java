package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;

@Path("faceplusplus/")
@Produces(MediaType.APPLICATION_JSON)
public class FacePlusPlusResource {

	public static String apiUrl = "http://apius.faceplusplus.com/";
	public static String apiKeyAndSecret = "?api_key=30b670da0c0cacf3741a7471d81324c3&api_secret=HwnpB8K-rhHzDx8dA-GyX3FhN0ahaILN&";
	public static String apiKey = "30b670da0c0cacf3741a7471d81324c3";
	public static String apiSecret = "HwnpB8K-rhHzDx8dA-GyX3FhN0ahaILN";

	/**
	 * Tries up to four times to make a request to the given url string. Keeps trying until it gets a 200 response.
	 */
	public HttpURLConnection makeRequest(String urlString) {
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

	@GET
	@Path("detection/detect")
	public String detect(@QueryParam("url") String img) throws IOException {
		try {
			String urlString = apiUrl + "detection/detect" + apiKeyAndSecret
					+ "url=" + img;
			HttpURLConnection conn = makeRequest(urlString);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			return response.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "hi";
	}
}
