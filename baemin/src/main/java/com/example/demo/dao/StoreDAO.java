package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.Store;

public interface StoreDAO {
	List<Store> storeList(Map<String, Object> map);
}
