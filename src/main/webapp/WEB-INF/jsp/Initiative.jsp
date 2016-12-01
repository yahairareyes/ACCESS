<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<html>
<head>
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${cp}/resources/bootstrap/css/custom.css">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
<link href="${cp}/resources/date/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container">
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Resources"><img class="logo" src="${cp}/resources/images/logo.png"/></a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">User</a></li>
      <li><a href="login">Log out</a></li>
    </ul>
  </div>
</nav>


<div class="panel panel-default left_nav pull-left">
<ul class="nav nav-pills nav-stacked text-center">
  <li class="active"><a href="Resources">My Resources</a></li>
  <li><a href="Directory">Directory</a></li>
  <li><a href="Analysis">Analysis</a></li>
 
</ul>
</div>

<div class="container">
<div class="pull-right panel panel-default content">
    <form:form action="/addInitiative" method="post" commandName="initiative">
<h3>
Initiative Detailed View
</h3>

   <div class="panel panel-primary">
      <div class="panel-heading">Initiative Information</div>
      <div class="panel-body">
  
      <div class="form-group form-inline">
      <label for="initiative title">*Title</label>
      <form:input path="title" class="form-control" id="initiative_title" value="Title" style="width:95%;" />
      </div>
    
    <div class="form-group form-inline">
      <label for="initiative category">*Category</label>
      
      <form:input path="category" class="form-control" id="initiative_category" style="width:30%;" list="categories" />
   <datalist id="categories">
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
   </datalist>
            <label for="start date">*Start Date</label>
          <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" style="width:21%;">
              <form:input path="startdate" class="form-control" value="Start Date"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
      </div>

              <label for="start date">*End Date</label>
          <div class="input-group date form_date col-md-5" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" style="width:21%;">
              <form:input path="enddate" class="form-control" value="Start Date"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
      </div>
    </div>
          <div class="form-group form-inline">
      <label for="project description">*Description</label>
      <form:input path="description" class="form-control" id="initiative_description" value="Description" style="width:50%;" />
      </form:form>
      <form:form action="/addInitiativeKeyword" method="post" commandName="initiative">
      <label for="project keywords">Keyword(s)</label>
      <form:input path="keyword" class="form-control" id="initiative_keywords" value="Keyword(s)" list="keywords"/>
      <datalist id="keywords">
           <c:forEach var="keyword" items="${keywords}"> 
           <option value="${keyword}">
           </c:forEach>
       </datalist>
      <button type="submit" class="btn btn-default icon_button">
  <span class="glyphicon glyphicon-plus"></span>
</button>
      </form:form>
      </div>

  <div class="form-group form-inline">
      <form:form action="/addInitiativePurpose" method="post" commandName="initiative">
      <label for="initiative purpose">Purpose</label>
       <form:input path="purpose" class="form-control" id="initiative_purpose" value="Purpose" style="width:87%;" list="purposes"/>
        <datalist id="purposes">
           <c:forEach var="purpose" items="${purposes}"> 
           <option value="${purpose}">
           </c:forEach>
       </datalist>
<button type="submit" class="btn btn-default icon_button">
  <span class="glyphicon glyphicon-plus"></span>
</button>
      </form:form>
      </div>

        <div class="form-group form-inline">
            <form>
         <label for="initiative document type">Type</label>
  
       <input path="type" class="form-control" id="document_type" style="width:30%;" list="types"/>
        <datalist id="types">
	<option>Data</option>
		<option>Evaluations Report</option>
	<option>Annual Report</option> 
	<option>Other</option>  
        </datalist>
 
       <input type="text" class="form-control" id="initiative_attachment_link" value="Attachment/Link" style="width:59%;">
<button type="button" class="btn btn-default icon_button">
  <span class="glyphicon glyphicon-plus"></span>
</button>
            </form>
      </div>

   <div class="form-group form-inline">
       <form:form action="/addInitiativeWebsite" method="post" commandName="initiative">
      <label for="initiative website">Website</label>
      <form:input path="website" class="form-control" id="initiative_website" value="Website" style="width:87%;" list="websites"/>
    <datalist id="websites">
           <c:forEach var="website" items="${websites}"> 
           <option value="${website}">
           </c:forEach>
       </datalist>
<button type="submit" class="btn btn-default icon_button">
  <span class="glyphicon glyphicon-plus"></span>
</button>
       </form:form>
      </div>     
  
    </div>
</div>

   <div class="panel panel-primary">
      <div class="panel-heading">Initiative Membership</div>
      <div class="panel-body">
  
          <div class="form-group form-inline">
    
      <table id="initiative_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
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
<input type="button" class="btn btn-default" value="Add Membership">
</div>
   
    </div>

</div>

   <div class="panel panel-primary">
      <div class="panel-heading">Activities Assocciated with Initiatives</div>
      <div class="panel-body">
  
    
          <div class="form-group form-inline">
    
      <table id="activity_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                    <th>Title</th>
                <th>Description</th>
                <th>Creation Date</th>
                <th>Linked Initiative</th>
         <th>Owner</th>
            </tr>
        </thead>

        <tbody>
        
        </tbody>
    </table>
      </div>
	  
	  	 
	    <div class="panel panel-default" id="linker" style="display:none;">
	           <div class="form-group ">
					  <h4>  <strong>Link Existing Activity</strong></h4>
	   	   <h5>Please Select an Activity.</h5><br/>
		    <input type="text" class="form-control pull-right" id="title" value="Search" style="width:20%; display:none;">
      <table id="linker" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Creation Date</th>
                <th>Linked Initiative</th>
         <th>Owner</th>
            </tr>
        </thead>

        <tbody>
        
        </tbody>
    </table>
	
	    <div class="custom_container text-right">
<input type="button" class="btn btn-default" value="Select">


<input type="button" class="btn btn-default" value="Cancel" onclick="hideLinker('linker')">
</div>
      </div>
	  </div>
	  
    <div class="form-inline pull-right custom_container">
<a href="Activity.html"><input type="button" class="btn btn-default" value="Add Activity"></a>


<input type="button" class="btn btn-default" value="Link Existing Activity" onclick="showLinker('linker')">
</div>
   
    </div>

</div>
<label for="required files">*Required Fields</label>
<div class="navbar-right"">
 <input type="button" class="btn btn-default" id = "save initiative" value="Save">
  <input type="button" class="btn btn-default" id = "save_return initiative" value="Save & Return">
   <input type="button" class="btn btn-default" id = "delete initiative" value="Delete">
    <input type="button" class="btn btn-default" id = "cancel initiative" value="Cancel">
  </div>

</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${cp}/resources/date/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${cp}/resources/date/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script src="${cp}/resources/bootstrap/js/Initiative_Controller.js"></script>
<script src="${cp}/resources/bootstrap/js/Activity_Controller.js"></script>
<script src="${cp}/resources/bootstrap/js/Linker.js"></script>
<script src="${cp}/resources/bootstrap/js/Interface.js"></script>

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
