package ClasesAdministrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import conexiones.Conexion;
import javax.swing.JTextPane;



public class InformacionAdmin extends JDialog {

	private final static JPanel contentPanel = new JPanel();
	private int materiales;
	private int usuarios;
	private int reservas;
	private int notas;
	private int articulos;
	private Conexion c=new Conexion();

	public InformacionAdmin() throws SQLException, IOException {
		setTitle("Información");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setModal(true);	
		setLocationRelativeTo(null);
		
			try{
				
			CallableStatement cs=c.getConectado().prepareCall("{? = call cuantosusuarios()}");
			cs.registerOutParameter(1,Types.INTEGER);
			cs.execute();
			usuarios=cs.getInt(1);
			
			}catch(SQLException e){
				
			 System.out.println(e);
			 
			}
			
			try{
				
			CallableStatement cs=c.getConectado().prepareCall("{? = call cuantosmateriales()}");
			cs.registerOutParameter(1,Types.INTEGER);
			cs.execute();
			materiales=cs.getInt(1);
				
			}catch(SQLException e){
					
			 System.out.println(e);
				 
			}
			
			try{
				
			CallableStatement cs=c.getConectado().prepareCall("{? = call cuantasnotas()}");
			cs.registerOutParameter(1,Types.INTEGER);
			cs.execute();
			notas=cs.getInt(1);
				
			}catch(SQLException e){
					
				System.out.println(e);
				 
			}
			
			try{
				
			CallableStatement cs=c.getConectado().prepareCall("{? = call cuantasreservas()}");
			cs.registerOutParameter(1,Types.INTEGER);
			cs.execute();
			reservas=cs.getInt(1);
				
			}catch(SQLException e){
					
				System.out.println(e);
				 
			}
			
			try{
				
			CallableStatement cs=c.getConectado().prepareCall("{? = call cuantosarticulos()}");
			cs.registerOutParameter(1,Types.INTEGER);
			cs.execute();
			articulos=cs.getInt(1);
				
			}catch(SQLException e){
					
				System.out.println(e);
				 
			}
		
		
		
		setUndecorated(true);
		setBounds(100, 100, 490, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JButton Salir = new JButton("Cerrar");
		Salir.setIcon(new ImageIcon("src/images/adiooos.png"));
		Salir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		Salir.setBounds(184, 389, 87, 31);
		Salir.setMnemonic('c');
		contentPanel.add(Salir);
		
		
		Salir.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				SalirActionPerformed(o);
			}
			private void SalirActionPerformed (ActionEvent o){
			
				dispose();
			}
		});
	
		//--------------------------------------------------------------------------
		InputMap map = new InputMap();

		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		       
		Salir.setInputMap(0, map);
		
		JLabel lblInfor = new JLabel("Informaci\u00F3n");
		lblInfor.setBounds(91, 30, 212, 31);
		lblInfor.setForeground(Color.YELLOW);
		lblInfor.setFont(new Font("VAGRounded BT", Font.BOLD | Font.ITALIC, 18));
		lblInfor.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfor.setHorizontalTextPosition(SwingConstants.CENTER);
		contentPanel.add(lblInfor);
		
		JLabel lbTotal = new JLabel("Hay un total de :");
		lbTotal.setBounds(10, 72, 167, 14);
		lbTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lbTotal.setForeground(Color.WHITE);
		lbTotal.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lbTotal);
		
		JLabel lblUsers = new JLabel("- Usuarios :");
		lblUsers.setBounds(61, 110, 140, 14);
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setForeground(Color.WHITE);
		lblUsers.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblUsers);
		
		JLabel lblMateriales = new JLabel("- Materiales");
		lblMateriales.setBounds(61, 149, 140, 14);
		lblMateriales.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateriales.setForeground(Color.WHITE);
		lblMateriales.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblMateriales);
		
		JLabel lblPrestamos = new JLabel("- Prestamos");
		lblPrestamos.setBounds(61, 187, 140, 14);
		lblPrestamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrestamos.setForeground(Color.WHITE);
		lblPrestamos.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblPrestamos);
		
		JLabel lblNotas = new JLabel(" - Notas      ");
		lblNotas.setBounds(61, 227, 140, 14);
		lblNotas.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotas.setForeground(Color.WHITE);
		lblNotas.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblNotas);
		
		JLabel label_1 = new JLabel("- Articulos");
		label_1.setBounds(61, 270, 140, 14);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(label_1);
		
		JLabel lblNewLabel = new JLabel(String.valueOf(usuarios));
		lblNewLabel.setBounds(211, 110, 46, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		contentPanel.add(lblNewLabel);
		
		JLabel label = new JLabel(String.valueOf(materiales));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		label.setBounds(211, 149, 46, 14);
		contentPanel.add(label);
		
		JLabel label_2 = new JLabel(String.valueOf(reservas));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		label_2.setBounds(211, 187, 46, 14);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel(String.valueOf(notas));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		label_3.setBounds(211, 227, 46, 14);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel(String.valueOf(articulos));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		label_4.setBounds(211, 270, 46, 14);
		contentPanel.add(label_4);
		
		JTextPane txtpnSeRecomiendaQue = new JTextPane();
		txtpnSeRecomiendaQue.setOpaque(false);
		txtpnSeRecomiendaQue.setEditable(false);
		txtpnSeRecomiendaQue.setFont(new Font("Gabriola", Font.BOLD | Font.ITALIC, 13));
		txtpnSeRecomiendaQue.setText("Se recomienda cambiar la contrase\u00F1a del usuario administrador que contiene la aplicaci\u00F3n por defecto por motivos de seguridad ");
		txtpnSeRecomiendaQue.setBounds(38, 308, 442, 58);
		contentPanel.add(txtpnSeRecomiendaQue);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("src/images/informacion.jpg"));
		fondo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		fondo.setBounds(0, 0, 490, 445);
		contentPanel.add(fondo);
		
	}
	
	public static void main(String[] args) {
		try {
			InformacionAdmin dialog = new InformacionAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(contentPanel);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
