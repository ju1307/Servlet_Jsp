package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.EmployeeDao;
import com.pojo.Employee;

import connection.DbConnection;

/**
 * @author j.utekar
 */

public class EmployeeDaoImpl implements EmployeeDao {

    private Connection con;
    private PreparedStatement pst;
    private int numberOfRows;
    private ResultSet resultSet;
    private List<Employee> empList;

    @Override
    public boolean addEmployee(final Employee employee) {
        try {
            // establish connection with database
            this.con = DbConnection.getconnection();

            // prepare sql query and getting PreparedStatement object
            this.pst = this.con.prepareStatement("insert into EMPLOYEE_DATA values(?,?,?,?,?)");

            // setting query parameters
            this.pst.setInt(1, employee.getEmpId());
            this.pst.setString(2, employee.getEmpName());
            this.pst.setInt(3, employee.getDeptId());
            this.pst.setString(4, employee.getContact());
            this.pst.setString(5, employee.getLocation());

            // executing query
            this.numberOfRows = this.pst.executeUpdate();
        }
        catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        finally {
            try {
                // close PreparedStatement object
                this.pst.close();
                // close Connection object
                this.con.close();
            }
            catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return this.numberOfRows == 1 ? Boolean.TRUE : Boolean.FALSE;

    }

    @Override
    public List<Employee> getEmployees() {
        try {
            this.con = DbConnection.getconnection();
            this.pst = this.con.prepareStatement("select * from EMPLOYEE_DATA");

            // extracting result set
            this.resultSet = this.pst.executeQuery();
            this.empList = new ArrayList<>();

            // iterating the result set and storing in employee list
            while (this.resultSet.next()) {
                final Employee employee = new Employee();
                employee.setEmpId(this.resultSet.getInt(1));
                employee.setEmpName(this.resultSet.getString(2));
                employee.setDeptId(this.resultSet.getInt(3));
                employee.setContact(this.resultSet.getString(4));
                employee.setLocation(this.resultSet.getString(5));
                this.empList.add(employee);
            }

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                this.pst.close();
                this.con.close();
            }
            catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return this.empList;
    }

    @Override
    public Employee getEmployeeById(final Integer empId) {
        Employee employee = null;
        try {
            this.con = DbConnection.getconnection();
            this.pst = this.con.prepareStatement("select * from EMPLOYEE_DATA where emp_Id=?");
            this.pst.setInt(1, empId);
            this.resultSet = this.pst.executeQuery();
            while (this.resultSet.next()) {
                employee = new Employee();
                employee.setEmpId(this.resultSet.getInt(1));
                employee.setEmpName(this.resultSet.getString(2));
                employee.setDeptId(this.resultSet.getInt(3));
                employee.setContact(this.resultSet.getString(4));
                employee.setLocation(this.resultSet.getString(5));
            }

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                this.pst.close();
                this.con.close();
            }
            catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    @Override
    public boolean updateEmployee(final Employee employee) {
        try {
            this.con = DbConnection.getconnection();
            this.pst = this.con.prepareStatement("update EMPLOYEE_DATA set emp_Name=?,dept_Id=?,contact=?,location=? where emp_Id=?");
            this.pst.setString(1, employee.getEmpName());
            this.pst.setInt(2, employee.getDeptId());
            this.pst.setString(3, employee.getContact());
            this.pst.setString(4, employee.getLocation());
            this.pst.setInt(5, employee.getEmpId());
            this.numberOfRows = this.pst.executeUpdate();

        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                this.pst.close();
                this.con.close();
            }
            catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return this.numberOfRows == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean deleteEmployee(final Integer empId) {
        try {
            this.con = DbConnection.getconnection();
            this.pst = this.con.prepareStatement("delete from EMPLOYEE_DATA where emp_Id=?");
            this.pst.setInt(1, empId);
            this.numberOfRows = this.pst.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                this.pst.close();
                this.con.close();
            }
            catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return this.numberOfRows == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

}
