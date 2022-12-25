package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.dto.CartList;
import com.example.demo.dto.OrderInfo;
import com.example.demo.dto.OrderList;
import com.example.demo.login.LoginService;

public interface OrderService {
	//장바구니에 담긴 금액과 db의 금액이 같은지 확인
	long orderPriceCheck(CartList cartList);
	
	public String order(CartList cart, OrderInfo info, LoginService user, HttpSession httpSession);
	
	//주문 목록
	List<OrderList> orderList(long userId);
}
