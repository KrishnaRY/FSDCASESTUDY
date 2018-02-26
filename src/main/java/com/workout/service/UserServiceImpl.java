package com.workout.service;

import org.springframework.stereotype.Service;

import com.workout.model.User;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public boolean isValid(User user) {
    return user != null;
       
  }
}
