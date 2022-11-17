package com.sof306.app;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sof306.bean.Contact;
import com.sof306.bean.Student2;

public class Jackson2 {

	public static void main(String[] args) {
		try {
			demo7();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void demo7() throws Exception {
		Contact contact = new Contact("anv@gmail.com", "0123456788");
		List<String> subjects = Arrays.asList("WEB123", "SOF306");
		Student2 student = new Student2("Nguyễn Văn A", true, 8.8, contact, subjects);

		ObjectMapper mapper = new ObjectMapper();
		// write to string
		String json = mapper.writeValueAsString(student);
		System.out.println(json);
		// write to output
		mapper.writeValue(System.out, student);
		// write to file
		mapper.writeValue(new File("D:\\Images\\student.json"), student);
	}

	private static void demo6() throws Exception {
		Map<String, Object> contact = new HashMap<String, Object>();
		contact.put("email", "nva@gmail.com");
		contact.put("phoen", "0123456789");
		List<String> subjects = Arrays.asList("WEB123", "SOF306");

		Map<String, Object> student = new HashMap<String, Object>();
		student.put("name", "Nguyễn Văn A");
		student.put("marks", 8.0);
		student.put("gender", true);
		student.put("contact", contact);
		student.put("subjects", subjects);

		ObjectMapper mapper = new ObjectMapper();
		// write to string
		String json = mapper.writeValueAsString(student);
		System.out.println(json);
		// write to output
		mapper.writeValue(System.out, student);
		// write to file
		mapper.writeValue(new File("D:\\Images\\student.json"), student);
	}

	private static void demo5() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode student = mapper.createObjectNode();
		student.put("name", "Nguyễn Văn A");
		student.put("marks", 8.0);
		student.put("gender", true);
		ObjectNode contact = student.putObject("contact");
		contact.put("email", "anv@gmail.com");
		contact.put("phone", "0123456789");
		ArrayNode subjects = student.putArray("subjects");
		subjects.add("WEB123");
		subjects.add("SOF306");

		// write to string
		String json = mapper.writeValueAsString(student);
		System.out.println(json);
		// write to output
		mapper.writeValue(System.out, student);
		// write to file
		mapper.writeValue(new File("D:\\Images\\student.json"), student);
	}

	private static void demo4() throws Exception {
		String path = "D:\\Java\\Projects\\SOF306_BAI1\\src\\main\\resources\\students.json";
		TypeReference<List<Student2>> type = new TypeReference<List<Student2>>() {
		};
		ObjectMapper mapper = new ObjectMapper();
		List<Student2> students = mapper.readValue(new File(path), type);
		students.forEach(student -> {
			System.out.println(">>Name: " + student.getName());
		});
	}

	private static void demo3() throws Exception {
		String path = "D:\\Java\\Projects\\SOF306_BAI1\\src\\main\\resources\\student.json";
		ObjectMapper mapper = new ObjectMapper();
		Student2 student = mapper.readValue(new File(path), Student2.class);

		System.out.println(">>Name: " + student.getName());
		System.out.println(">>Mark: " + student.getMarks());
		System.out.println(">>Gender: " + student.getGender());
		Contact contact = student.getContact();
		System.out.println(">>Email: " + contact.getEmail());
		System.out.println(">>Phone: " + contact.getPhone());
		List<String> subjects = student.getSubjects();
		subjects.forEach(subject -> {
			System.out.println(">>Subject: " + subject);
		});

	}

	private static void demo2() throws Exception {
		String path = "D:\\Java\\Projects\\SOF306_BAI1\\src\\main\\resources\\students.json";
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> students = mapper.readValue(new File(path), List.class);
		students.forEach(student -> {
			System.out.println(">>Name: " + student.get("name"));
		});
	}

	private static void demo1() throws Exception {
		String path = "D:\\Java\\Projects\\SOF306_BAI1\\src\\main\\resources\\student.json";
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> student = mapper.readValue(new File(path), Map.class);

		System.out.println(">>Name: " + student.get("name"));
		System.out.println(">>Mark: " + student.get("marks"));
		System.out.println(">>Gender: " + student.get("gender"));
		Map<String, Object> contact = (Map<String, Object>) student.get("contact");
		System.out.println(">>Email: " + contact.get("email"));
		System.out.println(">>Phone: " + contact.get("phone"));
		List<String> subjects = (List<String>) student.get("subjects");
		subjects.forEach(subject -> {
			System.out.println(">>Subject: " + subject);
		});

	}

}
