package com.intent.SpringSercurityWithJpa.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.intent.SpringSercurityWithJpa.Model.User;
@Repository
public interface UserRepo extends JpaRepository<User,Long> {
	Optional<User> findByUserName(String userName) throws UsernameNotFoundException;
}
