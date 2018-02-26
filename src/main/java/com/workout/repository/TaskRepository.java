package com.workout.repository;

import java.util.List;

import com.workout.model.Task;

public interface TaskRepository {

	void persist(Task task);
	List<Task> getAllTasks();
	void updateTask(Task task);
	//void deleteUser(int user_ID);
}
