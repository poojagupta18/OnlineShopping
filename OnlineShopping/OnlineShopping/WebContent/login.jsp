<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="CustomerController?action1=login" method="post">
	<table>
		<tr>
		<td>Username:</td>
		<td><input type="text" name="username"/><td>
		</tr>
		<tr>
		<td>Password:</td>
		<td><input type="password" name="password"/><td>
		</tr>
		<tr>
			<td><input type="submit" name="submit" value="login"/></td>
			<td><input type="reset" name="reset" value="Reset"/></td>
		</tr>
	</table>	
	
	<br>
	<br>
	<a href ="register.jsp">Register</a>
</form>
</body>
</html>