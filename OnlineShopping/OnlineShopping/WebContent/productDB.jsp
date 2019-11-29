<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product</title>
</head>
<body>
<form action="ProductDBController" method="post">
<c:if test="${param.action eq 'edit'}">
<input type="hidden" name="action" value="update" />
</c:if>
<c:if test="${param.action eq 'new'}">
<input type="hidden" name="action" value="save" />
</c:if>
<c:out value="${param.action}"></c:out>
<table border="2">

<tr>
<td>ProductCode</td>
<td><input type="text" name="productCode" id="productCode" value="${product.productCode}"/></td>
</tr>

<tr>
<td>Description</td>
<td><input type="text" name="description" id="description" value="${product.description}"/></td>
</tr>

<tr>
<td>Price</td>
<td><input type="text" name="price" id="price" value="${product.price}"/></td>
</tr>

<tr>
<td>Manufacturing Date</td>
<fmt:formatDate value="${product.manfDate}" pattern="dd/MM/yyyy" var="manfDate"/>
<td><input type="text" name="manfDate" id="manfDate" value="${manfDate}"/></td>
</tr>
<tr>



<td><input type="submit" name="submit" value="Submit"/></td>
<td><input type="reset" name="reset" value="Reset" /></td>
</tr>
</table>
</form>
</body>
</html>