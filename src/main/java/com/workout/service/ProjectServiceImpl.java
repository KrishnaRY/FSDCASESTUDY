package com.workout.service;

import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import com.workout.model.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Override
  public boolean isValid(Project project) {
    if( project == null){
       return false;
    }
    if( project.getEnd_Date() == null){
        return false;
     }
    if( project.getStart_Date() == null){
        return false;
     }
    if( !StringUtils.hasText(project.getProject())  ){
        return false;
     }
  	return false;
  }
}
