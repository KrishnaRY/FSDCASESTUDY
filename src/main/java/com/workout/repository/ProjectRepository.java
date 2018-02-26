package com.workout.repository;

import java.util.List;

import com.workout.model.Project;

public interface ProjectRepository {

	void persist(Project project);
	void updateProject(Project project);
	void suspendProject(int project_ID);
	List getAllProjects();
	
}
