package com.sof306.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sof306.services.UserService;

@Controller
public class AuthController {

	@Autowired
	UserService userService;
	
	//đăng nhập
	@RequestMapping("/auth/login/form")
	public String form() {
		return "auth/login";
	}

	//đăng nhập thành công
	@RequestMapping("/auth/login/success")
	public String success(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "forward:/auth/login/form";
	}

	//đăng nhập thất bại
	@RequestMapping("/auth/login/error")
	public String error(Model model) {
		model.addAttribute("message", "Đăng nhập thất bại!");
		return "auth/login";
	}

	//đăng xuất thành công
	@RequestMapping("/auth/logout/success")
	public String logout(Model model) {
		model.addAttribute("message", "Đăng xuất thành công!");
		return "forward:/auth/login/form";
	}

	//truy cập sai quyền
	@RequestMapping("/auth/access/denied")
	public String denied(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy cập!");
		return "forward:/auth/login/form";
	}
	
	//Oauth2
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		userService.loginFromOAuth2(oauth2);
		return "forward:/auth/login/success";
	}
}