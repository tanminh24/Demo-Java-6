package com.store.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.store.entities.Account;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	AccountService accountService;

	@Autowired
	AuthorityService authorityService;

	BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Account account = accountService.findByUsername(username);
			String password = account.getPassword();
			String[] roles = account.getAuthorities().stream().map(au -> au.getRole().getId())
					.collect(Collectors.toList()).toArray((new String[0]));
			return User.withUsername(username).password(pe.encode(password)).roles(roles).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username + " not found!");
		}
	}

	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		// fb
		String token = oauth2.getPrincipal().getAttribute("id");
		if (token == null) {
			// google
			token = oauth2.getPrincipal().getAttribute("sub");
		}
		String username = "user" + String.valueOf((int) (Math.random() * 99999999));
		while (accountService.findByUsername(username) != null) {
			username = "user" + String.valueOf((int) (Math.random() * 99999999));
		}
		String email = oauth2.getPrincipal().getAttribute("email");
		if (email == null) {
			email = "";
		}
		String fullname = oauth2.getPrincipal().getAttribute("name");
		String password = Long.toHexString(System.currentTimeMillis());

		Account accfindByToken = accountService.findAccByToken(token);
		if (accfindByToken != null) {
			username = accfindByToken.getUsername();
			password = accfindByToken.getPassword();
		} else {
			Account acc = new Account();
			acc.setUsername(username);
			acc.setPassword(password);
			acc.setFullname(fullname);
			acc.setEmail(email);
			acc.setActivated(true);
			acc.setPhoto("");
			acc.setToken(token);
			accountService.create(acc);
			authorityService.setCust(acc);
		}
		UserDetails user = User.withUsername(username).password(pe.encode(password)).roles("CUST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}

//  Google's response
//	{
//	  "sub": "1057abc98136861333615xz",
//	  "name": "My Name",
//	  "given_name": "My",
//	  "family_name": "Name",
//	  "picture": "https://lh3.googleusercontent.com/a-/AOh14qiJarwP9rRw7IzxO40anYi4pTTAU_xseuRPFeeYFg",
//	  "email": "MyName@gmail.com",
//	  "email_verified": true,
//	  "locale": "en"
//	}

//Facebook's response
//id
//first_name
//last_name
//middle_name
//name
//name_format
//picture
//short_name
