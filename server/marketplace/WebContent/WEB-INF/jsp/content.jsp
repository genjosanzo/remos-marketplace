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
<title>${node.name}</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="../default.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrapper">
<!-- start header -->
<div id="logo">
	<h1><a href="#">Remus Marketplace </a></h1>
	<h2> &raquo;&nbsp;&nbsp;&nbsp;<c:out value="${node.name}"></c:out></h2>
</div>
<jsp:include page="includes/header.html"/>
<!-- end header -->
</div>
<!-- start page -->
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<h1 class="title"><c:out value="${node.name}"></c:out></h1>
			<div class="entry">
				<img style="float: left; padding:5px;" src="${node.image}" width="110" height="80"></img>
				<p>${node.body}</p>
			</div>
			<c:if test="${node.screenshot != null}">
			<div class="entry">
				<h3>Screenshot</h3>
				<img src="${node.screenshot}" alt="" />
			</div>
			</c:if>
			<div class="entry">
				<h3>Additional details</h3>
				
			</div>
			<div class="meta">
				<p class="links"><a href="<c:out value="${node.supporturl}"></c:out>" class="more">Support</a> <b>|</b> <a href="#" class="comments">Comments (32)</a></p>
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
<jsp:include page="includes/footer.html"/> 
<!-- end footer -->
</body>
</html>
