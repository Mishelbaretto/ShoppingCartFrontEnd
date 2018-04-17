<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>















<form action="product/cart/add" method="post">

 <img alt="" src="${selectedProductImage}"><br>
${selectedProduct.id}
<img alt="" src="resources/images/${selectedProduct.id}.PNG">
Product Name:${selectedProduct.name} <br>
Price:${selectedProduct.price} <br>

Description: ${selectedProduct.description }<br>

<a href="product/cart/add/${selectedProduct.id}">AddtoCart</a>

</form>

</body>
</html>