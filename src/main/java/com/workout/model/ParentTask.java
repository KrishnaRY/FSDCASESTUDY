package com.workout.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class ParentTask implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int parent_ID;
	private String parent_Task;
	
public ParentTask(int parent_ID, String parent_Task) {
		super();
		this.parent_ID = parent_ID;
		this.parent_Task = parent_Task;
	}

public int getParent_ID() {
		return parent_ID;
	}

	public void setParent_ID(int parent_ID) {
		this.parent_ID = parent_ID;
	}

	public String getParent_Task() {
		return parent_Task;
	}

	public void setParent_Task(String parent_Task) {
		this.parent_Task = parent_Task;
	}

public ParentTask(){
	
}

}