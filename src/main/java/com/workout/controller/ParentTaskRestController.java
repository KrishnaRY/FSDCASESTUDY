package com.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.workout.repository.ParentTaskRepository;

@RestController
public class ParentTaskRestController {

 

  @Autowired private ParentTaskRepository parentTaskRepository;

  
  /*** Retrieve all Parent Tasks ***/
  
  @RequestMapping(value="/parenttasks",produces="application/json",
          method=RequestMethod.GET)
  public List getAllParentTasks()
  {
      List parentTaskList = parentTaskRepository.getAllParentTasks();
      return parentTaskList;
  }
  
 
}
