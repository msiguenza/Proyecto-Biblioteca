package ClasesUsuario;

import tablasBaseDeDatos.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



import conexiones.Conexion;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;


public class BusquedaReservas extends JFrame {

	private JPanel contentPane;
	private JTable tablaPrestamo;
	private Usuario u;
	private Conexion c=new Conexion();
	
	
	public BusquedaReservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/user.png"));
		setUndecorated(true);
		u=new Usuario();
		
		setTitle("PRÉSTAMOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 397);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel label_estrellaoro = new JLabel("");
		label_estrellaoro.setIcon(new ImageIcon("src/images/estrellaoro.png"));
		label_estrellaoro.setBounds(308, 21, 32, 28);
		contentPane.add(label_estrellaoro);
		
		JLabel label_estrellaoro2 = new JLabel("");
		label_estrellaoro2.setIcon(new ImageIcon("src/images/estrellaoro.png"));
		label_estrellaoro2.setBounds(411, 21, 46, 28);
		contentPane.add(label_estrellaoro2);
		
		JLabel lblPrstamos = new JLabel("Reservas");
		lblPrstamos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrstamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrstamos.setForeground(new Color(220, 20, 60));
		lblPrstamos.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 16));
		lblPrstamos.setBounds(30, 21, 679, 38);
		contentPane.add(lblPrstamos);
		
		JButton btnVolverAtras = new JButton("Volver Atr\u00E1s");
		btnVolverAtras.setMnemonic('R');
		btnVolverAtras.setIcon(new ImageIcon("src/images/return.PNG"));
		btnVolverAtras.setBounds(279, 319, 168, 38);
		contentPane.add(btnVolverAtras);
		
		btnVolverAtras.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				btnVolverAtrasActionPerformed(o);
			}
			private void btnVolverAtrasActionPerformed (ActionEvent o){
				dispose();
			}

		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 250, 205));
		scrollPane.setBounds(30, 77, 679, 187);
		contentPane.add(scrollPane);
		
		
		tablaPrestamo = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
		}};
		
		tablaPrestamo.setBackground(new Color(255, 250, 205));
		scrollPane.setViewportView(tablaPrestamo);
	    prepararTablaAlquiler();
		
		JLabel label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		label.setBounds(0, 0, 744, 396);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setLabelFor(this);
		label.setIcon(new ImageIcon("src/images/fondito2.png"));
		contentPane.add(label);
	}

	
	
	
	private void prepararTablaAlquiler() {
		try{
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("SELECT cod_reserva,material.cod_material,material.titulo,material.tipo,fecha_res,fecha_dev,devolucion FROM reserva,usuario,material WHERE usuario.dni=reserva.dni and reserva.cod_material=material.cod_material and usuario.dni='"+u.getDni()+"';");
			DefaultTableModel m;
			
			String titulos[] = {"Cod. Reserva","Cod. Material","Título Material","Tipo","Fecha reserva","Fecha devolución","Devuelto"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaPrestamo.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			tablaPrestamo.setModel(m);
			while(resulSql.next()){
				fila[0]=resulSql.getString("cod_reserva");
				fila[1]=resulSql.getString("cod_material");
				fila[2]=resulSql.getString("titulo");
				fila[3]=resulSql.getString("tipo");
				fila[4]=resulSql.getString("fecha_res");
				fila[5]=resulSql.getString("fecha_dev");
				fila[6]=resulSql.getString("devolucion");
				m.addRow(fila);
			}
			int i;
			i=0;
			while(i<titulos.length){
				tablaPrestamo.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error",JOptionPane.ERROR_MESSAGE);
			
		}
	}	

}
