package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Cart;
import com.example.demo.dto.CartList;
import com.example.demo.dto.OrderInfo;
import com.example.demo.dto.OrderList;
import com.example.demo.login.LoginService;
import com.example.demo.service.OrderService;
import com.example.demo.util.CreateOrderNum;
import com.example.demo.util.FoodInfoFromJson;
import com.example.demo.util.Page;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	/**
	 * 주문
	 * @param session
	 * @param model
	 * @param user
	 * @return
	 */
	@GetMapping("/order")
	public String order(HttpSession session, Model model, @AuthenticationPrincipal LoginService user){ 
		CartList cartList = (CartList)session.getAttribute("cartList");
		
		model.addAttribute("cartList", cartList);
		
		System.out.print(user);
		
		if(user != null) {
			model.addAttribute("user", user.getUser());
		}
		String orderNum = CreateOrderNum.createOrderNum();
		model.addAttribute("orderNum", orderNum);
		return "order/order";
	}
	/**
	 * 결제
	 * @param session
	 * @param orderInfo
	 * @param totalPrice
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@PostMapping("/order/payment-cash")
	public ResponseEntity<String> paymEntity(HttpSession session, OrderInfo orderInfo, long totalPrice, @AuthenticationPrincipal LoginService user) throws IOException { 
		CartList cartList = (CartList) session.getAttribute("cartList");
	    
	    long orderPriceCheck = orderService.orderPriceCheck(cartList);
	    
	    System.out.println("계산금액 = " + totalPrice + " 실제 계산해야할 금액 = " + orderPriceCheck );
	    
	    if(orderPriceCheck == totalPrice) {
	        orderService.order(cartList, orderInfo, user, session);
	        session.removeAttribute("cartList");
	    }
	 
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * 주문목록
	 * @param user
	 * @param model
	 * @return
	 */
	@GetMapping({"/orderList", "/orderList/{page}"})
	public String orderList(@AuthenticationPrincipal LoginService user, Model model, 
			@PathVariable(required = false) Integer page) {
		if (user == null) {
	        System.out.println("비로그인");
	    } else {
	        System.out.println("로그인");
	        long userId = user.getUser().getId();
	 
	        Page p = new Page(page);
	        List<OrderList> orderList = orderService.orderList(userId, p);
	 
	        if (orderList.size() == 0) {
	            return "order/orderList";
	        }
	 
	        List<List<Cart>> cartList = new ArrayList<>();
	 
	        for (int i=0;i<orderList.size();i++) {
	            cartList.add(FoodInfoFromJson.foodInfoFromJson(orderList.get(i).getFoodInfo()));
	        }
	        
	        p.totalPage(orderList.get(0).getListCount());
	        model.addAttribute("page", p);
	        model.addAttribute("user", user.getUser());
	        model.addAttribute("cartList", cartList);
	        model.addAttribute("orderList", orderList);
	    }
	 
	    return "order/orderList";
	}
}
