package ClasesUsuario;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

import conexiones.Conexion;
import java.awt.Toolkit;



public class VisualizacionNotas extends JFrame {

	private JPanel contentPane;
	private JTable tablaNotasMateriales;
	private JTable tablaNotasArticulos;
	private String filaNotaRevistaLibrosDvds[];//Creo este array para asignar desde el metodo preparar tablaNotasRevistaDvdLibros el array para pasarlo a la clase 
										//insertarNotasRevistasLibrosDvd y operar con él.
	
	private String filaNotaArticulos[]; //Mismo motivo que el anterior para tablaNotasArtículos
	private Conexion c=new Conexion();

	public VisualizacionNotas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/user.png"));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 474);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolver = new JButton("Volver Atr\u00E1s");
		btnVolver.setBounds(655, 237, 163, 38);
		btnVolver.setMnemonic('a');
		btnVolver.setIcon(new ImageIcon("src/images/return.png"));
		contentPane.add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				VolverAtrásActionPerformed(o);
			}
			private void VolverAtrásActionPerformed (ActionEvent o){
				dispose();
			}
		});
		// Zona de Notas de materiales
		JLabel lblNotasMateriales = new JLabel("Tabla para notas de revistas, libros y dvds");
		lblNotasMateriales.setBounds(31, 49, 594, 14);
		lblNotasMateriales.setForeground(Color.BLUE);
		lblNotasMateriales.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotasMateriales.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 16));
		contentPane.add(lblNotasMateriales);
		
		JScrollPane scrollPaneMateriales = new JScrollPane();
		scrollPaneMateriales.setBounds(31, 82, 594, 107);
		scrollPaneMateriales.setBackground(new Color(255, 250, 205));
		contentPane.add(scrollPaneMateriales);
		
		tablaNotasMateriales = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
		}};
		tablaNotasMateriales.setBackground(new Color(255, 250, 205));
		scrollPaneMateriales.setViewportView(tablaNotasMateriales);
		prepararMateriales();
		
		JButton btnVerComentarioNotaMateriales = new JButton("Ver comentario");
		btnVerComentarioNotaMateriales.setBounds(242, 200, 172, 34);
		btnVerComentarioNotaMateriales.setIcon(new ImageIcon("src/images/comentary.png"));
		contentPane.add(btnVerComentarioNotaMateriales);
		btnVerComentarioNotaMateriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int  filSel=tablaNotasMateriales.getSelectedRow();
				String comentario=(String)tablaNotasMateriales.getValueAt(filSel, 5);
				if(filSel==-1){
					JOptionPane.showMessageDialog(null, "Selecciona una fila");
				}else{
					ComentarioNota cn=new ComentarioNota(comentario);
					cn.setVisible(true);
				}
			}
		});
		
		//  Zona de Notas de articulos
		JLabel lblTablaNotas = new JLabel("Tabla para notas de art\u00EDculos");
		lblTablaNotas.setBounds(31, 261, 594, 14);
		lblTablaNotas.setForeground(Color.BLUE);
		lblTablaNotas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTablaNotas.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 16));
		contentPane.add(lblTablaNotas);
		
		JScrollPane scrollPaneTablatablaNotasArticulos = new JScrollPane();
		scrollPaneTablatablaNotasArticulos.setBounds(31, 295, 594, 119);
		scrollPaneTablatablaNotasArticulos.setBackground(new Color(255, 250, 205));
		contentPane.add(scrollPaneTablatablaNotasArticulos);
		tablaNotasArticulos = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
		}};
		
		tablaNotasArticulos.setBackground(new Color(255, 250, 205));
		scrollPaneTablatablaNotasArticulos.setViewportView(tablaNotasArticulos);
		prepararTablaNotasArticulo();
		
		JButton btnVerComentarioNotaArticulo = new JButton("Ver comentario");
		btnVerComentarioNotaArticulo.setBounds(242, 425, 172, 34);
		btnVerComentarioNotaArticulo.setMnemonic('c');
		btnVerComentarioNotaArticulo.setIcon(new ImageIcon("src/images/comentary.png"));
		contentPane.add(btnVerComentarioNotaArticulo);
		btnVerComentarioNotaArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int  filSel=tablaNotasArticulos.getSelectedRow();
				String comentario=(String)tablaNotasArticulos.getValueAt(filSel, 5);
				if(filSel==-1){
					JOptionPane.showMessageDialog(null, "Selecciona una fila");
				}else{
					ComentarioNota cn=new ComentarioNota(comentario);
					cn.setVisible(true);
				}
			}
		});
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 818, 524);
		label.setForeground(new Color(255, 0, 255));
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setLabelFor(this);
		label.setIcon(new ImageIcon("src/images/fondito.png"));
		contentPane.add(label);
		
		
	}
	
	//Tabla materiales
	protected void prepararMateriales() {
		try{
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("select material.cod_material,material.titulo,material.tipo,tema,nick,  comentario from usuario,material,nota where usuario.dni=nota.dni and nota.cod_material=material.cod_material;");
			DefaultTableModel m;
			
			String titulos[] = {"Cod. Material","Titulo material", "Tipo", "Tema de opinión","Usuario", "Comentario"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaNotasMateriales.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			filaNotaRevistaLibrosDvds=new String[titulos.length];
			tablaNotasMateriales.setModel(m);
			while(resulSql.next()){
				filaNotaRevistaLibrosDvds[0]=resulSql.getString(1);
				filaNotaRevistaLibrosDvds[1]=resulSql.getString(2);
				filaNotaRevistaLibrosDvds[2]=resulSql.getString(3);
				filaNotaRevistaLibrosDvds[3]=resulSql.getString(4);
				filaNotaRevistaLibrosDvds[4]=resulSql.getString(5);
				filaNotaRevistaLibrosDvds[5]=resulSql.getString(6);
				m.addRow(filaNotaRevistaLibrosDvds);
			}
			int i=0;
			//Centrar datos de la tabla
			while(i<titulos.length){
				tablaNotasMateriales.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	// tabla notas artículos			
	private void prepararTablaNotasArticulo() {
		try{
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("SELECT material.cod_material, material.titulo, tema,articulo.titulo,nick,comentario from articulo,nota,material,usuario  where articulo.issn=material.issn and articulo.cod_articulo=nota.cod_articulo and nota.dni=usuario.dni;");
			DefaultTableModel m;
			
			String titulos[] = {"Cod. Revista","Titulo revista", "Tema de opinión", "Título artículo", "Usuario", "Comentario"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaNotasArticulos.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			tablaNotasArticulos.setModel(m);
			while(resulSql.next()){
				fila[0]=resulSql.getString(1);
				fila[1]=resulSql.getString(2);
				fila[2]=resulSql.getString(3);
				fila[3]=resulSql.getString(4);
				fila[4]=resulSql.getString(5);
				fila[5]=resulSql.getString(6);
				m.addRow(fila);
			}
			
			int i;
			i=0;
			
			//Centrar datos de la tabla
			while(i<titulos.length){
				tablaNotasArticulos.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		//JOptionPane.showMessageDialog(null,"Bienvenido a BookNotes \n \n Gracias por utilizar nuestro servicio, esperamos que sea de su confianza \n \n Pulse Aceptar para comenzar con la aplicación","BookNotes", 
	    //JOptionPane.WARNING_MESSAGE, new ImageIcon("src/Images/Book.png"));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizacionNotas v = new VisualizacionNotas();
					v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
