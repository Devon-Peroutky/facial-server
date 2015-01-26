package logging;

import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import code.QueryService;

@Path("log")
public class LogResource {
	@Path("user")
	@POST
	public Response logUser(@BeanParam InstagramUser instagramUser) {
		
		System.out.println("Recieved log request");
		QueryService.logInstagramUser(instagramUser);
		return Response.ok().header("Access-Control-Allow-Origin", "*").build();
	}
}
