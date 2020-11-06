import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A simple data source for getting database connections.
 */
public class SimpleDataSource {
	private static String url;
	private static String username;
	private static String password;

	/**
	 * Gets a connection to the database.
	 * 
	 * @return the database connection
	 */
	public static Connection getConnection() throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "TG04";
		String config= "?useUnicode=true&characterEncoding=utf8";
		url = server + database + config;
		username = "TG04"; 
		password = "98ffe7";
		return DriverManager.getConnection(url, username, password);
	}
}
