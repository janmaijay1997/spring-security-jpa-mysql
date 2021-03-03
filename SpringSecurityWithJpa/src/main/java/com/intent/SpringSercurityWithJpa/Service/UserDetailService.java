package com.intent.SpringSercurityWithJpa.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.intent.SpringSercurityWithJpa.Dto.UserDetail;
import com.intent.SpringSercurityWithJpa.Model.User;
import com.intent.SpringSercurityWithJpa.Repository.UserRepo;

@Service
public class UserDetailService implements UserDetailsService {

	private UserRepo userRepo;

	//private PasswordEncoder passwordEncoder;

	@Autowired
	public UserDetailService(UserRepo userRepo) {
		this.userRepo = userRepo;
		//this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOp = userRepo.findByUserName(username);
		userOp.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		return new UserDetail(userOp.get());
	}

	@PostConstruct
	public void create() {
		List<User>userList=new ArrayList<>();
		if (userRepo.findAll().isEmpty()) {
			User user = new User();
			user.setUserName("User");
			BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(16);
			user.setPassword(bCryptPasswordEncoder.encode("User@123"));
			user.setRoles("ROLE_USER");
			userList.add(user);
			User admin = new User();
			admin.setUserName("Admin");
			admin.setPassword(bCryptPasswordEncoder.encode("Admin@123"));
			admin.setRoles("ROLE_ADMIN");
			userList.add(admin);
			userRepo.saveAll(userList);
		}

	}
}
