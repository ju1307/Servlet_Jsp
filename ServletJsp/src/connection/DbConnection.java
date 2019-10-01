package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author j.utekar
 */

public class DbConnection {

	static Connection con = null;

	private DbConnection() {
	}

	//getting database connection
	public static Connection getconnection() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");					//jdbc driver class
		final String url = "jdbc:mysql://127.0.0.1:3306/test";	//database url
		final String name = "root";								//database user name
		final String pswd = "";									//database password
		DbConnection.con = DriverManager.getConnection(url, name, pswd);	//getting connection object
		return DbConnection.con;
	}
}
