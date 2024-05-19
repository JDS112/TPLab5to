package estructuraTP.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Propuesta;

public class PropuestaDAO extends Conexion {
	private CategoriaDAO cDao = new CategoriaDAO();

	public boolean agregar(Propuesta p) {
		Connection c = null;
		int filasafectadas = 0;
		try {
			c = conectar();
			String sql = "INSERT INTO `propuesta`(`idcategoria`,`NroPropuesta`,`Origen`,`Autor`,`Fecha`,`Titulo`,`brevedescripcion`,`motivacion`,`estado`)VALUES(?,?,?,?,?,?,?,?,?);";
			PreparedStatement pStmt = c.prepareStatement(sql);
			int idcategoria = cDao.traerIDCategoria(p.getCategoria());
			pStmt.setInt(1, idcategoria);
			pStmt.setInt(2,p.getNro());
			pStmt.setString(3, p.getOrigen());
			pStmt.setString(4, p.getAutor());
			pStmt.setDate(5, convert(p.getFecha()));
			pStmt.setString(6, p.getTitulo());
			pStmt.setString(7, p.getBrevedescripcion());
			pStmt.setString(8, p.getMotivacion());
			pStmt.setString(9, p.getEstado());
			filasafectadas = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas != 0;
	}

	public boolean modificar(Propuesta p, int nro, String titulo) {
		Connection c = null;
		int filasafectadas =0;
		try {
			c = conectar();
			String sql = "UPDATE `propuesta` SET `idcategoria` = ?,`NroPropuesta` = ?, `Origen` = ?, `Autor` = ?, `Fecha` = ?, `Titulo` = ?, `brevedescripcion` = ?, `motivacion` = ? WHERE `NroPropuesta` = ? AND Titulo = ?";
			PreparedStatement pStmt = c.prepareStatement(sql);
			int idcategoria = cDao.traerIDCategoria(p.getCategoria());
			pStmt.setInt(1,idcategoria);
			pStmt.setInt(2, p.getNro());
			pStmt.setString(3, p.getOrigen());
			pStmt.setString(4, p.getAutor());
			pStmt.setDate(5, convert(p.getFecha()));
			pStmt.setString(6, p.getTitulo());
			pStmt.setString(7, p.getBrevedescripcion());
			pStmt.setString(8, p.getMotivacion());
			pStmt.setInt(9,nro);
			pStmt.setString(10,titulo);
			filasafectadas = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas != 0;
	}
	public boolean eliminar (Propuesta p) {
		Connection c = null;
		int filasafectadas = 0;
		try {
			c = conectar();
			String sql = "DELETE FROM `propuesta` WHERE NroPropuesta = ? AND Titulo=? ";
			PreparedStatement pStmt = c.prepareStatement(sql);
			pStmt.setInt(1,p.getNro());
			pStmt.setString(2,p.getTitulo());
			filasafectadas = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas !=0;
	}
	public ArrayList<Propuesta> traerTodas() {
		ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT propuesta.NroPropuesta, propuesta.Origen, propuesta.Autor, categoria.nombre, propuesta.Fecha, propuesta.Titulo, propuesta.brevedescripcion, propuesta.motivacion, propuesta.motivo, propuesta.estado FROM propuesta INNER JOIN categoria ON propuesta.idcategoria = categoria.Id_categoria;";
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int nro = rs.getInt("NroPropuesta");
				String origen = rs.getString("Origen");
				String autor = rs.getString("Autor");
				String categoria = rs.getString("categoria.nombre");
				Date fecha = rs.getDate("Fecha");
				String titulo = rs.getString("Titulo");
				String brevedescripcion = rs.getString("brevedescripcion");
				String motivacion = rs.getString("motivacion");
				String motivo = rs.getString("motivo");
				String estado = rs.getString("estado");
				propuestas.add(new Propuesta(nro ,origen,categoria,autor, fecha, titulo, brevedescripcion, motivacion, motivo, estado));
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
		return propuestas;
		
	}
	public boolean modificarestado(Propuesta p) {
		Connection c = null;
		int filasafectadas =0;
		try {
			c = conectar();
			String sql = "UPDATE `propuesta` SET `estado`= ? WHERE `NroPropuesta` = ? AND `Titulo` = ? ;";
			PreparedStatement pStmt = c.prepareStatement(sql);
			pStmt.setString(1, p.getEstado());
			pStmt.setInt(2, p.getNro());
			pStmt.setString(3,p.getTitulo());
			
			filasafectadas = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas != 0;
	}
	public boolean modificarEstadoMotivo(Propuesta p) {
		Connection c = null;
		int filasafectadas =0;
		try {
			c = conectar();
			String sql = "UPDATE `propuesta` SET `motivo`= ?, `estado` = ? WHERE `NroPropuesta` = ? AND `Titulo` = ? ;";
			PreparedStatement pStmt = c.prepareStatement(sql);
			pStmt.setString(1, p.getMotivo());
			pStmt.setString(2, p.getEstado());
			pStmt.setInt(3, p.getNro());
			pStmt.setString(4,p.getTitulo());
			
			filasafectadas = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas != 0;
	}
	public int traerIDPropuesta(int nro, String titulo) {
		Connection c = null;
		int idpropuesta = 0;
		try {
			c = conectar();
			String sql = "SELECT idPropuesta FROM propuesta WHERE NroPropuesta = ? AND Titulo = ?";
			PreparedStatement pStmt= c.prepareStatement(sql);
			pStmt.setInt(1, nro);
			pStmt.setString(2, titulo);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				idpropuesta = rs.getInt("idPropuesta");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return idpropuesta;
	}
	
	public java.sql.Date convert(java.util.Date d){
		java.sql.Date fechasql = new java.sql.Date(d.getTime());
		return fechasql;
	}
}
