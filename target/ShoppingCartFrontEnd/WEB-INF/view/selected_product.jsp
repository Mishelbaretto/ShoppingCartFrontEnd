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
<c:if test="${isUserSelectedProduct}">




 <!--  <form action="product/cart/add" method="post">   -->

 <img alt="" src="${selectedProductImage}"><br>

<%-- <img alt="" src="resources/images/ShoppingCartImages/${selectedProduct.id}.PNG"> --%>
Product Name:${selectedProduct.name} <br>
Price:${selectedProduct.price} <br>

Description: ${selectedProduct.description }<br>


<a href="product/cart/add/${selectedProduct.id}"   class="btn btn-primary">AddtoCart</a>
<!-- <a href="buy" class="btn btn-primary"><span class="glyphicon glyphicon-check"></span>BUY</a> <br> -->

</c:if>
<c:if test="${isUserSearchedProduct}">

   <c:forEach items="${products}" var="product">


<img alt="" src="${selectedProductImage}${product.id}.PNG"><br>
Product Name:${product.name}<br>

Price:${product.price}<br>


Description:${product.description}<br>





</c:forEach>    
<a href="product/cart/add/${selectedProduct.id}"   class="btn btn-primary">AddtoCart</a>
<a href="buy" class="btn btn-primary"><span class="glyphicon glyphicon-check"></span>BUY</a> <br>

<!-- </form> -->
</c:if>
</body>
</html>