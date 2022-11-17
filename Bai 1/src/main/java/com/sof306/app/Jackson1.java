package com.sof306.app;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson1 {

	public static void main(String[] args) {
		try {
			demo2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void demo2() throws Exception {
		String path = "D:\\Java\\Projects\\SOF306_BAI1\\src\\main\\resources\\students.json";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode sv = mapper.readTree(new File(path));
		sv.iterator().forEachRemaining(student->{
			System.out.println(">>Name: " + student.get("name").asText());
		});
	}

	private static void demo1() throws Exception {
		String path = "D:\\Java\\Projects\\SOF306_BAI1\\src\\main\\resources\\student.json";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode sv = mapper.readTree(new File(path));
		
		System.out.println(">>Name: " + sv.get("name").asText());
		System.out.println(">>Mark: " + sv.get("marks").asDouble());
		System.out.println(">>Gender: " + sv.get("gender").asBoolean());
		System.out.println(">>Email: " + sv.get("contact").get("email").asText());
		System.out.println(">>Phone: " + sv.findValue("phone").asText());
		sv.get("subjects").iterator().forEachRemaining(sub->{
			System.out.println(">>Subject: " + sub.asText());
		});
		System.out.println();
	}

}
