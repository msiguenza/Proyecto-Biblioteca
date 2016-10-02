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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.EventQueue;
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
import java.awt.Color;


public class ModificarMaterial extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTitulo;
	private JTextField textFieldTipo;
	private JTextField textFieldIsbn;
	private JTextField textFieldIssn;
	private JTextField textAutorDirector;
	private JTextField textFieldAno;
	private JTextField textFieldEditorial;
	private String datosFila[];
	private JTextField textFieldGenero;
	private JTextField textFieldPaginasDuracion;
	private JTextField textFieldCodMaterial;
	private Conexion c=new Conexion();
	

	//String fila[]
	public ModificarMaterial(String fila[]) {
		setTitle("Modificar Material");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		datosFila=fila;
		setModal(true);
		setBounds(100, 100, 571, 375);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 571, 375);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
   // Labels y Textfields		
		JLabel labelCodMaterial = new JLabel("Cod. Material:");
		labelCodMaterial.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		labelCodMaterial.setBounds(23, 29, 132, 14);
		contentPanel.add(labelCodMaterial);
		
		textFieldCodMaterial = new JTextField();
		textFieldCodMaterial.setText((String) null);
		textFieldCodMaterial.setColumns(10);
		textFieldCodMaterial.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldCodMaterial.setBounds(131, 26, 140, 20);
		textFieldCodMaterial.setEditable(false);
		textFieldCodMaterial.setText(datosFila[0]);
		contentPanel.add(textFieldCodMaterial);
		
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo *");
		lblTitulo.setForeground(Color.RED);
		lblTitulo.setBounds(23, 92, 78, 14);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblTitulo);
			
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(131, 91, 140, 20);
		textFieldTitulo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setText(datosFila[1]);
		contentPanel.add(textFieldTitulo);
		
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(23, 61, 78, 14);
		lblTipo.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblTipo);
		
		textFieldTipo = new JTextField();
		textFieldTipo.setBounds(131, 60, 140, 20);
		textFieldTipo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTipo.setColumns(10);
		textFieldTipo.setEditable(false);
		textFieldTipo.setText(datosFila[2]);
		contentPanel.add(textFieldTipo);
		
		
		JLabel lblIsbn = new JLabel("ISBN: *");
		lblIsbn.setForeground(Color.RED);
		lblIsbn.setBounds(23, 123, 78, 14);
		lblIsbn.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblIsbn);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(131, 122, 140, 20);
		textFieldIsbn.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldIsbn.setColumns(10);
		textFieldIsbn.setText(datosFila[4]);
		contentPanel.add(textFieldIsbn);
		
		
		JLabel lblIssn = new JLabel("ISSN: *");
		lblIssn.setForeground(Color.RED);
		lblIssn.setBounds(23, 152, 78, 14);
		lblIssn.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblIssn);
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(131, 151, 140, 20);
		textFieldIssn.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldIssn.setColumns(10);
		textFieldIssn.setText(datosFila[5]);
		contentPanel.add(textFieldIssn);
		
		
		JLabel textFieldAutorDirector = new JLabel("Autor/Director:");
		textFieldAutorDirector.setBounds(23, 184, 112, 14);
		textFieldAutorDirector.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(textFieldAutorDirector);
		
		textAutorDirector = new JTextField();
		textAutorDirector.setBounds(131, 184, 140, 20);
		textAutorDirector.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textAutorDirector.setColumns(10);
		textAutorDirector.setText(datosFila[6]);
		contentPanel.add(textAutorDirector);
		
		
		JLabel lblEditorial = new JLabel("Editorial: ");
		lblEditorial.setBounds(312, 27, 112, 14);
		lblEditorial.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPanel.add(lblEditorial);
		
		textFieldEditorial = new JTextField();
		textFieldEditorial.setColumns(10);
		textFieldEditorial.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldEditorial.setBounds(383, 26, 140, 20);
		textFieldEditorial.setText(datosFila[7]);
		contentPanel.add(textFieldEditorial);
		

		JLabel labelAno = new JLabel("A\u00F1o: ");
		labelAno.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		labelAno.setBounds(310, 52, 78, 14);
		contentPanel.add(labelAno);
		
		textFieldAno = new JTextField();
		textFieldAno.setColumns(10);
		textFieldAno.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldAno.setBounds(383, 52, 140, 20);
		textFieldAno.setText(datosFila[8]);
		contentPanel.add(textFieldAno);
		
		
		JLabel lblPaginasDuracion = new JLabel("P\u00E1ginas/Duracion (min):");
		lblPaginasDuracion.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblPaginasDuracion.setBounds(312, 77, 224, 14);
		contentPanel.add(lblPaginasDuracion);
		
		textFieldPaginasDuracion = new JTextField();
		textFieldPaginasDuracion.setText((String) null);
		textFieldPaginasDuracion.setColumns(10);
		textFieldPaginasDuracion.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldPaginasDuracion.setBounds(383, 101, 140, 20);
		textFieldPaginasDuracion.setText(datosFila[9]);
		contentPanel.add(textFieldPaginasDuracion);
		
		
		JLabel lblGenero = new JLabel("G\u00E9nero:");
		lblGenero.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lblGenero.setBounds(310, 134, 78, 14);
		contentPanel.add(lblGenero);
	
		textFieldGenero = new JTextField();
		textFieldGenero.setText((String) null);
		textFieldGenero.setColumns(10);
		textFieldGenero.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldGenero.setBounds(383, 145, 140, 20);
		textFieldGenero.setText(datosFila[10]);
		contentPanel.add(textFieldGenero);
		
	
        //------ Botones ------------------
		JButton btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setMnemonic('g');
		btnGuardarCambios.setBounds(226, 291, 112, 41);
		contentPanel.add(btnGuardarCambios);
		btnGuardarCambios.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardarCambios.setIcon(new ImageIcon("src/images/save.png"));
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(422, 291, 112, 41);
		contentPanel.add(btnCancelar);
		btnCancelar.setIcon(new ImageIcon("src/images/adiooos.png"));
		
		JLabel labelfondomaterial = new JLabel("");
		labelfondomaterial.setBounds(0, 0, 571, 375);
		contentPanel.add(labelfondomaterial);
		labelfondomaterial.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelfondomaterial.setIcon(new ImageIcon("src/images/fondoverde.jpg"));
		
		btnCancelar.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnCancelarActionPerformed(o);
			}

			private void btnCancelarActionPerformed(ActionEvent o) {
				dispose();
				UsuariosMaterialesDoReservas u= new UsuariosMaterialesDoReservas();
				u.setVisible(true);
			}
			
		});
		
		
		btnGuardarCambios.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnGurdarCambiosActionPerformed(o);
			}
		});
		
		if(datosFila[2].equalsIgnoreCase("Dvd")){
			textFieldIsbn.setEditable(false);
			textFieldIssn.setEditable(false);
			textFieldEditorial.setEditable(false);
		}else if(datosFila[2].equalsIgnoreCase("libro")){
			textFieldIssn.setEditable(false);
		}else{
			textFieldIsbn.setEditable(false);
		}
		
	}
