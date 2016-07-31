package com.junglee.crons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.junglee.dao.UserDao;

@Component
public class SendEmails {

	@Autowired
	private UserDao userDao;
	
	@Scheduled(fixedDelay = 86400000)
	public void sendEmailsToUsers() {
         //Read users from database
		 //Send email to all users
	}
}