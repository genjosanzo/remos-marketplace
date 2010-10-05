<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
<link href="../../default.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="<c:url value="../../dwr/interface/MarketManager.js" />"></script>
 <script type='text/javascript' src="<c:url value="../../dwr/engine.js" />" ></script>
 <script type='text/javascript' src="<c:url value="../../dwr/util.js" />" ></script>
 <script type="text/javascript">
 	function errh(msg, exc) {
	  alert("Error message is: " + msg + " - Error Details: " + dwr.util.toDescriptiveString(exc, 2));
	}

	dwr.engine.setErrorHandler(errh);

	function handleIuDeleteClicked(iuId) {
		if (confirm("Do you want to delete this installable unit?")) {
			MarketManager.deleteIu(iuId, function() {
				location.reload();
			});
		}
	}
	function handleIuCreation(data) {
		location.reload();
	}
 </script>
</head>
<body>
<div id="wrapper">
<!-- start header -->
<div id="logo">
	<h1><a href="/marketplace">My Eclipse Marketplace </a></h1>
	<h2> &raquo;&nbsp;&nbsp;&nbsp;Edit solution</h2>
</div>
<jsp:include page="../includes/header.html"/>' 
<!-- end header -->
</div>
<!-- start page -->
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post" id="post">
			<a href="../">Back</a> 
			<form:form commandName="editSolution" method="POST" action="">
			<table border="0">
			<tr>
				<td>Name</td><td><form:input path="name" cssClass="nodeForm"/><form:errors path="name"/></td>
			</tr>
			<tr>
				<td>Type</td><td><form:input path="resource" cssClass="nodeForm"/><form:errors path="resource"/></td>
			</tr>
			<tr>
				<td>Owner</td><td><form:input path="owner" cssClass="nodeForm"/><form:errors path="owner"/></td>
			</tr>
			<tr>
				<td>Shortdescription</td><td> <form:textarea path="shortdescription" cssClass="nodeForm"/><form:errors path="shortdescription"/></td>
			</tr>
			<tr>
				<td>Body</td><td> <form:textarea path="body" cssClass="nodeForm"/><form:errors path="body"/></td>
			</tr>
			<tr>
				<td>Screenshot</td><td><form:input path="screenshot" cssClass="nodeForm"/><form:errors path="screenshot"/></td>
			</tr>
			<tr>
				<td>Homepage-Url</td><td><form:input path="homepageurl" cssClass="nodeForm"/><form:errors path="homepageurl"/></td>
			</tr>
			<tr>
				<td>Update-Url</td><td><form:input path="updateurl" cssClass="nodeForm"/><form:errors path="updateurl"/></td>
			</tr>
			<tr>
				<td>Image</td><td><form:input path="image" cssClass="nodeForm"/><form:errors path="image"/></td>
			</tr>
			<tr>
				<td>License</td><td><form:input path="license" cssClass="nodeForm"/><form:errors path="license"/></td>
			</tr>
			<tr>
				<td>Featured</td><td><form:input path="foundationmember" cssClass="nodeForm"/><form:errors path="foundationmember"/></td>
			</tr>
			<tr>
				<td>Versions</td><td><form:input path="eclipseversion" cssClass="nodeForm"/><form:errors path="eclipseversion"/></td>
			</tr>
			<tr>
				<td>Platforms</td><td><form:select path="platforms" multiple="true" items="${platforms}" itemLabel="name" itemValue="id" ></form:select><form:errors path="platforms"/></td>
			</tr>
			<tr>
				<td>&nbsp;</td><td><input type="submit" value="Save"></input></td>
			</tr>
			</table>
			</form:form>
		</div>
		<div class="entry">
				<h3>Installable Units</h3>
				<p>
					<table border="0">
				<form name="addIU" onSubmit="return false;">
					<tr>
					<td>Add new UI:</td><td><input type="text" value="" class="adminForm" name="name"></input></td>
					<td><input type="button" value="Create" onClick="javascript:MarketManager.createIu(document.addIU.name.value, document.addIU.refNodeId.value, handleIuCreation);"></input></td>
					<input type="hidden" name="refNodeId" value="${nodeId}" ></input>
					</tr>
				</form>
				<c:if test="${fn:length(ius) > 0}">
				<form name="deleteIU" onSubmit="return false;">
					<tr>
					<td>Delete UI: </td><td><select class="adminForm" name="iu">
						<c:forEach var="item" items="${ius}">
						<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select>
					</td>
					<td><input type="button" value="Delete" onClick="handleIuDeleteClicked(document.deleteIU.iu.value)"></input></td>
					</tr>
				</form>
				</c:if>
				</table>
				</p>
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
