package com.sof306.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/hello.th")
	public String hello(Model model) {
		model.addAttribute("message", "Hi, <b>Tan Minh</b>");
		return "hello";
	}
}
