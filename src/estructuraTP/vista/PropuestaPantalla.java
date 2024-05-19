package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.PropuestaDAO;
import estructuraTP.modelo.Propuesta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;

public class PropuestaPantalla extends JPanel {
	private JTable table;
	private ArrayList<Propuesta> propuestas;
	/**
	 * Create the panel.
	 */
	public PropuestaPantalla() {
		setSize(new Dimension(800, 600));
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 193, 731, 355);
		add(scrollPane);
		
		table = new JTable();
		table.setBounds(23, 113, 569, 341);
		DefaultTableModel dataModel = new DefaultTableModel(new Object[][] {}, new String [] {"NroPropuesta", "Origen","Categoria", "Autor", "Fecha","Titulo","Descripcion","Motivacion","Motivo", "Estado"});
		table.setModel(dataModel);
		scrollPane.setViewportView(table);
	

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAgregar.setBounds(54, 135, 108, 46);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new PropuestaAM());
				marco.validate();
				/*JPanel pantalla = new PropuestaPantalla();
				PropuestaAM pantallAM = new PropuestaAM();
				pantalla.setVisible(false);
				pantalla.setVisible(true);
				repaint();
				revalidate();*/
				
			}
		});
		add(btnAgregar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificar.setBounds(430, 135, 108, 46);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Propuesta p = obtenerPropuestaSeleccionada();
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new PropuestaAM(p));
				marco.validate();

			}
		});
		add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(238, 135, 108, 46);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Propuesta p = obtenerPropuestaSeleccionada();
				PropuestaDAO pDAO = new PropuestaDAO();
				pDAO.eliminar(p);
				int fila= table.getSelectedRow();
				dataModel.removeRow(fila);
				cargarTabla(dataModel);
				
				
			}
		});
		add(btnEliminar);
		
		JLabel lblNewLabel = new JLabel("Propuestas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(292, 11, 207, 58);
		add(lblNewLabel);
		
		JButton btnVerDetalle = new JButton("Ver Detalle");
		btnVerDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Propuesta p = obtenerPropuestaSeleccionada();
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new AceptacionPropuesta(p));
				marco.validate();
			}
		});
		btnVerDetalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVerDetalle.setBounds(615, 135, 108, 46);
		add(btnVerDetalle);
		
		cargarTabla(dataModel);

	}
	private void cargarTabla(DefaultTableModel tabla) {
		try {
			tabla.setRowCount(0);
			PropuestaDAO pDao = new PropuestaDAO();
			 propuestas = pDao.traerTodas();
			for (Propuesta p: propuestas) {
				tabla.addRow(new Object[] {p.getNro(), p.getOrigen(), p.getCategoria(),p.getAutor(), p.getFecha(), p.getTitulo(), p.getBrevedescripcion(), p.getMotivacion(),p.getMotivo(), p.getEstado() });
				
			}
			
		}catch(Exception sqle) {
			JOptionPane.showMessageDialog(null, "Error al llenar JTable "+sqle);
		}
	}
	private Propuesta obtenerPropuestaSeleccionada() {
		// TODO Mejorar (evitar relacionar el índice de la tabla con el índice del ArrayList)
		int filaSeleccionada = table.getSelectedRow();
		return propuestas.get(filaSeleccionada);
	}

}		