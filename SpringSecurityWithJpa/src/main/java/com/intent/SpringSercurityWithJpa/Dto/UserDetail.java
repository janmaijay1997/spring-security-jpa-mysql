package com.intent.SpringSercurityWithJpa.Dto;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.intent.SpringSercurityWithJpa.Model.User;

public class UserDetail implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password;
	private SimpleGrantedAuthority simpleGrantedAuthority;
	private String userName;
	
	public  UserDetail() {
	}
	public UserDetail(User user) {
		this.password=user.getPassword();
		this.userName=user.getUserName();
		this.simpleGrantedAuthority=new SimpleGrantedAuthority(user.getRoles());
	}
	
	public UserDetail(String password, String userName) {
		
		this.password = password;
		this.userName = userName;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
