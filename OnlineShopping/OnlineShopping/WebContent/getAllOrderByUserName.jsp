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
	<table border=3>
		<tr>
			<td>
				<table border=0>
					<tr>
						<td>Order Id:${order.orderId}</td>
						<td></td>
						<td></td>
						<td></td>
						<td>Order Date: ${order.orderDate}</td>
					</tr>
					<tr>
						<td>Customer Name: ${customer.name}</td>
						<td></td>
						<td></td>
						<td></td>
						<td>Phone Number: ${customer.phonenumber}</td>
					</tr>
				</table>

				<table>
					<tr>
						<th>Sr No</th>
						<th>Product id</th>
						<th>Product Name</th>
						<th>Price</th>
						<th>Quality</th>
						<th>Total</th>
					</tr>
					<tr>
						<td>1</td>
						<td></td>
						<td>Product name</td>
						<td>Price</td>
						<td>Quality</td>
						<td>Total</td>
					</tr>
				</table>
		</tr>

	</table>
</body>
</html>