package com.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

import code.QueryService;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;

import jsonObjects.Star;
import jsonObjects.StarImage;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:9090/myapp/";
    public static HashMap<String, Star> faceIdToStarMap;

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig()
        .packages("com.example", 
        		"jsonObjects",
        		"jooq.generated",
        		"jooq.generated.tables",
        		"code",
        		"logging");
        
        loadFaceIdToStarMap();

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }
    
    public static void loadFaceIdToStarMap() {
    	HashSet<Star> stars = QueryService.loadStars();
    	HashSet<StarImage> starImages = QueryService.loadStarImages();
    	HashMap<String, Star> nameToStarMap = Maps.newHashMap();
    	
    	for (Star star : stars) {
    		nameToStarMap.put(star.name, star);
    	}
    	
    	faceIdToStarMap = Maps.newHashMap();
    	for (StarImage starImage : starImages) {
    		faceIdToStarMap.put(starImage.faceId, nameToStarMap.get(starImage.name));
    	}
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

