package ClasesAdministrador;
import java.awt.BorderLayout;   
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
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
import com.toedter.calendar.JDateChooser;

import conexiones.Conexion;

import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;


public class CrearArticulo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldISSN;
	private JTextField textFieldAutor;
	private JTextField textFieldTitulo;
	private JTextField textFieldIni;
	private JTextField textFieldFin;
	private Conexion c=new Conexion();
	private JDateChooser dateChooserFecha;

	
	public CrearArticulo(String c) {
		setTitle("Creación Artículos");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setModal(true);
		setUndecorated(true);
		setBounds(100, 100, 313, 303);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		textFieldISSN = new JTextField();
		textFieldISSN.setBounds(149, 23, 140, 20);
		textFieldISSN.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldISSN.setEditable(false);
		textFieldISSN.setText(c);
		contentPanel.add(textFieldISSN);
		textFieldISSN.setColumns(10);
		
		JLabel lblISSN = new JLabel("ISSN:");
		lblISSN.setBounds(24, 24, 78, 14);
		lblISSN.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblISSN);
		
		
		textFieldAutor = new JTextField();
		textFieldAutor.setBounds(149, 54, 140, 20);
		textFieldAutor.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldAutor);
		textFieldAutor.setColumns(10);
		
		JLabel lblAutor = new JLabel("*  Autor: ");
		lblAutor.setForeground(Color.RED);
		lblAutor.setBounds(24, 55, 78, 14);
		lblAutor.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblAutor);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(149, 85, 140, 20);
		textFieldTitulo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		contentPanel.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(24, 86, 78, 14);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblTitulo);
		
		
		JLabel lblAno = new JLabel("A\u00F1o");
		lblAno.setBounds(24, 115, 78, 14);
		lblAno.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		contentPanel.add(lblAno);
		
		dateChooserFecha = new JDateChooser();
		dateChooserFecha.setDateFormatString("yyyy/MM/dd");
		dateChooserFecha.setBounds(149, 116, 140, 20);
		contentPanel.add(dateChooserFecha);
		
		JButton btnCrearArticulo = new JButton("A\u00F1adir");
		btnCrearArticulo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCrearArticulo.setMnemonic('a');
		btnCrearArticulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCrearArticulo.setBounds(24, 227, 120, 31);
		btnCrearArticulo.setIcon(new ImageIcon("src/images/confirm.png"));
		contentPanel.add(btnCrearArticulo);
		
		
		btnCrearArticulo.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnCrearArticuloActionPerformed(o);
			}
		});
		
		
		JButton Salir = new JButton("Cancelar");
		Salir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Salir.setMnemonic('c');
		Salir.setBounds(169, 227, 120, 31);
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
		
		JLabel lblInicio = new JLabel("P\u00E1gina inicio:");
		lblInicio.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblInicio.setBounds(24, 148, 134, 14);
		contentPanel.add(lblInicio);
		
		JLabel lblFin = new JLabel("P\u00E1gina fin:");
		lblFin.setFont(new Font("Comic Sans MS", Font.ITALIC, 13));
		lblFin.setBounds(24, 179, 134, 14);
		contentPanel.add(lblFin);
		
		textFieldIni = new JTextField();
		textFieldIni.setText("0");
		textFieldIni.setColumns(10);
		textFieldIni.setBounds(149, 147, 140, 20);
		contentPanel.add(textFieldIni);
		
		textFieldFin = new JTextField();
		textFieldFin.setText("0");
		textFieldFin.setColumns(10);
		textFieldFin.setBounds(149, 178, 140, 20);
		contentPanel.add(textFieldFin);
		
		JLabel lblCamposObligatorios = new JLabel("Campos obligatorios con el s\u00EDmbolo *");
		lblCamposObligatorios.setForeground(Color.RED);
		lblCamposObligatorios.setBounds(24, 269, 216, 14);
		contentPanel.add(lblCamposObligatorios);
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblImagenFondo.setBounds(0, 0, 314, 303);
		lblImagenFondo.setIcon(new ImageIcon("src/images/createuser.jpg"));
		contentPanel.add(lblImagenFondo);
		
		
	}
	
	protected void btnCrearArticuloActionPerformed (ActionEvent o){
		try{
			
			String sql="INSERT INTO articulo (issn,autor,titulo,ano,pagina_ini,pagina_fin) VALUES" + "(?,?,?,?,?,?)";//Consulta preparada para introducir los valores posteriormente
			
			int issn,pagina_ini,pagina_fin;
			String autor, titulo, ano;
			
			
			issn=Integer.parseInt(textFieldISSN.getText());
			autor=textFieldAutor.getText();
			titulo=textFieldTitulo.getText();
			
			pagina_ini=Integer.parseInt(textFieldIni.getText());
			pagina_fin=Integer.parseInt(textFieldFin.getText());
			
			String fecha=dateChooserFecha.getDateFormatString();
			Date fechaDAte=new Date();
			SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy/MM/dd");
			fecha=sdf.format(fechaDAte);
			
			
			PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
			
			prest.setInt(1, issn);
			prest.setString(2, autor);
			prest.setString(3, titulo);
			prest.setString(4, fecha);
			prest.setInt(5, pagina_ini);
			prest.setInt(6, pagina_fin);
			prest.execute();
			
			JOptionPane.showMessageDialog(null, "Articulo creado");
			dispose();
		}catch(SQLException exSql){
			JOptionPane.showMessageDialog(null, "Posibles errores:\n- Formato de la fecha incorrecta. Introduce formato: AA-MM-DD con separadores - o /\n- Error de conexión con la base de datos, asegúrese de tener activado el host\n\n Error Mysq: "+exSql.getMessage());
		}catch(Exception e){
		    JOptionPane.showMessageDialog(null, "Campos de páginas vacíos o con letras, introduzca números");
		}
		
	}
}



