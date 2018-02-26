package com.workout.DTO;

import java.util.Date;

public class ProjectDTO {


private int project_ID;
private String project;

private Date start_Date;

private Date end_Date;
private int priority;
private int user_ID;

public int getUser_ID() {
	return user_ID;
}
public void setUser_ID(int user_ID) {
	this.user_ID = user_ID;
}
public int getProject_ID() {
	return project_ID;
}
public void setProject_ID(int project_ID) {
	this.project_ID = project_ID;
}
public String getProject() {
	return project;
}
public void setProject(String project) {
	this.project = project;
}
public Date getStart_Date() {
	return start_Date;
}
public void setStart_Date(Date start_Date) {
	this.start_Date = start_Date;
}
public Date getEnd_Date() {
	return end_Date;
}
public void setEnd_Date(Date end_Date) {
	this.end_Date = end_Date;
}

public int getPriority() {
	return priority;
}
public void setPriority(int priority) {
	this.priority = priority;
}
public ProjectDTO(int project_ID, String project, Date start_Date, Date end_Date, int priority,int user_ID) {
	super();
	this.project_ID = project_ID;
	this.project = project;
	this.start_Date = start_Date;
	this.end_Date = end_Date;
	this.priority = priority;
	this.user_ID= user_ID;
}
public ProjectDTO( String project, Date start_Date, Date end_Date,  int priority,int user_ID) {
	super();
	
	this.project = project;
	this.start_Date = start_Date;
	this.end_Date = end_Date;
	this.priority = priority;
	this.user_ID=user_ID;
}
public ProjectDTO(){
	
}
}
