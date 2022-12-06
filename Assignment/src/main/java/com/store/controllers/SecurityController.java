package com.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.services.UserService;

@Controller
@RequestMapping("/security")
public class SecurityController {

	@Autowired
	UserService userService;

	@RequestMapping("/login/form")
	public String loginForm(Model model) {
		return "security/login";
	}

	@RequestMapping("/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		return "redirect:/product/list";
	}

	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập");
		return "security/login";
	}

	@RequestMapping("/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy suất");
		return "security/login";
	}

	@RequestMapping("/logout/success")
	public String logoutSuccess(Model model) {
		model.addAttribute("message", "Đăng xuất thành công");
		return "redirect:/product/list";
	}

	@RequestMapping("/register/form")
	public String registerForm(Model model) {
		return "security/register";
	}

	@RequestMapping("/forget-password/form")
	public String forgetPasswordForm(Model model) {
		return "security/forget-password";
	}

	// Oauth2
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		userService.loginFromOAuth2(oauth2);
		return "forward:/security/login/success";
	}

}
