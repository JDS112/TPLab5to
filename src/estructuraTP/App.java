package estructuraTP;

import java.awt.EventQueue;

import javax.swing.JFrame;

import estructuraTP.vista.JornadaPantalla;
import estructuraTP.vista.MaterialPantalla;
import estructuraTP.vista.PantallaInicio;
import estructuraTP.vista.PropuestaPantalla;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class App extends JFrame {
	
	private PropuestaPantalla propuestapantalla;
	private MaterialPantalla materialpantalla;
	private JornadaPantalla jornadapantalla;
	private PantallaInicio inicio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 800, 600);
		inicio= new PantallaInicio();
		setContentPane(inicio);
		validate();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//declaras un jmenu
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		//asignarle los items
		JMenuItem InicioItem = new JMenuItem("Inicio");
		InicioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setContentPane(inicio);
			}
		});
		menu.add(InicioItem);
		
		JMenuItem PropuestaItem = new JMenuItem("Propuestas");
		PropuestaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				propuestapantalla = new PropuestaPantalla();
				//asignarle un panel al jframe
				setContentPane(propuestapantalla);
			}
		});
		menu.add(PropuestaItem);
		
		JMenuItem MaterialItem = new JMenuItem("Materiales");
		MaterialItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				materialpantalla = new MaterialPantalla();
				setContentPane(materialpantalla);

			}
		});
		menu.add(MaterialItem);
		
		JMenuItem JornadaItem = new JMenuItem("Jornadas");
		JornadaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jornadapantalla = new JornadaPantalla();
				
				setContentPane(jornadapantalla);
			}
		});
		menu.add(JornadaItem);
		
		
		
	
	}


}
