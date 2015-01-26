package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestService {
	public static String makeRequest(String urlString) {
		try {
			// send the request
			HttpURLConnection conn = null;
			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			
			// read the response
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
		
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine + "\n");
			}
			in.close();
	
			// return result
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
