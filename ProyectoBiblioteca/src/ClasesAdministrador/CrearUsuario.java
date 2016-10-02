package ClasesAdministrador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.PreparedStatement;

import conexiones.Conexion;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;


public class CrearUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldDni;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldDir;
	private JTextField textFieldTlf;
	private JTextField textFieldNick;
	private JTextField textFieldClave;
	private JComboBox comboBoxTipo;
	private Conexion c=new Conexion();

	
	public CrearUsuario() {
		setTitle("Crear Usuarios");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setModal(true);
		setUndecorated(true);
		setBounds(100, 100, 539, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		JLabel lblDni = new JLabel("*  DNI:");
		lblDni.setForeground(Color.RED);
		lblDni.setBounds(24, 40, 114, 14);
		lblDni.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblDni);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(148, 40, 140, 20);
		textFieldDni.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldDni);
		textFieldDni.setColumns(10);
		
		JLabel lblNombre = new JLabel("*  Nombre:");
		lblNombre.setForeground(Color.RED);
		lblNombre.setBounds(24, 71, 114, 14);
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblNombre);
		
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(148, 71, 140, 20);
		textFieldNombre.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(Color.WHITE);
		lblApellidos.setBounds(24, 102, 114, 14);
		lblApellidos.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblApellidos);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(148, 102, 140, 20);
		textFieldApellidos.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldApellidos);
		textFieldApellidos.setColumns(10);
		
		JLabel lblContrasena = new JLabel("*  Contrase\u00F1a: ");
		lblContrasena.setForeground(Color.RED);
		lblContrasena.setBounds(24, 197, 114, 14);
		lblContrasena.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblContrasena);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setBounds(24, 133, 114, 14);
		lblDireccion.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblDireccion);
		
		textFieldDir = new JTextField();
		textFieldDir.setBounds(148, 133, 140, 20);
		textFieldDir.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldDir);
		textFieldDir.setColumns(10);
		
		JLabel lblTelefono = new JLabel("*  Tel\u00E9fono: ");
		lblTelefono.setForeground(Color.RED);
		lblTelefono.setBounds(24, 164, 114, 14);
		lblTelefono.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblTelefono);
		
		textFieldTlf = new JTextField();
		textFieldTlf.setBounds(148, 164, 140, 20);
		textFieldTlf.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldTlf);
		textFieldTlf.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setBounds(24, 259, 114, 14);
		lblTipo.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblTipo);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(148, 259, 140, 20);
		comboBoxTipo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Normal", "Administrador"}));
		contentPanel.add(comboBoxTipo);
		
		JButton btnCrearUsuario = new JButton("A\u00F1adir");
		btnCrearUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCrearUsuario.setMnemonic('a');
		btnCrearUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCrearUsuario.setBounds(24, 322, 120, 31);
		btnCrearUsuario.setIcon(new ImageIcon("src/images/confirm.png"));
		contentPanel.add(btnCrearUsuario);
		
		
		btnCrearUsuario.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnCrearUsuarioActionPerformed(o);
			}
		});
		
		
		JButton Salir = new JButton("Cancelar");
		Salir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Salir.setMnemonic('c');
		Salir.setBounds(168, 322, 120, 31);
		Salir.setIcon(new ImageIcon("src/images/adiooos.png"));
		contentPanel.add(Salir);
		
		Salir.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				SalirActionPerformed(o);
			}
			private void SalirActionPerformed (ActionEvent o){
				dispose();
			}

		});
		
		JLabel labelusuarios = new JLabel("");
		labelusuarios.setIcon(new ImageIcon("src/images/users.png"));
		labelusuarios.setBounds(282, 0, 257, 389);
		contentPanel.add(labelusuarios);
		
	
		
		JLabel labelNick = new JLabel("*  Nick: ");
		labelNick.setForeground(Color.RED);
		labelNick.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		labelNick.setBounds(24, 228, 114, 14);
		contentPanel.add(labelNick);
		
		textFieldNick = new JTextField();
		textFieldNick.setColumns(10);
		textFieldNick.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldNick.setBounds(148, 228, 140, 20);
		contentPanel.add(textFieldNick);
		
		textFieldClave = new JTextField();
		textFieldClave.setColumns(10);
		textFieldClave.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldClave.setBounds(148, 197, 140, 20);
		contentPanel.add(textFieldClave);
		
		
		JLabel labelImagen = new JLabel("");
		labelImagen.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelImagen.setBounds(0, 0, 539, 389);
		labelImagen.setIcon(new ImageIcon("src/images/fondonotas.jpg"));
		contentPanel.add(labelImagen);
		
	}
	
	protected void btnCrearUsuarioActionPerformed (ActionEvent o){
		try{
			
			String sql="INSERT INTO usuario (dni,nombre,apellidos,nick,clave,direccion,telefono,tipo) VALUES" + "(?,?,?,?,?,?,?,?)";//Consulta preparada para introducir los valores posteriormente
			
			
			String dni, nombre, apellidos, nick, clave, direccion, telefono;
			int tipo;
			dni=textFieldDni.getText();
			nombre=textFieldNombre.getText();
			apellidos=textFieldApellidos.getText();
			nick=textFieldNick.getText();
			clave=textFieldClave.getText();
			direccion=textFieldDir.getText();
			telefono=textFieldTlf.getText();
			if(comboBoxTipo.getSelectedItem().toString().equalsIgnoreCase("normal")){
				tipo=0;
			}else{
				tipo=1;
			}
			
			PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
			
			prest.setString(1, dni);
			prest.setString(2, nombre);
			prest.setString(3, apellidos);
			prest.setString(4, nick);
			prest.setString(5, clave);
			prest.setString(6, direccion);
			prest.setString(7, telefono);
			prest.setInt(8, tipo);
			prest.execute();
			JOptionPane.showMessageDialog(null, "Usuario creado con éxito");
			dispose();
			}catch(SQLException exSql){
				JOptionPane.showMessageDialog(null, exSql.getMessage());
			}catch(Exception e){
			    JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos, as","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
