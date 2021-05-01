package com.intent.SpringSercurityWithJpa.AuthFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.intent.SpringSercurityWithJpa.Utils.JwtUtils;

@Component
public class AuthFilter extends OncePerRequestFilter {

	private UserDetailsService userDetailsService;
	private JwtUtils jwtUtils;

	public AuthFilter(UserDetailsService userDetailsService, JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
		this.userDetailsService = userDetailsService;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader=request.getHeader("Authorization");
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			String jwtToken=authHeader.replace("Bearer ", "");
			System.out.println(jwtToken);
			String userName=jwtUtils.extractUsername(jwtToken);
			if(userName!=null) {
				UserDetails userDetails=userDetailsService.loadUserByUsername(userName);
				if(jwtUtils.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					
				}
	
			}
			
		}
		filterChain.doFilter(request, response);
	}

}
