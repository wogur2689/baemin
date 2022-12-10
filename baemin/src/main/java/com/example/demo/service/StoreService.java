package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Store;

public interface StoreService {
	List<Store> storeList(int category, int address);
}
