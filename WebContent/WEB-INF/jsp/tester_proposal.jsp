<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>MakeItUsable! - Proposal Details</title>

<%@include file="/WEB-INF/jsp/includes.jsp" %>
<style type="text/css">
#sidebar-list {
	font-size: 14px;
}
</style>
	
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.8/jquery.validate.min.js"></script>
<script type="text/javascript"> $(document).ready(function(){ $("#subForm").validate(); }); </script>
<script src="http://jquery.bassistance.de/validate/additional-methods.js"></script>
<script type="text/javascript">
	
$(document).ready(function () {
   
   $('#submit').click(function(event) {
	   var projectId = $('#projectId').val();
	   var proposal = $('#proposal').val();
	   var proposalDescription = $('#proposalDescription').val();
		//params to be displayed here are not handled - projName,Desc,etc	  
	   
		$.ajax({
			url : "/project-vars/tester_proposal.htm",
		    type: "POST",
		    data : "projectId=" + projectId + "&proposal=" + proposal + "&proposalDescription=" + proposalDescription,
		    success:function(data, textStatus, jqXHR){
		    	window.location.href="/project-vars/viewProjects.htm";
		    },
		    error: function(jqXHR, textStatus, errorThrown){
		    	alert("Could not process request.. " + errorThrown);
		    }
		});
   });
});
	
	
</script>
</head>
<body>
<%@include file="/WEB-INF/jsp/layout/header.jsp" %>
	<div id="container" style="padding-top: 40px;">
	<%
			if (session.getAttribute("user") == null) {
		%>
		<div class="container-fluid">
			<div class="row-fluid">
				<p>
					Please <a href="login.htm">login</a> to view this page.
				</p>
			</div>
		</div>
		<%
			} else {
		%>
		<div id="container" style="padding-top: 40px;">
		<div class="container-fluid">
			<div class="row-fluid">
				<div id="sidebar" class="span2">
					<!--Sidebar content-->
					<ul id="sidebar-list" class="nav nav-list">

					</ul>
				</div>
				<div>
					<div class="tab-content">
						<div class="tab-pane active" id="testerproposal">
							<table>
								<!--Body content-->
								<tr>
									<h2>
										Tester Proposal
									</h2>
								</tr>
								<input type="hidden" id="projectId" name="projectId" value="${project.project_id }"/>
								<tr>
									<td><h5>Project Title</h5></td>
									<td></td>
									<td><div class="col-sm-10">
    									  <p class="form-control-static" id="titleLabel">${project.title }</p>
  										  </div></td>
								</tr>
								<tr>
									<td><h5>Domain</h5></td>
									<td></td>
									<td><div class="col-sm-10">
    									  <p class="form-control-static" id="domainLabel">${project.domain }</p>
  										  </div></td>
								</tr>
								<tr>
									<td><h5>Description</h5></td>
									<td></td>
									<td><div class="col-sm-10">
    									  <p class="form-control-static" id="descriptionLabel">${project.description }</p>
  										  </div></td>
								</tr>
								<tr>
									<td><h5>Proposal</h5></td>
									<td></td>
									<td>
										<div class="input-prepend input-append">
											<span class="add-on">$</span> <input class="required"
												id="proposal" type="text"
												placeholder="Amount in USD">
										</div>
									</td>
								</tr>
								<tr>
									<td><h5>
											<span> Proposal Description</span>
										</h5></td>
									<td></td>
									<td><textarea rows="4" class="required" id="proposalDescription" placeholder="Description regarding proposal "></textarea></td>
								</tr>
								<tr> </tr>
								<tr> </tr>
								
								<tr>
									<td></td>
									<td></td>
									
									<td >
										<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" class="btn btn-default" id="submit" value="Submit"/>
								</div>

									</td>

								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<% } %>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/layout/footer.jsp" %>
</body>

</html>