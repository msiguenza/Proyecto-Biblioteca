package ClasesUsuario;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.PreparedStatement;

import conexiones.Conexion;
import tablasBaseDeDatos.*;
import java.awt.Toolkit;

public class InsertarNotasRevistasLibrosDvd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTema;
	private JTextField textFieldCodMaterial;
	private JTextField textFieldTitulo;
	private JTextField txtFieldTipo;
	//private Material material;
	private Usuario u;
	private JTextArea textAreaComentario;
	private String listaMat[];
	private Conexion c=new Conexion();

	
	public InsertarNotasRevistasLibrosDvd(String lista[]) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/user.png"));
		this.listaMat=lista;
		//material=new Material();
		u=new Usuario();
		
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 539, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		textFieldTema = new JTextField();
		textFieldTema.setBounds(132, 185, 364, 20);
		textFieldTema.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldTema);
		textFieldTema.setColumns(10);
		
		JLabel lblTema = new JLabel("Tema:");
		lblTema.setForeground(Color.WHITE);
		lblTema.setBounds(34, 186, 48, 14);
		lblTema.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblTema);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setForeground(Color.WHITE);
		lblComentario.setBounds(32, 228, 88, 14);
		lblComentario.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblComentario);
		
		JButton btnInsertarNota = new JButton("Insertar");
		btnInsertarNota.setMnemonic('m');
		btnInsertarNota.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInsertarNota.setBounds(261, 347, 106, 31);
		btnInsertarNota.setIcon(new ImageIcon("src/images/confirm.png"));
		contentPanel.add(btnInsertarNota);
		
		
		btnInsertarNota.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent o){
				btnInsertarNotaPerformed(o);
			}
		});
		
		
		JButton Salir = new JButton("Cancelar");
		Salir.setMnemonic('a');
		Salir.setBounds(416, 347, 113, 31);
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
		
		JLabel lblCrearNotas = new JLabel("Insercci\u00F3n de notas de libros, revistas y dvds");
		lblCrearNotas.setForeground(new Color(222, 184, 135));
		lblCrearNotas.setFont(new Font("Tekton Pro Ext", Font.BOLD | Font.ITALIC, 15));
		lblCrearNotas.setBounds(73, 23, 459, 50);
		contentPanel.add(lblCrearNotas);
		
		JLabel lblCodMaterial = new JLabel("Cod. Material:");
		lblCodMaterial.setForeground(Color.WHITE);
		lblCodMaterial.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblCodMaterial.setBounds(32, 84, 146, 14);
		contentPanel.add(lblCodMaterial);
		textFieldCodMaterial = new JTextField();
		textFieldCodMaterial.setColumns(10);
		textFieldCodMaterial.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldCodMaterial.setBounds(132, 84, 140, 20);
		textFieldCodMaterial.setEditable(false);
		textFieldCodMaterial.setText(listaMat[0]);
		contentPanel.add(textFieldCodMaterial);
		
		JLabel lblTituloMaterial = new JLabel("T\u00EDtulo:");
		lblTituloMaterial.setForeground(Color.WHITE);
		lblTituloMaterial.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblTituloMaterial.setBounds(32, 120, 146, 14);
		contentPanel.add(lblTituloMaterial);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTitulo.setBounds(132, 119, 364, 20);
		textFieldTitulo.setEditable(false);
		textFieldTitulo.setText(listaMat[1]);
		contentPanel.add(textFieldTitulo);
		
		System.out.print("h");
		txtFieldTipo = new JTextField();
		txtFieldTipo.setColumns(10);
		txtFieldTipo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		txtFieldTipo.setBounds(132, 154, 364, 20);
		txtFieldTipo.setEditable(false);
		txtFieldTipo.setText(lista[2]);
		contentPanel.add(txtFieldTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblTipo.setBounds(34, 160, 146, 14);
		contentPanel.add(lblTipo);
		
		textAreaComentario = new JTextArea();
		textAreaComentario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textAreaComentario.setBounds(132, 225, 364, 111);
		contentPanel.add(textAreaComentario);
		
		JLabel lblNombre = new JLabel("");
		lblNombre.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblNombre.setBounds(0, 0, 539, 389);
		lblNombre.setIcon(new ImageIcon("src/images/fondoazul.jpg"));
		contentPanel.add(lblNombre);
		
	}
	
	

	protected void btnInsertarNotaPerformed (ActionEvent o){
		try{
			Statement stm=c.getConectado().createStatement();
			int resulSql=stm.executeUpdate("INSERT INTO nota (tema,dni,cod_material,comentario) "+"VALUES ('"+textFieldTema.getText()+"','"+u.getDni()+"',"+listaMat[0]+",'"+textAreaComentario.getText()+"');");
		
			JOptionPane.showMessageDialog(null, "Nota insertada con éxito");
			dispose();
			}catch(SQLException exSql){
				JOptionPane.showMessageDialog(null, "Error al insertar nota, asegurese de introducir los datos correctamente.","Error",JOptionPane.ERROR_MESSAGE);
			}catch(Exception e){
			    JOptionPane.showMessageDialog(null, "Error conexion con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	

	
}
