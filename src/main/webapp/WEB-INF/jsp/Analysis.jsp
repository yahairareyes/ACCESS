<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<html>
<head>
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/custom.css">
</head>

<body>

<div class="container">
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Resources.html"><img class="logo" src="${cp}/resources/images/logo.png"/></a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">User</a></li>
      <li><a href="/ACCESS/">Log out</a></li>
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

<div class="container" style="height:auto;">
<div class="pull-right panel panel-default content">
<form>
<h4>
Welcome to Accelerating, Connecting, and Evaluating Student Success (ACCESS)
</h4>

   <div class="panel panel-primary">
      <div class="panel-heading panel-header">Query</div>
      <div class="panel-body">
	
      <div class="form-group form-inline">
	  <div class="col-sm-4">
      <label for="query type">Type</label>
	  </div>
      	<select class="form-control" id="query_type" style="width:30%;">
		<option>Type</option>   
		<option>List</option> 
		<option>Comparison</option>
		<option>Graduation Rate</option>
		<option>Retention Rate</option> 

		</select>
      </div>

</div>
</div>
   <div class="panel panel-primary">
      <div class="panel-heading">Filters</div>
      <div class="panel-body">
	  
		<div class="form-group form-inline">
	  <div class="col-sm-4">
      <label for="analysis category">Category</label>
	  </div>
      	<select class="form-control" id="query_type" style="width:30%;">
		<option>Category</option>  
		<option>Learning Communities</option>
		<option>Development Workshops</option> 
			<option>Mentoring</option>
				<option>Student Employment</option>  
				<option>Student Leadership</option>
			<option>First Year Experience</option>
			<option>Undergraduate Research & Creative Activity</option>
				<option>Internship & Practinum</option>
			<option>Study Abroad/Study Away</option>
			<option>Community Engagement & Service Learning</option>  
			<option>Capstone Experience</option>  
		</select>
      </div>
	  
	  		<div class="form-group form-inline">
	  <div class="col-sm-4">
      <label for="analysis clasification">Clasification</label>
	  </div>
      	<select class="form-control" id="query_type" style="width:30%;">
		<option>Clasification</option> 
		<option>Freshman</option>
		<option>Sophomore</option>
		<option>Junior</option>
		<option>Senior</option>
		<option>Master</option>
		<option>Ph.D.</option>
		<option>All</option>
		</select>
      </div>
	  
	  <div class="form-group form-inline">
	  <div class="col-sm-4">
      <label for="analysis graduates">Graduates</label>
	  </div>
	<label class="radio-inline"><input type="radio" name="graduate">Yes</label>
	<label class="radio-inline"><input type="radio" name="no-graduate">No</label>
      </div>
	  
	  <div class="form-group form-inline">
	  <div class="col-sm-4">
      <label for="analysis major">Major Field</label>
	  </div>
      	<select class="form-control" id="query_type" style="width:30%;">
		<option>Major Field</option>   
		<option>STEM</option>
		<option>Science</option>
		<option>Engineering</option>
		<option>Liberal Arts</option>
		<option>Health Science</option>
		<option>Business</option>
		<option>Pharmacy</option>
		<option>Nursing</option>
		</select>
      </div>
	  
	  <div class="form-group form-inline">
	  <div class="col-sm-4">
      <label for="analysis department">Department</label>
	  </div>
      	<select class="form-control" id="query_type" style="width:30%;">
		<option>Department</option>
     <option>Accounting and Information Systems</option>
	 <option>Economics and Finance</option>
	 <option>Marketing and Management</option>  
	  <option>Educational Leadership and Foundations</option> 
	   <option>Teacher Education</option> 
	    <option>Civil Engineering</option> 
		 <option>Computer Science</option> 
		  <option>Electrical and Computer Engineering</option> 
		  <option>Engineering Education & Leadership</option> 
		   <option>Industrial, Manufacturing, and Systems Engineering</option> 
		    <option>Mechanical Engineering</option> 
			<option>Metallurgical and Materials Engineering</option> 
			<option>Kinesiology</option> 
			<option>Public Health Sciences</option> 
			<option>Rehabilitation Sciences</option> 
			<option>Art</option> 
			<option>Communication</option>
			<option>Creative Writing</option>
			<option>Criminal Justice English</option>
			<option>History</option>
			<option>Languages and Linguistics</option>
			<option>Military Science Music</option>
			<option>Philosophy</option>
			<option>Political Science</option>
			<option>Psychology</option>
			<option>Sociology and Anthropology</option>
			<option>Biological Sciences</option>
			<option>Chemistry</option>
			<option>Geological Sciences</option>
			<option>Physics</option>
		</select>
      </div>
	  
    </div>

</div>

   <div class="panel panel-primary">
      <div class="panel-heading">Restrictions</div>
      <div class="panel-body">
  	  <div class="form-group form-inline">
	  <div class="col-sm-4">
      <label for="analysis degree">Time to Degree</label>
	  </div>
      	<select class="form-control" id="query_type" style="width:30%;">
		<option>Time to Degree</option>  
		<option>4</option>
		<option>5</option> 
		<option>6</option>
		<option>7-10</option>
		<option>>10</option> 
		</select>
      </div>
	  
	  	  <div class="form-group form-inline">
	  <div class="col-sm-4">
      <label for="analysis advancement">Undergraduated to Graduate Advancement</label>
	  </div>
	<label class="radio-inline"><input type="radio" name="advancement">Yes</label>
	<label class="radio-inline"><input type="radio" name="no-advancement">No</label>
      </div>

</div>

</div>

<div class="navbar-right">
 <input type="button" class="btn btn-default" id = "download_results" value="Download Results">
    <input type="button" class="btn btn-default" id = "cancel_activity" value="Cancel">
	</div>
</form>
</div>
</div>
</div>


 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  

</body>

</html>
