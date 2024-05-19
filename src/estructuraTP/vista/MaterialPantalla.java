package estructuraTP.vista;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.MaterialDAO;
import estructuraTP.dao.PropuestaDAO;
import estructuraTP.modelo.Material;
import estructuraTP.modelo.MaterialInstitucional;
import estructuraTP.modelo.MaterialporPropuesta;
import estructuraTP.modelo.Propuesta;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MaterialPantalla extends JPanel {
	private ArrayList<Material> Materiales;
	private ArrayList<MaterialInstitucional> materialesIns;
	private ArrayList<MaterialporPropuesta> materialesmp;
	private JTable table;
	/**
	 * Create the panel.
	 */
	public MaterialPantalla() {
		setSize(new Dimension(800, 600));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 183, 678, 378);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(59, 183, 678, 378);
		
		scrollPane.setViewportView(table);
		
		DefaultTableModel dataModel = new DefaultTableModel(new Object[][] {}, new String [] {"NroMaterial", "Titulo","Categoria","Descripcion","Fuente", "Enlace"});
		DefaultTableModel dataModelMp = new DefaultTableModel(new Object[][] {}, new String [] {"NroMaterial", "Titulo","Categoria","Descripcion","Fuente", "Enlace", "Prioridad"});
		DefaultTableModel dataModelMi = new DefaultTableModel(new Object[][] {}, new String [] {"NroMaterial", "Titulo","Categoria","Descripcion","Fuente", "Enlace","Procedencia", "Prioridad"});
		JComboBox<String> filtro = new JComboBox<String>();
		filtro.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (filtro.getItemAt(filtro.getSelectedIndex()).equals("Mostrar todos los materiales")) {
					table.setModel(dataModel);
					cargarTabla(dataModel);
					System.out.println("SELECTED"+e.getItem());
				}
				else {
					if (filtro.getItemAt(filtro.getSelectedIndex()).equals("Solo Materiales Institucionales")) {
						table.setModel(dataModelMi);
						cargarTablaMi(dataModelMi);
					}
					else {
						table.setModel(dataModelMp);
						cargarTablaMp(dataModelMp);
					}
				}
			
			}
		});
		
		filtro.setBounds(616, 118, 121, 37);
		filtro.addItem("Mostrar todos los materiales");
		filtro.addItem("Solo Materiales por Propuestas");
		filtro.addItem("Solo Materiales Institucionales");
		
		add(filtro);
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new MaterialAM());
				marco.validate();
			}
		});
		btnNuevo.setBounds(59, 118, 121, 37);
		add(btnNuevo);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filtro.getItemAt(filtro.getSelectedIndex()).equals("Solo Materiales Institucionales")) {
					MaterialInstitucional m = obtenerMaterialSeleccionado();
					JFrame marco = 
							(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
					marco.setContentPane(new MaterialAM(m));
					marco.validate();
				}
				else {
					if(filtro.getItemAt(filtro.getSelectedIndex()).equals("Solo Materiales por Propuestas")) {
						MaterialporPropuesta m = obtenerMpSeleccionado();
						JFrame marco = 
								(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
						marco.setContentPane(new MaterialAM(m));
						marco.validate();
					}
				}
			}
		});
		btnActualizar.setBounds(440, 118, 106, 37);
		add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filtro.getItemAt(filtro.getSelectedIndex()).equals("Solo Materiales Institucionales")) {
					MaterialInstitucional m = obtenerMaterialSeleccionado();
					MaterialDAO pDAO = new MaterialDAO();
					pDAO.eliminar(m.getNro());
					int fila= table.getSelectedRow();
					dataModelMi.removeRow(fila);
				}
				else {
					if(filtro.getItemAt(filtro.getSelectedIndex()).equals("Solo Materiales por Propuestas")) {
						MaterialporPropuesta m = obtenerMpSeleccionado();
						MaterialDAO pDAO = new MaterialDAO();
						pDAO.eliminar(m.getNro());
						int fila= table.getSelectedRow();
						dataModelMp.removeRow(fila);
					}
				}
			}
		});
		btnEliminar.setBounds(250, 118, 106, 37);
		add(btnEliminar);
		
		
		
		JLabel lblNewLabel = new JLabel("Materiales");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(293, 27, 144, 37);
		add(lblNewLabel);
		
		
		
		
		
		
		
		
	}
	private void cargarTabla(DefaultTableModel tabla) {
		try {
			tabla.setRowCount(0);
			MaterialDAO mDao = new MaterialDAO();
			
			 materialesIns = mDao.mostrar();
			 materialesmp = mDao.mostrarMp();
			 Materiales = new ArrayList<Material>();
			 Materiales.addAll(materialesIns);
			 Materiales.addAll(materialesmp);
			for (Material m: Materiales) {
				tabla.addRow(new Object[] {m.getNro(), m.getTitulo(), m.getCategoria(), m.getDescripcion(),m.getFuente(),m.getEnlace()});
			}
			
			
		}catch(Exception sqle) {
			JOptionPane.showMessageDialog(null, "Error al llenar JTable "+sqle);
		}
	}
	private void cargarTablaMp(DefaultTableModel tabla) {
		try {
			tabla.setRowCount(0);
			MaterialDAO mDao = new MaterialDAO();
			 materialesmp = mDao.mostrarMp();
			for (MaterialporPropuesta m: materialesmp) {
				tabla.addRow(new Object[] {m.getNro(), m.getTitulo(), m.getCategoria(), m.getDescripcion(),m.getFuente(),m.getEnlace(),m.esPrioritario()});
			}
			
			
		}catch(Exception sqle) {
			JOptionPane.showMessageDialog(null, "Error al llenar JTable "+sqle);
		}
	}
	private void cargarTablaMi(DefaultTableModel tabla) {
		try {
			tabla.setRowCount(0);
			MaterialDAO mDao = new MaterialDAO();
			 materialesIns = mDao.mostrar();
			for (MaterialInstitucional m: materialesIns) {
				tabla.addRow(new Object[] {m.getNro(), m.getTitulo(), m.getCategoria(), m.getDescripcion(),m.getFuente(),m.getEnlace(),m.getProcedencia(),m.isPrioritario()});
			}
			
			
		}catch(Exception sqle) {
			JOptionPane.showMessageDialog(null, "Error al llenar JTable "+sqle);
		}
	}
	private MaterialInstitucional obtenerMaterialSeleccionado() {
		// TODO Mejorar (evitar relacionar el índice de la tabla con el índice del ArrayList)
		int filaSeleccionada = table.getSelectedRow();
		return materialesIns.get(filaSeleccionada);
	}
	private MaterialporPropuesta obtenerMpSeleccionado() {
		int filaSeleccionada = table.getSelectedRow();
		return materialesmp.get(filaSeleccionada);
	}
}
