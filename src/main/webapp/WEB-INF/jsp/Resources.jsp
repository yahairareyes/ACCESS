<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<html>
<head>
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/custom.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

<script src="${cp}/resources/bootstrap/js/Resources_Controller.js"></script>

</head>

<body>

<div class="container">
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><img class="logo" src="${cp}/resources/images/logo.png"/></a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">User</a></li>
      <li><a href="/">Logout</a></li>
    </ul>
  </div>
</nav>


<div class="panel panel-default left_nav pull-left">
<ul class="nav nav-pills nav-stacked text-center">
    <li class="active"><a href="/ACCESS/resource/${user}/${level}">My Resources</a></li>
  <li><a href="/ACCESS/initiative/directory/none/${user}/${level}">Directory</a></li>
  <li><a href="/ACCESS/analysis/${user}/${level}">Analysis</a></li>
 
</ul>
</div>

<div class="container">
<div class="pull-right panel panel-default content">
<form>
<div class="custom_container">
<table id="directory_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Title</th>
                <th>Type</th>
                <th>Role</th>
                <th>Last Modify Date</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${table.resource}" var="resource">
               
                <tr> 
                   <td><a href="/ACCESS/resource/view/${user}/${level}/${resource.type}/${resource.id}">${resource.title}</a></td>
                   <td><a href="/ACCESS/resource/view/${user}/${level}/${resource.type}/${resource.id}">${resource.type}</a></td>
                   <td><a href="/ACCESS/resource/view/${user}/${level}/${resource.type}/${resource.id}">${resource.role}</a></td>
                   <td><a href="/ACCESS/resource/view/${user}/${level}/${resource.type}/${resource.id}">${resource.creationdate}</a></td>
                </tr>
                
            </c:forEach>
        
        </tbody>
    </table>

</div>
<div class="form-inline pull-right custom_container">
<a href="/ACCESS/project/create/${user}/${level}"><input type="button" class="btn btn-default" value="Add Project"></a>


<a href="/ACCESS/initiative/create/${user}/${level}"><input type="button" class="btn btn-default" value="Add Initiative"></a>
</div>
</div>
</form>


</div>
</div>
</body>

</html>