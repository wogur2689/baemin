package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.FoodOption;
import com.example.demo.dto.Review;
import com.example.demo.dto.Store;
import com.example.demo.dto.StoreDetail;

public interface StoreService {
	List<Store> storeList(int category, int address);
	
	StoreDetail storeDetail(long id, long userId);
	
	//해당 메뉴의 옵션 가져오기
	List<FoodOption> foodOption(int foodId);
	
	void reviewWrite(Review review);
	
	void reviewModify(Review review);
	
	List<Store> storeList(int category, int address, String sort, int page);
	
	// 찜
	void likes(long storeId, String likes, long userId);
	
	// 찜한 가게들
	List<Store> likesList(long userId);
	
	// 찜한 가게들(비회원)
	List<Store> likesListNonUser(String likes);
}
