package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StoreDAO;
import com.example.demo.dto.Food;
import com.example.demo.dto.FoodOption;
import com.example.demo.dto.Review;
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
		List<Review> reviewList = storeDAO.reviewList(storeId);
		
		System.out.println("가게 정보 = " + storeInfo);
		System.out.println("메뉴목록 = " + foodList);
		System.out.println("댓글목록 = " + reviewList);
		
		return new StoreDetail(storeInfo, foodList, reviewList);
	}

	@Override
	public List<FoodOption> foodOption(int foodId) {
		return storeDAO.foodOption(foodId);
	}

	@Override
	public void reviewWrite(Review review) {
		storeDAO.reviewWrite(review);
	}
	
	@Override
	public void reviewModify(Review review) {
	    storeDAO.reviewModify(review);
	}

}
