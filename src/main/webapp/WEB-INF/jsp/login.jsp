<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/custom.css">

</head>

<body style="background-color:#002b72;">

<div class="container" style=" display: flex; align-items: center; height:100%;">
  <div class="jumbotron text-center" style="width:100%; background-color:#002b72;">
  <img src="${cp}/resources/images/login.png" style="width:20%;" />
  <br>
  <br>
  <form:form style="color:#ffffff;" action="login" method="post" commandName="credentials">
<div class="form-inline">
  <div class="form-group">
    <label for="email">User name:</label>
    <form:input class="form-control" id="email" path="username" />
  </div>
  <div class="form-group">
    <label for="pwd">Password:</label>
    <form:password class="form-control" id="pwd" path="password" />
  </div>
  </div>
    <div class="form-group" style="margin-top:50px;">
    <input type="submit" class="btn btn-default" id = "login" value="Login">
	</div>
</form:form>
</div>
</div>


 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>

