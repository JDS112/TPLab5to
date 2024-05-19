package estructuraTP.vista;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class PantallaInicio extends JPanel {

	/**
	 * Create the panel.
	 */
	public PantallaInicio() {
		setSize(new Dimension(800, 600));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 270, 600);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Proyecto Laboratorio");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(50, 48, 173, 37);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Equipo Duckgod");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 445, 128, 20);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("5to 3ra");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 494, 67, 20);
		panel.add(lblNewLabel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(0, 75, 270, 10);
		panel.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Inicio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(470, 11, 133, 113);
		add(lblNewLabel_2);

	}
}
