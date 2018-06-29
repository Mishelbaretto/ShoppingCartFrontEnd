<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
  
  <style>
body {
	font-family: Arial, Helvetica, sans-serif;
}
.carousel-inner > .item > img, .carousel-inner > .item > a > img {
    width: 100%;
    margin: auto;
  }
</style>
</head>
  
    
</head>
<body>

	<center>
		<h2>Welcome to Shopping Cart</h2>
		${logoutMessage} ${success} 
	</center>

	<br>

	<jsp:include page="loginheader.jsp"></jsp:include>
	
	

	<hr class="blue" size="5">
	<%--<jsp:include page="product-menu.jsp"></jsp:include>--%>
    
	<jsp:include page="bootstrap_product_menu.jsp"></jsp:include>



	<c:if test="${isUserSelectedProduct==true }">
		<jsp:include page="selected_product.jsp"></jsp:include>
	</c:if>
<c:if test="${isUserSearchedProduct==true }">
		<jsp:include page="selected_product.jsp"></jsp:include>
	</c:if>
	<c:if test="${isAdmin==true }">
		<jsp:include page="admin/adminhome.jsp"></jsp:include>
	</c:if>



<c:if test="${carouselDisplayedOnce== true}">
		<c:if test="${isAdmin!= true}">
			<c:if test="${isUserClickedMyCart!= true}">
				<c:if test="${isUserSelectedProduct!= true}">
					<jsp:include page="carousels.jsp"></jsp:include>
				</c:if>
			</c:if>
		</c:if>
	</c:if>


	<c:if test="${isUserClickedMyCart==true }">
		<jsp:include page="cart.jsp"></jsp:include>
	</c:if>

	${welcomeMessage} ${errorMessage} ${successMessage }


	<c:if test="${isUserClickedLogin==true}">
		<jsp:include page="login.jsp"></jsp:include>
	</c:if>
	
	<c:if test="${isUserClickedCheckout==true}">
		<jsp:include page="checkout.jsp"></jsp:include>
	</c:if>
	<c:if test="${clickedPlaceOrder==true}">
		<jsp:include page="placeOrder.jsp"></jsp:include>
		</c:if>
	<c:if test="${isUserClickedRegister==true}">
		<jsp:include page="registration.jsp"></jsp:include>
	</c:if>
	${cartErrorMessage} <br>
	 ${noItems}
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>