package estructuraTP.modelo;

public class MaterialInstitucional extends Material {
	
	private String Procedencia;
	private boolean Prioritario;
	
	public MaterialInstitucional(int nro, String titulo, String categoria, String descripcion, String fuente,
			String enlace,String procedencia,boolean prioridad) {
		super(nro, titulo, categoria, descripcion, fuente, enlace);
		this.Procedencia = procedencia;
		this.Prioritario = prioridad;
		// TODO Auto-generated constructor stub
	}
	public boolean isPrioritario() {
		return Prioritario;
	}
	public void setPrioritario(boolean prioritario) {
		Prioritario = prioritario;
	}
	public String getProcedencia() {
		return Procedencia;
	}
	public void setProcedencia(String procedencia) {
		Procedencia = procedencia;
	}

	@Override
	public boolean esPrioritario() {
		return Prioritario == true;
	}

}
