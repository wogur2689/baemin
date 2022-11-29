package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.Join;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public void join(Join join) {
		userDAO.join(join);
	}

	@Override
	public int overlapCheck(String value, String valueType) {
		return userDAO.overlapCheck(value, valueType);
	}
}
