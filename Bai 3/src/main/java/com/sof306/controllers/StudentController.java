package com.sof306.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sof306.beans.Student;
import com.sof306.beans.Student2;

@Controller
public class StudentController {
	
	@GetMapping("/student/form")
	public String form(Model model) {
		Student2 student = new Student2();
		model.addAttribute("sv",student);
		return "student/form";
	}
	
	@PostMapping("/student/save")
	public String save(Model model, @Validated @ModelAttribute("sv") Student2 form, Errors errors) {
		if(errors.hasErrors()) {
			model.addAttribute("messgae","Vui lòng sửa các lỗi sau");
			return "student/form";
		}
		return "student/success";
	}
	
}
