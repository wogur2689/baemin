package com.example.demo.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccess implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		String referer = (String) session.getAttribute("referer");
		
		if(referer != null) {
			String domain =
			request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort();
			String uri = referer.replace(domain, "");
			System.out.println("uri = " + uri);
			if(uri.equals("/order")) { 
				response.sendRedirect(uri);
				return;
			}
		}
		
		response.sendRedirect("/mypage");
	}
	
}
