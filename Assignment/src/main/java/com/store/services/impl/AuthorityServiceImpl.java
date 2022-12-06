package com.store.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.entities.Account;
import com.store.entities.Authority;
import com.store.reponsitories.AccountRepository;
import com.store.reponsitories.AuthorityRepository;
import com.store.reponsitories.RoleRepository;
import com.store.services.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	AuthorityRepository authRepo;

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountRepository.getAdministrators();
		// Lấy ra các quyền dc cấp cho các tài khoản admin
		return authRepo.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return authRepo.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return authRepo.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authRepo.deleteById(id);
	}

	@Override
	public Authority setCust(Account acc) {
		Authority auth = new Authority();
		auth.setRole(roleRepo.findById("CUST").get());
		auth.setAccount(acc);
		return authRepo.save(auth);
	}

}
