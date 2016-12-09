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

<div class="container" id="demo">
 <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Resources.html"><img class="logo" src="${cp}/resources/images/logo.png"/></a>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#">User</a></li>
      <li><a href="login">Log out</a></li>
     
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
<h3>
Project Detailed View
</h3>

<div class="custom_container">
<div class="form-inline">
<span class="button">
<label for="is founded">*Is this a funded project?</label>
<label class="radio-inline"><input type="radio" id="funded" name="funded" onclick = "showView('funded-projects-view','funded','nofunded')">Yes</label>
<label class="radio-inline"><input type="radio" checked id="nofunded" name="nofunded" onclick="hideView('funded-projects-view','nofunded','funded')">No</label>
</span>
</div>
</div>
    <form:form action="${action}" method="post" commandName="project">
       
        
   <div class="panel panel-primary">
      <div class="panel-heading">Project Information</div>
      <div class="panel-body">
  
      <div class="form-group form-inline">
      <label for="project title">*Title</label>
      <form:input path="title" class="form-control" id="title" value="${project.title}" />
        <label for="start date">*Start Date</label>
         <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" style="width:25%;">
             <form:input path="startdate" type='text' class="form-control" value="${project.startdate}"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
      </div>

              <label for="start date">*End Date</label>
         <div class="input-group date form_date col-md-5" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd" style="width:25%;">
             <form:input path="enddate" type='text' class="form-control" value="${project.enddate}"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
      </div>

      </div>
    
          <div class="form-group form-inline">
      <label for="project description">*Description</label>
      <form:input path="description" type="text" class="form-control" id="description" value="${description}" style="width:47%;" />
      <label for="project keywords">Keyword(s)</label>
      <form:input path="keyword" class="form-control" list="keywords" />
       <datalist id="keywords">
           <c:forEach var="keyword" items="${keywords}"> 
           <option value="${keyword}">
           </c:forEach>
       </datalist>
      <button type="submit" onClick="setSender('keyword')" class="btn btn-default icon_button">
     
  <span class="glyphicon glyphicon-plus"></span>
</button>
        
      </div>

  <div class="form-group form-inline">
      <label for="project goal">Goal(s)</label>
      <form:input path="goal" type="text" class="form-control" id="goal" style="width:35%;" list="goals"/>
        <datalist id="goals">
           <c:forEach var="goal" items="${goals}"> 
           <option value="${goal}">
           </c:forEach>
       </datalist>
<button type="submit" onClick="setSender('goal')" class="btn btn-default icon_button">

  <span class="glyphicon glyphicon-plus"></span>
</button>
     
      <label for="project objetive">Objetive(s)</label>
       <form:input path="objetive" class="form-control" id="objetive" style="width:35%;" list="objetives"/>
        <datalist id="objetives">
           <c:forEach var="objetive" items="${objetives}"> 
           <option value="${objetive}">
           </c:forEach>
       </datalist>
<button type="submit" onClick="setSender('objetive')" class="btn btn-default icon_button">
  <span class="glyphicon glyphicon-plus"></span>
</button>
      </div>

        <div class="form-group form-inline">
            
         <label for="document type">Type</label>
         
         <form:input path="document.type" value="${type}" class="form-control" style="width:30%;" list="types" id="type_list" onChange="setType()"/>
              <datalist id="types">  
	<option>Data</option>
		<option>Evaluations Report</option>
	<option>Annual Report</option> 
	<option>Other</option> 
    </datalist>

         <form:input path="document.attachment" class="form-control" id="attachment_link"  style="width:57%;" list="attachments"/>
          <datalist id="attachments">
           <c:forEach var="document" items="${documents}"> 
                    <option value="${document.value}">
           </c:forEach>
       </datalist>
<button type="submit" onClick="setSender('document')" class="btn btn-default icon_button">
   
  <span class="glyphicon glyphicon-plus"></span>
