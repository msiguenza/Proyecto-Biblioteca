package ClasesAdministrador;
import java.awt.BorderLayout;
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
import java.awt.Component;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;


public class CrearMaterial extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldISBN;
	private JTextField textFieldissn;
	private JTextField textFieldautor;
	private JTextField textFieldTitulo;
	private JTextField textFieldEditorial;
	private JTextField textFieldPagDur;
	private JTextField textFieldano;
	private JComboBox comboBoxTipo;
	private JTextField textFieldGenero;
	private Conexion c=new Conexion();
	private JLabel lblIssn;
	private JLabel lblisbn;
	private JLabel lblAutorDirector;
	private JLabel lblTitulo;
	private JLabel label;

	
	public CrearMaterial() {
		setTitle("Crear Material");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 539, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		lblisbn = new JLabel("ISBN:");
		lblisbn.setBounds(24, 51, 78, 14);
		lblisbn.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblisbn);
		
		textFieldISBN = new JTextField();
		textFieldISBN.setBounds(181, 50, 140, 20);
		textFieldISBN.setEditable(false);
		textFieldISBN.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldISBN.setColumns(10);
		contentPanel.add(textFieldISBN);
		
		textFieldissn = new JTextField();
		textFieldissn.setBounds(181, 75, 140, 20);
		textFieldissn.setEditable(false);
		textFieldissn.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldissn.setColumns(10);
		contentPanel.add(textFieldissn);
	
	    lblIssn = new JLabel("ISSN:");
	    lblIssn.setBounds(24, 76, 78, 14);
		lblIssn.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblIssn);
		
		
		lblAutorDirector = new JLabel("Autor/Director:");
		lblAutorDirector.setBounds(24, 102, 120, 14);
		lblAutorDirector.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblAutorDirector);
		
		textFieldautor = new JTextField();
		textFieldautor.setBounds(181, 101, 140, 20);
		textFieldautor.setEditable(false);
		textFieldautor.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldautor);
		textFieldautor.setColumns(10);
		
		JLabel lblAno = new JLabel("A\u00F1o: ");
		lblAno.setBounds(24, 197, 88, 14);
		lblAno.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblAno);
		
		lblTitulo = new JLabel("T\u00EDtulo: ");
		lblTitulo.setBounds(24, 133, 78, 14);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(181, 132, 140, 20);
		textFieldTitulo.setEditable(false);
		textFieldTitulo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(24, 164, 120, 14);
		lblEditorial.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblEditorial);
		
		textFieldEditorial = new JTextField();
		textFieldEditorial.setBounds(181, 163, 140, 20);
		textFieldEditorial.setEditable(false);
		textFieldEditorial.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldEditorial);
		textFieldEditorial.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(24, 26, 78, 14);
		lblTipo.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblTipo);
		
		JButton btnAnadirMaterial = new JButton("A\u00F1adir");
		btnAnadirMaterial.setBounds(24, 323, 120, 31);
		btnAnadirMaterial.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnAnadirMaterial.setMnemonic('a');
		btnAnadirMaterial.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAnadirMaterial.setIcon(new ImageIcon("src/images/confirm.png"));
		contentPanel.add(btnAnadirMaterial);
		
		
		btnAnadirMaterial.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnAnadirMaterialPerformed(o);
			}
		});
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(165, 323, 120, 31);
		btnCancelar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCancelar.setMnemonic('c');
		btnCancelar.setIcon(new ImageIcon("src/images/adiooos.png"));
		contentPanel.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelarPerformed(e);
			}
			private void btnCancelarPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel labelPagDur = new JLabel("P\u00E1ginas/Duraci\u00F3n (min):");
		labelPagDur.setBounds(24, 228, 167, 14);
		labelPagDur.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(labelPagDur);
		
		textFieldPagDur = new JTextField();
		textFieldPagDur.setBounds(181, 227, 140, 20);
		textFieldPagDur.setEditable(false);
		textFieldPagDur.setColumns(10);
		textFieldPagDur.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldPagDur);
		
		textFieldano = new JTextField();
		textFieldano.setBounds(181, 196, 140, 20);
		textFieldano.setEditable(false);
		textFieldano.setColumns(10);
		textFieldano.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldano);
		
		textFieldGenero = new JTextField();
		textFieldGenero.setBounds(181, 254, 140, 20);
		textFieldGenero.setEditable(false);
		textFieldGenero.setColumns(10);
		textFieldGenero.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldGenero);
		
		JLabel labelGenero = new JLabel("G\u00E9nero:");
		labelGenero.setBounds(24, 257, 167, 14);
		labelGenero.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(labelGenero);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(181, 25, 140, 20);
		comboBoxTipo.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"-- Selecciona tipo --", "Libro", "Revista", "Dvd"}));
		comboBoxTipo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(comboBoxTipo);
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setBounds(0, 0, 539, 389);
		lblImagenFondo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblImagenFondo.setIcon(new ImageIcon("src/images/iniciofondo.PNG"));
		contentPanel.add(lblImagenFondo);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("src/images/Book.png"));
		label.setBounds(284, 0, 255, 389);
		contentPanel.add(label);
		comboBoxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBoxTipo.getSelectedIndex()==1){ //Libros
					lblisbn.setForeground(Color.RED);
					lblAutorDirector.setForeground(Color.RED);
					textFieldISBN.setEditable(true);
					textFieldissn.setEditable(false);
					textFieldissn.setText("");
					textFieldano.setEditable(true);
					textFieldautor.setEditable(true);
					
					textFieldEditorial.setEditable(true);
					textFieldGenero.setEditable(true);
					textFieldTitulo.setEditable(true);
					textFieldPagDur.setEditable(true);
				}else if(comboBoxTipo.getSelectedIndex()==2){ //Revistas
					lblIssn.setForeground(Color.RED);
					lblisbn.setForeground(Color.BLACK);
					textFieldissn.setEditable(true);
					textFieldISBN.setEditable(false);
					textFieldISBN.setText("");
					textFieldano.setEditable(true);
					textFieldautor.setEditable(true);
					textFieldEditorial.setEditable(true);
					textFieldGenero.setEditable(true);
					textFieldTitulo.setEditable(true);
					textFieldPagDur.setEditable(true);
				}else{//DVD
					lblIssn.setForeground(Color.BLACK);
					lblTitulo.setForeground(Color.RED);
					textFieldissn.setEditable(false);
					textFieldISBN.setEditable(false);
					textFieldISBN.setText("");
					textFieldissn.setText("");
					textFieldano.setEditable(true);
					textFieldautor.setEditable(true);
					textFieldEditorial.setEditable(false);
					textFieldEditorial.setText("");
					textFieldGenero.setEditable(true);
					textFieldTitulo.setEditable(true);
					textFieldPagDur.setEditable(true);
				}
			}
		});
		
		
	}
	
	protected void btnAnadirMaterialPerformed (ActionEvent o){
		try{
		
			
			if(comboBoxTipo.getSelectedIndex()==1){
				String sql="INSERT INTO material (isbn,autor_director,titulo,editorial,ano,paginas_duracion,genero,tipo) VALUES" + "(?,?,?,?,?,?,?,?)";//Consulta preparada para introducir los valores posteriormente
				PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
				prest.setInt(1, Integer.parseInt(textFieldISBN.getText()));//
				prest.setString(2, textFieldautor.getText());
				prest.setString(3, textFieldTitulo.getText());//
				prest.setString(4, textFieldEditorial.getText());
				prest.setString(5, textFieldano.getText());
				prest.setInt(6, Integer.parseInt(textFieldPagDur.getText()));
				prest.setString(7, textFieldGenero.getText());//
				prest.setString(8, (String)comboBoxTipo.getSelectedItem());
				prest.execute();
			}else if(comboBoxTipo.getSelectedIndex()==2){
				String sql="INSERT INTO material (issn,autor_director,titulo,editorial,ano,paginas_duracion,genero,tipo) VALUES" + "(?,?,?,?,?,?,?,?)";//Consulta preparada para introducir los valores posteriormente
				PreparedStatement prest2=(PreparedStatement) c.getConectado().prepareStatement(sql);
				prest2.setInt(1, Integer.parseInt(textFieldissn.getText()));//
				prest2.setString(2, textFieldautor.getText());
				prest2.setString(3, textFieldTitulo.getText());//
				prest2.setString(4, textFieldEditorial.getText());
				prest2.setString(5, textFieldano.getText());
				prest2.setInt(6, Integer.parseInt(textFieldPagDur.getText()));
				prest2.setString(7, textFieldGenero.getText());//
				prest2.setString(8, (String)comboBoxTipo.getSelectedItem());
				prest2.execute();
			}else{
				String sql="INSERT INTO material (autor_director,titulo,editorial,ano,paginas_duracion,genero,tipo) VALUES" + "(?,?,?,?,?,?,?)";//Consulta preparada para introducir los valores posteriormente
				PreparedStatement prest3=(PreparedStatement) c.getConectado().prepareStatement(sql);
				prest3.setString(1, textFieldautor.getText());
				prest3.setString(2, textFieldTitulo.getText());//
				prest3.setString(3, textFieldEditorial.getText());
				prest3.setString(4, textFieldano.getText());
				prest3.setInt(5, Integer.parseInt(textFieldPagDur.getText()));
				prest3.setString(6, textFieldGenero.getText());//
				prest3.setString(7, (String)comboBoxTipo.getSelectedItem());
				prest3.execute();
			}
			JOptionPane.showMessageDialog(null, "Material creado con éxito");
			dispose();
			}catch(SQLException exSql){
				exSql.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error de conexión","Error",JOptionPane.ERROR_MESSAGE);
			}catch(Exception e){
				e.printStackTrace();
				System.out.print("2");
				JOptionPane.showMessageDialog(null, "Introduce los parámetros correctos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
