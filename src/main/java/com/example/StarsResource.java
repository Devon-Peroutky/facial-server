package com.example;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jsonObjects.Star;
import code.QueryService;


@Path("stars/{name}")
@Produces(MediaType.APPLICATION_JSON)
public class StarsResource {
	@GET
	public Response getStar(
			@PathParam("name") String name) {
		try {
		System.out.println("received request for " + name);
		Star star = QueryService.getStar(name);
		ArrayList<String> images = QueryService.getImages(name);
		star.images = images;
		System.out.println(star);
		return Response.ok().entity(star).header("Access-Control-Allow-Origin", "*").build();
		} catch(Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
}
