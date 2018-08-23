package com.osese.Yarpp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.osese.Yarpp.RestoUser.RestoUser;
import com.osese.Yarpp.RestoUser.RestoUserRepo;
import com.osese.Yarpp.RestoUser.RestoUserService;
import com.osese.Yarpp.UserRole.UserRole;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private RestoUserService restoUserService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(this.restoUserService)
				.passwordEncoder(RestoUser.PASSWORD_ENCODER);
	}

		 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/*.js",
						"/*.jsx", "/main.css").permitAll()
				.anyRequest().authenticated()
				.and()
			.httpBasic()
				.and().formLogin()
				.and()
			.csrf().disable()
			.logout()
				.logoutSuccessUrl("/");
	}

} 
