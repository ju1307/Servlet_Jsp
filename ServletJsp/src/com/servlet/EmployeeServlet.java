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
 * @author j.utekar
 */

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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        // get action from jsp page
        final String action = request.getParameter("action");

        // action for employee details
        if (action.equals("details")) {
            this.employeeList = this.employeeDaoImpl.getEmployees();
            this.session = request.getSession();
            // set employee list to session
            this.session.setAttribute("empList", this.employeeList);
            response.sendRedirect("Employee.jsp");
        }
        // action for employee update
        else if (action.equals("update")) {
            final int empId = Integer.parseInt(request.getParameter("empId"));
            this.employee = this.employeeDaoImpl.getEmployeeById(empId);
            final HttpSession session = request.getSession();
            // set employee object to session
            session.setAttribute("emp", this.employee);
            response.sendRedirect("updateEmployee.jsp");
        }
        // action for employee delete
        else if (action.equals("delete")) {
            final int empId = Integer.parseInt(request.getParameter("empId"));
            final boolean b = this.employeeDaoImpl.deleteEmployee(empId);
            if (b) {
                response.sendRedirect("employeeServlet?action=details");
            }
            else {
                response.sendRedirect("Error.jsp");
            }
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        // get action from jsp page
        final String action = request.getParameter("action");
        this.out = response.getWriter();

        // action for add employee
        if (action.equals("addEmployee")) {
            this.employee = new Employee();
            // getting parameters from jsp page and assigning to employee object
            this.employee.setEmpId(Integer.parseInt(request.getParameter("id")));
            this.employee.setEmpName(request.getParameter("name"));
            this.employee.setDeptId(Integer.parseInt(request.getParameter("did")));
            this.employee.setContact(request.getParameter("contact"));
            this.employee.setLocation(request.getParameter("location"));

            // check if employee with given id is already present or not
            final Employee empIsPresent = this.employeeDaoImpl.getEmployeeById(this.employee.getEmpId());

            if (empIsPresent != null) {
                // if present show alert
                this.out.println("<script type=\"text/javascript\">");
                this.out.println("alert('Employee with this id alredy exist');");
                this.out.println("</script>");
                this.requestDispatcher = request.getRequestDispatcher("Index.jsp");
                this.requestDispatcher.include(request, response);
                return;
            }

            // if not present already add employee in database
            final boolean b = this.employeeDaoImpl.addEmployee(this.employee);
            if (b) {
                this.requestDispatcher = request.getRequestDispatcher("AddEmployee.jsp");
                this.requestDispatcher.include(request, response);
                this.out.print("employee added successfully");
            }
            else {
                response.sendRedirect("error.jsp");
            }
        }

        // action for update employee
        else if (action.equals("updateEmployee")) {
            this.employee = new Employee();
            // getting parameters from jsp page and assigning to employee object
            this.employee.setEmpId(Integer.parseInt(request.getParameter("id")));
            this.employee.setEmpName(request.getParameter("name"));
            this.employee.setDeptId(Integer.parseInt(request.getParameter("did")));
            this.employee.setContact(request.getParameter("contact"));
            this.employee.setLocation(request.getParameter("location"));

            // update employee
            final boolean b = this.employeeDaoImpl.updateEmployee(this.employee);
            if (b) {
                response.sendRedirect("employeeServlet?action=details");
            }
            else {
                response.sendRedirect("error.jsp");
            }
        }
    }

}
