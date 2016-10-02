package ClasesAdministrador;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
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
import javax.swing.JLabel;

import conexiones.Conexion;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class Notas extends JFrame {
	
	private JTable tablaNotasMateriales;
	private JTable tablaNotasArticulos;
	private JPanel contentPane;
	private JButton btnModificarNotaMat;
	private JButton btnVolverAtrs;
	private Conexion c=new Conexion();
	
	

	public Notas() {
		setTitle("Notas");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1008, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		

		btnVolverAtrs = new JButton("Volver atr\u00E1s");
		btnVolverAtrs.setBounds(751, 515, 191, 34);
		btnVolverAtrs.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnVolverAtrs.setIcon(new ImageIcon("src/images/return.png"));
		btnVolverAtrs.setMnemonic('a');
		contentPane.add(btnVolverAtrs);
		btnVolverAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		//----------------- Zona notas de materiales
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(72, 42, 651, 170);
		scrollPane2.setBackground(new Color(255, 250, 205));
		contentPane.add(scrollPane2);
		
		tablaNotasMateriales = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
		}};
		
		tablaNotasMateriales.setBackground(new Color(255, 250, 205));
		scrollPane2.setViewportView(tablaNotasMateriales);
		prepararTablaNotasMateriales();
	
		JButton btnRefrescarNotas = new JButton("Refrescar");
		btnRefrescarNotas.setBounds(767, 233, 161, 34);
		btnRefrescarNotas.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRefrescarNotas.setIcon(new ImageIcon("src/images/refresh.png"));
		btnRefrescarNotas.setMnemonic('r');
		contentPane.add(btnRefrescarNotas);
		
		btnModificarNotaMat = new JButton("Modificar");
		btnModificarNotaMat.setBounds(154, 223, 117, 34);
		btnModificarNotaMat.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModificarNotaMat.setIcon(new ImageIcon("src/images/modify.png"));
		btnModificarNotaMat.setMnemonic('m');
		contentPane.add(btnModificarNotaMat);
		
		JLabel lblNewLabel = new JLabel("Notas de materiales");
		lblNewLabel.setBounds(250, 49, 290, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnEliminarNotaMat = new JButton("Eliminar");
		btnEliminarNotaMat.setBounds(309, 223, 117, 34);
		btnEliminarNotaMat.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnEliminarNotaMat.setIcon(new ImageIcon("src/images/adiooos.png"));
		btnEliminarNotaMat.setMnemonic('e');
		contentPane.add(btnEliminarNotaMat);
		btnEliminarNotaMat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEliminarPerformed(arg0);
			}
		});
		
		btnModificarNotaMat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnModificarPerformer(arg0);
			}
		});
		
		
		btnRefrescarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepararTablaNotasMateriales();
				prepararTablaNotasArticulos();
			}
		});
		
		JButton btnVerComentarioNotaMaterial = new JButton("Ver comentario");
		btnVerComentarioNotaMaterial.setIcon(new ImageIcon("src/images/lupa.png"));
		btnVerComentarioNotaMaterial.setBounds(784, 112, 135, 40);
		btnVerComentarioNotaMaterial.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnVerComentarioNotaMaterial.setMnemonic('v');
		contentPane.add(btnVerComentarioNotaMaterial);
		btnVerComentarioNotaMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int filsel;
				String comentario;
				filsel=tablaNotasMateriales.getSelectedRow();
						
				try{
					if(filsel==-1){
						JOptionPane.showMessageDialog(null, "No se ha seleccionado comentario");
					}else{
						comentario=(String)tablaNotasMateriales.getValueAt(filsel, 6);
						VerComentarioNotas vc=new VerComentarioNotas(comentario);
						vc.setVisible(true);
					}
				}catch(Exception f){
					f.printStackTrace();
					JOptionPane.showMessageDialog(null,"Error al mostrar el comentario");
				}
				
			}
				
			
		});
		
		// Fin zona notas materiales
		
		//------------- Zona notas articulos-------------------
		
		JLabel lblRevistas = new JLabel("Notas sobre articulos");
		lblRevistas.setBounds(72, 11, 651, 23);
		lblRevistas.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 11));
		contentPane.add(lblRevistas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 300, 651, 170);
		scrollPane.setBackground(new Color(255, 250, 205));
		contentPane.add(scrollPane);
		
		tablaNotasArticulos = new JTable();
		tablaNotasArticulos.setBackground(new Color(255, 250, 205));
		scrollPane.setViewportView(tablaNotasArticulos);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon("src/images/adiooos.png"));
		btnEliminar.setBounds(309, 484, 117, 34);
		btnEliminar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnEliminar.setMnemonic('d');
		contentPane.add(btnEliminar);
		
		JButton btnVerComentario = new JButton("Ver comentario");
		btnVerComentario.setIcon(new ImageIcon("src/images/lupa.png"));
		btnVerComentario.setBounds(784, 357, 135, 40);
		btnVerComentario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		btnVerComentario.setMnemonic('c');
		contentPane.add(btnVerComentario);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setMnemonic('i');
		btnModificar.setIcon(new ImageIcon("src/images/modify.png"));
		btnModificar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModificar.setBounds(154, 481, 117, 37);
		contentPane.add(btnModificar);
		
		JLabel lblNotasSobreRevistas = new JLabel("Notas sobre revistas");
		lblNotasSobreRevistas.setBounds(72, 268, 651, 23);
		lblNotasSobreRevistas.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 11));
		contentPane.add(lblNotasSobreRevistas);
		
		JLabel labelfondo = new JLabel("");
		labelfondo.setBounds(0, 0, 1022, 577);
		labelfondo.setIcon(new ImageIcon("src/images/fondonotitas.jpg"));
		contentPane.add(labelfondo);
		

		btnVerComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int filsel;
				String comentario;
				filsel=tablaNotasArticulos.getSelectedRow();
						
				try{
					if(filsel==-1){
						JOptionPane.showMessageDialog(null, "No se ha seleccionado comentario");
					}else{
						comentario=(String)tablaNotasArticulos.getValueAt(filsel, 6);
						VerComentarioNotas vc=new VerComentarioNotas(comentario);
						vc.setVisible(true);
					}
				}catch(Exception f){
					f.printStackTrace();
					JOptionPane.showMessageDialog(null,"Error al mostrar el comentario");
				}
				
			}
		});
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int filsel;
				String datosFila[]= new String[tablaNotasArticulos.getColumnCount()]; 
				filsel=tablaNotasArticulos.getSelectedRow();
						
				try{
					
					if(filsel==-1){
						JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna nota");
					}else{
						datosFila[0]=(String)tablaNotasArticulos.getValueAt(filsel, 0);
						datosFila[1]=(String)tablaNotasArticulos.getValueAt(filsel, 1);
						datosFila[2]=(String)tablaNotasArticulos.getValueAt(filsel, 2);
						datosFila[3]=(String)tablaNotasArticulos.getValueAt(filsel, 3);
						datosFila[4]=(String)tablaNotasArticulos.getValueAt(filsel, 4);
						datosFila[5]=(String)tablaNotasArticulos.getValueAt(filsel, 5);
						datosFila[6]=(String)tablaNotasArticulos.getValueAt(filsel, 6);
						ModificarNotaArticulo mna=new ModificarNotaArticulo(datosFila);
						mna.setVisible(true);
					}
					
				}catch(Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Error al modificar la nota");
				}
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try{
				
				String sql="DELETE FROM nota WHERE id_nota=?";
				PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
				
				int filsel;
				String id_nota;
				int r;
				filsel=tablaNotasArticulos.getSelectedRow();
				
				if(filsel==-1){
					JOptionPane.showMessageDialog(null, "Selecciona la nota que quieres eliminar");
				}else{
					id_nota=(String) tablaNotasArticulos.getValueAt(filsel, 0);
					r=JOptionPane.showConfirmDialog(null, "¿Desea eliminar la nota seleccionada con Id Nota: "+id_nota+"?","Eliminar",JOptionPane.YES_NO_OPTION);
					if(r==JOptionPane.YES_OPTION){
						prest.setString(1,id_nota);
						prest.executeUpdate();
						prepararTablaNotasArticulos();
					}
				}
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null,"Error al borrar la nota.");
				JOptionPane.showMessageDialog(null, e.getCause());
			}catch(Exception ex){
				 JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos");
			}
			}
		});

		prepararTablaNotasArticulos();
		
		
		
	}
	//------------- Fin Constructor ------------------
	
	
	


	//---- Acciones de notas de materiales
	private void prepararTablaNotasMateriales() {
		try{
			
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("SELECT id_nota,usuario.dni,nombre,material.titulo,material.tipo,tema,comentario FROM usuario,material,nota WHERE usuario.dni=nota.dni and nota.cod_material=material.cod_material and nota.cod_articulo IS NULL");
			DefaultTableModel m;
			
			String titulos[] = {"Id. Nota", "Tema", "Título","Tipo","Dni usuario","Nombre usuario","Comentario"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaNotasMateriales.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String filaNotasMateriales[]=new String[titulos.length];
			tablaNotasMateriales.setModel(m);
			while(resulSql.next()){
				filaNotasMateriales[0]=resulSql.getString("id_nota");
				filaNotasMateriales[1]=resulSql.getString("tema");
				filaNotasMateriales[2]=resulSql.getString("material.titulo");
				filaNotasMateriales[3]=resulSql.getString("material.tipo");
				filaNotasMateriales[4]=resulSql.getString("usuario.dni");
				filaNotasMateriales[5]=resulSql.getString("nombre");
				filaNotasMateriales[6]=resulSql.getString("comentario");
			    m.addRow(filaNotasMateriales);
			}
			
			int i;
			i=0;
			while(i<titulos.length){
				tablaNotasMateriales.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla");
		}
	}
	
	
    protected void btnModificarPerformer(ActionEvent arg0) {
    	int fsel;
		String datosFila[]= new String[tablaNotasMateriales.getColumnCount()]; 
		fsel=tablaNotasMateriales.getSelectedRow();
		if(fsel==-1){
			JOptionPane.showMessageDialog(null, "Selecciona una fila");
		}else{
			
			datosFila[0]=(String)tablaNotasMateriales.getValueAt(fsel, 0);//id Nota
			datosFila[1]=(String)tablaNotasMateriales.getValueAt(fsel, 1);// tema nota
			datosFila[2]=(String)tablaNotasMateriales.getValueAt(fsel, 2);// titulo de material
			datosFila[3]=(String)tablaNotasMateriales.getValueAt(fsel, 3);// tipo 
			datosFila[4]=(String)tablaNotasMateriales.getValueAt(fsel, 4);// dni
			datosFila[5]=(String)tablaNotasMateriales.getValueAt(fsel, 5);// nombre
			datosFila[6]=(String)tablaNotasMateriales.getValueAt(fsel, 6);// comentario
			ModificarNotasMaterial mnm=new ModificarNotasMaterial(datosFila);
			mnm.setVisible(true);
		}
		
	}
    
    protected void btnEliminarPerformed(ActionEvent arg0) {
    	try{
    		
			String sql="DELETE FROM nota WHERE id_nota=?";
			PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
			
			int filsel;
			int idnota;
			int r;
			filsel=tablaNotasMateriales.getSelectedRow();
			
			if(filsel==-1){
				JOptionPane.showMessageDialog(null, "Selecciona una fila");
			}else{
				idnota=Integer.parseInt((String) tablaNotasMateriales.getValueAt(filsel, 0));
				r=JOptionPane.showConfirmDialog(null, "¿Desea eliminar la nota","Eliminar",JOptionPane.YES_NO_OPTION);
				if(r==JOptionPane.YES_OPTION){
					prest.setInt(1,idnota);
					prest.executeUpdate();
					prepararTablaNotasMateriales();
				}
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Error en la conexión de la base de datos, asegúrese de tener el host activo");
		}catch(Exception ex){
			ex.printStackTrace();
			 JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos");
		}
		
	}
	//-------------- Fin de acciones notas de materiales
    
   //-------- Zona acciones notas articulos
    
	private void prepararTablaNotasArticulos() {
		try{
			
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("select id_nota,usuario.dni,usuario.nombre,material.titulo,articulo.titulo,tema,comentario from articulo,nota,material,usuario where articulo.issn=material.issn and articulo.cod_articulo=nota.cod_articulo and nota.dni=usuario.dni");
			DefaultTableModel m;
			
			String titulos[] = {"Id Nota","DNI","Nombre","Titulo Revista","Titulo Articulo","Tema Nota","Comentario"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaNotasArticulos.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			tablaNotasArticulos.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("id_nota");
					fila[1]=resulSql.getString("usuario.dni");
					fila[2]=resulSql.getString("usuario.nombre");
					fila[3]=resulSql.getString("material.titulo");
					fila[4]=resulSql.getString("articulo.titulo");
					fila[5]=resulSql.getString("tema");
					fila[6]=resulSql.getString("comentario");
					m.addRow(fila);
			}
			
			int i=0;
			while(i<titulos.length){
				tablaNotasArticulos.getColumnModel().getColumn(i).setCellRenderer(tcr);
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
					Notas v = new Notas();
					v.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
