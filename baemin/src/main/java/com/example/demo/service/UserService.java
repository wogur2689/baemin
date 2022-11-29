package com.example.demo.service;

import com.example.demo.dto.Join;

public interface UserService {
	void join(Join join);
	
	int overlapCheck(String value, String valueType);
}
