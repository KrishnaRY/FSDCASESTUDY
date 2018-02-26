package com.workout.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_ID;
	private String first_Name;
	private String last_Name;
	private String employee_ID;
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	public String getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	public String getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}
	public String getEmployee_ID() {
		return employee_ID;
	}
	public void setEmployee_ID(String employee_ID) {
		this.employee_ID = employee_ID;
	}

public User(int user_ID,String first_Name,String last_Name,String employee_ID){
	this.user_ID= user_ID;
	this.first_Name=first_Name;
	this.last_Name=last_Name;
	this.employee_ID=employee_ID;
	
	
}
public User(String first_Name,String last_Name,String employee_ID){
	
	this.first_Name=first_Name;
	this.last_Name=last_Name;
	this.employee_ID=employee_ID;
	
	
}
public User(){
	
}

}