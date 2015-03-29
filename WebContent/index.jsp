<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>MakeItUsable!</title>
	
		<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-responsive.css">
		<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-responsive.min.css">
		<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		
		<style type="text/css">
			#sidebar-list {
				font-size: 14px;
				margin: 10px 5px 10px 5px;
			}
			#sidebar, #main-content {
				background-color: #eeeeee;
			}
		</style>
</head>
<body>
	<div id="header">
		<div class="navbar">
			<div class="navbar-inner">
				<a class="brand" href="#"><span style="color: black;">MakeItUsable!</span></a>
				<ul class="nav">
					<li><a href="hello.htm">Home</a></li>
					<li class="divider-vertical"></li>
					<li><a href="#">Link</a></li>
					<li class="divider-vertical"></li>
					<li><a href="#">Link</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div id="container" style="padding-top: 20px;">
		<div class="container-fluid">
			<div class="row-fluid">
				<div id="sidebar" class="span2">
					<!--Sidebar content-->
					<ul id="sidebar-list" class="nav nav-list">
						<li class="nav-header">Project Options</li>
						<li><a href="#">Post new Project</a></li>
						<li><a href="#">View My Projects</a></li>
					</ul>
				</div>
				<div id="main-content" class="span10">
					<!--Body content-->
				</div>
			</div>
		</div>
	</div>
	
	<div id="footer">
		
	</div>

</body>
</html>