# Servlet_Jsp

The project is about basic understanding of how servlet, JSP and JDBC works together.
How the MVC flow goes from firstly from JSP(View) to the servlet(Controller) and then further to the JDBC (database Connection).
Basic CRUD operations are being performed on the Model Object.

The ui is build using basic html and css. One can update and use customized styling and validations.

The Database and Table structure is as follows:

Create database TestDB

CREATE TABLE `Employee_data` (
  `emp_id` int(10) unsigned NOT NULL,
  `emp_name` varchar(45) NOT NULL,
  `dept_id` int(10) NOT NULL,
  `location` varchar(45) NOT NULL,
  `contact` varchar(45) NOT NULL,
  PRIMARY KEY (`emp_id`)
)
