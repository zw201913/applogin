package com.example.chap01.service.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.chap01.model.User;
import com.example.chap01.service.LoginService;

@Component
@Aspect
public class PermissionAspect {
	
	@Before("@annotation(com.example.chap01.annotation.UserAccess)")
	public void checkPermission(JoinPoint joinPoint) throws Exception{
		System.out.println("前置通知");
		String methodName = joinPoint.getSignature().getName();  
		Object target = joinPoint.getTarget();  
		Method method = getMethodByClassAndName(target.getClass(), methodName);    //得到拦截的方法  
		Object[] args = joinPoint.getArgs();
		HttpServletRequest request=(HttpServletRequest)args[0];
		String token=request.getParameter("token");
		System.out.println("前置通知  token:"+token);
		User user=LoginService.loginUserMap.get(token);
		if(user==null){
			System.out.println("验证不通过！");
			throw new Exception("没有权限");
		}
	}
    
    /** 
     * 根据类和方法名得到方法 
     */  
    public Method getMethodByClassAndName(Class c , String methodName){  
        Method[] methods = c.getDeclaredMethods();  
        for (Method method : methods) {  
            if(method.getName().equals(methodName)){  
                return method ;  
            }  
        }  
        return null;  
    }
}
