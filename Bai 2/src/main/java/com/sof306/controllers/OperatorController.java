package com.sof306.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OperatorController {
	@RequestMapping("/demo/operator")
	public String index(Model model) {
		model.addAttribute("x", 5);
		model.addAttribute("y", 7);
		return "operator";
	}

}
