<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="WEB-INF/mydateformat.tld" prefix="mytag" %>  


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product List Display</title>
</head>
<body>
<mytag:mydateformat pattern="dd/mm/yyyy" value="12/01/1997"></mytag:mydateformat>

<h3> Product list Display</h3>
<br>
<a href = "ProductDBController?action=new">ADD</a>
<br>
<br>
	<table border="2">
		<tr>
			<th>ProductId</th>
			<th>Code</th>
			<th>Description</th>
			<th>Price</th>
			<th>Manufacturing Date</th>
			<th>Category</th>
		</tr>

		<c:forEach items="${productList}" var="product">
		<tr>
			<td>${product.productId}</td>
			<td>${product.productCode}</td>
			<td>${product.description}</td>
			<td>${product.price}</td>
			<td><fmt:formatDate value="${product.manfDate}"
					pattern="dd/MM/yyyy" /></td>
					<td>${product.categoryList}</td>
			<td><a href="ProductDBController?action=edit&id=${product.productId}">Edit</a>|<a href="ProductDBController?action=delete&id=${product.productId}">Delete</a></td>
		</tr>
		</c:forEach>

	</table>

</body>
</html>