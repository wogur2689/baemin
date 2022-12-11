package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StoreDAO;
import com.example.demo.dto.Food;
import com.example.demo.dto.FoodOption;
import com.example.demo.dto.Store;
import com.example.demo.dto.StoreDetail;

@Service
public class StoreServiceImp implements StoreService {

	@Autowired
	private StoreDAO storeDAO;
	
	@Override
	public List<Store> storeList(int category, int address) {
		Map<String, Object> map = new HashMap<>();
		map.put("category", category);
		map.put("address1", address);
		
		return storeDAO.storeList(map);
	}
	
	@Override
	public StoreDetail storeDetail(long storeId) {
		Store storeInfo = storeDAO.storeDetail(storeId);
		List<Food> foodList = storeDAO.foodList(storeId);
//		List<Review> reviewList = storeDAO.reviewList(storeId);
		
		return new StoreDetail(storeInfo, foodList);
	}

	@Override
	public List<FoodOption> foodOption(int foodId) {
		return storeDAO.foodOption(foodId);
	}
}
