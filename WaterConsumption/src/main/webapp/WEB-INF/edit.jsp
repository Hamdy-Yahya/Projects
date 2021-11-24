<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Edit</title>
</head>
<body>
	<div class="position-relative">
	  <div class="position-absolute top-0 start-0"><a href="/home" type="button" class="btn btn-primary">Back to Home</a></div>
	  <div class="position-absolute top-0 end-0"><a href="/logout" type="button" class="btn btn-primary btn-sm">Logout</a></div>
	</div>
	<div>
	<h1>Edit a water task</h1>
	</div>
	<form:form action="/update/${editTask.getId()}" method="post" modelAttribute="editTask">
		<form:errors path="*"/>
		<p>Task:
			<form:select path="task">
				<form:option value="Shower">Shower</form:option>
				<form:option value="Bath">Bath</form:option>
				<form:option value="Toilet">Toilet</form:option>
				<form:option value="Dishes by Hand">Dishes (by hand)</form:option>
				<form:option value="Dishes by Machine">Dishes (by machine)</form:option>
				<form:option value="Laundry">Laundry</form:option>
				<form:option value="Car Wash">Car Wash</form:option>
				<form:option value="Watered Lawn">Watered Lawn</form:option>
			</form:select>
		</p>
		<p>
			<form:label path="time">Time:</form:label>
			<form:input path="time" type="number" min="0"/>
		</p>
		<p>Month:
			<form:select path="month">
				<form:option value="January">January</form:option>
				<form:option value="February">February</form:option>
				<form:option value="March">March</form:option>
				<form:option value="April">April</form:option>
				<form:option value="May">May</form:option>
				<form:option value="June">June</form:option>
				<form:option value="July">July</form:option>
				<form:option value="August">August</form:option>
				<form:option value="September">September</form:option>
				<form:option value="October">October</form:option>
				<form:option value="November">November</form:option>
				<form:option value="December">December</form:option>
			</form:select>
			Day:
			<form:input type="number" min="1" max="31" path="day"/>
			Year:
			<form:input type="number" path="year"/>
		</p>
		<input type="submit" value="Create"/>
	</form:form>
</body>
</html>