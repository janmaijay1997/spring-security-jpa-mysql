package com.intent.SpringSercurityWithJpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityWithJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityWithJpaApplication.class, args);
	}
	

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(16);
		return bCryptPasswordEncoder;
		//return NoOpPasswordEncoder.getInstance();
	}
	

	

}