</button>
      </div>

   <div class="form-group form-inline">
      
      <label for="website">Website</label>
      <form:input path="website" class="form-control" id="website" style="width:85%;" list="websites" />
            <datalist id="websites">
           <c:forEach var="website" items="${websites}"> 
           <option value="${website}">
           </c:forEach>
       </datalist>
<button type="submit" onClick="setSender('website')" class="btn btn-default icon_button">

  <span class="glyphicon glyphicon-plus"></span>
</button>

      </div>  

	   <div class="panel panel-default" id="funded-projects-view" style="display:none;">
	   
	   	 
	           <div class="form-group ">
					<h4>  <strong>List of Funded Projects</strong></h4>
	   	   <h5>Please Select from the list of funded projects.</h5><br/>
                  
		    <input type="text" class="form-control pull-right" id="title" value="Search" style="width:20%; display:none;">
        <table id="funded-project-table" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
               
                <th>Project Title</th>
                <th>Project Description</th>
                
            </tr>
        </thead>

        <tbody>
          <c:forEach items="${funded_projects}" var="project">
               <tr> 
                   <td><a href="/ACCESS/project/view/${user}/${level}/${projectId}">${project.title}</a></td>
                   <td><a href="/ACCESS/project/view/${user}/${level}/${projectId}">${project.description}</a></td>
                </tr>
                
            </c:forEach>
        </tbody>
    </table>
	
	    <div class="custom_container text-right">
<!--<input type="button" class="btn btn-default" value="Select">-->


<input type="button" class="btn btn-default" onclick="hideView('funded-projects-view','nofunded','funded')" value="Cancel">
</div>
      </div>
	   
  </div>
    </div>
</div>

   <div class="panel panel-primary">
      <div class="panel-heading">Project Membership</div>
      <div class="panel-body">
 
          <div class="form-group form-inline">
             
      <table id="project_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
          <c:forEach items="${membership}" var="member_row">
               <tr> 
                   
                   <td><a href="#">${member_row.name}</a></td>
                   <td><a href="#">${member_row.email}</a></td>
                   <td><a href="#">${member_row.role}</a></td>
                   <td><a href="/ACCESS/project/delete/${user}/${level}/${projectId}/${member_row.id}">Delete</a></td>
                </tr>
                
            </c:forEach>
            
        </tbody>
      </table>
 
         <table class="table table-striped table-bordered" id="membership_form" cellspacing="0" width="70%">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
        </thead>

        <tbody>
         
               <tr> 
                   <td><form:input path="membership.id"/></td>
                   <td><form:input path ="membership.name" /></td>
                   <td><form:input path="membership.email"/></td>
                   <td><form:input path="membership.role" /></td>
                </tr>
 
        </tbody>
      </table>
         
      </div>
    
    <div class="form-inline pull-right custom_container">
<input type="submit" id="membership" class="btn btn-default" value="Add Membership">
<a href="/ACCESS/project/view/${user}/${level}/${projectId}"><input type="button" style="display:none;" id="cancel_membership" class="btn btn-default" value="Cancel"></a>

</div>
   
    </div>

