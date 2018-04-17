<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>















  <form action="product/cart/add" method="post">  

 <img alt="" src="${selectedProductImage}"><br>

<%-- <img alt="" src="resources/images/ShoppingCartImages/${selectedProduct.id}.PNG"> --%>
Product Name:${selectedProduct.name} <br>
Price:${selectedProduct.price} <br>

Description: ${selectedProduct.description }<br>

<a href="product/cart/add/${selectedProduct.id}">AddtoCart</a>




 <c:forEach items="${products}" var="product">

${product.name}

${product.price}


${product.description}

<img alt="" src="resources/images/${product.id}.PNG">



</c:forEach>  


</form>

</body>
</html>