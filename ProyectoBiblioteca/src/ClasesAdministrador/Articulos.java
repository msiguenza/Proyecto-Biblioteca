package ClasesAdministrador;
 
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import conexiones.Conexion;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class Articulos extends JFrame {

	private JPanel contentPane;
	private JTable tablaArticulos;
	private Conexion c=new Conexion();


	public Articulos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setTitle("Artículos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);;
		contentPane.setLayout(null);
			
		JLabel lblRevistas = new JLabel("Articulos");
		lblRevistas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRevistas.setBounds(41, 11, 594, 23);
		lblRevistas.setFont(new Font("VAGRounded BT", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(lblRevistas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 51, 594, 170);
		scrollPane.setBackground(new Color(255, 250, 205));
		contentPane.add(scrollPane);
		
		tablaArticulos = new JTable(){
			 public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
		        }}; //return false: Desabilitar edición de celdas.
		
		tablaArticulos.setBackground(new Color(255, 250, 205));
		scrollPane.setViewportView(tablaArticulos);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCerrar.setIcon(new ImageIcon("src/images/adiooos.png"));
		btnCerrar.setMnemonic('c');
		btnCerrar.setBounds(580, 323, 99, 29);
		contentPane.add(btnCerrar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModificar.setIcon(new ImageIcon("src/images/modify.png"));
		btnModificar.setMnemonic('m');
		btnModificar.setBounds(127, 232, 137, 35);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnEliminar.setIcon(new ImageIcon("src/images/cepillo.png"));
		btnEliminar.setMnemonic('e');
		btnEliminar.setBounds(400, 232, 124, 35);
		contentPane.add(btnEliminar);
		
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRefrescar.setIcon(new ImageIcon("src/images/refresh.png"));
		btnRefrescar.setMnemonic('r');
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepararTablaArticulos();		
			}
		});
		
		
		
		btnRefrescar.setBounds(266, 290, 137, 41);
		contentPane.add(btnRefrescar);
		
		JLabel labelfondo = new JLabel("");
		labelfondo.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelfondo.setIcon(new ImageIcon("src/images/fondito5.jpg"));
		labelfondo.setBounds(0, 0, 689, 363);
		contentPane.add(labelfondo);
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int filsel;
				String datosFila[]= new String[tablaArticulos.getColumnCount()]; 
				filsel=tablaArticulos.getSelectedRow();
						
				try{
					
					if(filsel==-1){
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún articulo");
					}else{
						//tablaArticulos.getModel();
						datosFila[0]=(String)tablaArticulos.getValueAt(filsel, 0);//DNI
						datosFila[1]=(String)tablaArticulos.getValueAt(filsel, 1);//Nombre
						datosFila[2]=(String)tablaArticulos.getValueAt(filsel, 2);//Apellidos
						datosFila[3]=(String)tablaArticulos.getValueAt(filsel, 3);//Nick
						datosFila[4]=(String)tablaArticulos.getValueAt(filsel, 4);//Clave
						datosFila[5]=(String)tablaArticulos.getValueAt(filsel, 5);//Dirección
						datosFila[6]=(String)tablaArticulos.getValueAt(filsel, 6);//Teléfono*/
						ModificarArticulo ma=new ModificarArticulo(datosFila);
						ma.setVisible(true);
					}
					
				}catch(Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Error al modificar el articulo","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try{
				
				String sql="DELETE FROM articulo WHERE cod_articulo=?";
				PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
				
				int filsel;
				String cod_articulo;
				int r;
				filsel=tablaArticulos.getSelectedRow();
				
				if(filsel==-1){
					JOptionPane.showMessageDialog(null, "Selecciona un artículo");
				}else{
					cod_articulo=(String) tablaArticulos.getValueAt(filsel, 0);
					r=JOptionPane.showConfirmDialog(null, "¿Desea eliminar el articulo seleccionado con Cod_Articulo: "+cod_articulo+"?","Eliminar",JOptionPane.YES_NO_OPTION);
					if(r==JOptionPane.YES_OPTION){
						prest.setString(1,cod_articulo);
						prest.executeUpdate();
						prepararTablaArticulos();
					}
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,"Error de conexión, asegúrese de tener el host activado.","Error",JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, e.getCause());
			}catch(Exception ex){
				 JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
			}
			}
		});

		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		prepararTablaArticulos();
	
		
	}
	
	
	private void prepararTablaArticulos() {
		try{
	
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("SELECT * from articulo");
			Class.forName("com.mysql.jdbc.Driver");
			DefaultTableModel m;
			
			String titulos[] = {"Cod. Articulo","ISSN","Autor","Titulo","Año","Pagina Inicio","Pagina Fin"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaArticulos.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			tablaArticulos.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("cod_articulo");
					fila[1]=resulSql.getString("issn");
					fila[2]=resulSql.getString("autor");
					fila[3]=resulSql.getString("titulo");
					fila[4]=resulSql.getString("ano");
					fila[5]=resulSql.getString("pagina_ini");
					fila[6]=resulSql.getString("pagina_fin");
					m.addRow(fila);
			}
			
			int i=0;
			while(i<titulos.length){
				tablaArticulos.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Articulos v = new Articulos();
					v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
