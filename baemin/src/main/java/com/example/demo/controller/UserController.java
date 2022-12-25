package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Join;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private UserService userService;

	@GetMapping("/myPage")
	public String myPage() {
		return "user/myPage";
	}
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpSession session) {
		String referee = (String) request.getHeader("referer");
		session.setAttribute("referee", referee);
		return "user/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinProc(@Valid Join join, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {		
			List<FieldError> list = bindingResult.getFieldErrors();
			Map<String, String> errorMsg = new HashMap<>();
			
			for(int i = 0; i<list.size(); i++) {
				String field = list.get(i).getField();
				String message = list.get(i).getDefaultMessage();
				
				System.out.println("필드 = " + field);
				System.out.println("메세지 = " + message);
				
				errorMsg.put(field, message);
			}
			model.addAttribute("errorMsg", errorMsg);
			return "user/join";
		}
		String encPwd = pwdEncoder.encode(join.getPassword());
		join.setPassword(encPwd);
		userService.join(join);
		
		return "redirect:/login";
	}
	
	@ResponseBody
	@GetMapping("/overlapCheck")
	public int overlapCheck(String value, String valueType) {
		System.out.print(value);
		System.out.print(valueType);
		int count = userService.overlapCheck(value, valueType);
		
		System.out.print(count);
		return count;
	}
}
