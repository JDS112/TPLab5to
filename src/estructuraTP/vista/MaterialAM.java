package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import estructuraTP.dao.CategoriaDAO;
import estructuraTP.dao.MaterialDAO;
import estructuraTP.dao.PropuestaDAO;

import estructuraTP.modelo.MaterialInstitucional;
import estructuraTP.modelo.MaterialporPropuesta;
import estructuraTP.modelo.Propuesta;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

public class MaterialAM extends JPanel {
	private JTextField textNroMaterial;
	private JTextField textTitulo;
	private JTextField textDescripcion;
	private JTextField textFuente;
	private JTextField textEnlace;
	private JTextField textProcedencia;
	private MaterialInstitucional material;
	private MaterialporPropuesta mp;
	private ArrayList<Propuesta> todasLasPropuestas;
	

	/**
	 * Create the panel.
	 */
	public MaterialAM() {
		setSize(new Dimension(800, 600));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nro Material");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(75, 166, 83, 20);
		add(lblNewLabel);
		
		textNroMaterial = new JTextField();
		textNroMaterial.setBounds(169, 165, 73, 26);
		add(textNroMaterial);
		textNroMaterial.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(75, 230, 46, 14);
		add(lblNewLabel_1);
		
		textTitulo = new JTextField();
		textTitulo.setBounds(169, 226, 115, 26);
		add(textTitulo);
		textTitulo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Categoria");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(75, 286, 83, 20);
		add(lblNewLabel_2);
		
		
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		DefaultListModel<String> jl = new DefaultListModel<String>();
		comboBox.addItemListener(new ItemListener() {
			

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
					System.out.println("SELECTED "+e.getItem());
					PropuestaDAO pDAO = new PropuestaDAO();
					todasLasPropuestas = pDAO.traerTodas();
					for(Propuesta p: todasLasPropuestas) {
						String categoria = comboBox.getItemAt(comboBox.getSelectedIndex());
						if(p.getCategoria().equals(categoria)) {
								jl.addElement(p.getTitulo());
						}
					}
				}
				else {
					System.out.println("DESELECTED "+e.getItem());
					jl.clear();
				}
				
			}
		});
		
		CategoriaDAO cDAO = new CategoriaDAO();
	   	ArrayList<String> categorias = new ArrayList<String>();
	   	categorias = cDAO.traercategorias();
	   	 for(String item : categorias) {
	   		 comboBox.addItem(item);
	   	 }

		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(169, 283, 115, 26);
		add(comboBox);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Prioridad");
		chckbxNewCheckBox.setBounds(383, 408, 97, 23);
		add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_3 = new JLabel("Descripcion");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(383, 167, 80, 18);
		add(lblNewLabel_3);
		
		textDescripcion = new JTextField();
		textDescripcion.setBounds(473, 165, 101, 26);
		add(textDescripcion);
		textDescripcion.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Fuente");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(383, 229, 46, 16);
		add(lblNewLabel_4);
		
		textFuente = new JTextField();
		textFuente.setBounds(473, 226, 101, 26);
		add(textFuente);
		textFuente.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Enlace");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(383, 289, 46, 14);
		add(lblNewLabel_5);
		
		textEnlace = new JTextField();
		textEnlace.setBounds(473, 285, 101, 26);
		add(textEnlace);
		textEnlace.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Procedencia");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(383, 354, 80, 14);
		add(lblNewLabel_6);
		
		textProcedencia = new JTextField();
		textProcedencia.setBounds(473, 350, 101, 26);
		add(textProcedencia);
		textProcedencia.setColumns(10);
		JList<String> list = new JList<String>(jl);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setVisible(false);
		list.setBounds(169, 355, 115, 104);
		add(list);
		JComboBox<String> cb = new JComboBox<String>();
		cb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
					if (cb.getItemAt(cb.getSelectedIndex()).equals("Material Institucional")) {
						list.setVisible(false);
						chckbxNewCheckBox.setVisible(true);
						textProcedencia.setVisible(true);
						lblNewLabel_6.setVisible(true);
						System.out.println("SELECTED "+e.getItem());
					}else {
						list.setVisible(true);
						chckbxNewCheckBox.setVisible(false);
						textProcedencia.setVisible(false);
						lblNewLabel_6.setVisible(false);
						System.out.println("DESELECTED "+e.getItem());
					}
			}
		});
		cb.setBounds(75, 79, 152, 26);
		cb.addItem("Material Institucional");
		cb.addItem("Material por propuesta");
		add(cb);
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nro = Integer.parseInt(textNroMaterial.getText());
				String titulo = textTitulo.getText();
				String categoria = ""+comboBox.getItemAt(comboBox.getSelectedIndex());
				String descripcion= textDescripcion.getText();
				String fuente = textFuente.getText();
				String enlace = textEnlace.getText();
				String procedencia = textProcedencia.getText();
				boolean prioritario = chckbxNewCheckBox.isSelected();
				MaterialDAO mDAO= new MaterialDAO();
				if (cb.getItemAt(cb.getSelectedIndex()).equals("Material Institucional")){
					if(esModificacion()) {
						MaterialInstitucional mModificado =  new MaterialInstitucional(nro, titulo, categoria , descripcion, fuente, enlace, procedencia,prioritario);
						mDAO.modificar(mModificado, material.getNro());
					}
					else {
						MaterialInstitucional m =  new MaterialInstitucional(nro, titulo, categoria, descripcion, fuente, enlace, procedencia,prioritario);
						mDAO.agregar(m);
					}

				}
				else {
					if(esModificacion()) {
						MaterialporPropuesta mModificado =  new MaterialporPropuesta(nro, titulo, categoria, descripcion, fuente, enlace);
						mDAO.modificarMp(mModificado, mp.getNro());
					}
					else {
						MaterialporPropuesta m =  new MaterialporPropuesta(nro, titulo, categoria, descripcion, fuente, enlace);
						if (list.getSelectedIndex() != -1) {
							for(String tituloDePorpuestaElegida : list.getSelectedValuesList()) {
								m.agregarPropuesta(encontrarPropuestaPorTitulo(tituloDePorpuestaElegida));
							}
						}
						
						mDAO.agregarMp(m);
					}
				}
				
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new MaterialPantalla());
				marco.validate();
			}
		});
		btnGuardar.setBounds(283, 474, 146, 54);
		add(btnGuardar);
		
		JLabel lblNewLabel_7 = new JLabel("Alta Material");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_7.setBounds(268, 55, 176, 58);
		add(lblNewLabel_7);
		
		

	
		
		
	}
	private Propuesta encontrarPropuestaPorTitulo(String tituloDePropuestaElegida) {
		Propuesta propuesta = null;
		for (Propuesta p : todasLasPropuestas) {
			if (p.getTitulo().equals(tituloDePropuestaElegida)) {
				propuesta = p;
			}
		}
		return propuesta;
		
	}
	public MaterialAM(MaterialInstitucional m) {
		this();
		textNroMaterial.setText(""+m.getNro());
		textTitulo.setText(""+m.getTitulo());
		textDescripcion.setText(""+m.getDescripcion());
		textFuente.setText(""+m.getFuente());
		textEnlace.setText(""+m.getEnlace());
		textProcedencia.setText(""+m.getProcedencia());
		this.material = m;
	}
	public MaterialAM(MaterialporPropuesta m) {
		// TODO Auto-generated constructor stub
		this();
		textNroMaterial.setText(""+m.getNro());
		textTitulo.setText(""+m.getTitulo());
		textDescripcion.setText(""+m.getDescripcion());
		textFuente.setText(""+m.getFuente());
		textEnlace.setText(""+m.getEnlace());
		this.mp = m;
	}
	public boolean esModificacion() {
		return this.mp !=null || this.material != null;
	}
	
//	public int determinarnumcategoria(String categoria) {
//		int id=0;
//		switch(categoria) {
//		case "Acoso Escolar":
//			id = 1;
//			break;
//		case "Violencia":
//			id = 2;
//			break;
//		case "Discriminacion":
//			id = 3;
//			break;
//		case "Sexualidad":
//			id = 4;
//			break;
//		default:
//			JOptionPane.showMessageDialog(null, "Error la categoria no esta");
//		}
//		
//		return id;
//	}
}
