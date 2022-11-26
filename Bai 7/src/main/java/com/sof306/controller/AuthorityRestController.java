package com.sof306.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sof306.entities.Authority;
import com.sof306.repositories.AccountRepository;
import com.sof306.repositories.AuthRepository;
import com.sof306.repositories.RoleRepository;

@CrossOrigin("*")
@RestController
public class AuthorityRestController {

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	AuthRepository authRepo;

	@GetMapping("/rest/authorities")
	public Map<String, Object> getAuthorities() {
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authRepo.findAll());
		data.put("roles", roleRepo.findAll());
		data.put("accounts", accountRepo.findAll());
		return data;
	}
	
	@PostMapping("/rest/authorities")
	public Authority create(@RequestBody Authority authority) {
		return authRepo.save(authority);
	}

	@DeleteMapping("/rest/authorities/{id}")
	public void create(@PathVariable("id") Integer id) {
		authRepo.deleteById(id);
	}
}
