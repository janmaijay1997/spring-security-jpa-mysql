package com.intent.SpringSercurityWithJpa.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SpringSecurityController {
	
	@GetMapping("/")
	public String Welcome() {
		return "<h1>Welcome<h1>";
	}
	
	@GetMapping("/user")
	public String WelcomeUser() {
		return "<h1>Welcome User<h1>";
	}
	
	@GetMapping("/admin")
	public String WelcomeAdmin() {
		return "<h1>Welcome Admin<h1>";
	}
}
