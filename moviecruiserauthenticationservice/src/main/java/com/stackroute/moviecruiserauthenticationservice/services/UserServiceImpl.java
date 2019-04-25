package com.stackroute.moviecruiserauthenticationservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserauthenticationservice.domain.User;
import com.stackroute.moviecruiserauthenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.moviecruiserauthenticationservice.exception.UserNotFoundException;
import com.stackroute.moviecruiserauthenticationservice.repositoryervice.UserRepository;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private final UserRepository userRepo;
	private UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo=userRepo;
	}
	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException{
		Optional<User> u1 = userRepo.findById(user.getUserId());
		if(u1.isPresent()) {
			throw new UserAlreadyExistsException("user with id already exsts");
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepo.findByUserIdAndPassword(userId, password);
		if(user==null)
			throw new UserNotFoundException("user not found");
		
		return user;
	}

}
