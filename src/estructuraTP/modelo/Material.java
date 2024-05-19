package estructuraTP.modelo;


public abstract class Material  {
	private int nro;
	private String titulo;
	private String categoria;
	private String descripcion;
	private String fuente;
	
	private String enlace;

	public Material(int nro, String titulo, String categoria, String descripcion, String fuente, String enlace) {
		this.nro = nro;
		this.titulo = titulo;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fuente = fuente;
		this.enlace = enlace;
	}

	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public abstract boolean esPrioritario();
	
}
