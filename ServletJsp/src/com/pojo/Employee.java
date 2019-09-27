package com.pojo;

public class Employee {

	private Integer empId;
	private String empName;
	private Integer deptId;
	private String contact;
	private String location;

	public Employee() {
		super();
	}

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(final Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(final String empName) {
		this.empName = empName;
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(final Integer deptId) {
		this.deptId = deptId;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(final String contact) {
		this.contact = contact;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + this.empId + ", empName=" + this.empName + ", deptId=" + this.deptId + ", contact="
				+ this.contact + ", location=" + this.location + "]";
	}

}
