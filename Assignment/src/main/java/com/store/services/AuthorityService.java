package com.store.services;

import java.util.List;

import com.store.entities.Account;
import com.store.entities.Authority;

public interface AuthorityService {

	List<Authority> findAuthoritiesOfAdministrators();

	List<Authority> findAll();

	Authority create(Authority auth);

	void delete(Integer id);

	Authority setCust(Account acc);

}
