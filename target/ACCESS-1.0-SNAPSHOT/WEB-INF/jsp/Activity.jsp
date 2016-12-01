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
      <li><a href="#">Log out</a></li>
      
    </ul>
  </div>
</nav>


<div class="panel panel-default left_nav pull-left">
<ul class="nav nav-pills nav-stacked text-center">
   <li class="active"><a href="Resources.html">My Resources</a></li>
  <li><a href="Directory.html">Directory</a></li>
  <li><a href="Analysis.html">Analysis</a></li>
 
</ul>
</div>

<div class="container">
<div class="pull-right panel panel-default content">
    <form:form action="${url}" method="post" commandName="activity">
<h3>
Activity Detailed View
</h3>

   <div class="panel panel-primary">
      <div class="panel-heading">Activity Information</div>
      <div class="panel-body">
  
      <div class="form-group form-inline">
      <label for="activity title">*Title</label>
       <form:input path="title" class="form-control" id="activity_title" value="Title" style="width:95%;" />
      </div>
    
    <div class="form-group form-inline">
            <label for="start date">*Start Date</label>
         <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" style="width:21%;">
                    <input type='text' class="form-control" value="Start Date"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
      </div>

              <label for="start date">*End Date</label>
         <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" style="width:21%;">
                    <input type='text' class="form-control" value="Start Date"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
      </div>
          <label for="activity keywords">Keyword(s)</label>
       <input type="text" class="form-control" id="initiative_keywords" value="Keyword(s)">
      <button type="button" class="btn btn-default icon_button">
  <span class="glyphicon glyphicon-plus"></span>
</button>
    </div>
          <div class="form-group form-inline">
      <label for="activity description">*Description</label>
       <input type="text" class="form-control" id="activity_description" value="Description" style="width:95%;">
      <br/>
    <br/>
    <form >
    <label style="font-weight:bold;">Target Audience</label><br/>
    <label for="initiative document type">*Classification</label>
    <input class="form-control" id="audience_classification" style="width:30%;" list="classifications" />
        <datalist id="classifications">  
	 <option>Freshman</option>
	  <option>Sophomore</option>
	   <option>Junior</option>
	   <option>Senior</option>
	    <option>Master</option>
		 <option>Ph.D.</option>
		  <option>All</option>
        </datalist>
    <input class="form-control" id="classification_description" value="Description" style="width:51%;" />
    <button type="submit" class="btn btn-default icon_button">
    <span class="glyphicon glyphicon-plus"></span>
    </button>
    </form>

      </div>  
    </div>
</div>

   <div class="panel panel-primary">
      <div class="panel-heading">Activity Course Mapping Information</div>
      <div class="panel-body">
  
          <div class="form-group form-inline">
         <label for="activity semester">Semester</label>
  <select class="form-control" id="initiative_document_type" style="width:30%;">
    <option>Semester</option>   
	<option>Spring</option>
	<option>Fall</option>
  </select>

    <label for="activity course" style="font-weight:bold;">Course Name</label>
       <input type="text" class="form-control" id="activity_course" value="Course name (Example)" style="width:44%;">
<button type="button" class="btn btn-default icon_button">
  <span class="glyphicon glyphicon-plus"></span>
</button>
      </div>   
    </div>

</div>

   <div class="panel panel-primary">
      <div class="panel-heading">Activity Participant Information</div>
      <div class="panel-body">
  
          <div class="form-group form-inline">
    
      <table id="activity_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
				 <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
        
        </tbody>
    </table>
      </div>
    
    <div class="form-inline pull-right custom_container">
<input type="button" class="btn btn-default" value="Add Participant">
</div>
</div>

</div>

<label for="required files">*Required Fields</label>
<div class="navbar-right">
 <input type="button" class="btn btn-default" id = "save activity" value="Save">
  <input type="button" class="btn btn-default" id = "save_return activity" value="Save & Return">
   <input type="button" class="btn btn-default" id = "delete activity" value="Delete">
    <input type="button" class="btn btn-default" id = "cancel activity" value="Cancel">
  </div>
</form:form>
</div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${cp}/resources/date/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${cp}/resources/date/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script src="${cp}/resources/bootstrap/js/Activity_Controller.js"></script>
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

<script>
   function addAudience(){
       $('#')
       
   }
    </script>

</body>

</html>

