package com.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workout.model.Task;
import com.workout.repository.TaskRepository;

@RestController
public class TaskRestController {

	

	@Autowired
	private TaskRepository taskRepository;

	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public ResponseEntity<String> addTask(@RequestBody Task task) {
		// if (userService.isValid(user)) {
		taskRepository.persist(task);
		return ResponseEntity.status(HttpStatus.CREATED).build();
		// }
		// return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
	}

	/*** Update a Project ***/

	@RequestMapping(value = "/updateTask", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> updateUser(@RequestBody Task task) {
		taskRepository.updateTask(task);
		 return ResponseEntity.status(HttpStatus.OK).build();
	}

	  /*** Retrieve all Tasks ***/
	  
	  @RequestMapping(value="/tasks",produces="application/json",
	          method=RequestMethod.GET)
	  public List getAllTasks()
	  {
	      List taskList = taskRepository.getAllTasks();
	      return taskList;
	  }
}