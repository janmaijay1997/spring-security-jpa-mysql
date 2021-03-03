package com.intent.SpringSercurityWithJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailService;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
		this.userDetailService=userDetailsService;
		this.passwordEncoder=passwordEncoder;
		
	}
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// this.disableLocalConfigureAuthenticationBldr = true;
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
	}
	


	@Override
	public void configure(HttpSecurity security) throws Exception {
				security.authorizeRequests().antMatchers("/user").hasAnyRole("USER")
				.antMatchers("/admin").hasRole("ADMIN").antMatchers("/").permitAll().and().formLogin();

	}
}
