<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<html>
<head>
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/custom.css">
<link href="${cp}/resources/date/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="${cp}/resources/icons/css/font-awesome.min.css">
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

<div class="container">
<div class="pull-right panel panel-default content">
<form>
<h4>
Welcome to Accelerating, Connecting, and Evaluating Student Success (ACCESS)
</h4>
<form:form action="${action}" method="post" commandName="directory">
<div class="custom_container">
<div class="row">
<div class="col-sm-3 text-right">
 <label for="search by college">Search by College</label>
 </div>
 <div class="col-sm-4">
     <c:if test="${level  == 'faculty' || level  == 'chair' }">
      <form:input path="college" class="form-control" id="initiative_category" readonly="true" list="colleges" />
      </c:if>
          <c:if test="${level  == 'dean' ||level  == 'admin' }">
      <form:input path="college" class="form-control" id="initiative_category" list="colleges" />
      </c:if>
   <datalist id="colleges">
   
	<option>College of Business</option>
	<option>College of Education</option>
	<option>College of Engineering</option>
	<option>College of Health Sciences</option>
	<option>College of Liberal Arts</option>
	<option>College of Nursing</option>
	<option>College of Science</option>
  </datalist>
 </div>
</div>

<div class="row">
<div class="col-sm-3 text-right">
 <label for="search by department">Search by Department</label>
 </div>
 <div class="col-sm-4">
     <c:if test="${level  == 'faculty'}">
    <form:input path="department" class="form-control" id="initiative_category"  readonly="true" list="departments" />
       </c:if>
        <c:if test="${level  == 'chair' || level  == 'dean' || level=='admin'}">
    <form:input path="department" class="form-control" id="initiative_category"   list="departments" />
       </c:if>
   <datalist id="departments">
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
  </datalist>

 </div>
</div>

<div class="row">
<div class="col-sm-3 text-right">
 <label for="search by category">Search by Category</label>
 </div>
 <div class="col-sm-4">
    <form:input path="category" class="form-control" id="initiative_category" list="categories" />
   <datalist id="categories">
     
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
   </datalist>
 </div>
</div>

<div class="row">
<div class="col-sm-3 text-right">
 <label for="search by dates">Search by Date</label>
 </div>
 <div class="col-sm-4">   
 <input type='text' class="input-group date form_date form-control" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" value="Start Date" readonly/>
 </div>
 
  <div class="col-sm-4">
 <input type='text' class="input-group date form_date form-control" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" value="End Date" readonly/>
 </div>
</div>

<div class="row">
<div class="col-sm-3 text-right">
 <label for="search by keyword">Search by Keyword</label>
 </div>
 <div class="col-sm-4">
   <select class="form-control" id="initiative_document_type">
    <option>Keyword</option>   
  </select>
 </div>
</div>

<div class="row">
<div class="col-sm-3 text-right">
 <label for="show initiatives">Show All Initiatives</label>
 </div>
 <div class="col-sm-4">
   <input type="submit" class="btn btn-default form-control" id = "show_intiatives" value="Show All">
 </div>
</div>

</div>
    
      <table id="directory_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
                <th>Title</th>
                <th>Description</th>
                <th>Category</th>
                <th>Owner</th>
	<th>Creation Date</th>
            </tr>
        </thead>

        <tbody>
            
            
                       <c:forEach items="${directory.initiatives}" var="initiative" varStatus="i" begin="0">
               <tr> 
                   <input type="hidden" name="initiatives[${i.index}].id" value="${initiative.id}" />
                    <input type="hidden" name="initiatives[${i.index}].title" value="${initiative.title}" />
                     <input type="hidden" name="initiatives[${i.index}].description" value="${initiative.description}" />
                      <input type="hidden" name="initiatives[${i.index}].owner" value="${initiative.owner}" />
                       <input type="hidden" name="initiatives[${i.index}].category" value="${initiative.category}" />
                        <input type="hidden" name="initiatives[${i.index}].creationdate" value="${initiative.creationdate}" />
                   <td><form:checkbox path="initiatives[${i.index}].active" /></td>
                   <td>${initiative.title}</a></td>
                  <td>${initiative.description}</a></td>
                   <td>${initiative.category}</a></td>
                        <td>${initiative.owner}</a></td>
                    <td>${initiative.creationdate}</a></td>
                   
                </tr>
                
            </c:forEach>
        </tbody>
    </table>
  
    
          <div class="row">
      <div class="col-sm-6">
    <label for="download Summary Report">Download Summary Report as Excel <a href="#">
    <a href="${sumary_report}"><i  class="fa fa-file-excel-o" aria-hidden="true"></i></a>
    </a> or PDF <a href="#">
    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
    </a></label>
      </div>
      </div>
    
       <div class="row">
      <div class="col-sm-6">
    <label for="download Summary Report">Download Detailed Report as Excel <a href="#">
     <a href="${detailed_report}"><i class="fa fa-file-excel-o" aria-hidden="true"></i></a>
    </a> or PDF <a href="#">
    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
    </a></label>
      </div>
      </div>

</div>
    
</form:form>


</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${cp}/resources/date/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${cp}/resources/date/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script src="${cp}/resources/bootstrap/js/Directory_Controller.js"></script>
<script>
	$('.form_date').datetimepicker({
        language:  'en',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		startDate:new Date(),
		minView: 2,
		forceParse: 0
    });
</script>
</body>

</html>
