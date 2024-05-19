package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Jornada;

public class JornadaDAO extends Conexion{
	public boolean agregar(Jornada j) {
		Connection c = null;
		int filasafectadas = 0;
		try {
	   		 c = conectar();
	   		 String sql = "INSERT INTO jornada (NroJornada, RI, objetivo, titulo) VALUES (?,?,?,?)";
	   		 PreparedStatement pStmt = c.prepareStatement(sql);
	   		 pStmt.setInt(1, j.getNro());
	   		 pStmt.setString(2, j.getRI());
	   		 pStmt.setString(3, j.getObjetivo());
	   		 pStmt.setString(4, j.getTitulo());
	   		 filasafectadas = pStmt.executeUpdate();
	   	 } catch (SQLException e) {
	   		 // TODO Auto-generated catch block
	   		 e.printStackTrace();
	   	 }
	   	 return filasafectadas != 0;
	}
	public boolean modificar(Jornada j, int nro, String titulo) {
		Connection c= null;
		int filasafectadas = 0;
		try {
	   		 c = conectar();
	   		 String sql = "UPDATE `jornada` SET `NroJornada` = ?, `RI` = ?, `objetivo` = ?, `titulo` = ? WHERE `NroJornada` = ? AND `titulo` = ?";
	   		 PreparedStatement pStmt = c.prepareStatement(sql);
	   		 pStmt.setInt(1, j.getNro());
	   		 pStmt.setString(2, j.getRI());
	   		 pStmt.setString(3, j.getObjetivo());
	   		 pStmt.setString(4, j.getTitulo());
	   		 pStmt.setInt(5, nro);
	   		 pStmt.setString(6, titulo);
	   		 filasafectadas = pStmt.executeUpdate();
	   	 } catch (SQLException e) {
	   		 // TODO Auto-generated catch block
	   		 e.printStackTrace();
	   	 }
	   	 return filasafectadas != 0;

	}
	public boolean eliminar (Jornada j) {
		Connection c = null;
	   	 int filasafectadas = 0;
	   	 try {
	   		 c = conectar();
	   		 String sql = "DELETE FROM `jornada` WHERE NroJornada = ? AND Titulo = ?";
	   		 PreparedStatement pStmt = c.prepareStatement(sql);
	   		 pStmt.setInt(1,j.getNro());
	   		 pStmt.setString(2, j.getTitulo());
	   		 filasafectadas = pStmt.executeUpdate();
	   	 } catch (SQLException e) {
	   		 // TODO Auto-generated catch block
	   		 e.printStackTrace();
	   	 }
	   	 return filasafectadas !=0;

	}
	public ArrayList<Jornada> mostrar() {
		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
		Connection c= null;
		try {
			c = conectar();
			String sql = "SELECT `NroJornada`,`RI`,`objetivo`,`titulo` FROM `jornada`;";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int nro = rs.getInt("NroJornada");
				String RI = rs.getString("RI");
				String objetivo = rs.getString("objetivo");
				String titulo = rs.getString("titulo");
				jornadas.add(new Jornada(nro, RI, objetivo, titulo));
			}

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return jornadas;
	}
}
