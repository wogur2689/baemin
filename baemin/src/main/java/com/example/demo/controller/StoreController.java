package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.FoodOption;
import com.example.demo.dto.Review;
import com.example.demo.dto.Store;
import com.example.demo.dto.StoreDetail;
import com.example.demo.login.LoginService;
import com.example.demo.service.StoreService;
import com.example.demo.util.UploadFile;

@Controller
public class StoreController {

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private UploadFile uploadFile;

	
	@GetMapping("/store/{category}/{address1}")
	public String store(@PathVariable int category, @PathVariable int address1, Model model) {
		
		List<Store> storeList = storeService.storeList(category, address1 / 100);
		model.addAttribute("storeList",storeList);
		
		return "store/store";
	}
	
	@GetMapping("/store/detail/{id}")
	public String storeDetai(@PathVariable long id, Model model) {
		StoreDetail storeDetail = storeService.storeDetail(id);
		model.addAttribute("store", storeDetail);
		return "store/detail";
	}
	
	@ResponseBody
	@GetMapping("/foodOption")
	public List<FoodOption> menuDetail(int foodId) {
		List<FoodOption> foodOption = storeService.foodOption(foodId);
		return foodOption;
	}
	
	// 리뷰 작성
	@PostMapping("/store/review")
	public String review(Review review, MultipartFile file, @AuthenticationPrincipal LoginService user) throws IOException {
	    if (file.isEmpty()) {
	        String img = "";
	        review.setReviewImg(img);
	    } else {
	        String img = uploadFile.fildUpload(file);
	        review.setReviewImg(img);
	    }
	    long userId = user.getUser().getId();
	    review.setUserId(userId);
	 
	    storeService.reviewWrite(review);
	 
	    return "redirect:/orderList";
	}
	 
	 
	// 리뷰 수정
	@PostMapping("/store/reviewModify")
	public String reviewModify(Review review, MultipartFile file, @AuthenticationPrincipal LoginService user) throws IOException {
	    if(!file.isEmpty()){
	        String img = uploadFile.fildUpload(file);
	        review.setReviewImg(img);
	    }
	    long userId = user.getUser().getId();
	    review.setUserId(userId);
	 
	    storeService.reviewModify(review);
	 
	    return "redirect:/orderList";
	}


}
