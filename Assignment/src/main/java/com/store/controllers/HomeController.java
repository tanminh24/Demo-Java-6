package com.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping({ "/", "/home/index" })
	public String home() {
		return "redirect:/product/list";
	}

	@RequestMapping({ "/admin", "/admin/index" })
	public String admin() {
		return "forward:/assets/admin/index.html";
	}

}
