 package ClasesAdministrador;
import java.awt.BorderLayout;   
import java.awt.Toolkit;

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
import javax.swing.border.LineBorder;
import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;
import conexiones.Conexion;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.awt.Color;

public class ModificarArticulo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCod;
	private JTextField textFieldISSN;
	private JTextField textFieldAutor;
	private JTextField textFieldTitulo;
	private JTextField textFieldAno;
	private JTextField textFieldFin;
	private JTextField textFieldInicio;
	private JDateChooser dateChooserFecha;
	private Conexion c=new Conexion();
		
	public ModificarArticulo(String datosFila[]) throws ParseException {
		setTitle("Modificación Artículo");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 313, 387);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		
		JLabel lblCod = new JLabel("Cod. Articulo:");
		lblCod.setBounds(24, 40, 112, 14);
		lblCod.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblCod);
			
		textFieldCod = new JTextField();
		textFieldCod.setBounds(132, 40, 140, 20);
		textFieldCod.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldCod.setColumns(10);
		textFieldCod.setText(datosFila[0]);
		textFieldCod.setEditable(false);
		contentPanel.add(textFieldCod);
		
		JLabel lblISSN = new JLabel("ISSN:");
		lblISSN.setBounds(24, 71, 112, 14);
		lblISSN.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblISSN);
		
		textFieldISSN = new JTextField();
		textFieldISSN.setBounds(132, 71, 140, 20);
		textFieldISSN.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldISSN.setColumns(10);
		textFieldISSN.setText(datosFila[1]);
		textFieldISSN.setEditable(false);
		contentPanel.add(textFieldISSN);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(24, 102, 112, 14);
		lblAutor.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblAutor);
		
		textFieldAutor = new JTextField();
		textFieldAutor.setBounds(132, 102, 140, 20);
		textFieldAutor.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldAutor.setColumns(10);
		textFieldAutor.setText(datosFila[2]);
		contentPanel.add(textFieldAutor);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(24, 133, 112, 14);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(132, 133, 140, 20);
		textFieldTitulo.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setText(datosFila[3]);
		contentPanel.add(textFieldTitulo);
		
		JLabel lblAno = new JLabel("A\u00F1o:");
		lblAno.setBounds(24, 164, 112, 14);
		lblAno.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblAno);
	
		
		JLabel lblInicio = new JLabel("Pag. Inicio:");
		lblInicio.setBounds(24, 197, 112, 14);
		lblInicio.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblInicio);
		
		textFieldInicio = new JTextField();
		textFieldInicio.setColumns(10);
		textFieldInicio.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldInicio.setBounds(132, 197, 140, 20);
		textFieldInicio.setText(datosFila[5]);
		contentPanel.add(textFieldInicio);

		JLabel labelFin = new JLabel("Pag. Fin:");
		labelFin.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		labelFin.setBounds(24, 228, 112, 14);
		contentPanel.add(labelFin);
		
		textFieldFin = new JTextField();
		textFieldFin.setColumns(10);
		textFieldFin.setBorder(new LineBorder(SystemColor.inactiveCaptionText, 1, true));
		textFieldFin.setBounds(132, 228, 140, 20);
		textFieldFin.setText(datosFila[6]);
		contentPanel.add(textFieldFin);
		
		JButton btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setMnemonic('g');
		btnGuardarCambios.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnGuardarCambios.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGuardarCambios.setBounds(24, 291, 112, 39);
		btnGuardarCambios.setIcon(new ImageIcon("src/images/save.png"));
		contentPanel.add(btnGuardarCambios);
		
		String fechaString=datosFila[4];
		SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
		Date fecha=formato.parse(fechaString);
		
		dateChooserFecha = new JDateChooser();
		dateChooserFecha.setDateFormatString("yyyy/MM/dd");
		dateChooserFecha.setBounds(112, 163, 140, 20);
		dateChooserFecha.setDate(fecha);
		contentPanel.add(dateChooserFecha);
		
		btnGuardarCambios.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnGurdarCambiosActionPerformed(o);
			}
		});
		
		
		JButton Salir = new JButton("Cancelar");
		Salir.setMnemonic('c');
		Salir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Salir.setBounds(160, 291, 112, 39);
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
		
		JLabel lblImagenFondo = new JLabel("");
		lblImagenFondo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblImagenFondo.setBounds(0, 0, 314, 387);
		lblImagenFondo.setIcon(new ImageIcon("src/images/fondito4.jpg"));
		contentPanel.add(lblImagenFondo);
		
		
		
	}
	
	protected void btnGurdarCambiosActionPerformed (ActionEvent o){
		try{
			
			String sql="UPDATE articulo SET cod_articulo=?, issn=?, autor=?, titulo=?, ano=?, pagina_ini=?, pagina_fin=? WHERE cod_articulo=? ";
			PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
			
			String autor,titulo,ano;
			int cod_articulo,issn,pagina_ini,pagina_fin;
		
			cod_articulo=Integer.parseInt(textFieldCod.getText());
			issn=Integer.parseInt(textFieldISSN.getText());
			autor=textFieldAutor.getText();
			titulo=textFieldTitulo.getText();
			String fecha=dateChooserFecha.getDateFormatString();
			
			Date fechaDAte=new Date();
			SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy/MM/dd");
			fecha=sdf.format(fechaDAte);
			
		
			JOptionPane.showMessageDialog(null, fecha);
			
				
			pagina_ini=Integer.parseInt(textFieldInicio.getText());
			pagina_fin=Integer.parseInt(textFieldFin.getText());
			
			prest.setInt(1, cod_articulo);
			prest.setInt(2, issn);
			prest.setString(3, autor);
			prest.setString(4, titulo);
			prest.setString(5, fecha);
			prest.setInt(6, pagina_ini);
			prest.setInt(7, pagina_fin);
			prest.setInt(8, cod_articulo);
			prest.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Articulo modificado con éxito");
			dispose();
			}catch(SQLException exSql){
				JOptionPane.showMessageDialog(null, "Posibles errores:\n- Formato de la fecha incorrecta. Introduce formato: AA-MM-DD con separadores - o /\n- Error de conexión con la base de datos, asegúrese de tener activado el host\n\n Error Mysq: "+exSql.getMessage());
				exSql.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			    JOptionPane.showMessageDialog(null, "Campos de páginas vacíos o con letras, introduzca números");
		}
	}
}