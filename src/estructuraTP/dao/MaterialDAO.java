package estructuraTP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import estructuraTP.modelo.MaterialInstitucional;
import estructuraTP.modelo.MaterialporPropuesta;
import estructuraTP.modelo.Propuesta;

public class MaterialDAO extends Conexion{
	private CategoriaDAO cDao = new  CategoriaDAO();
	public boolean agregar (MaterialInstitucional m) {
		Connection c = null;
		int filasafectadas = 0;
		try {
			c = conectar();
			String sql = "INSERT INTO `material`(`id_categoria`,`nromaterial`,`titulo`,`descripcion`,`fuente`,`enlace`,`procedencia`,`prioridad`)VALUES(?,?,?,?,?,?,?,?);";
			PreparedStatement pStmt = c.prepareStatement(sql);
			int idCategoria  = cDao.traerIDCategoria(m.getCategoria());
			pStmt.setInt(1, idCategoria);
			pStmt.setInt(2,m.getNro());
			pStmt.setString(3, m.getTitulo());
			pStmt.setString(4,m.getDescripcion());
			pStmt.setString(5, m.getFuente());
			pStmt.setString(6, m.getEnlace());
			pStmt.setString(7, m.getProcedencia());
			pStmt.setBoolean(8, m.esPrioritario());
			filasafectadas = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas != 0;
	
	}
	public boolean modificar(MaterialInstitucional m, int nro) {
		Connection c = null;
		int filasafectadas = 0;
		try {
			c = conectar();
			String sql = "UPDATE `material` SET id_categoria = ?,nromaterial = ?, titulo = ?, descripcion= ?, fuente = ?, enlace= ?, procedencia = ?, prioridad = ? WHERE nromaterial = ?;";
			PreparedStatement pStmt = c.prepareStatement(sql);
			int idCategoria  = cDao.traerIDCategoria(m.getCategoria());
			pStmt.setInt(1, idCategoria);
			pStmt.setInt(2, m.getNro());
			pStmt.setString(3, m.getTitulo());
			pStmt.setString(4,m.getDescripcion());
			pStmt.setString(5, m.getFuente());
			pStmt.setString(6, m.getEnlace());
			pStmt.setString(7, m.getProcedencia());
			pStmt.setBoolean(8,m.esPrioritario());
			pStmt.setInt(9, nro);
			filasafectadas = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas != 0;
	}
	public boolean eliminar(int nro) {
		Connection c = null;
		int filasafectadas = 0;
		try {
			c = conectar();
			String sql = "DELETE FROM `material` WHERE nromaterial = ? ";
			PreparedStatement pStmt = c.prepareStatement(sql);
			pStmt.setInt(1,nro);
			filasafectadas = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas != 0;
	}
	public ArrayList<MaterialInstitucional> mostrar() {
		Connection c = null; 
		ArrayList<MaterialInstitucional> materiales = new ArrayList<MaterialInstitucional>();
		try {
			c = conectar();
			String sql = "SELECT material.nromaterial,material.titulo,categoria.nombre, material.descripcion,material.fuente,material.enlace,material.procedencia, material.prioridad FROM material INNER JOIN categoria ON material.id_categoria = categoria.Id_categoria WHERE procedencia IS NOT NULL;";
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {	
				int nro = rs.getInt("nromaterial");
				String titulo = rs.getString("titulo");
				String categoria = rs.getString("categoria.nombre");
				String descripcion = rs.getString("descripcion");
				String fuente = rs.getString("fuente");
				String enlace = rs.getString("enlace");
				String procedencia = rs.getString("procedencia");
				boolean prioridad = rs.getBoolean("prioridad");
				materiales.add(new MaterialInstitucional (nro, titulo, categoria, descripcion, fuente, enlace, procedencia, prioridad));
				
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
		return materiales;
	}
	public ArrayList<MaterialporPropuesta> mostrarMp() {
		Connection c = null; 
		ArrayList<MaterialporPropuesta> materialesMp = new ArrayList<MaterialporPropuesta>();
		try {
			c = conectar();
			String sql = "SELECT material.nromaterial,material.titulo,categoria.nombre, material.descripcion,material.fuente,material.enlace, material.prioridad,material.propuestas FROM material INNER JOIN categoria ON material.id_categoria = categoria.Id_categoria WHERE procedencia IS NULL;";
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {	
				int nro = rs.getInt("nromaterial");
				String titulo = rs.getString("titulo");
				String categoria = rs.getString("categoria.nombre");
				String descripcion = rs.getString("descripcion");
				String fuente = rs.getString("fuente");
				String enlace = rs.getString("enlace");
				boolean prioridad = rs.getBoolean("prioridad");
				int cantPropuesta = rs.getInt("propuestas");
				
				materialesMp.add(new MaterialporPropuesta (nro, titulo, categoria, descripcion, fuente, enlace, prioridad, cantPropuesta));
				
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
		return materialesMp;
	}
	public boolean agregarMp(MaterialporPropuesta m) {
		Connection c = null;
		int filasafectadas = 0;
		try {
			c = conectar();
			String sql = "INSERT INTO material (id_categoria,nromaterial, titulo, descripcion, fuente, enlace, prioridad, propuestas) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			CategoriaDAO cDao = new CategoriaDAO();
			pStmt.setInt(1, cDao.traerIDCategoria(m.getCategoria()));
			pStmt.setInt(2,m.getNro());
			pStmt.setString(3, m.getTitulo());
			pStmt.setString(4,m.getDescripcion());
			pStmt.setString(5, m.getFuente());
			pStmt.setString(6, m.getEnlace());
			pStmt.setBoolean(7,m.esPrioritario());
			pStmt.setInt(8, m.getPropuestasquetieneencuenta().size());
			filasafectadas = pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			int materialId = 0;
			if (rs.next()) {
			    materialId = rs.getInt(1);
			}
			for (Propuesta p : m.getPropuestasquetieneencuenta()) {
				guardarPropuestaPorMaterial(materialId, p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas != 0;
	
	}
	private boolean guardarPropuestaPorMaterial(int materialId, Propuesta p) {
		//Busca id de propuesta por titulo, como hicimos con id de categoria por nombre.
		int filasafectadas =0;
		Connection c = null;
		PropuestaDAO pDao = new PropuestaDAO ();
		int idPropuesta =0;
		try {
			c = conectar();
			String sql = "INSERT INTO materialpropuesta (idPropuesta, idmaterial) VALUES(?,?);";
			PreparedStatement pStmt = c.prepareStatement(sql);
			idPropuesta = pDao.traerIDPropuesta(p.getNro(), p.getTitulo());
			pStmt.setInt(1, idPropuesta);
			pStmt.setInt(2, materialId);
			filasafectadas = pStmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//Inserto en la tabla puente materialxpropuesta ambos id
		return filasafectadas !=0;
	}
	public boolean modificarMp(MaterialporPropuesta m, int nro) {
		Connection c = null;
		int filasafectadas = 0;
		try {
			c = conectar();
			String sql = "UPDATE `material` SET id_categoria = ?,nromaterial = ?, titulo = ?, descripcion= ?, fuente = ?, enlace= ?,prioridad = ? WHERE nromaterial = ?;";
			PreparedStatement pStmt = c.prepareStatement(sql);
			int idCategoria  = cDao.traerIDCategoria(m.getCategoria());
			pStmt.setInt(1, idCategoria);
			pStmt.setInt(2, m.getNro());
			pStmt.setString(3, m.getTitulo());
			pStmt.setString(4,m.getDescripcion());
			pStmt.setString(5, m.getFuente());
			pStmt.setString(6, m.getEnlace());
			pStmt.setBoolean(7, m.esPrioritario());
			pStmt.setInt(8, nro);
			filasafectadas = pStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filasafectadas != 0;
	}
	
}
