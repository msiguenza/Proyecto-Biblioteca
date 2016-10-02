package ClasesUsuario;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import ClasesInterfaz.Validacion;

import tablasBaseDeDatos.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class VentanaPrincipalUsuario extends JFrame {

	private JPanel contentPane;
	
	private Usuario us;
	
	
	public VentanaPrincipalUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/user.png"));
		us=new Usuario();
	
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesi\u00F3n");
		btnCerrarSesin.setBounds(57, 293, 156, 41);
		btnCerrarSesin.setIcon(new ImageIcon("src/images/exit.png"));
		contentPane.add(btnCerrarSesin);
		
		btnCerrarSesin.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnCerrarSesinActionPerformed(o);
			}
			private void btnCerrarSesinActionPerformed (ActionEvent o){
				JOptionPane.showMessageDialog(null,"Sesión finalizada exitosamente");
				dispose();
				Validacion valin = new Validacion();
				valin.setVisible(true);
			}
		});
		
		
		JLabel lblUsuario = new JLabel("Usuario:"+us.getNick());
		lblUsuario.setBounds(10, 11, 163, 14);
		lblUsuario.setForeground(new Color(46, 139, 87));
		lblUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		contentPane.add(lblUsuario);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(10, 11, 234, 275);
		label_1.setIcon(new ImageIcon("src/images/usuarios.png"));
		contentPane.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(295, 27, 274, 297);
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNotas = new JButton("<"+"html"+">Notas de Artículos<"+"br"+">Y Materiales<");
		btnNotas.setBounds(25, 31, 227, 41);
		panel.add(btnNotas);
		btnNotas.setIcon(new ImageIcon("src/images/notes.png"));
		
		JButton Material = new JButton("<"+"html"+">Búsqueda de Material<"+"br"+">Insercción de Notas<");
		Material.setBounds(25, 197, 227, 76);
		panel.add(Material);
		Material.setIcon(new ImageIcon("src/images/materiales.png"));
		
		
		JButton Alquiler = new JButton("Reservas");
		Alquiler.setBounds(25, 114, 227, 41);
		panel.add(Alquiler);
		Alquiler.setIcon(new ImageIcon("src/images/prestamo.png"));
		Alquiler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		
		Alquiler.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				AlquilerActionPerformed(o);
			}
			private void AlquilerActionPerformed (ActionEvent o){
				BusquedaReservas frame = new BusquedaReservas();
				frame.setVisible(true);
			}

		});
		
		
		Material.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				MaterialActionPerformed(o);
			}
			
		});
		
		
		btnNotas.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnNotasActionPerformed(o);
			}
			private void btnNotasActionPerformed (ActionEvent o){
				VisualizacionNotas frame = new VisualizacionNotas();
				frame.setVisible(true);
			}

		});
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 603, 354);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		label.setIcon(new ImageIcon("src/images/Base de datos 001.jpg"));
		contentPane.add(label);
	}

	protected void MaterialActionPerformed(ActionEvent o) {
		BusquedaMaterialesArticulosInserccionesNotas frame = new BusquedaMaterialesArticulosInserccionesNotas();
		frame.setVisible(true);
		
	}
}