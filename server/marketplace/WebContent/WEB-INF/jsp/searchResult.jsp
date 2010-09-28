<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>Emporium by Free Css Templates</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrapper">
<!-- start header -->
<div id="logo">
	<h1><a href="/marketplace">Remus Marketplace </a></h1>
	<h2> &raquo;&nbsp;&nbsp;&nbsp;Search</h2>
</div>
<jsp:include page="includes/header.html"/>' 
<!-- end header -->
</div>
<!-- start page -->
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<h1 class="title">Search results for: <c:out value="${query}"></c:out></h1>
			<c:forEach var="item" items="${node}">
			<div class="entry">
				<img style="float: left; padding:5px;" src="${item.image}" width="110" height="80"></img>
				<h3><a href="${item.url}">${item.name}</a></h3>
				<p>${item.shortdescription}</p>
			</div>
			</c:forEach>
		</div>
	</div>
	<!-- end content -->
	<!-- start sidebar -->
	
	<!-- end sidebar -->
	<div style="clear: both;">&nbsp;</div>
</div>
<!-- end page -->
<!-- start footer -->
<jsp:include page="includes/footer.html"/> 
<!-- end footer -->
</body>
</html>
