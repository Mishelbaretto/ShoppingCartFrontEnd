<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>




  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse"  >
  <div class="container-fluid">
   
   
   
    <ul class="nav navbar-nav navbar-left">
      <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
     <c:if test="${isLoggedIn==true }">
        <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </c:if>
     
    </ul>
    
    
    
    <form action="search">
    
    
    
     <div class="col-xs-4">
     <div class="input-group input-group-sm">
      <input type="text" class="form-control" placeholder="Search for products" name="searchString">
      
      
      <div class="input-group-btn">
       
        <button class="btn btn-default btn-md" type="submit"><i class="glyphicon glyphicon-search"></i></button>
        </div>
      </div>
    </div>
    
  </form>
    
    
    
     <ul class="nav navbar-nav navbar-right">
     <c:if test="${isLoggedIn!=true}">
     
      <li><a href="register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
     </c:if>
     <c:if test="${isLoggedIn==true }">
        <li><a href="mycart"><span class="glyphicon glyphicon-shopping-cart"></span>MyCart(${size})</a></li>
      </c:if>
     
    </ul>
    
  </div>
</nav>
  
</body>
</html>