package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import estructuraTP.dao.PropuestaDAO;
import estructuraTP.modelo.Propuesta;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

public class AceptacionPropuesta extends JPanel {
		JLabel lblNro, lblorigen, lblcategoria, lblautor, lblfecha, lbltitulo, lbldescripcion, lblmotivacion;
		private Propuesta propuestas;
	/**
	 * Create the panel.
	 */
	public AceptacionPropuesta() {
		setBackground(SystemColor.menu);
		setSize(new Dimension(800, 600));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nro: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(195, 75, 65, 31);
		add(lblNewLabel);
		
		JLabel lblOrigen = new JLabel("Origen: ");
		lblOrigen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrigen.setBounds(195, 117, 65, 31);
		add(lblOrigen);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setBounds(195, 200, 65, 31);
		add(lblCategoria);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(195, 158, 65, 31);
		add(lblFecha);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutor.setBounds(195, 241, 65, 31);
		add(lblAutor);
		
		JLabel lblTitulo = new JLabel("Titulo: ");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(195, 286, 65, 31);
		add(lblTitulo);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(195, 328, 93, 31);
		add(lblDescripcion);
		
		JLabel lblMotivacion = new JLabel("Motivacion:");
		lblMotivacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotivacion.setBounds(195, 370, 93, 31);
		add(lblMotivacion);
		
		lblNro = new JLabel("_");
		lblNro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNro.setBounds(340, 75, 108, 31);
		add(lblNro);
		
		lblorigen = new JLabel("_");
		lblorigen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblorigen.setBounds(340, 117, 108, 31);
		add(lblorigen);
		
		lblcategoria = new JLabel("_");
		lblcategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblcategoria.setBounds(340, 200, 108, 31);
		add(lblcategoria);
		
		lblfecha = new JLabel("_");
		lblfecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblfecha.setBounds(340, 159, 108, 31);
		add(lblfecha);
		
		lblautor = new JLabel("_");
		lblautor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblautor.setBounds(340, 241, 108, 31);
		add(lblautor);
		
		lbltitulo = new JLabel("_");
		lbltitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltitulo.setBounds(340, 286, 108, 31);
		add(lbltitulo);
		
		lbldescripcion = new JLabel("_");
		lbldescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbldescripcion.setBounds(340, 328, 108, 31);
		add(lbldescripcion);
		
		lblmotivacion = new JLabel("_");
		lblmotivacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmotivacion.setBounds(340, 370, 108, 31);
		add(lblmotivacion);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(192, 192, 192)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(126, 59, 545, 417);
		add(panel);
		panel.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(164, 365, 99, 31);
		panel.add(btnAceptar);
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setBounds(287, 365, 99, 31);
		panel.add(btnRechazar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(0, 0, 800, 317);
		add(panel_1);
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estado = "Rechazado";
				String motivo = JOptionPane.showInputDialog("Ingrese el motivo del rechazo");
	   			 propuestas = new Propuesta(propuestas.getNro(), propuestas.getOrigen(),propuestas.getCategoria(),propuestas.getAutor(), propuestas.getFecha(), propuestas.getTitulo(), propuestas.getBrevedescripcion(), propuestas.getMotivacion(), motivo, estado );
	   			 PropuestaDAO pDAO= new PropuestaDAO();
	   			 pDAO.modificarEstadoMotivo(propuestas);
	   			JFrame marco =
	   					 (JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
	   			 marco.setContentPane(new PropuestaPantalla());
	   			 marco.validate();
	   			 
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String estado = "Aprobado";
				propuestas = new Propuesta(propuestas.getNro(), propuestas.getOrigen(),propuestas.getCategoria(),propuestas.getAutor(), propuestas.getFecha(), propuestas.getTitulo(), propuestas.getBrevedescripcion(), propuestas.getMotivacion(), "", estado );
				PropuestaDAO pDAO= new PropuestaDAO();
				pDAO.modificarestado(propuestas);
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) e.getSource());
				marco.setContentPane(new PropuestaPantalla());
				marco.validate();
			}
		});
		
		
		
		

	}
	public AceptacionPropuesta(Propuesta p) {
		this();
		lblNro.setText(""+p.getNro());
		lblorigen.setText(""+p.getOrigen());
		lblcategoria.setText(""+p.getCategoria());
		lblfecha.setText(""+p.getFecha());
		lblautor.setText(""+p.getAutor());
		lbltitulo.setText(""+p.getTitulo());
		lbldescripcion.setText(""+p.getBrevedescripcion());
		lblmotivacion.setText(""+p.getMotivacion());
		this.propuestas = p;
	}
}
