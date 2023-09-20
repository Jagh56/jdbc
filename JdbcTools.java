package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {

	private static String urldb;
	private static String userdb;
	private static String passworddb;

	private static Connection connection = null;

	public static Connection getConnection() throws SQLException {
		// Charger le pilote
		if(connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String jdbcUrl = "jdbc:mysql://localhost:3306/jdbc"; // URL de la base de données (changez-la en fonction de votre configuration)
				String user = "root"; // Nom d'utilisateur MySQL
				String password = ""; // Mot de passe MySQL
				connection = DriverManager.getConnection(jdbcUrl, user, password);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}		
		return connection;
	}
}
