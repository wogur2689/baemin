package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.FoodOption;
import com.example.demo.dto.Store;
import com.example.demo.dto.StoreDetail;
import com.example.demo.service.StoreService;

@Controller
public class StoreController {

	@Autowired
	private StoreService storeService;
	
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
}
