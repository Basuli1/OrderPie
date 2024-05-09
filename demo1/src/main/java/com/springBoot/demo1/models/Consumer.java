package com.springBoot.demo1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Consumer {

	@Id
	private String username;
	private String password;
	@ManyToOne
	@JoinColumn(name="last_pie")
	private Pie lastPie;
	
	public Consumer() {
		
	}

	public Consumer(String username, String password, Pie lastPie) {
		super();
		this.username = username;
		this.password = password;
		this.lastPie = lastPie;
	}

	//getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Pie getLastPie() {
		return lastPie;
	}

	public void setLastPie(Pie lastPie) {
		this.lastPie = lastPie;
	}

	
}