</div>

   <div class="panel panel-primary">
      <div class="panel-heading">Initiatives Assocciated with Project</div>
      <div class="panel-body">
    
          <div class="form-group">
 
 <input type="text" class="form-control pull-right" id="title" value="Search" style="width:20%; display:none;" >
      <table id="initiative_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
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
          <c:forEach items="${initiatives}" var="initiative">
              <tr> 
                   <td><a href="/ACCESS/initiative/view/${user}/${level}/${initiative.id}">${initiative.title}</a></td>
                   <td><a href="/ACCESS/initiative/view/${user}/${level}/${initiative.id}">${initiative.description}</a></td>
                   <td><a href="/ACCESS/initiative/view/${user}/${level}/${initiative.id}">${initiative.creationdate}</a></td>
                   <td><a href="/ACCESS/initiative/view/${user}/${level}/${initiative.id}">${initiative.activity}</a></td>
                    <td><a href="/ACCESS/initiative/view/${user}/${level}/${initiative.id}">${initiative.owner}</a></td>
                   
                </tr>
             
            </c:forEach>
            
        </tbody>
    </table>
      </div>
	 
	    <div class="panel panel-default" id="linker" style="display:none;">
	           <div class="form-group ">
					  <h4>  <strong>Link Existing Initiative</strong></h4>
	   	   <h5>Please Select an Initiative(s) to link to the project.</h5><br/>
		    <input type="text" class="form-control pull-right" id="title" value="Search" style="width:20%; display:none;">
              
        <table id="initiative_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
                <th>Title</th>
                <th>Description</th>
                <th>Creation Date</th>
                <th>Linked Initiative</th>
         <th>Owner</th>
            </tr>
        </thead>

        <tbody>
                     <c:forEach items="${project.initiatives}" var="initiative" varStatus="i" begin="0">
               <tr> 
                      <input type="hidden" name="initiatives[${i.index}].id" value="${initiative.id}" />
                      <td><form:checkbox path="initiatives[${i.index}].active" value="${initiative.active}" /></td>
                   <td>${initiative.title}</td>
                  <td>${initiative.description}</td>
                   <td>${initiative.creationdate}</td>
                    <td>${initiative.activity}</td>
                   <td>${initiative.owner}</td>
                </tr>
                
            </c:forEach>
        </tbody>
    </table>
                   
	    <div class="custom_container text-right">
<input type="submit" class="btn btn-default" value="Select">


<input type="button" class="btn btn-default" value="Cancel" onclick="hideLinker('linker')">
</div>
      </div>
	  </div>
	  
    <div class="form-inline pull-right custom_container">
<a href="${add_initiative}"><input type="button" class="btn btn-default" value="Add Initiative"></a>


<input type="button" class="btn btn-default" value="Link Existing Initiative" onclick="showLinker('linker')">
</div>
   

    </div>

</div>
<label for="required files">*Required Fields</label>
<div class="navbar-right">
 <input type="submit" onClick="setSender('project')" class="btn btn-default" id = "save project" value="Save" />
  <input type="submit" onClick="setSender('project')" class="btn btn-default" id = "save_return project" value="Save & Return">
   <a href="${delete_project}"><input type="button" onClick= "setAction('delete')" class="btn btn-default" id = "delete project" value="Delete"></a>
    <a href="${cancel}"><input type="button" class="btn btn-default" id = "cancel project" value="Cancel"></a>
  </div>
</form:form>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://editor.datatables.net/extensions/Editor/js/dataTables.editor.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/select/1.2.0/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="${cp}/resources/date/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="${cp}/resources/date/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script src="${cp}/resources/bootstrap/js/Initiative_Controller.js"></script>
<script src="${cp}/resources/bootstrap/js/Funded-Project-Controller.js"></script>
<script src="${cp}/resources/bootstrap/js/Linker.js"></script>
<script src="${cp}/resources/bootstrap/js/Interface.js"></script>

<script>
    $(document).ready(function() {
    $('#project_table').DataTable();
   
} );
    </script>



<script>
    function setSender(sender){
        document.getElementById("sender").value = sender;
        alert( document.getElementById("sender").value = sender);
    }
   </script>

   <script>
    function setAction(action){
        document.getElementById("action").value = action;
        alert( document.getElementById("action").value = action);
    }
   </script>
   
      <script>
    function setType(){
        
        var value = document.getElementById("type_list").value;
        var list = document.getElementById("attachments");
                <c:forEach var="attachment" items="${attachments}"> 
                    <c:if test="${attachment}.type == value">
                        var opt = document.createElement("option");
                        option.text = "${attachment}.attachment";
                    </c:if>
           <option value="${attachment}">,
           </c:forEach>
       
        
        alert(value);
    }
   </script>
   
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

