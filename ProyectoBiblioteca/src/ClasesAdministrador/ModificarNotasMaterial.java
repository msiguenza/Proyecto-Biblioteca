package ClasesAdministrador;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

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
import javax.swing.ImageIcon;


public class ModificarNotasMaterial extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTituloMat;
	private JTextField textFielTemaNota;
	private JTextField textFieldTipoMat;
	private JTextField textFieldDni;
	private JTextField textNombreUsuario;
	private JTextField textFieldNickUs;
	private String datosFila[];
	private JTextField textFieldCodNota;
	private JTextArea textAreaComentNotaMaterial;
	private Conexion c=new Conexion();
	

	
	public ModificarNotasMaterial(String fila[]) {
		setTitle("Modificar Notas De Material");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		datosFila=fila;
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
		labelCodNota.setForeground(SystemColor.menu);
		labelCodNota.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		labelCodNota.setBounds(23, 29, 132, 14);
		contentPanel.add(labelCodNota);
		
		textFieldCodNota = new JTextField();
		textFieldCodNota.setText((String) null);
		textFieldCodNota.setColumns(10);
		textFieldCodNota.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldCodNota.setBounds(151, 28, 140, 20);
		textFieldCodNota.setEditable(false);
		textFieldCodNota.setText(datosFila[0]);
		contentPanel.add(textFieldCodNota);
		
		
		JLabel lblTituloMat = new JLabel("T\u00EDtulo del material:");
		lblTituloMat.setForeground(SystemColor.menu);
		lblTituloMat.setBounds(23, 92, 184, 14);
		lblTituloMat.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblTituloMat);
			
		textFieldTituloMat = new JTextField();
		textFieldTituloMat.setBounds(151, 91, 140, 20);
		textFieldTituloMat.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTituloMat.setColumns(10);
		textFieldTituloMat.setEditable(false);
		textFieldTituloMat.setText(datosFila[2]);
		contentPanel.add(textFieldTituloMat);
		
		
		JLabel lblTipoTemaNota = new JLabel("Tema de la nota: ");
		lblTipoTemaNota.setForeground(SystemColor.menu);
		lblTipoTemaNota.setBounds(23, 61, 132, 14);
		lblTipoTemaNota.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblTipoTemaNota);
		
		textFielTemaNota = new JTextField();
		textFielTemaNota.setBounds(151, 61, 140, 20);
		textFielTemaNota.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFielTemaNota.setColumns(10);
    	textFielTemaNota.setText(datosFila[1]);
		contentPanel.add(textFielTemaNota);
		
		
		JLabel lblTipoMat = new JLabel("Tipo de material: ");
		lblTipoMat.setForeground(SystemColor.menu);
		lblTipoMat.setBounds(23, 123, 156, 14);
		lblTipoMat.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblTipoMat);
		
		textFieldTipoMat = new JTextField();
		textFieldTipoMat.setBounds(151, 122, 140, 20);
		textFieldTipoMat.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTipoMat.setColumns(10);
		textFieldTipoMat.setEditable(false);
		textFieldTipoMat.setText(datosFila[3]);
		contentPanel.add(textFieldTipoMat);
		
		
		JLabel lblDni = new JLabel("Dni usuario: ");
		lblDni.setForeground(SystemColor.menu);
		lblDni.setBounds(346, 29, 98, 14);
		lblDni.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblDni);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(454, 28, 140, 20);
		textFieldDni.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldDni.setColumns(10);
		textFieldDni.setEditable(false);
		textFieldDni.setText(datosFila[4]);
		contentPanel.add(textFieldDni);
		
		
		JLabel textFieldNombreUs = new JLabel("Nombre usuario:");
		textFieldNombreUs.setForeground(SystemColor.menu);
		textFieldNombreUs.setBounds(345, 61, 112, 14);
		textFieldNombreUs.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(textFieldNombreUs);
		
		textNombreUsuario = new JTextField();
		textNombreUsuario.setBounds(454, 60, 140, 20);
		textNombreUsuario.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textNombreUsuario.setColumns(10);
		textNombreUsuario.setEditable(false);
		textNombreUsuario.setText(datosFila[5]);
		contentPanel.add(textNombreUsuario);

		JLabel labelComentNota = new JLabel("Comentario: ");
		labelComentNota.setForeground(SystemColor.menu);
		labelComentNota.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		labelComentNota.setBounds(23, 165, 93, 14);
		contentPanel.add(labelComentNota);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setMnemonic('g');
		btnGuardar.setIcon(new ImageIcon("src/images/save.png"));
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardar.setBounds(211, 378, 112, 39);
		contentPanel.add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGuardarPerformed(e);
			}
		});
		
		JButton butnCancelar = new JButton("Cancelar");
		butnCancelar.setMnemonic('c');
		butnCancelar.setIcon(new ImageIcon("src/images/adiooos.png"));
		butnCancelar.setBounds(362, 378, 112, 39);
		contentPanel.add(butnCancelar);
		butnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		
		textAreaComentNotaMaterial = new JTextArea();
		textAreaComentNotaMaterial.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		textAreaComentNotaMaterial.setBounds(89, 190, 505, 166);
		textAreaComentNotaMaterial.setText(datosFila[6]);
		contentPanel.add(textAreaComentNotaMaterial);
		
		JLabel labelfondo = new JLabel("");
		labelfondo.setIcon(new ImageIcon("src/images/fondomodificarnotamaterial.png"));
		labelfondo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelfondo.setBounds(0, 0, 679, 455);
		contentPanel.add(labelfondo);
	
	}



	protected void btnGuardarPerformed(ActionEvent e) {
		try{
			
			String sql="UPDATE nota SET tema=?, comentario=? WHERE id_nota=? ";
			PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
			
			prest.setString(1,textFielTemaNota.getText());
			prest.setString(2,textAreaComentNotaMaterial.getText());
			prest.setInt(3, Integer.parseInt(textFieldCodNota.getText()));

			prest.executeUpdate();
			JOptionPane.showMessageDialog(null, "Nota modificada con éxito");
			dispose();
			}catch(SQLException exSql){
				exSql.printStackTrace();
				//JOptionPane.showMessageDialog(null, exSql.getMessage());
			}catch(Exception ex){
				ex.printStackTrace();
			  //  JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos");
		}
		
	}
}
