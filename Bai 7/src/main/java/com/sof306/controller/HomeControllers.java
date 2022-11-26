package com.sof306.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControllers {

	// Bài 6.7
//	@Autowired
//	private HttpServletRequest request;

	@RequestMapping("/home/index")
	public String index(Model model) {
		model.addAttribute("message", "HOME");
		return "home/index";
	}

	@RequestMapping("/home/about")
	public String about(Model model) {
		model.addAttribute("message", "ABOUT");
		return "home/index";
	}

//	@PreAuthorize("hasRole('ADMIN')") BÀI 6.6
	@GetMapping("/home/admins")
	public String admins(Model model) {
//	Bài 6.7	
//		if (!request.isUserInRole("ADMIN")) {
//			return "redirect:/auth/access/denied";
//		}
		model.addAttribute("message", "ADMINS");
		return "home/index";
	}

//	@PreAuthorize("hasAnyRole('ADMIN', 'USER')") BÀI 6.6
	@GetMapping("/home/users")
	public String users(Model model) {
//	Bài 6.7 
//		if (!(request.isUserInRole("ADMIN") || request.isUserInRole("USER"))) {
//			return "redirect:/auth/access/denied";
//		}
		model.addAttribute("message", "USERS");
		return "home/index";
	}

//	@PreAuthorize("isAuthenticated()") BÀI 6.6
	@GetMapping("/home/authenticated")
	public String authenticated(Model model) {
//	Bài 6.7 
//		if (request.getRemoteUser() == null) {
//			return "redirect:/auth/login/form";
//		}
		model.addAttribute("message", "AUTHENTICATED");
		return "home/index";
	}

	@GetMapping("/home/thymeleaf1")
	public String thymeleaf1(Model model) {
		model.addAttribute("message", "Thymeleaf - Without Extras");
		return "home/thymeleaf1";
	}

	@GetMapping("/home/thymeleaf2")
	public String thymeleaf2(Model model) {
		model.addAttribute("message", "Thymeleaf - With Extras");
		return "home/thymeleaf2";
	}

}
