package ClasesAdministrador;

import java.awt.BorderLayout;   
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

import ClasesInterfaz.Validacion;

import conexiones.Conexion;
import javax.swing.border.LineBorder;

public class RevistasAddArticulos extends JFrame {

	private JPanel contentPane;
	private JTable tablaRevistas;
	private Conexion c=new Conexion();

	
	public RevistasAddArticulos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);;
		contentPane.setLayout(null);
			
		JLabel lblRevistas = new JLabel("Revistas");
		lblRevistas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRevistas.setBounds(10, 11, 661, 23);
		lblRevistas.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(lblRevistas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 661, 170);
		scrollPane.setBackground(new Color(255, 250, 205));
		contentPane.add(scrollPane);
		
		tablaRevistas = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
			}};
		tablaRevistas.setBackground(new Color(255, 250, 205));
		scrollPane.setViewportView(tablaRevistas);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCerrar.setBounds(582, 295, 89, 44);
		btnCerrar.setMnemonic('c');
		btnCerrar.setIcon(new ImageIcon("src/images/adiooos.png"));
		contentPane.add(btnCerrar);
		
		JButton btnAnadir = new JButton("Agregar articulo");
		btnAnadir.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnAnadir.setBounds(278, 232, 129, 44);
		btnAnadir.setMnemonic('a');
		btnAnadir.setIcon(new ImageIcon("src/images/prestamo.png"));
		contentPane.add(btnAnadir);
		
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon("src/images/revistasaddarticulo.jpg"));

		fondo.setBounds(-17, 0, 706, 410);
		contentPane.add(fondo);
		
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int filsel;
				String issn;
				
				try{
					
					filsel=tablaRevistas.getSelectedRow();
					
					if(filsel==-1){
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna revista");
					}else{
						tablaRevistas.getModel();
						issn=(String) tablaRevistas.getValueAt(filsel,1);
						CrearArticulo ca=new CrearArticulo(issn);
						ca.setVisible(true);
					}
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Error al agregar articulo");
				}
			}
		});

		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		prepararTablaRevistas();
	
		
	}
	
	
	private void prepararTablaRevistas() {
		try{
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("SELECT cod_material,issn,autor_director,titulo,editorial,ano,paginas_duracion,genero,disponible FROM material where tipo='revista'");
			DefaultTableModel m;
			
			String titulos[] = {"Cod. Material","ISSN","Autor","Titulo","Editorial","Año","Paginas","Genero","Disponible"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaRevistas.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			tablaRevistas.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("cod_material");
					fila[1]=resulSql.getString("issn");
					fila[2]=resulSql.getString("autor_director");
					fila[3]=resulSql.getString("titulo");
					fila[4]=resulSql.getString("editorial");
					fila[5]=resulSql.getString("ano");
					fila[6]=resulSql.getString("paginas_duracion");
					fila[7]=resulSql.getString("genero");
					fila[8]=resulSql.getString("disponible");
					m.addRow(fila);
			}
			
			int i=0;
			while(i<titulos.length){
				tablaRevistas.getColumnModel().getColumn(i).setCellRenderer(tcr);
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
					RevistasAddArticulos v = new RevistasAddArticulos();
					v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}

