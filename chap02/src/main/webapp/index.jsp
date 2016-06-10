<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="<c:url value="/resources/js/common/jquery-2.2.4.min.js" />"></script>


<body>
	<h2>Hello World!</h2>
	<form action="/chap01/login/loginin">
		用户名：<input type="text" name="name" id="name"/> 密 码：<input type="password" name="password" id="password"/>
		<input type="button" value="登录" onclick="login()"/>
	</form>
	
	
	
</body>

<script type="text/javascript">
var login=function(){
	var url="http://127.0.0.1:8080/chap02/login/loginin";
	var name=$("#name").val();
	var password=$("#password").val();
	$.ajax({
	    type: "GET",
	    url: url,
	    data: {name:name, password:password},
	    contentType: "application/text; charset=utf-8",
        dataType: "text",
	    success:function(data){
	    	alert("success"+data);
	    },
	    error:function(e,e1,e2){
	    	alert("error"+e+"e1:"+e1+"e2"+e2);
	    }
	});
}



</script>
</html>
