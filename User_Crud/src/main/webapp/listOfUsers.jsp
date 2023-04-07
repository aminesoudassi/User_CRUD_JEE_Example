<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Of Users</title>
<link rel="stylesheet" href="	https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h3 class="text-center">List Of Users</h3>
	<hr>
	<div class="container text-left">
		<a href="<%= request.getContextPath()%>/new" class="btn btn-success">Add New User</a>
	</div>
	<br>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Country</th>
				<th>Actions</th>
			</tr>
			
		</thead>
		<tbody>
			<c:forEach var="user" items="${listOfUsers}">
			<tr>
				<td><c:out value="${user.id }"/></td>
				<td><c:out value="${user.name }"/></td>
				<td><c:out value="${user.email }"/></td>
				<td><c:out value="${user.country }"/></td>
				<td><a href="edit?id=<c:out value='${user.id}'/>">Edit</a>
				<a href="delete?id=<c:out value='${user.id}'/>">Delete</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>	
</body>
</html>