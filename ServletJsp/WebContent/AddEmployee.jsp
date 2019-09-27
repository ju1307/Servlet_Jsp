<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
th {
	text-align: center;
}

h1 {
	color: red;
	background-color: cyan;
}

table {
	background-color: lime;
}
</style>
</head>
<body>
<a href="employeeServlet?action=details"> Show Employees</a><br>
	<center>
		<h1>Employee Registration Form</h1>
		<form action="employeeServlet" method="post">
			<table border="1">
				<tr>
					<th>Employee name:</th>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<th>Employee ID:</th>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<th>Department ID:</th>
					<td><input type="text" name="did"></td>
				</tr>
				<tr>
					<th>Contact Number:</th>
					<td><input type="text" name="contact"></td>
				</tr>
				<tr>
					<th>Location:</th>
					<td><select name="location">
							<option value="Mumbai">Mumbai</option>
							<option value="Bagnlore">Bagnlore</option>
							<option value="Delhi">Delhi</option>
							<option value="Hyderabad">Hyderabad</option>
					</select></td>
				</tr>
				<tr>
					<td>
					<td><input type="submit" value="Submit"> <input
						type="hidden" name="action" value="addEmployee">
			</table>
		</form>
	</center>
</body>
</html>