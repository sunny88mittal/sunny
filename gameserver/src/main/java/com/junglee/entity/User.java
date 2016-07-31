package com.junglee.entity;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class User {

	private int id;
	
	private String name;
	
	private Date lastLogin;
	
	private Date lastLogout;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private String authToken;
	
    private UserStats userStats;
}
