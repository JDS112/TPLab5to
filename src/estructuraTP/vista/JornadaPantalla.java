package estructuraTP.vista;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.JornadaDAO;
import estructuraTP.modelo.Jornada;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class JornadaPantalla extends JPanel {
	private JTable table;
	private ArrayList<Jornada> jornadas;

	/**
	 * Create the panel.
	 */
	public JornadaPantalla() {
		setSize(new Dimension(800, 600));
		setLayout(null);
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {}, new String [] {"NroJornada", "Referente Institucional","Objetivo", "Titulo"});
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new JornadaAM());
				marco.validate();
			}
		});
		btnAgregar.setBounds(121, 124, 129, 42);
		add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jornada j = obtenerJornadaSeleccionada();
				JornadaDAO jDAO = new JornadaDAO();
				jDAO.eliminar(j);
				int fila = table.getSelectedRow();
				tableModel.removeRow(fila);
				cargarTabla(tableModel);
			}
		});
		btnEliminar.setBounds(296, 124, 137, 42);
		add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jornada j = obtenerJornadaSeleccionada();
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new JornadaAM(j));
				marco.validate();
			}
		});
		btnModificar.setBounds(490, 125, 118, 40);
		add(btnModificar);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 200, 702, 365);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(50, 200, 702, 365);
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		
		JLabel lblNewLabel = new JLabel("Jornadas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblNewLabel.setBounds(296, 24, 169, 71);
		add(lblNewLabel);
		cargarTabla(tableModel);
	}
	private void cargarTabla(DefaultTableModel tabla) {
		try {
			tabla.setRowCount(0);
			JornadaDAO jDao = new JornadaDAO();
			 jornadas = jDao.mostrar();
			for (Jornada j: jornadas) {
				tabla.addRow(new Object[] {j.getNro(),j.getRI(),j.getObjetivo(),j.getTitulo() });
				
			}
			
		}catch(Exception sqle) {
			JOptionPane.showMessageDialog(null, "Error al llenar JTable "+sqle);
		}
	}
	private Jornada obtenerJornadaSeleccionada() {
		// TODO Mejorar (evitar relacionar el índice de la tabla con el índice del ArrayList)
		int filaSeleccionada = table.getSelectedRow();
		return jornadas.get(filaSeleccionada);
	}
}
