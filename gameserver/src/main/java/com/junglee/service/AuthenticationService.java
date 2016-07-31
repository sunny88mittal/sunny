package com.junglee.service;

public interface AuthenticationService {

	public void registerUser(String username);

	public void authenticateUser(String username, String password);
}
