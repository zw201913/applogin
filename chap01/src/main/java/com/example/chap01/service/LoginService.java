package com.example.chap01.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.chap01.model.User;
import com.example.chap01.util.MD5Util;

@Service
public class LoginService {
	/**
	 * 存放“用户名：token”键值对
	 */
	public static Map<String,String> tokenMap=new HashMap<String,String>();
	/**
	 * 存放“token:User”键值对
	 */
	public static Map<String,User> loginUserMap=new HashMap<String,User>();
	
	
	
	public String login(String name,String password){
		System.out.println(name+"-----"+password);
		/**
		 *  判断是否登录成功
		 *  1.登录成功
		 *   1.1.成功生成对应的token并更新
		 *   1.2.失败就抛异常
		 */
		String token=tokenMap.get(name);
		User user=null;
		if(token==null){
			user=new User();
			user.setName(name);
			user.setPassword(password);
			System.out.println("新用户登录");
		}else{
			user=loginUserMap.get(token);
			loginUserMap.remove(token);
			System.out.println("更新用户登录token");
		}
		token=MD5Util.MD5(name+password+new Date().getTime());
		loginUserMap.put(token, user);
		tokenMap.put(name, token);
		System.out.println("目前有"+tokenMap.size()+"个用户");
		for(User u:loginUserMap.values()){
			System.out.println(u.getName()+":"+u.getPassword());
		}
		return token;
	} 
}
