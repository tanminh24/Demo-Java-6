package com.sof306.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sof306.beans.Student;

@Controller
public class HomeController {
	@RequestMapping("/home/index")
	public String index(Model model) throws Exception{
		model.addAttribute("message","Hi Tan Mih");
		
		ObjectMapper mapper = new ObjectMapper();
		String path = "D:\\Java\\Projects\\SOF306_BAI2\\src\\main\\resources\\static\\student.json";
		Student student = mapper.readValue(new File(path), Student.class);
		model.addAttribute("sv",student);
		
		return "home/index";
	}
}
