package biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection(String nomPilote, String URLBD, String authorizationID, String password)
			throws ClassNotFoundException, SQLException {
		try {
			Class.forName(nomPilote);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver non present dans le classpath  -  " + e.getMessage());
			System.exit(1);
		}
		return DriverManager.getConnection(URLBD, authorizationID, password);
	}

	public Connection getConnectionSansAutoCommit(String nomPilote, String URLBD, String authorizationID,
			String password) throws ClassNotFoundException, SQLException {
		try {
			Class.forName(nomPilote);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver non present dans le classpath  -  " + e.getMessage());
			System.exit(1);
		}
		Connection cnx = DriverManager.getConnection(URLBD, authorizationID, password);
		cnx.setAutoCommit(false);
		return cnx;
	}

}