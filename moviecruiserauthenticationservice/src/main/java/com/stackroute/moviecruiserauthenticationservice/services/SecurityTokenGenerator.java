package com.stackroute.moviecruiserauthenticationservice.services;

import java.util.Map;

import com.stackroute.moviecruiserauthenticationservice.domain.User;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
