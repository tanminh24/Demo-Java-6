package com.sof306.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sof306.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserService userService;

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Quản lý dữ liệu người sử dụng
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// CSRF, CROS
		http.csrf().disable().cors().disable();

		// Phân quyền sử dụng
		http.authorizeRequests()
			.antMatchers("/home/admins").hasRole("ADMIN")
			.antMatchers("/home/users").hasAnyRole("ADMIN", "USER")
			.antMatchers("/home/authenticated").authenticated()
			.anyRequest().permitAll();
		
		// Điều khiễn lỗi khi đăng nhập không đúng vai trò
		http.exceptionHandling().accessDeniedPage("/auth/access/denied");

		// Đăng nhập
		http.formLogin()
			.loginPage("/auth/login/form")
			.loginProcessingUrl("/auth/login")
			.defaultSuccessUrl("/auth/login/success", false)
			.failureUrl("/auth/login/error")
			.usernameParameter("username")
			.passwordParameter("password");
		http.rememberMe()
			.rememberMeParameter("remember");

		// Đăng xuất
		http.logout()
			.logoutUrl("/auth/logout")
			.logoutSuccessUrl("/auth/logout/success");

		// Oauth2
		http.oauth2Login()
			.loginPage("/auth/login/form")
			.defaultSuccessUrl("/oauth2/login/success", true)
			.failureUrl("/auth/login/error")
			.authorizationEndpoint()
			.baseUri("/oauth2/authorization");
	}

}
