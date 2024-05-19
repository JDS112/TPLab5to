package estructuraTP.vista;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import estructuraTP.dao.JornadaDAO;
import estructuraTP.modelo.Jornada;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class JornadaAM extends JPanel {
	private Jornada jornada;
	private JTextField textNroJornada;
	private JTextField textRI;
	private JTextField textObjetivo;
	private JTextField textTitulo;

	/**
	 * Create the panel.
	 */
	public JornadaAM() {
		setSize(new Dimension(800, 600));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nro Propuesta");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(118, 168, 110, 20);
		add(lblNewLabel);
		
		textNroJornada = new JTextField();
		textNroJornada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNroJornada.setBounds(238, 158, 110, 32);
		add(textNroJornada);
		textNroJornada.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Referente Institucional");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(415, 162, 146, 32);
		add(lblNewLabel_1);
		
		textRI = new JTextField();
		textRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textRI.setBounds(571, 158, 119, 32);
		add(textRI);
		textRI.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Objetivo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(118, 258, 74, 17);
		add(lblNewLabel_2);
		
		textObjetivo = new JTextField();
		textObjetivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textObjetivo.setBounds(238, 246, 110, 32);
		add(textObjetivo);
		textObjetivo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Titulo");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(415, 246, 86, 29);
		add(lblNewLabel_3);
		
		textTitulo = new JTextField();
		textTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textTitulo.setBounds(571, 246, 119, 32);
		add(textTitulo);
		textTitulo.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nro = Integer.parseInt(textNroJornada.getText());
				String RI = textRI.getText();
				String objetivo = textObjetivo.getText();
				String titulo = textTitulo.getText();
				JornadaDAO jDAO = new JornadaDAO();
				if (esModificacion()) {
					Jornada jModificacion = new Jornada(nro, RI, objetivo, titulo);
					jDAO.modificar(jModificacion, jornada.getNro(), jornada.getTitulo());
				}
				else {
					Jornada j = new Jornada(nro, RI, objetivo, titulo);
					jDAO.agregar(j);
				}
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new JornadaPantalla());
				marco.validate();
			}
		});
		btnGuardar.setBounds(343, 479, 146, 42);
		add(btnGuardar);
		
		JLabel lblNewLabel_4 = new JLabel("Alta Jornadas\r\n");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblNewLabel_4.setBounds(343, 51, 238, 32);
		add(lblNewLabel_4);
		
	}
	public JornadaAM(Jornada j) {
		// TODO Auto-generated constructor stub
		this();
		textNroJornada.setText(""+j.getNro());
		textRI.setText(""+j.getRI());
		textObjetivo.setText(""+j.getObjetivo());
		textTitulo.setText(""+j.getTitulo());
		this.jornada = j;
	}
	public boolean esModificacion() {
		return this.jornada != null;
	}
}
