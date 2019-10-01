package com.dao;

import java.util.List;

/**
 * @author j.utekar
 */

import com.pojo.Employee;

public interface EmployeeDao {

	boolean addEmployee(Employee employee);

	List<Employee> getEmployees();

	Employee getEmployeeById(Integer id);

	boolean updateEmployee(Employee employee);

	boolean deleteEmployee(Integer id);

}
