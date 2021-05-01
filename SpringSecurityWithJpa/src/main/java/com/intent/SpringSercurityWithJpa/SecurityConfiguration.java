package com.intent.SpringSercurityWithJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.intent.SpringSercurityWithJpa.AuthFilter.AuthFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailService;
	private PasswordEncoder passwordEncoder;
	private AuthFilter authFilter;

	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder,
			AuthFilter authFilter) {
		this.userDetailService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.authFilter = authFilter;

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// this.disableLocalConfigureAuthenticationBldr = true;
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(HttpSecurity security) throws Exception {
		security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable()
				.authorizeRequests().antMatchers("/user").hasAnyRole("USER")
				.antMatchers("/admin").hasRole("ADMIN").antMatchers("/").permitAll().antMatchers("/auth").permitAll().anyRequest()
				.authenticated().and().addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
