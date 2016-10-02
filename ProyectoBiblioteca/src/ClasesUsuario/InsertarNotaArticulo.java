package ClasesUsuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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

import ClasesInterfaz.Validacion;

import com.mysql.jdbc.PreparedStatement;

import conexiones.Conexion;
import tablasBaseDeDatos.*;
import java.awt.Toolkit;

public class InsertarNotaArticulo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTema;
	private JTextField textFieldCodRevista;
	private JTextField textFieldTituloRevista;
	//private Material material;
	private Usuario u;
	private JTextArea textAreaComentario;
	private String listaMat[];
	private JTextField textFieldArticulo;
	private Conexion c=new Conexion();

	
	public InsertarNotaArticulo(String lista[]) {
		setModal(true);
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/user.png"));
		this.listaMat=lista;
		//material=new Material();
		u=new Usuario();
		
		
		
		setBounds(100, 100, 547, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		textFieldTema = new JTextField();
		textFieldTema.setBounds(132, 179, 186, 20);
		textFieldTema.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldTema);
		textFieldTema.setColumns(10);
		
		JLabel lblTema = new JLabel("Tema:");
		lblTema.setBounds(33, 180, 48, 14);
		lblTema.setForeground(Color.WHITE);
		lblTema.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblTema);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setBounds(32, 228, 88, 14);
		lblComentario.setForeground(Color.WHITE);
		lblComentario.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblComentario);
		
		JButton btnInsertarNota = new JButton("Insertar");
		btnInsertarNota.setBounds(261, 347, 106, 31);
		btnInsertarNota.setMnemonic('i');
		btnInsertarNota.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnInsertarNota.setIcon(new ImageIcon("src/images/confirm.png"));
		contentPanel.add(btnInsertarNota);
		
		
		btnInsertarNota.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent o){
				btnInsertarNotaPerformed(o);
			}
		});
		
		
		JButton Salir = new JButton("Cancelar");
		Salir.setBounds(416, 347, 113, 31);
		Salir.setMnemonic('c');
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
		
		JLabel lblInsertarNotaArticulo = new JLabel("Insercci\u00F3n de notas para art\u00EDculos");
		lblInsertarNotaArticulo.setBounds(110, 23, 459, 50);
		lblInsertarNotaArticulo.setForeground(new Color(222, 184, 135));
		lblInsertarNotaArticulo.setFont(new Font("Tekton Pro Ext", Font.BOLD | Font.ITALIC, 15));
		contentPanel.add(lblInsertarNotaArticulo);
		
		JLabel lblCodRevista = new JLabel("Cod. Material:");
		lblCodRevista.setBounds(32, 84, 146, 14);
		lblCodRevista.setForeground(Color.WHITE);
		lblCodRevista.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblCodRevista);
		textFieldCodRevista = new JTextField();
		textFieldCodRevista.setBounds(132, 84, 72, 20);
		textFieldCodRevista.setColumns(10);
		textFieldCodRevista.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldCodRevista.setEditable(false);
		textFieldCodRevista.setText(listaMat[1]);
		contentPanel.add(textFieldCodRevista);
		
		JLabel lblTituloRevista = new JLabel("T\u00EDtulo revista:");
		lblTituloRevista.setBounds(32, 120, 146, 14);
		lblTituloRevista.setForeground(Color.WHITE);
		lblTituloRevista.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblTituloRevista);
		
		textFieldTituloRevista = new JTextField();
		textFieldTituloRevista.setBounds(132, 119, 186, 20);
		textFieldTituloRevista.setColumns(10);
		textFieldTituloRevista.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTituloRevista.setEditable(false);
		textFieldTituloRevista.setText(listaMat[0]);
		contentPanel.add(textFieldTituloRevista);
		
		textAreaComentario = new JTextArea();
		textAreaComentario.setBounds(132, 225, 364, 111);
		contentPanel.add(textAreaComentario);
		
		JLabel lblTituloArticulo = new JLabel("Art\u00EDculo:");
		lblTituloArticulo.setBounds(33, 154, 146, 14);
		lblTituloArticulo.setForeground(Color.WHITE);
		lblTituloArticulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblTituloArticulo);
		
		textFieldArticulo = new JTextField();
		textFieldArticulo.setBounds(132, 148, 186, 20);
		textFieldArticulo.setText((String) null);
		textFieldArticulo.setEditable(false);
		textFieldArticulo.setColumns(10);
		textFieldArticulo.setText(lista[3]);
		textFieldArticulo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldArticulo);
		
		JLabel lblNombre = new JLabel("");
		lblNombre.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblNombre.setBounds(0, 0, 547, 403);
		lblNombre.setIcon(new ImageIcon("src/images/fondoinformacion\u00E7.jpg"));
		contentPanel.add(lblNombre);
		
	}
	
	protected void btnInsertarNotaPerformed (ActionEvent o){
		try{
			Statement stm=c.getConectado().createStatement();																	//Tema                 //dni		//codMaterial         //Comentario			 	  //codArticulo
			int resulSql=stm.executeUpdate("INSERT INTO nota (tema,dni,cod_material,comentario,cod_articulo) "+"VALUES ('"+textFieldTema.getText()+"','"+u.getDni()+"',"+listaMat[1]+",'"+textAreaComentario.getText()+"',"+listaMat[2]+");");
			JOptionPane.showMessageDialog(null, "Nota insertada con éxito");
			dispose();
			}catch(SQLException exSql){
				JOptionPane.showMessageDialog(null, "Error al insertar nota, asegurese de introducir los datos correctamente.","Error",JOptionPane.ERROR_MESSAGE);
			}catch(Exception e){
			    JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	}

