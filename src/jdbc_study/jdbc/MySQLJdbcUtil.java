package jdbc_study.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLJdbcUtil {
	public static void main(String[] args) {
		try {
			Connection con = MySQLJdbcUtil.getConnection();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		try(InputStream is = ClassLoader.getSystemResourceAsStream("db.properties")) {
			Properties properties = new Properties();
			properties.load(is);
			
/*			System.out.println(properties.getProperty("user"));
			System.out.println(properties.getProperty("password"));
			System.out.println(properties.getProperty("driver"));
			System.out.println(properties.getProperty("url"));*/
			
			conn = DriverManager.getConnection(properties.getProperty("url"), properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
