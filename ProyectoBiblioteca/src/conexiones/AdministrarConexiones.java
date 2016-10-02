package conexiones;

import java.awt.BorderLayout;     
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import ClasesInterfaz.Validacion;


public class AdministrarConexiones extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldip;
	private JTextField textFieldbdname;
	private JTextField textFielduser;
	private JTextField textFieldpass;
	protected static String ip;
	private static String bdname;
	private static String user;
	private static String pass;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarConexiones frame = new AdministrarConexiones();
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
	public AdministrarConexiones() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		textFieldip = new JTextField();
		textFieldip.setBounds(152, 27, 86, 20);
		contentPane.add(textFieldip);
		textFieldip.setColumns(10);
		
		textFieldbdname = new JTextField();
		textFieldbdname.setBounds(152, 89, 86, 20);
		contentPane.add(textFieldbdname);
		textFieldbdname.setColumns(10);
		
		textFielduser = new JTextField();
		textFielduser.setBounds(152, 148, 86, 20);
		contentPane.add(textFielduser);
		textFielduser.setColumns(10);
		
		textFieldpass = new JTextField();
		textFieldpass.setBounds(152, 211, 86, 20);
		contentPane.add(textFieldpass);
		textFieldpass.setColumns(10);
		
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(31, 30, 46, 14);
		contentPane.add(lblIp);
		
		JLabel lblNombreBd = new JLabel("Nombre BD");
		lblNombreBd.setBounds(31, 92, 86, 14);
		contentPane.add(lblNombreBd);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(31, 151, 74, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(31, 214, 74, 14);
		contentPane.add(lblContrasea);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(304, 63, 89, 23);
		contentPane.add(btnAceptar);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(304, 147, 89, 23);
		contentPane.add(btnCancelar);
		
		
		
		

		
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				ip=textFieldip.getText();
				bdname=textFieldbdname.getText();
				user=textFielduser.getText();
				pass=textFieldpass.getText();
				
				if(pass.length()<=1){
					
					pass="";
					
				}
				
				Conexion c=new Conexion();
				c.escribirArchivo();
				JOptionPane.showMessageDialog(null, "Se han cambiado los datos de la conexion");
				dispose();
				
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null, "Cancelado");
				dispose();
				Validacion v=new Validacion();
				v.setVisible(true);
				
			}
		});
		
	}


	public String getIp() {
		return ip;
	}

	public String getBdname() {
		return bdname;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	
}

