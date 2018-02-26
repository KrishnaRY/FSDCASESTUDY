package com.workout.service;

import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.workout.model.Project;
import com.workout.model.Task;

@Service
public class TaskServiceImpl implements TaskService {

  @Override
  public boolean isValid(Task task) {
    if( task == null){
       return false;
    }
  
  	return false;
  }
}
