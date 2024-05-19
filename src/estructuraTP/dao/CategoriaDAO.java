package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriaDAO extends Conexion{
	public ArrayList<String> traercategorias(){
		ArrayList<String> categorias = new ArrayList<>();
		Connection c = null;
		
		try {
			c = conectar();
			String sql = "SELECT nombre FROM categoria";
			Statement stmt;
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String nomcategoria= rs.getString("nombre");
				categorias.add(nomcategoria);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return categorias;
		
	}
	
	public int traerIDCategoria(String nombre) {
		int id = 0;
		Connection c = null;
		try {
			c = conectar();
			String sql  = "SELECT Id_categoria FROM categoria WHERE nombre = ?;";
			PreparedStatement pStmt = c.prepareStatement(sql);
			pStmt.setString(1, nombre);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				id = rs.getInt("Id_categoria");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
}
