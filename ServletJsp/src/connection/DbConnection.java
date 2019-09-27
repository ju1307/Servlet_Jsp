package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	static Connection con = null;

	private DbConnection() {
	}

	public static Connection getconnection() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");
		final String url = "jdbc:mysql://127.0.0.1:3306/test";
		final String name = "root";
		final String pswd = "";
		DbConnection.con = DriverManager.getConnection(url, name, pswd);
		return DbConnection.con;
	}
}
