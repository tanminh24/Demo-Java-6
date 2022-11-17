package com.sof306.controllers;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sof306.beans.Student;

@Controller
public class Student2Controller {

	@RequestMapping("/student/list")
	public String index(Model model, @RequestParam("index") Optional<Integer> index) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		File path = new ClassPathResource("/static/students.json").getFile();
		TypeReference<List<Student>> typeref = new TypeReference<>() {
		};
		List<Student> list = mapper.readValue(path, typeref);

		model.addAttribute("sv", list.get(index.orElse(0)));
		model.addAttribute("dssv", list);

		return "student/list";
	}

}
