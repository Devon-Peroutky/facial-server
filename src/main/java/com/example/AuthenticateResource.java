package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import javax.ws.rs.core.MediaType;

import code.HelloWorld;
import jsonObjects.AuthenticateBean;

@Path("authenticate")
public class AuthenticateResource {
	public static HelloWorld hw = new HelloWorld();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public AuthenticateBean authenticate(@QueryParam("username") String username,
			@QueryParam("password") String password) {
		boolean result = hw.authenticate(username, password);
		return new AuthenticateBean(result);
	}
}
