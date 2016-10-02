package ClasesInterfaz;
import java.awt.EventQueue;  
import java.awt.Toolkit;
import java.sql.*;
//import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import conexiones.AdministrarConexiones;
import conexiones.Conexion;
import ClasesAdministrador.VentanaAdmin;
import ClasesUsuario.VentanaPrincipalUsuario;
import tablasBaseDeDatos.*;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
//import tablasBaseDeDatos.*;


public class Validacion extends JFrame {
	private JPanel contentPane;
	private JTextField tFUsuario;
	private JPasswordField passwordField;
	private Usuario tablaUsuario;
	private String pass, nick;
	private Conexion c=new Conexion();
	public Validacion() {
		setUndecorated(true);

		tablaUsuario=new Usuario();

		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/Book.png"));
		setTitle("Carga de Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 435);
		setResizable(false);
		contentPane = new JPanel(); 
		contentPane.setBorder(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		
		JLabel lblUsuario = new JLabel("Usuario :");
		lblUsuario.setBounds(74, 91, 110, 24);
		lblUsuario.setFont(new Font("Emmett", Font.PLAIN, 16));
		lblUsuario.setForeground(Color.WHITE);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a :");
		lblContrasena.setBounds(351, 92, 273, 23);
		lblContrasena.setFont(new Font("Emmett", Font.PLAIN, 16));
		lblContrasena.setForeground(Color.WHITE);
		contentPane.add(lblContrasena);
		
		JButton btnCargarUsuario = new JButton("Ingresar");
		btnCargarUsuario.setMnemonic('i');
		
		btnCargarUsuario.setBounds(19, 374, 116, 50);
		btnCargarUsuario.setIcon(new ImageIcon("src/Images/iconIngreso.png"));
		btnCargarUsuario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnCargarUsuario.setBackground(SystemColor.controlHighlight);
		contentPane.add(btnCargarUsuario);
		
		JButton btnCancelar = new JButton("Limpiar");
		btnCancelar.setMnemonic('l');
		btnCancelar.setBounds(493, 374, 116, 50);
		btnCancelar.setIcon(new ImageIcon("src/Images/cepillo.png"));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		btnCancelar.setBackground(SystemColor.controlHighlight);
		contentPane.add(btnCancelar);
		
		JLabel labelUser = new JLabel("");
		labelUser.setBounds(19, 79, 56, 50);
		labelUser.setIcon(new ImageIcon("src/images/flechaderecha.png"));
		contentPane.add(labelUser);
		
		JLabel LabelClave = new JLabel("");
		LabelClave.setBounds(296, 79, 56, 50);
		LabelClave.setIcon(new ImageIcon("src/images/flechaderecha.png"));
		contentPane.add(LabelClave);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(411, 183, 129, 24);
		passwordField.setText("123456");
		contentPane.add(passwordField);
		
		passwordField.addActionListener(new ActionListener(){//añadir
			public void actionPerformed(ActionEvent evt) {
				btnCargarUsuarioActionPerformed(evt);
			}
		});
		
		tFUsuario = new JTextField();
		tFUsuario.setBounds(123, 183, 121, 24);
		contentPane.add(tFUsuario);
		tFUsuario.setText("administrador");
		tFUsuario.setColumns(10);
		
		JButton buttonSalir = new JButton("Salir App");
		buttonSalir.setMnemonic('s');
		buttonSalir.setBounds(247, 374, 116, 50);
		buttonSalir.setIcon(new ImageIcon("src/Images/exit.png"));
		buttonSalir.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		buttonSalir.setBackground(SystemColor.controlHighlight);
		contentPane.add(buttonSalir);
		
		
		JButton btnConexion = new JButton("Conexion");
		btnConexion.setMnemonic('c');
		btnConexion.setBounds(481, 11, 129, 33);
		contentPane.add(btnConexion);
		contentPane.add(btnConexion);
		btnConexion.setIcon(new ImageIcon("src/Images/connect.png"));
		
		JLabel labelfondo = new JLabel("");
		labelfondo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelfondo.setBounds(0, 0, 634, 435);
		contentPane.add(labelfondo);
		labelfondo.setIcon(new ImageIcon("src/images/lapiz.jpg"));
		
		btnConexion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				AdministrarConexiones ac=new AdministrarConexiones();
				ac.setVisible(true);
				
			}
		});
		
