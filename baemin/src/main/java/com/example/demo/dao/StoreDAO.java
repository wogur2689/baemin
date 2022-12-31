package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.internal.metadata.aggregated.rule.VoidMethodsMustNotBeReturnValueConstrained;

import com.example.demo.dto.Food;
import com.example.demo.dto.FoodOption;
import com.example.demo.dto.Review;
import com.example.demo.dto.Store;

public interface StoreDAO {
	List<Store> storeList(Map<String, Object> map);

	Store storeDetail(long storeId, long userId);
	
	List<Food> foodList(long storeId);
	
	List<FoodOption> foodOption(int foodId);
	
	void reviewWrite(Review review);
	
	List<Review> reviewList(long id);
	
	void reviewModify(Review review);
	
	void addLikes(Map<String, Long> map);
	 
	void deleteLikes(Map<String, Long> map);

}
