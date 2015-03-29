<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>MakeItUsable! - Project Details</title>

<%@include file="/WEB-INF/jsp/includes.jsp" %>
<style type="text/css">
#sidebar-list {
	font-size: 14px;
}
</style>

<script type="text/javascript">
	
$(document).ready(function () {
   
   $('#submitResponse').click(function(event) {
	   var results = $('#resultsInput').val();
	   var projectTitle = $('#projectTitle').val();	  
	   console.log("project title:"+ projectTitle );
		$.ajax({
			url : "/project-vars/tester_project.htm",
		    type: "POST",
		    data : "results=" + results + "&projectTitle=" +projectTitle,
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
	
	<div id="container" style="padding: 50px 0px 70px 0px;">
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
		<input type="hidden" id="projectTitle" name="projectTitle" value="${project.title}" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div id="sidebar" class="span2">
						<!--Sidebar content-->
					<ul id="sidebar-list" class="nav nav-list">
					</ul>
				</div>
					<div class="tab-content">
						<div class="tab-pane active" id="Project">
							<table cellpadding="5px">
								<!--Body content-->
								<tr>
  									<td><h2>Project Details</h2></td>
								</tr>
								<tr>
									<td><h5>Project Title</h5></td>
									<td></td>
									<td><div class="col-sm-10">
    									  <p class="form-control-static" id="titleInput">${project.title }</p>
  										  </div></td>
								</tr>
								<tr></tr>
								<tr>
									<td><h5>Description</h5></td>
									<td></td>
									<td><div class="col-sm-10">
    									  <p class="form-control-static" id="descriptionInput">${project.description }</p>
  										  </div></td>
								</tr>
								<tr></tr>
								<tr>
									<td><h5>Domain</h5></td>
									<td></td>
									<td><div class="col-sm-10">
    									  <p class="form-control-static" id="descriptionInput">${project.domain }</p>
  										  </div></td>
								</tr>
								<tr></tr>
								<tr>
									<td><h5>Developer Name</h5></td>
									<td></td>
									<td><div class="col-sm-10">
    									  <p class="form-control-static" id="NameInput">${project.developerName}</p>
  										  </div></td>
								</tr>
								<tr></tr>
								<c:choose>
									<c:when test="${project.tester_id ne 0}">	
								<tr>
									<td><h5>Tester's Suggestions</h5></td>
									<td></td>
									<td><textarea id="resultsInput"class="proposalDescription-Input" rows="3" ></textarea>
									</td>
								</tr>
								</c:when>
								<c:otherwise>
																	
								</c:otherwise>
							</c:choose>
								<tr></tr>
								<c:choose>
									<c:when test="${project.tester_id eq 0}">
								<tr>
									<td><h5>Bids</h5></td>
									<td></td>
									<td><a href="/project-vars/project/bids/${project.project_id}.htm">Bid this project!</a></td>
								</tr>
									</c:when>
								<c:otherwise>
								
								</c:otherwise>
								</c:choose>
								<tr></tr>
								<tr></tr>
								
								<c:choose>
									<c:when test="${project.tester_id ne null}">
								<tr>
									<td></td>
									<td></td>
									<td><div class="col-sm-offset-2 col-sm-10">
									<input type="submit" class="btn btn-default" id="submitResponse" value="Submit"/>
								</div></td>
								</tr>
								</c:when>
								<c:otherwise>
								
								</c:otherwise>
								</c:choose>
								
							</table>
						</div>
					</div>
				</div>
			</div>
			</div>
			<% } %>
		</div>
<%@include file="/WEB-INF/jsp/layout/footer.jsp" %>

</body>
</html>