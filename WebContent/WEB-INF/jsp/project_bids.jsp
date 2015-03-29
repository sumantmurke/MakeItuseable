<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>MakeItUsable! - Bids for Project</title>

<%@include file="/WEB-INF/jsp/includes.jsp" %>

<script type="text/javascript">
	
$(document).ready(function () {
   
    $('#submit').click(function(event) {
 	   var bidIdinput = $("input:radio[name=bidRadio]:checked").val();
 		//params to be displayed here are not handled - projName,Desc,etc	  
 		$.ajax({
 			url : "selectBid/" + bidIdinput + ".htm",
 		    type: "POST",
 		   data : "bidId="+ bidIdinput,
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
<script type="text/javascript">
$(document).ready(function() {
    $('#bidding').dataTable( {
    	"sDom": "<'row'<'offset1 span4 'l><'offset3 span4'f>r>t<'row'<'offset1 span4'i><'offset3 span4'p >>"
    	//"sPaginationType": "bootstrap"
    });
} );
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
		<div class="container-fluid">
			<div class="row-fluid">
				<div id="sidebar" class="span2" style="margin: 0px 20px 0px 20px;">
					
				</div>
				<div>
					<div class="tab-content">
						<c:choose>
						<c:when test="${bids ne null}">
						<div class="tab-pane active" id="ShowAllBids">
							<div id="bids">
							<table style = "width:100%">
							<tr>
							<td>
								<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered"  id="bidding">
									<thead>
									<tr>
										<th>Bid ID</th>
										<th>Project Name</th>
										<th>Tester Name</th>
										<th>Bid Description</th>
										<th>Amount</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach var="b" items="${bids}">
										<tr>
										<td><input name="bidRadio" id="bidRadio" type="radio" class="required" value="${b.id}"></td>
										<td><a href="/project-vars/project/devp_project/${b.projectId}.htm">${b.projectName}</a></td>
										<td>${b.testerName}</td>
										<td>${b.description}</td>
										<td>$${b.amount}</td>
									</tr>
									
									</c:forEach>
									</tbody>
									</table>
											</td>
											</tr>
											<tr>
											</tr>
											</table>
											<table>
											<tr>
												<td style="width: 86%;"></td>
												<td>
													<div>
														<input type="submit" class="btn btn-primary" id="submit"
															value="Submit" />
													</div>
												</td>

											</tr>
											</table>
							</div>
						</div>
						</c:when>
						<c:otherwise>
							<div class="tab-pane active" id="ShowAllBids">
								<p>Sorry, no bids found for this project..</p>
							</div>
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<% } %>
	</div>

		<%@include file="/WEB-INF/jsp/layout/footer.jsp" %>

</body>
</html>