		btnCargarUsuario.addActionListener(new ActionListener(){//añadir
			public void actionPerformed(ActionEvent evt) {
				btnCargarUsuarioActionPerformed(evt);
			}
		});
		//LIMPIAR
		buttonSalir.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				buttonSalirActionPerformed(o);
			}
			private void buttonSalirActionPerformed (ActionEvent o){
				System.exit (0);
			}
		});
	
	
	//Salir
			btnCancelar.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent o){
					btnLimpiarActionPerformed(o);
				}
				private void btnLimpiarActionPerformed (ActionEvent o){
					tFUsuario.setText("");
					passwordField.setText("");
				}

			});
	}

	// Metodo que conecta con el servidor MYSQL y valida los usuarios
	private boolean validarUsuario(String n, String p )throws IOException{//añadir
		try{
			
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("SELECT * FROM usuario WHERE nick='"+n+"' AND clave='"+p+"'");
			Class.forName("com.mysql.jdbc.Driver");
			if(resulSql.first()){
				tablaUsuario.setDni(String.valueOf(resulSql.getString("dni")));
				tablaUsuario.setNombre(String.valueOf(resulSql.getString("nombre")));
				tablaUsuario.setApellidos(String.valueOf(resulSql.getString("apellidos")));
				tablaUsuario.setNick(String.valueOf(resulSql.getString("nick")));
				tablaUsuario.setClave(String.valueOf(resulSql.getString("clave")));
				tablaUsuario.setDireccion(String.valueOf(resulSql.getString("direccion")));
				tablaUsuario.setTelefono(String.valueOf(resulSql.getString("telefono")));
				tablaUsuario.setPenalizado(Boolean.parseBoolean(resulSql.getString("penalizado")));
				tablaUsuario.setAlquiler(Boolean.parseBoolean(resulSql.getString("alquiler")));
				tablaUsuario.setTipo(Integer.parseInt(resulSql.getString("tipo")));
				return true;
			}else{
				return false;
			}
		}catch(ClassNotFoundException e){
				e.printStackTrace();
				return false;
		}catch(SQLException e1){
			JOptionPane.showMessageDialog(null,"Error en la conexión, asegúrese de tener el host activo","Error",JOptionPane.ERROR_MESSAGE);
				return false;
		}
	}
	
	
	//Metodo que carga el usuario e inicia el programa según el tipo de usario que sea (Administrador o normal). 
	protected void btnCargarUsuarioActionPerformed(ActionEvent evt) {
		try{
			this.pass=String.valueOf(passwordField.getPassword());
			this.nick=tFUsuario.getText();
			//Chequea si el usuario ha introducido datos en los check de comprobación de usuario
			if(tFUsuario.getText().length()>0 && passwordField.getPassword().length>0){
				//Si el usuario es validado correctamente
				if(validarUsuario(nick, pass)==true){//enviar datos a validar
					if(tablaUsuario.getTipo()==1){
						setVisible(false);
						VentanaAdmin va=new VentanaAdmin();
						va.setVisible(true);
					}else{
						setVisible(false);
						VentanaPrincipalUsuario vpu=new VentanaPrincipalUsuario();
						vpu.setVisible(true);
					}
				}else{
					JOptionPane.showMessageDialog(null, "El nombre de usuario y/o contraseña no son válidos","Error",JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
				}
			}else{
				 JOptionPane.showMessageDialog(null, "El nombre de usuario y/o contraseña no son válidos","Error",JOptionPane.ERROR_MESSAGE);
				 tFUsuario.setText("");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Validacion v = new Validacion();
					v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