/// ----------- Fin Constructor --------------

// ------- Acciones botones ---------------
	//------- Acción de guardar cambios
	protected void btnGurdarCambiosActionPerformed(ActionEvent o) {
		try{
			
			if(datosFila[2].equalsIgnoreCase("Dvd")){
				String sql="UPDATE material SET autor_director=?, titulo=?, ano=?, paginas_duracion=?, genero=?, tipo=? WHERE cod_material=? ";
				PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
				prest.setString(1, textAutorDirector.getText());
				prest.setString(2, textFieldTitulo.getText());
				prest.setString(3, textFieldAno.getText());
				prest.setInt(4, Integer.parseInt(textFieldPaginasDuracion.getText()));
				prest.setString(5, textFieldGenero.getText());
				prest.setString(6, textFieldTipo.getText());
				prest.setInt(7, Integer.parseInt(textFieldCodMaterial.getText()));
				prest.executeUpdate();
			}else if(datosFila[2].equalsIgnoreCase("libro")){
				String sql2="UPDATE material SET isbn=?, autor_director=?, titulo=?, editorial=?, ano=?, paginas_duracion=?, genero=?, tipo=? WHERE cod_material=? ";
				PreparedStatement prest2=(PreparedStatement) c.getConectado().prepareStatement(sql2);
				prest2.setInt(1, Integer.parseInt(textFieldIsbn.getText()));
				prest2.setString(2, textAutorDirector.getText());
				prest2.setString(3, textFieldTitulo.getText());
				prest2.setString(4, textFieldEditorial.getText());
				prest2.setString(5, textFieldAno.getText());
				prest2.setInt(6, Integer.parseInt(textFieldPaginasDuracion.getText()));
				prest2.setString(7, textFieldGenero.getText());
				prest2.setString(8, textFieldTipo.getText());
				prest2.setInt(9, Integer.parseInt(textFieldCodMaterial.getText()));
				prest2.executeUpdate();
			}else{
				String sql3="UPDATE material SET issn=?, autor_director=?, titulo=?, editorial=?, ano=?, paginas_duracion=?, genero=?, tipo=? WHERE cod_material=? ";
				PreparedStatement prest3=(PreparedStatement) c.getConectado().prepareStatement(sql3);
				prest3.setInt(1, Integer.parseInt(textFieldIssn.getText()));
				prest3.setString(2, textAutorDirector.getText());
				prest3.setString(3, textFieldTitulo.getText());
				prest3.setString(4, textFieldEditorial.getText());
				prest3.setString(5, textFieldAno.getText());
				prest3.setInt(6, Integer.parseInt(textFieldPaginasDuracion.getText()));
				prest3.setString(7, textFieldGenero.getText());
				prest3.setString(8, textFieldTipo.getText());
				prest3.setInt(9, Integer.parseInt(textFieldCodMaterial.getText()));
				prest3.executeUpdate();
			}
			JOptionPane.showMessageDialog(null, "Material modificado con éxito");
			dispose();
			}catch(SQLException exSql){
				exSql.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			    JOptionPane.showMessageDialog(null, "Error ");
		}
	}
	
}