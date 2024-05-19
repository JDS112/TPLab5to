package estructuraTP.modelo;

import java.util.ArrayList;

public class Jornada {
	private int Nro;
	private String RI;
	private String Objetivo;
	private String Titulo;
	private ArrayList<Material> materiales = new ArrayList<Material>();
	
	public Jornada(int nro, String ri, String objetivo, String titulo) {
		super();
		Nro = nro;
		RI = ri;
		Objetivo = objetivo;
		Titulo = titulo;
	}

	public boolean esPrioritario() {
		ArrayList<Material> materialesprioritarios = new ArrayList<Material>();
		for(Material m : materiales) {
			if (m.esPrioritario()) {
				materialesprioritarios.add(m);
			}
		}
		return materialesprioritarios.size()>= materiales.size()/2 ;
	}

	public int getNro() {
		return Nro;
	}
	public void setNro(int nro) {
		Nro = nro;
	}
	public String getRI() {
		return RI;
	}
	public void setRI(String rI) {
		RI = rI;
	}
	
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public ArrayList<Material> getMateriales() {
		return materiales;
	}
	public void setMateriales(ArrayList<Material> materiales) {
		this.materiales = materiales;
	}

	public String getObjetivo() {
		return Objetivo;
	}

	public void setObjetivo(String objetivo) {
		Objetivo = objetivo;
	}
	
}
