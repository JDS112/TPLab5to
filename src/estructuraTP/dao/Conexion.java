package estructuraTP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	protected Connection conectar() {
		String url = "jdbc:mysql://localhost:3306/bdlaboratorio";
		String usr = "root";
		String pass = "admin123";
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, usr, pass);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return c;

	}
}
