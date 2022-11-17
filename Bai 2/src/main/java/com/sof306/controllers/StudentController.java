package com.sof306.controllers;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sof306.beans.Student;

@Controller
public class StudentController {

	@RequestMapping("/student")
	public String index(Model model, @RequestParam("index") Optional<Integer> index) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		File path = ResourceUtils.getFile("D:\\Java\\Projects\\SOF306_BAI2\\src\\main\\resources\\static\\students.json");
		TypeReference<List<Student>> typeref = new TypeReference<>() {
		};
		List<Student> students = mapper.readValue(path, typeref);

		int i = index.orElse(0);
		model.addAttribute("n", i);
		model.addAttribute("sv", students.get(i));

		return "scope/student";
	}

}
