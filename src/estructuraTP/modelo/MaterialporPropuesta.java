package estructuraTP.modelo;

import java.util.ArrayList;

public class MaterialporPropuesta extends Material {
	private ArrayList<Propuesta> propuestasquetieneencuenta= new ArrayList<>();

	public ArrayList<Propuesta> getPropuestasquetieneencuenta() {
		return propuestasquetieneencuenta;
	}


	public void setPropuestasquetieneencuenta(ArrayList<Propuesta> propuestasquetieneencuenta) {
		this.propuestasquetieneencuenta = propuestasquetieneencuenta;
	}


	public MaterialporPropuesta(int nro, String titulo, String categoria , String descripcion, String fuente,
			String enlace) {
		super(nro, titulo, categoria, descripcion, fuente, enlace);
		// TODO Auto-generated constructor stub
	}

	
	public MaterialporPropuesta(int nro, String titulo, String categoria, String descripcion, String fuente,
			String enlace, boolean prioridad,int cantPropuesta) {
		// TODO Auto-generated constructor stub
		super(nro, titulo, categoria, descripcion, fuente, enlace);
	}


	@Override
	public boolean esPrioritario() {
		// TODO Auto-generated method stub
		return propuestasquetieneencuenta.size()>= 4;
	}

	public void agregarPropuesta(Propuesta propuesta) {
		propuestasquetieneencuenta.add(propuesta);
		
	}

}
