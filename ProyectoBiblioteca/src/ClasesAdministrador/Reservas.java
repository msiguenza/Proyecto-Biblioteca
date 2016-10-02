package ClasesAdministrador;
  
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//import com.mysql.jdbc.PreparedStatement;

import conexiones.Conexion;
import javax.swing.border.LineBorder;

public class Reservas extends JFrame {

	private JPanel contentPane;
	private JTable tablaPrestamos;
	private Conexion c=new Conexion();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Reservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);;
			
		JLabel lblPrstamos = new JLabel("Reserva");
		lblPrstamos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrstamos.setFont(new Font("VAGRounded BT", Font.BOLD | Font.ITALIC, 16));
		lblPrstamos.setBounds(41, 11, 594, 23);
		contentPane.add(lblPrstamos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 250, 205));
		scrollPane.setBounds(41, 51, 594, 170);
		contentPane.add(scrollPane);
		
		tablaPrestamos = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
		}};
		
		tablaPrestamos.setBackground(new Color(255, 250, 205));
		scrollPane.setViewportView(tablaPrestamos);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCerrar.setMnemonic('c');
		btnCerrar.setIcon(new ImageIcon("src/Images/adiooos.PNG"));

		btnCerrar.setBounds(582, 295, 89, 34);
		contentPane.add(btnCerrar);
		
		JButton btnModificar = new JButton("Devolución");
		btnModificar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModificar.setMnemonic('d');
		btnModificar.setIcon(new ImageIcon("src/Images/refresh.png"));

		btnModificar.setBounds(249, 246, 102, 34);
		contentPane.add(btnModificar);
		
		JLabel fondoreservas = new JLabel("");
		fondoreservas.setIcon(new ImageIcon("src/Images/reservafondo.jpg"));
		fondoreservas.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		fondoreservas.setBounds(-20, -12, 721, 542);
		contentPane.add(fondoreservas);
		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				
					String sql="update reserva set devolucion=1 where cod_reserva=?";
			//		PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
					int filsel;
					int cod_reserva;
					int res;
					filsel=tablaPrestamos.getSelectedRow();
					
					if(filsel==-1){
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún prestamo");
					}else{
						tablaPrestamos.getModel();
						cod_reserva=Integer.parseInt((String)tablaPrestamos.getValueAt(filsel, 1));//DNI
						res=JOptionPane.showConfirmDialog(null, "¿Desea confirmar la devolucion?","Cancelar",JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION){
						//	prest.setInt(1, cod_reserva);
					//		prest.executeUpdate();
							JOptionPane.showMessageDialog(null, "Devolucion realizada");
							prepararTablaPrestamos();
						}
					}
					
				}catch(Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Error al modificar la devolucion");
				}
			}
		});
		
		prepararTablaPrestamos();
	
		
	}
	
	
	private void prepararTablaPrestamos() {
		try{
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("select reserva.dni,cod_reserva,reserva.cod_material,material.titulo,material.tipo,fecha_res,fecha_dev,devolucion from reserva,material where material.cod_material=reserva.cod_material");
			DefaultTableModel m;
			
			String titulos[] = {"DNI","Cod. Reserva","Cod. Material","Titulo","Tipo","Fecha Reserva","Fecha Devolucion","Devolucion"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaPrestamos.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			tablaPrestamos.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("dni");
					fila[1]=resulSql.getString("cod_reserva");
					fila[2]=resulSql.getString("cod_material");
					fila[3]=resulSql.getString("titulo");
					fila[4]=resulSql.getString("tipo");
					fila[5]=resulSql.getString("fecha_res");
					fila[6]=resulSql.getString("fecha_dev");
					fila[7]=resulSql.getString("devolucion");
					m.addRow(fila);
			}
			
			int i=0;
			while(i<titulos.length){
				tablaPrestamos.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla");
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas v = new Reservas();
					v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}

