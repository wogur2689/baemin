package com.example.demo.dao;

import com.example.demo.dto.Join;

public interface UserDAO {
	void join(Join join);
	int overlapCheck(String value, String valueType);
}
