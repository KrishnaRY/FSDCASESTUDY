package com.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workout.model.User;
import com.workout.repository.UserRepository;
import com.workout.service.UserService;

@RestController
public class UserRestController {

  @Autowired private UserService userService;

  @Autowired private UserRepository userRepository;

  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
  public ResponseEntity<String> persistPerson(@RequestBody User user) {
    if (userService.isValid(user)) {
    	userRepository.persist(user);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
  
  /*** Retrieve all Users ***/
  
  @RequestMapping(value="/users",produces="application/json",
          method=RequestMethod.GET)
  public List getAllUsers()
  {
      List userList = userRepository.getAllUsers();
      return userList;
  }
  
  /*** Update a User ***/
  
  @RequestMapping(value="/updateUser", method=RequestMethod.POST, 
          produces="application/json", consumes="application/json")
  public ResponseEntity<String> updateUser(@RequestBody User user)
  {
	  userRepository.updateUser(user);
	  return ResponseEntity.status(HttpStatus.OK).build();
  }
  
  /*** Delete a User ***/
  
  @RequestMapping(value="/delete/{user_ID}",method = RequestMethod.DELETE,
           produces="application/json")
  public ResponseEntity<String> deleteUser(@PathVariable("user_ID") int user_ID)
  {
	  userRepository.deleteUser(user_ID);
	  return ResponseEntity.status(HttpStatus.OK).build();
  }
}
