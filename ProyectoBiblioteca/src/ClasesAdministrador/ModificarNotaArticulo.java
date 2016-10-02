package ClasesAdministrador;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import com.mysql.jdbc.PreparedStatement;

import conexiones.Conexion;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextArea;
import java.awt.Color;


public class ModificarNotaArticulo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldRevista;
	private JTextField textFieldTema;
	private JTextField textFieldArticulo;
	private JTextField textFieldDni;
	private JTextField textNombreUsuario;
	private JTextField textFieldCodNota;
	private JTextArea textAreaComent;
	private Conexion c=new Conexion();
	

	
	public ModificarNotaArticulo(String fila[]) {
		setUndecorated(true);
		setTitle("Modificar Nota Artículo");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setModal(true);
		setBounds(100, 100, 679, 455);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 679, 455);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
   // Labels y Textfields		
		JLabel labelCodNota = new JLabel("Cod. Nota: ");
		labelCodNota.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		labelCodNota.setBounds(23, 29, 132, 14);
		contentPanel.add(labelCodNota);
		
		textFieldCodNota = new JTextField();
		textFieldCodNota.setText((String) null);
		textFieldCodNota.setColumns(10);
		textFieldCodNota.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldCodNota.setBounds(151, 28, 140, 20);
		textFieldCodNota.setEditable(false);
		textFieldCodNota.setText(fila[0]);
		contentPanel.add(textFieldCodNota);
		
		
		JLabel lblTituloRev = new JLabel("T\u00EDtulo de la revista:");
		lblTituloRev.setBounds(23, 92, 184, 14);
		lblTituloRev.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblTituloRev);
			
		textFieldRevista = new JTextField();
		textFieldRevista.setBounds(151, 91, 140, 20);
		textFieldRevista.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldRevista.setColumns(10);
		textFieldRevista.setEditable(false);
		textFieldRevista.setText(fila[3]);
		contentPanel.add(textFieldRevista);
		
		
		JLabel lblTipoTemaNota = new JLabel("Tema de la nota: ");
		lblTipoTemaNota.setBounds(23, 61, 132, 14);
		lblTipoTemaNota.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblTipoTemaNota);
		
		textFieldTema = new JTextField();
		textFieldTema.setBounds(151, 61, 140, 20);
		textFieldTema.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTema.setColumns(10);
    	textFieldTema.setText(fila[5]);
		contentPanel.add(textFieldTema);
		
		
		JLabel lblTipoMat = new JLabel("Titulo del articulo: ");
		lblTipoMat.setBounds(23, 123, 156, 14);
		lblTipoMat.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblTipoMat);
		
		textFieldArticulo = new JTextField();
		textFieldArticulo.setBounds(151, 122, 140, 20);
		textFieldArticulo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldArticulo.setColumns(10);
		textFieldArticulo.setEditable(false);
		textFieldArticulo.setText(fila[4]);
		contentPanel.add(textFieldArticulo);
		
		
		JLabel lblDni = new JLabel("Dni usuario: ");
		lblDni.setBounds(346, 29, 98, 14);
		lblDni.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblDni);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(454, 28, 140, 20);
		textFieldDni.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldDni.setColumns(10);
		textFieldDni.setEditable(false);
		textFieldDni.setText(fila[1]);
		contentPanel.add(textFieldDni);
		
		
		JLabel textFieldNombreUs = new JLabel("Nombre usuario:");
		textFieldNombreUs.setBounds(345, 61, 112, 14);
		textFieldNombreUs.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(textFieldNombreUs);
		
		textNombreUsuario = new JTextField();
		textNombreUsuario.setBounds(454, 60, 140, 20);
		textNombreUsuario.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textNombreUsuario.setColumns(10);
		textNombreUsuario.setEditable(false);
		textNombreUsuario.setText(fila[2]);
		contentPanel.add(textNombreUsuario);
		

		JLabel labelComentNota = new JLabel("Comentario: ");
		labelComentNota.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		labelComentNota.setBounds(23, 165, 95, 14);
		contentPanel.add(labelComentNota);
		
		textAreaComent = new JTextArea();
		textAreaComent.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textAreaComent.setBounds(89, 190, 505, 166);
		textAreaComent.setText(fila[6]);
		contentPanel.add(textAreaComent);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setMnemonic('g');
		btnGuardar.setIcon(new ImageIcon("src/images/save.png"));
		btnGuardar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.setBounds(211, 380, 112, 41);
		contentPanel.add(btnGuardar);
		
		JButton butnCancelar = new JButton("Cancelar");
		butnCancelar.setMnemonic('c');
		butnCancelar.setIcon(new ImageIcon("src/images/adiooos.png"));
		butnCancelar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		butnCancelar.setBounds(362, 380, 112, 41);
		contentPanel.add(butnCancelar);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 679, 455);
		contentPanel.add(label);
		label.setIcon(new ImageIcon("src/images/fondogris.jpg"));
		
		btnGuardar.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnGurdarActionPerformed(o);
				
			}
		});
		
		butnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				butnCancelarActionPerformed(o);
			}
			private void butnCancelarActionPerformed (ActionEvent o){
				dispose();
			}

		});
			
	}
	
	protected void btnGurdarActionPerformed (ActionEvent o){
		try{
			
			String sql="UPDATE nota SET tema=?,comentario=? where id_nota=?";
			PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
			
			int id_nota;
			String comentario,tema;
			
			id_nota=Integer.parseInt(textFieldCodNota.getText());
			comentario=textAreaComent.getText();
			tema=textFieldTema.getText();
			
			prest.setString(1, tema);
			prest.setString(2, comentario);
			prest.setInt(3, id_nota);
			prest.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Nota modificada con éxito");
			dispose();
			
			}catch(SQLException exSql){
				exSql.printStackTrace();
				JOptionPane.showMessageDialog(null, exSql.getMessage());
			}catch(Exception e){
				e.printStackTrace();
			    JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos");
		}
	}
	
	
}

