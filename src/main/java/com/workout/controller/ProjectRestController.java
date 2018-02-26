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

import com.workout.model.Project;
import com.workout.model.User;
import com.workout.repository.ProjectRepository;
import com.workout.service.ProjectService;
import com.workout.util.FSDUtil;


@RestController
public class ProjectRestController {

@Autowired private ProjectService projectService;

@Autowired private ProjectRepository projectRepository;

@RequestMapping(value = "/addProject", method = RequestMethod.POST)
public ResponseEntity<String> addProject(@RequestBody Project project) {
 // if (userService.isValid(user)) {
	System.out.println(project.getStart_Date());
	//System.out.println(FSDUtil.prepareYearMonthDateFromString(project.getStart_Date().toString()));
	projectRepository.persist(project);
    return ResponseEntity.status(HttpStatus.CREATED).build();
 // }
 // return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
}

/*** Update a Project ***/

@RequestMapping(value="/updateProject", method=RequestMethod.POST, 
        produces="application/json", consumes="application/json")
public ResponseEntity<String> updateUser(@RequestBody Project project)
{
	System.out.println(project.getStart_Date());
	projectRepository.updateProject(project);
	 return ResponseEntity.status(HttpStatus.OK).build();
}

/*** Retrieve all Users ***/

@RequestMapping(value="/projects",produces="application/json",
        method=RequestMethod.GET)
public List getAllProjects()
{
    List projectList = projectRepository.getAllProjects();
    return projectList;
}



@RequestMapping(value="/suspendProject/{project_ID}", consumes="application/json",method=RequestMethod.PUT)
public ResponseEntity<String> suspendProject(@PathVariable("project_ID") int project_ID)
{
	projectRepository.suspendProject(project_ID);
	 return ResponseEntity.status(HttpStatus.OK).build();
}

}