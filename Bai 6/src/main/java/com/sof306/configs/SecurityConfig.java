package com.sof306.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //Bài 6.6
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		UserDetails user = User.withUsername("user1").password(pe.encode("123")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user);
	}

//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
//		auth.inMemoryAuthentication().withUser("user1").password(pe.encode("123")).roles("GUEST").and()
//				.withUser("user2").password(pe.encode("123")).roles("GUEST", "USER").and()
//				.withUser("user3").password(pe.encode("123")).roles("GUEST", "USER", "ADMIN");
//		return auth.build();
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// CSRF, CROS
		http.csrf().disable().cors().disable();

		// Phân quyền sử dụng
		http.authorizeRequests()
			.antMatchers("/home/admins").hasRole("ADMIN")
			.antMatchers("/home/users").hasAnyRole("ADMIN","USER")
			.antMatchers("/home/authenticated").authenticated()
			.anyRequest().permitAll(); //Bài 6.6 giữ lại dòng này 3 dòng trên bỏ

		//Điều khiễn lỗi khi đăng nhập không đúng vai trò
		http.exceptionHandling()
			.accessDeniedPage("/auth/access/denied");
		
		// Giao diện đăng nhập
		http.formLogin().loginPage("/auth/login/form") // địa chỉ
				.loginProcessingUrl("/auth/login") // sau khi submit
				.defaultSuccessUrl("/auth/login/success", false) // đăng nhập thành công -> success (false -> ở trang A
																	// đăng nhập xong về trang A, true là về trang
																	// success)
				.failureUrl("/auth/login/error").usernameParameter("username") // 2 dòng username password có hay không
																				// cũng được
				.passwordParameter("password");
		http.rememberMe().rememberMeParameter("remember"); // [[remember-me]] trong ngoặc vuông là giá trị mặc định

//		Đăng xuất
		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/logout/success");
		return http.build();
	}

}
