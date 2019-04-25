package com.stackroute.moviecruiserauthenticationservice.services;

import com.stackroute.moviecruiserauthenticationservice.domain.User;
import com.stackroute.moviecruiserauthenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.moviecruiserauthenticationservice.exception.UserNotFoundException;

public interface UserService {
    boolean saveUser(User user) 
    		throws UserAlreadyExistsException;

   public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
