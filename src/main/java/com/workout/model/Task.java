package com.workout.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int task_ID;
	private int parent_ID;
	private int project_ID;
	private String task;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date start_Date;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date end_Date;
	private int priority;
	private String  status;
	private int user_ID;
	
	public int getUser_ID() {
		return user_ID;
	}
	public Task(int task_ID, int parent_ID, int project_ID, String task, Date start_Date, Date end_Date, int priority,
			String status, int user_ID) {
		super();
		this.task_ID = task_ID;
		this.parent_ID = parent_ID;
		this.project_ID = project_ID;
		this.task = task;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.priority = priority;
		this.status = status;
		this.user_ID = user_ID;
	}
	public Task(int parent_ID, int project_ID, String task, Date start_Date, Date end_Date, int priority, String status,
			int user_ID) {
		super();
		this.parent_ID = parent_ID;
		this.project_ID = project_ID;
		this.task = task;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
		this.priority = priority;
		this.status = status;
		this.user_ID = user_ID;
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

	public int getTask_ID() {
		return task_ID;
	}
	public void setTask_ID(int task_ID) {
		this.task_ID = task_ID;
	}
	public int getParent_ID() {
		return parent_ID;
	}
	public void setParent_ID(int parent_ID) {
		this.parent_ID = parent_ID;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Task(){
		
	}

}
