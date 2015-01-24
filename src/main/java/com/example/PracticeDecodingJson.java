package com.example;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PracticeDecodingJson {
	public static void main (String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		MyValue value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
		System.out.println(value.name);
	}
	
	
	public static class MyValue {
	  public String name;
	  public int age;
	  // NOTE: if using getters/setters, can keep fields `protected` or `private`
	}
}
