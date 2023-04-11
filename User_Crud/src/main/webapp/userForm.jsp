<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
<link rel="stylesheet" href="	https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
	<br>
	<div class="container col-md-5">
		<div class="container text-left">
			<a href="<%= request.getContextPath()%>/" class="btn btn-success">List Of Users</a>
		</div>
		<br>
		<div class="card">
			<div class="card-body">
				<c:if test="${user!=null }">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user==null }">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
					<c:if test="${user!=null }">
						Edit User
					</c:if>
					<c:if test="${user==null }">
						Add New User
					</c:if>
					</h2>
				</caption>
				<c:if test="${user!=null }">
					<input type="hidden" name="id" value="<c:out value='${user.id }'/>"/>
				</c:if>
				
				<fieldset class="form-group">
					<label>User Name</label>
					<input type="text" name="name" value="<c:out value='${user.name }'/>"
					class="form-control" required="required"/>
				</fieldset class="form-group">
				<fieldset>
					<label>User Email</label>
					<input type="text" name="email" value="<c:out value='${user.email }'/>" 
					class="form-control" required="required"/>
				</fieldset>
				<fieldset class="form-group">
					<label>User Country</label>
					<input type="text" name="country" value="<c:out value='${user.country }'/>" 
					class="form-control"/>
				</fieldset>
				<br>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			
		</div>
	</div>
</div>
</body>
</html>