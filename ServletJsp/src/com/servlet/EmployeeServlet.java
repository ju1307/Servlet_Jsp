package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.impl.EmployeeDaoImpl;
import com.pojo.Employee;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/employeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
	private Employee employee;
	private RequestDispatcher requestDispatcher;
	private List<Employee> employeeList;
	private HttpSession session;
	private PrintWriter out;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final String action = request.getParameter("action");

		if (action.equals("details")) {
			this.employeeList = this.employeeDaoImpl.getEmployees();
			this.session = request.getSession();
			this.session.setAttribute("empList", this.employeeList);
			response.sendRedirect("Employee.jsp");
		} else if (action.equals("update")) {
			final int empId = Integer.parseInt(request.getParameter("empId"));
			this.employee = this.employeeDaoImpl.getEmployeeById(empId);
			final HttpSession session = request.getSession();
			session.setAttribute("emp", this.employee);
			response.sendRedirect("updateEmployee.jsp");
		} else if (action.equals("delete")) {
			final int empId = Integer.parseInt(request.getParameter("empId"));
			final boolean b = this.employeeDaoImpl.deleteEmployee(empId);
			if (b) {
				response.sendRedirect("employeeServlet?action=details");
			} else {
				response.sendRedirect("Error.jsp");
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {

		final String action = request.getParameter("action");
		this.out = response.getWriter();

		if (action.equals("addEmployee")) {
			this.employee = new Employee();
			this.employee.setEmpId(Integer.parseInt(request.getParameter("id")));
			this.employee.setEmpName(request.getParameter("name"));
			this.employee.setDeptId(Integer.parseInt(request.getParameter("did")));
			this.employee.setContact(request.getParameter("contact"));
			this.employee.setLocation(request.getParameter("location"));

			final Employee empIsPresent = this.employeeDaoImpl.getEmployeeById(this.employee.getEmpId());

			if (empIsPresent != null) {
				this.out.println("<script type=\"text/javascript\">");
				this.out.println("alert('Employee with this id alredy exist');");
				this.out.println("</script>");
				this.requestDispatcher = request.getRequestDispatcher("Index.jsp");
				this.requestDispatcher.include(request, response);
				return;
			}

			final boolean b = this.employeeDaoImpl.addEmployee(this.employee);
			if (b) {
				this.requestDispatcher = request.getRequestDispatcher("AddEmployee.jsp");
				this.requestDispatcher.include(request, response);
				this.out.print("employee added successfully");
			} else {
				response.sendRedirect("error.jsp");
			}
		}

		else if (action.equals("updateEmployee")) {
			this.employee = new Employee();
			this.employee.setEmpId(Integer.parseInt(request.getParameter("id")));
			this.employee.setEmpName(request.getParameter("name"));
			this.employee.setDeptId(Integer.parseInt(request.getParameter("did")));
			this.employee.setContact(request.getParameter("contact"));
			this.employee.setLocation(request.getParameter("location"));

			final boolean b = this.employeeDaoImpl.updateEmployee(this.employee);
			if (b) {
				response.sendRedirect("employeeServlet?action=details");
			} else {
				response.sendRedirect("error.jsp");
			}
		}
	}

}
