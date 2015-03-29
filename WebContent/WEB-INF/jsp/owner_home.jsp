<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>MakeItUsable! - Project Owner</title>

<%@include file="/WEB-INF/jsp/includes.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
    $('#example').dataTable( {
    	"sDom": "<'row'<'offset1 span4 'l><'offset3 span4'f>r>t<'row'<'offset1 span4'i><'offset3 span4'p >>"
    	//,"sPaginationType": "bootstrap"
    });
} );
</script>
<script>
$.extend( $.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
} );

</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#submitProject')
								.click(
										function(event) {

											var projectTitle = $(
													'#ProjectTitleInput').val();
											var domain = $('#DomainInput')
													.val();
											var description = $(
													'#DescriptionInput').val();
											var projectURL = $(
											'#ProjectURLInput').val();
											var minBudget = $(
											'#MiniBudgetInput').val();
											var maxBudget = $(
											'#MaxBudgetInput').val();
											var projectSkills = $(
											'#ProjectSkillsInput').val();
											var projectUsers = $(
											'#ProjectUsersInput').val();

											$
													.ajax({
														url : "project.htm",
														type : "POST",
														data : "projectTitle="
																+ projectTitle
																+ "&domain="
																+ domain
																+ "&description="
																+ description
																+ "&projectURL="
																+ projectURL
																+ "&minBudget="
																+ minBudget
																+ "&maxBudget="
																+ maxBudget
																+ "&projectSkills="
																+ projectSkills
																+ "&projectUsers="
																+ projectUsers,
																
														success : function(
																data,
																textStatus,
																jqXHR) {
															window.location.href = "viewProjects.htm";
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															alert("Could not process request.. "
																	+ errorThrown);
														}
													});
										});
					});
	
</script>

<style type="text/css">
#sidebar-list {
	font-size: 14px;
}
</style>
</head>
<body>

	<%@include file="/WEB-INF/jsp/layout/header.jsp"%>

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
		<div class="container-fluid">
			<div class="row-fluid">
				<div id="sidebar" class="span2" style="margin: 35px 20px 0px 20px; padding: 20px 0px 50px 0px; background-color: ghostwhite;">
					<!--Sidebar content-->
					<ul id="sidebar-list" class="nav nav-list">
						<li class="nav-header" style="font-size: 15px;">Project Options</li>
						<li class="active" style="font-size: 15px;"><a href="#ViewMyProjects"
							data-toggle="tab">View My Projects</a></li>
						<li style="font-size: 15px;"><a href="#PostNewProject" data-toggle="tab">Post new
								Project</a></li>
						<li style="font-size: 15px;"><a href="#ViewCompletedProjects" data-toggle="tab">View Completed Projects</a></li>
					</ul>
				</div>
				<div>
					<div class="tab-content">
						<div class="tab-pane" id="PostNewProject">
							<table>
							
								<!--Body content-->
								<tr>
									<td><h5  style="font-size: 14px;height: 18px;">Project Title</h5></td>
									<td></td>
									<td><div>
											<input type="text" id="ProjectTitleInput" style="width: 310px;" class="form-control">
										</div></td>
								</tr>
								<tr>
									<td><h5  style="font-size: 14px;height: 18px;">Domain</h5></td>
									<td></td>
									<td><div>
											<input type="text" style="width: 310px;" id="DomainInput" class="form-control">
										</div></td>
								</tr>
								<tr>
									<td><h5  style="font-size: 14px;height: 18px;">Description</h5></td>
									<td></td>
									<td><div>
											<textarea rows="4" class="required" style="width: 310px;" id="DescriptionInput" placeholder="Project Description "></textarea>
										</div></td>
								</tr>
								<tr>
									<td><h5  style="font-size: 14px;height: 18px;">Project URL</h5></td>
									<td></td>
									<td><div>
											<input type="url" id="ProjectURLInput" style="width: 310px;" class="form-control"
												placeholder="Enter your Website URl">
										</div></td>
								</tr>
								
								<tr>
									<td><h5  style="font-size: 14px;height: 18px;">Budget</h5></td>
									<td></td>
									<td><div>
											<span class="add-on">$</span> 
											<input id="MiniBudgetInput" type="text" style="width: 295px;" placeholder="Minimum Amount">
										</div> 
										
										
										To
						
										
										<div>
											<span class="add-on">$</span> <input 
												id="MaxBudgetInput" type="text" style="width: 295px;" placeholder="Maximum Amount"> </div>
									</td>
								</tr>	
								<tr>
									<td><h5  style="font-size: 14px;height: 18px;">Project Skills</h5></td>
									<td></td>
									<td><div>
											<input type="text" id="ProjectSkillsInput" style="width: 310px;"
												class="form-control">
										</div></td>
								</tr>
								<tr>
									<td><h5  style="font-size: 14px;height: 18px;">Project Users</h5></td>
									<td></td>
									<td><div>
											<input type="text" id="ProjectUsersInput" style="width: 310px;"
												class="form-control">
										</div></td>
								</tr>
								<tr>
								<td></td>
								<td></td>
									<td><div class="col-sm-offset-2 col-sm-10" style="margin-left: 100px ; margin-top: 15px ">
											<input type="submit" class="btn btn-primary"
												id="submitProject" value="Submit Project" />
										</div></td>
								</tr>
							</table>

						</div>
						<div class="tab-pane active" id="ViewMyProjects">
							<c:choose>
								<c:when test="${projects ne null}">
									<div id="projects">
										<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered"  id="example" style="background-color: ghostwhite;">
											<thead>
											<tr>
												<th>Project Title</th>
												<th>Description</th>
												<th>Domain</th>
											</tr>
											</thead>
											<tbody>
											<c:forEach var="p" items="${projects}">
												<tr>
													<td><a href="/project-vars/project/devp_project/${p.project_id}.htm">${p.title}</a></td>
													<td>${p.description}</td>
													<td>${p.domain}</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
								</c:when>
								<c:otherwise>
									<div id="projects">
										<p>
											Sorry, no projects found under your name.. Start by posting a
											project <a href="#PostNewProject" data-toggle="tab">here</a>
										</p>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="tab-pane" id="ViewCompletedProjects">
							<c:choose>
								<c:when test="${myCompletedProjects ne null}">
									<div id="projects">
										<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered"  id="example" style="background-color: ghostwhite;">
											<thead>
											<tr>
												<th>Project Title</th>
												<th>Description</th>
												<th>Domain</th>
											</tr>
											</thead>
											<tbody>
											<c:forEach var="p" items="${myCompletedProjects}">
												<tr>
													<td><a href="/project-vars/project/devp_project/${p.project_id}.htm">${p.title}</a></td>
													<td>${p.description}</td>
													<td>${p.domain}</td> 
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
								</c:when>
								<c:otherwise>
									<div id="projects">
										<p>
											Sorry, no completed projects.
										</p>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%
			}
		%>
	</div>

	<%@include file="/WEB-INF/jsp/layout/footer.jsp"%>
</body>
</html>
