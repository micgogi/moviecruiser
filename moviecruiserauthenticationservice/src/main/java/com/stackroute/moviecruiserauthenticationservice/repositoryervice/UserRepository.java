package com.stackroute.moviecruiserauthenticationservice.repositoryervice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.moviecruiserauthenticationservice.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

	
	
	User findByUserIdAndPassword(String userId,String password);
}
