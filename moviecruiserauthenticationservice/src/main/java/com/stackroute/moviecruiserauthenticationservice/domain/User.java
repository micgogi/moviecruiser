package com.stackroute.moviecruiserauthenticationservice.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {

	@Id
	@Column(length = 64)
	private String userId;
	
	private String firstName;
	
	
	private String lastName;
	
	private String password;
	@CreationTimestamp
	private Date created;
	
	public User() {
		
	}
	public User(String userId, String firstName, String lastName, String password, Date created) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.created = created;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
