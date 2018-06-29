<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
${cartErrorMessage}
 <b> ${noItems} </b> 

<form action="checkout" method="post">
<%-- <c:if test="${ cartList.size()!=0}">     --%>


<c:forEach var="cart" items="${cartList}">
 
  <img alt="" src="${selectedProductImage}${cart.productID }.PNG"><br>
 <%--    <img alt="" src="${ImagesFolder}${cart.productID }.PNG">--%>
   <%--  <img alt="" src="resources/images/${cart.productID}.PNG">--%>
Name:<input type="text" name="price" value="${cart.productName}"><br>
Price:<input type="text" name="price" value="${cart.price}"><br>
Quantity:<input type="text" name="price" value="${cart.quantity}">
<td><a href="remove/${cart.id}"><button type="button" class="btn btn-danger btn-sm">Remove</button></a><br></td>

</c:forEach>
<!-- <a href="buy"    class="btn btn-primary"><span class="glyphicon glyphicon-check">BUY</a> <br> -->
 <p style="text-align:right;margin:0px 80px;"><font size="4" face="verdana"><b><i>Total : ${cartsum} </i></b></font></p></div><br>
  <p style="text-align:right;margin:0px 80px;">
					<button style="width:10%;" form action="checkout" method="post"> Check Out </button></p>
<%-- </c:if> --%>
</form>
</body>
</html>