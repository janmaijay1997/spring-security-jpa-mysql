package com.intent.SpringSercurityWithJpa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intent.SpringSercurityWithJpa.Dto.AuthResponse;
import com.intent.SpringSercurityWithJpa.Utils.JwtUtils;


@RestController
public class SpringSecurityController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	
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
	@PostMapping("/auth")
	public ResponseEntity<?> auth(String userName,String password) throws Exception{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		
		}catch (BadCredentialsException e) {
		throw new Exception("Incorrect username and password",e);
		}
		UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
		
		return  ResponseEntity.ok(new AuthResponse(JwtUtils.generateToken(userDetails)));
	}
}
