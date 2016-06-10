package com.example.chap01.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.chap01.annotation.UserAccess;
import com.example.chap01.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@UserAccess
	@RequestMapping(value="/loginin",method=RequestMethod.GET)
	public @ResponseBody String login(HttpServletRequest request){
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String token=loginService.login(name, password);
		return token;
	}
}
