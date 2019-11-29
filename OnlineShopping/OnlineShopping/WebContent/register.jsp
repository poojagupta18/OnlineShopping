<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="CustomerController?action1=register" method="post">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" />
				<td>
			</tr>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username" />
				<td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" />
				<td>
			</tr>
			<tr>
				<td>Phone Number:</td>
				<td><input type="text" name="phonenumber" />
				<td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="register" /></td>
				<td><input type="reset" name="reset" value="Reset" /></td>
			</tr>
		</table>
	</form>
</body>
</html>