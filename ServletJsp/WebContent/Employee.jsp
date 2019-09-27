<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.pojo.Employee"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
th {
	text-align: center;
}
</style>
</head>
<body>
	<a href="AddEmployee.jsp"> Add Employees</a>
	<br>
	<center>
		<h2>Employee Info</h2>
		<%
			session = request.getSession();
			List<Employee> empList = (List<Employee>) session.getAttribute("empList");
		%>
		<table border="2">
		<tr>
			<th>Employee ID</th>
			<th>Employee name</th>
			<th>Department Id</th>
			<th>Location</th>
			<th>Contact</th>
			<th colspan="2">Action</th>
				<%
					for (Employee e : empList) {
				%>
			
			<tr>
				<td>
					<%
						out.print(e.getEmpId());
					%>

				</td>
				<td>
					<%
						out.print(e.getEmpName());
					%>
				</td>
				<td>
					<%
						out.print(e.getDeptId());
					%>
				</td>
				<td>
					<%
						out.print(e.getLocation());
					%>
				</td>
				<td>
					<%
						out.print(e.getContact());
					%>
				</td>
				<td><a
					href="employeeServlet?action=update&empId=<%=e.getEmpId()%>">Update</a></td>
				<td><a
					href="employeeServlet?action=delete&empId=<%=e.getEmpId()%>">Delete</a></td>
			</tr>

			<%
				}
			%>
		</table>
	</center>
</body>
</html>