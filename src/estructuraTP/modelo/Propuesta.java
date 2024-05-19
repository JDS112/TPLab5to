package estructuraTP.modelo;

import java.util.Date;

public class Propuesta {
	private int nro;
	private String origen;
	private String categoria;
	private String autor;
	private Date fecha;
	private String titulo;
	private String brevedescripcion;
	private String motivacion;

	private String motivo;
	private String Estado;
	public Propuesta(int nro,String origen, String categoria, String autor, Date fecha, String titulo, String brevedescripcion,
			String motivacion, String motivo, String Estado) {
		this.nro = nro;
		this.origen = origen;
		this.categoria = categoria;
		this.autor = autor;
		this.fecha = fecha;
		this.titulo = titulo;
		this.brevedescripcion = brevedescripcion;
		this.motivacion = motivacion;
		this.motivo = motivo;
		this.Estado = Estado;
	}

	public Propuesta(int nro2, String origen2, String categoria2, String autor2, Date fecha2, String titulo2,
			String descripcion, String motivacion2) {
		// TODO Auto-generated constructor stub
		this.nro = nro2;
		this.origen = origen2;
		this.categoria = categoria2;
		this.autor = autor2;
		this.fecha = fecha2;
		this.titulo = titulo2;
		this.brevedescripcion = descripcion;
		this.motivacion = motivacion2;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {	
		this.origen = origen;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getBrevedescripcion() {
		return brevedescripcion;
	}

	public void setBrevedescripcion(String brevedescripcion) {
		this.brevedescripcion = brevedescripcion;
	}

	public String getMotivacion() {
		return motivacion;
	}

	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}
	
	
}
