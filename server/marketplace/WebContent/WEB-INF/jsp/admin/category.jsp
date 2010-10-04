<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--

Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Title      : Emporium
Version    : 1.0
Released   : 20090222
Description: A two-column, fixed-width and lightweight template ideal for 1024x768 resolutions. Suitable for blogs and small websites.

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Administration</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="../../default.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<c:url value="../../dwr/interface/MarketManager.js" />"></script>
 <script type='text/javascript' src="<c:url value="../../dwr/engine.js" />" ></script>

</head>
<body>
<div id="wrapper">
<!-- start header -->
<div id="logo">
	<h1><a href="/marketplace">Remus Marketplace </a></h1>
	<h2> &raquo;&nbsp;&nbsp;&nbsp;Category</h2>
</div>
<jsp:include page="../includes/header.html"/>' 
<!-- end header -->
</div>
<!-- start page -->
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post" id="post">
			<h1 class="title">${category.name}</h1>
			<div>
				Solutions in this category:
				<a href="../markets.htm">Back</a>
				<ul>
				<c:forEach var="item" items="${category.nodes}">
				<li>
					<a href="../node/${item.id}.htm">${item.name}</a> 
				</li>
				</c:forEach>
				</ul>
				Assign existing solution to this category:
				<form:form commandName="setSolutions" method="POST">
					<form:select path="node" >
						<form:options items="${availableSolutions}" itemLabel="name" itemValue="id"/>
					</form:select>
					<form:hidden path="category"  />
					<input type="submit" value="Add solution" />
				</form:form>
			</div>
		</div>
	</div>
	<!-- end content -->
	<!-- start sidebar -->
	
	<!-- end sidebar -->
	<div style="clear: both;">&nbsp;</div>
</div>
<!-- end page -->
<!-- start footer -->
<jsp:include page="../includes/footer.html"/> 
<!-- end footer -->
</body>
</html>
