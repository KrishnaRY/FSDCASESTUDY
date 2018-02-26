package com.workout.repository;

import java.util.List;

import com.workout.model.User;

public interface UserRepository {

	void persist(User user);
	List<User> getAllUsers();
	void updateUser(User user);
	void deleteUser(int user_ID);
}
