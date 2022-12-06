package com.store.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.Role;
import com.store.reponsitories.RoleRepository;
import com.store.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<Role> findAll() {
		return roleRepo.findAll();
	}
	
	
}
