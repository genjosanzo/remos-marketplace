<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
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
<link href="../default.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<c:url value="../dwr/interface/MarketManager.js" />"></script>
 <script type='text/javascript' src="<c:url value="../dwr/engine.js" />" ></script>
 <script type='text/javascript' src="<c:url value="../dwr/util.js" />" ></script>
<script type="text/javascript">

	function errh(msg, exc) {
	  alert("Error message is: " + msg + " - Error Details: " + dwr.util.toDescriptiveString(exc, 2));
	}

	dwr.engine.setErrorHandler(errh);
		
	function handleCreation(data) {
		location.reload();
	}
	function handleCategoryCreation(data) {
		location.reload();
	}
	function handlePlatformCreation(data) {
		location.reload();
	}
	function handleSolutionCreation(data) {
		location.href = "node/" + data.id + ".htm";
	}
	
	function handlePlatformDeleteClicked(platformId) {
		if (confirm("Do you want to delete this platform?")) {
			MarketManager.deletePlatform(platformId, function() {
				location.reload();
			});
		}
	}
	function handleSolutionDeleteClicked(solutionId) {
		if (confirm("Do you want to delete this platform?")) {
			MarketManager.deleteSolution(platformId, function() {
				location.reload();
			});
		}
	}

	
	
	function handleDeleteClicked(marketId) {
		if (confirm("Do you want to delete this market?")) {
			MarketManager.deleteMarket(marketId, function() {
				location.reload();
			});
		}
		
	}
	function handldeCategoryDeleteClicked(categoryId) {
		if (confirm("Do you want to delete this category?")) {
			MarketManager.deleteCategory(categoryId, function() {
				location.reload();
			});
		}
	}
</script>
</head>
<body>
<div id="wrapper">
<!-- start header -->
<div id="logo">
	<h1><a href="/marketplace">My Eclipse Marketplace </a></h1>
	<h2> &raquo;&nbsp;&nbsp;&nbsp;Markets</h2>
</div>
<jsp:include page="../includes/header.html"/>' 
<!-- end header -->
</div>
<!-- start page -->
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post" id="post">
			<h1 class="title">Administration:</h1>
			<div class="entry">
				<h3>Markets</h3>
				<p>
					<table border="0">
				<form name="addMarket" onSubmit="return false;">
					<tr>
					<td>Add new market:</td><td><input type="text" value="" class="adminForm" name="name"></input></td>
					<td><input type="button" value="Create" onClick="javascript:MarketManager.createMarket(document.addMarket.name.value, handleCreation);"></input></td>
					</tr>
				</form>
				<c:if test="${fn:length(markets) > 0}">
				<form name="deleteMarket" onSubmit="return false;">
					<tr>
					<td>Delete market: </td><td><select class="adminForm" name="market">
						<c:forEach var="item" items="${markets}">
						<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select> 
					</td>
					<td><input type="button" value="Delete" onClick="handleDeleteClicked(document.deleteMarket.market.value)"></input></td>
					</tr>
				</form>
				</c:if>
				</table>
				</p>
			</div>
			<div class="entry">
				<h3>Categories</h3>
				<table border="0">
				<tr>
				<form name="addCategory" onSubmit="return false;">
					<td>Add new category:</td><td>
					<select name="market" class="adminForm">
						<c:forEach var="item" items="${markets}">
						<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select> 
					<input type="text" class="adminForm" value="" name="name"></input>
					</td>
					<td><input type="button" value="Create Category" onClick="javascript:MarketManager.createCategory(document.addCategory.name.value,document.addCategory.market.value, handleCategoryCreation);"></input>
					</td>
				</form>
				</tr>
				<c:if test="${fn:length(categories) > 0}">
				<tr>
					<td>Delete category:</td>
					<td><form name="deleteCategory" onSubmit="return false;">
					<select name="category" class="adminForm">
						<c:forEach var="item" items="${categories}">
						<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select></td><td>
					<input type="button" value="Delete" onClick="handldeCategoryDeleteClicked(document.deleteCategory.category.value)"></input>
					</td>
					</form>
					</tr>
				</c:if>
				</table>
			</div>
			<div class="entry">
				<h3>Platforms</h3>
				<p>
					<table border="0">
				<form name="addPlatform" onSubmit="return false;">
					<tr>
					<td>Add new platform:</td><td><input type="text" value="" class="adminForm" name="name"></input></td>
					<td><input type="button" value="Create" onClick="javascript:MarketManager.createPlatform(document.addPlatform.name.value, handlePlatformCreation);"></input></td>
					</tr>
				</form>
				<c:if test="${fn:length(platforms) > 0}">
				<form name="deletePlatform" onSubmit="return false;">
					<tr>
					<td>Delete platform: </td><td><select class="adminForm" name="platform">
						<c:forEach var="item" items="${platforms}">
						<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select> 
					</td>
					<td><input type="button" value="Delete" onClick="handlePlatformDeleteClicked(document.deletePlatform.platform.value)"></input></td>
					</tr>
				</form>
				</c:if>
				</table>
				</p>
			</div>
			<div class="entry">
				<h3>Solutions</h3>
				<p>
					<table border="0">
				<form name="addSolution" onSubmit="return false;">
					<tr>
					<td>Add new solution:</td><td><input type="text" value="" class="adminForm" name="name"></input></td>
					<td><input type="button" value="Create" onClick="javascript:alert(document.addSolution.name.value);MarketManager.createSolution(document.addSolution.name.value, handleSolutionCreation);"></input></td>
					</tr>
				</form>
				<c:if test="${fn:length(solutions) > 0}">
				<form name="deleteSolution" onSubmit="return false;">
					<tr>
					<td>Delete solution: </td><td><select class="adminForm" name="solution">
						<c:forEach var="item" items="${solutions}">
						<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select> 
					</td>
					<td><input type="button" value="Delete" onClick="handleSolutionDeleteClicked(document.deleteSolution.solution.value)"></input></td>
					</tr>
				</form>
				</c:if>
				</table>
				</p>
			</div>
			<c:forEach var="item" items="${markets}">
			<div class="entry" id="market_${item.id}">
				<h3>${item.name}</h3>
				<p><ul id="ul_${item.id}"><c:forEach var="category" items="${item.categories}">
				<li id="li_${category.id}"><a href="category/${category.id}.htm">${category.name}</a></li>
				</c:forEach></ul></p>
			</div>
			</c:forEach>
			<c:if test="${fn:length(orphans) > 0}">
			<div class="entry">
			<h3>Orphan solutions:</h3>
				<p><ul>
			<c:forEach var="item" items="${orphans}">
				<li><a href="node/${item.id}.htm">${item.name}</a></li>
			</c:forEach>
				</ul></p>
			</div>
			</c:if>
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
