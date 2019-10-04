<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.pojo.Employee"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="employeeServlet?action=details"> Show Employees</a><br>
	<!--get employee object stored in session -->
	<%
		Employee employee = (Employee) session.getAttribute("emp");
	%>
	<form action="employeeServlet" method="post">
		<table>
			<tr>
				<td>Employee id:</td>
				<td><input type="text" name="id"
					value="<%=employee.getEmpId()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>Employee name:</td>
				<td><input type="text" name="name"
					value="<%=employee.getEmpName()%>"></td>
			</tr>
			<tr>
				<td>Employee price:</td>
				<td><input type="text" name="did"
					value="<%=employee.getDeptId()%>"></td>
			</tr>
			<tr>
				<td>Employee Contact:</td>
				<td><input type="text" name="contact"
					value="<%=employee.getContact()%>"></td>
			</tr>
			<tr>
				<td>Employee Location:</td>
				<td><input type="text" name="location"
					value="<%=employee.getLocation()%>"></td>
			</tr>
			<tr>
				<td><input type="submit" value="SUBMIT"></td>
				<td><input type="reset" value="RESET"></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="updateEmployee">
	</form>
</body>
 
</html>