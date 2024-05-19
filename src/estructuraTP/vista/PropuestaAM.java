package estructuraTP.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import estructuraTP.dao.CategoriaDAO;
import estructuraTP.dao.PropuestaDAO;
import estructuraTP.modelo.Propuesta;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Dimension;

public class PropuestaAM extends JPanel {
	private Propuesta propuestas;
	private PropuestaDAO pDAO;
	private JTextField textOrigen;
	private JTextField textautor;
	private JTextField textFecha;
	private JTextField textTitulo;
	private JTextField textDescripcion;
	private JTextField textMotivacion;
	private JTextField textNroPropuesta;
	/**
	 * Create the panel.
	 */
	public PropuestaAM() {
		setSize(new Dimension(800, 600));
		setBackground(SystemColor.menu);
		setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("N\u00B0de Propuesta");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(60, 100, 106, 30);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Origen");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(82, 141, 56, 34);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Categoria");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(70, 186, 86, 30);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Autor");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(80, 227, 46, 34);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Fecha");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(397, 100, 46, 30);
		add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Titulo");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(397, 141, 46, 26);
		add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Descripcion");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(62, 272, 76, 30);
		add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Motivacion");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_5.setBounds(380, 189, 76, 24);
		add(lblNewLabel_1_5);
		
		textNroPropuesta = new JTextField();
		textNroPropuesta.setBackground(SystemColor.text);
		textNroPropuesta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNroPropuesta.setBounds(166, 102, 56, 30);
		add(textNroPropuesta);
		textNroPropuesta.setColumns(10);

		textOrigen = new JTextField();
		textOrigen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textOrigen.setBounds(166, 143, 136, 34);
		add(textOrigen);
		textOrigen.setColumns(10);

		textautor = new JTextField();
		textautor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textautor.setColumns(10);
		textautor.setBounds(166, 231, 120, 30);
		add(textautor);

		textFecha = new JTextField();
		textFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFecha.setColumns(10);
		textFecha.setBounds(453, 97, 98, 30);
		add(textFecha);

		textTitulo = new JTextField();
		textTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textTitulo.setColumns(10);
		textTitulo.setBounds(453, 143, 98, 30);
		add(textTitulo);

		textDescripcion = new JTextField();
		textDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(166, 278, 385, 118);
		add(textDescripcion);

		textMotivacion = new JTextField();
		textMotivacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textMotivacion.setColumns(10);
		textMotivacion.setBounds(453, 183, 98, 30);
		add(textMotivacion);
		CategoriaDAO cDAO = new CategoriaDAO();
		ArrayList<String> c = new ArrayList<>();
		c = cDAO.traercategorias();
		JComboBox comboBox = new JComboBox();
		for(Object item : c) {
			comboBox.addItem(item);
		}
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(166, 190, 136, 30);
		add(comboBox);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nro = Integer.parseInt(textNroPropuesta.getText());
				String origen = textOrigen.getText();
				String autor = textautor.getText();
				String categoria=""+comboBox.getItemAt(comboBox.getSelectedIndex());
				String fechastring = textFecha.getText();
				Date fecha = null;
				try {
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
					fecha = formato.parse(fechastring);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String titulo =textTitulo.getText();
				String descripcion = textDescripcion.getText();
				String motivacion = textMotivacion.getText();
				String estado = "Pendiente de aprobacion";
				PropuestaDAO pDAO = new PropuestaDAO();
				if(esModificacion()) {
					Propuesta pModificacion = new Propuesta(nro,origen,categoria, autor, fecha, titulo, descripcion, motivacion);
					pDAO.modificar(pModificacion, propuestas.getNro(), propuestas.getTitulo());
				}
				else {
					Propuesta p = new Propuesta(nro,origen,categoria,autor, fecha, titulo, descripcion, motivacion,"",estado);
					pDAO.agregar(p);
				}
				
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new PropuestaPantalla());
				marco.validate();
			}
		});
		btnNewButton.setBounds(166, 470, 162, 44);
		add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Alta de Propuestas");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(227, 11, 348, 67);
		add(lblNewLabel_3);
		
		
	}
	//pregunto si es modificacion.
	public boolean esModificacion() {
		return this.propuestas != null;
	}
	public PropuestaAM(Propuesta p) {
		this();
		textNroPropuesta.setText(""+p.getNro());
		textOrigen.setText(""+p.getOrigen());
		textautor.setText(""+p.getAutor());
		textFecha.setText(""+p.getFecha());
		textTitulo.setText(""+p.getTitulo());
		textDescripcion.setText(""+p.getBrevedescripcion());
		textMotivacion.setText(""+p.getMotivacion());
		this.propuestas = p;
	}
}
