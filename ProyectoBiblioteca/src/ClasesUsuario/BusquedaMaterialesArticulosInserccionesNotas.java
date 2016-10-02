package ClasesUsuario;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import com.itextpdf.text.Document;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;

import conexiones.Conexion;


import tablasBaseDeDatos.Material;
import java.awt.Font;
import java.awt.Toolkit;


public class BusquedaMaterialesArticulosInserccionesNotas extends JFrame {

	private JPanel contentPane;
	private JTable tablaMateriales;
	private String textoTextField;
	private String consultaTextField;
	private JTable tablaArticulos;
	private Conexion c=new Conexion();
	
	
	
	public BusquedaMaterialesArticulosInserccionesNotas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/user.png"));
		setUndecorated(true);
		String consultaMaterial="SELECT * FROM material";
		consultaTextField="SELECT * FROM material WHERE editorial LIKE '%"+textoTextField+"%'";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 537);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Botón regreso
		JButton VolverAtrás = new JButton("Atr\u00E1s");
		VolverAtrás.setMnemonic('a');
		VolverAtrás.setIcon(new ImageIcon("src/images/return.PNG"));
		VolverAtrás.setBounds(34, 476, 146, 33);
		contentPane.add(VolverAtrás);
		VolverAtrás.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent o){
				VolverAtrásActionPerformed(o);
			}
			private void VolverAtrásActionPerformed (ActionEvent o){
				dispose();
			}

		});
		
 //Zona Materiales
		JLabel lblRevistasLibrosDvds = new JLabel("Tabla de disponibilidad de reservas e insercci\u00F3n de notas sobre materiales ");
		lblRevistasLibrosDvds.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRevistasLibrosDvds.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRevistasLibrosDvds.setForeground(new Color(0, 0, 128));
		lblRevistasLibrosDvds.setFont(new Font("Trajan Pro", Font.BOLD | Font.ITALIC, 15));
		lblRevistasLibrosDvds.setBounds(29, 53, 797, 14);
		contentPane.add(lblRevistasLibrosDvds);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 250, 205));
		scrollPane.setBounds(30, 78, 796, 163);
		contentPane.add(scrollPane);
		tablaMateriales = new JTable(){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }}; //return false: Desabilitar edición de celdas.
	        
		tablaMateriales.setBackground(new Color(255, 250, 205));
		tablaMateriales.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(tablaMateriales);
		prepararTablaMateriales(consultaMaterial);
		
		JButton btnInsertarNotasMateriales = new JButton("Insertar nota");
		btnInsertarNotasMateriales.setMnemonic('i');
		btnInsertarNotasMateriales.setIcon(new ImageIcon("src/images/Notas.png"));
		btnInsertarNotasMateriales.setBounds(655, 252, 145, 33);
		contentPane.add(btnInsertarNotasMateriales);
		btnInsertarNotasMateriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnInsertarNotasMaterialesPerformed(arg0);
			}
		});
		
		JButton btnExportar = new JButton("Exportar material y sus notas");
		btnExportar.setMnemonic('e');
		btnExportar.setIcon(new ImageIcon("src/images/insertar.png"));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnExportarPerformed(arg0);
				
			}
		});
		btnExportar.setBounds(34, 252, 243, 36);
		contentPane.add(btnExportar);
		
		
 //Fin zona materiales//		
		
 //Zona para Articulos
		JLabel lblArticulos = new JLabel("Tabla de insercci\u00F3n de notas sobre art\u00EDculos");
		lblArticulos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblArticulos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArticulos.setForeground(new Color(0, 0, 128));
		lblArticulos.setFont(new Font("Trajan Pro", Font.BOLD | Font.ITALIC, 15));
		lblArticulos.setBounds(103, 299, 723, 14);
		contentPane.add(lblArticulos);
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBackground(new Color(255, 250, 205));
		scrollPane2.setBounds(30, 324, 796, 140);
		contentPane.add(scrollPane2);
		tablaArticulos = new JTable(){
			 public boolean isCellEditable(int rowIndex, int vColIndex) {
		            return false;
		        }}; //return false: Desabilitar edición de celdas.
		
	
		tablaArticulos.setBackground(new Color(255, 250, 205));
		tablaArticulos.setBounds(30, 267, 796, 102);
		scrollPane2.setViewportView(tablaArticulos);
		prepararTablaArticulos();
		
		
		JButton btnInsertarNotasArticulos = new JButton("Insertar nota");
		btnInsertarNotasArticulos.setMnemonic('i');
		btnInsertarNotasArticulos.setIcon(new ImageIcon("src/images/Notas.png"));
		btnInsertarNotasArticulos.setBounds(655, 475, 145, 34);
		contentPane.add(btnInsertarNotasArticulos);
		
		// Fondog
		JLabel labelFondo = new JLabel("");
		labelFondo.setBorder(new LineBorder(null, 1, true));
		labelFondo.setBounds(0, 0, 882, 537);
		labelFondo.setVerticalAlignment(SwingConstants.TOP);
		labelFondo.setLabelFor(this);
		labelFondo.setIcon(new ImageIcon("src/images/fondoabstracto.jpg"));
		contentPane.add(labelFondo);
		
	
		btnInsertarNotasArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnInsertarNotasArticulosPerformed(arg0);
			}
		});
	}
	
	
	
	
	protected void btnExportarPerformed(ActionEvent arg0) {
		

		JFileChooser filechooser=new JFileChooser();
		
		String ruta="";
		
		
		try{
			
			if(filechooser.showSaveDialog(null)==filechooser.APPROVE_OPTION){
				
				ruta=filechooser.getSelectedFile().getAbsolutePath();
				
			}

			
		}catch(Exception e){
			
			e.printStackTrace();
		}
	
		int filsel;
		String cod_material;

		try{
							
			filsel=tablaMateriales.getSelectedRow();
			
			if(filsel==-1){
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún material","Error",JOptionPane.ERROR_MESSAGE);
			}else{
				
			
				PreparedStatement prep=(PreparedStatement)c.getConectado().prepareStatement("select * from material where cod_material=?");
				PreparedStatement prep2=(PreparedStatement)c.getConectado().prepareStatement("select * from nota where cod_material=?");
				
				
		
					tablaMateriales.getModel();
					cod_material=(String) tablaMateriales.getValueAt(filsel,0);
					
					prep.setString(1,cod_material);
					prep2.setString(1,cod_material);
					prep.executeQuery();
					prep2.executeQuery();

					if(prep.getResultSet().first()){
							
						String cod=prep.getResultSet().getString(1);
						String isbn=prep.getResultSet().getString(2);
						String issn=prep.getResultSet().getString(3);
						String aut_dir=prep.getResultSet().getString(4);
						String titulo=prep.getResultSet().getString(5);
						String editorial=prep.getResultSet().getString(6);
						String anio=prep.getResultSet().getString(7);
						String pags=prep.getResultSet().getString(8);
						String gen=prep.getResultSet().getString(9);
						String tipo=prep.getResultSet().getString(10);								
						
						File f=new File(ruta+".pdf");
						Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
							      Font.BOLD);
						Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
							      Font.BOLD);
						Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
							      Font.BOLD);
				
						
						try{
						
							Document document = new Document();
						    PdfWriter.getInstance(document, new FileOutputStream(f));
						    document.open();
						    addMetaData(document); ---> No quitar comentario
						    document.addTitle("Material y Notas");
						    document.addAuthor("BookRent");
						    document.addCreator("BookRent");
						    addTitlePage(document);
						    Paragraph preface = new Paragraph();
						    preface.add(new Paragraph("Materiales y notas", catFont));
						    preface.add(new Paragraph("\n Material", subFont));
						    preface.add(new Paragraph("\t Codigo: "+cod+" \t ISBN: "+isbn+" \t ISSN: "+issn+" " +
						    		"\t Autor: "+aut_dir+" \t Titulo: "+titulo+" " +
						    				"\t\r\n\t Editorial: "+editorial+" \t Año: "+anio+" \t " +
						    						"Paginas_duracion: "+pags+" \t Genero: "+gen+" \n Tipo: " +
						    								""+tipo+"\r\n\r\n",smallBold));
						    preface.add(new Paragraph("\n Notas", subFont));
					
						while (prep2.getResultSet().next()){
							
							String id=prep2.getResultSet().getString(1);
							String tema=prep2.getResultSet().getString(2);
							String com=prep2.getResultSet().getString(6);
							preface.add(new Paragraph("\t * Id de nota: "+id+" \t Tema: "+tema+" \n Comentario: "+com+"\r\n",smallBold));
							
						}
						
						//document.add(preface);
						//document.close();
						JOptionPane.showMessageDialog(null, "Exportado con éxito");
						
						}catch(Exception e){
							
							JOptionPane.showMessageDialog(null, "No se ha podido exportar","Error",JOptionPane.ERROR_MESSAGE);
						}
						
						
					}
					
				}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error al exportar el archivo","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}




	//Acción del botón de insertart notas resvistas, libros, dvds
	protected void btnInsertarNotasMaterialesPerformed(ActionEvent arg0) {
		int fsel;
		String datosFilaMaterial[]= new String[4]; 
		fsel=tablaMateriales.getSelectedRow();
		if(fsel==-1){
			JOptionPane.showMessageDialog(null, "Selecciona una fila");
		}else{
			datosFilaMaterial[0]=(String)tablaMateriales.getValueAt(fsel, 3);//Cod_material
			datosFilaMaterial[1]=(String)tablaMateriales.getValueAt(fsel, 1);//titulo
			datosFilaMaterial[2]=(String)tablaMateriales.getValueAt(fsel, 2);//tipo
			
			InsertarNotasRevistasLibrosDvd inrld=new InsertarNotasRevistasLibrosDvd(datosFilaMaterial);
			inrld.setVisible(true);
		}
	}

	//Tabla materiales
	private void prepararTablaMateriales(String conSql) {
		try{
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery(conSql);
			DefaultTableModel m;
			
			String titulos[] = {"Código","Título","Tipo","ISBN","ISSN","Autor/Director","Año","Género","Editorial","Páginas/Duración","Disponible"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaMateriales.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			tablaMateriales.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("cod_material");
					fila[1]=resulSql.getString("titulo");
					fila[2]=resulSql.getString("tipo");
					fila[3]=resulSql.getString("isbn");
					fila[4]=resulSql.getString("issn");
					fila[5]=resulSql.getString("autor_director");
					fila[6]=resulSql.getString("ano");
					fila[7]=resulSql.getString("genero");
					fila[8]=resulSql.getString("editorial");
					fila[9]=resulSql.getString("paginas_duracion");
					fila[10]=resulSql.getString("disponible");
					m.addRow(fila);
			}
			
			//Centrar datos de la tabla
			int i;
			i=0;
			while(i<titulos.length){
				tablaMateriales.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/// TAbla Artículos
	protected void prepararTablaArticulos() {
		try{
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("SELECT articulo.titulo,articulo.autor,material.titulo,articulo.ano,articulo.pagina_ini,articulo.pagina_fin,material.cod_material,articulo.cod_articulo FROM material,articulo where material.issn=articulo.issn;");
			DefaultTableModel m;
			
			
			String titulos[] = {"Artículo","Autor","Revista","Año","Página inicio","Página fin","cod. Material","cod. Articulo"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaArticulos.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String filaArticulo[]=new String[titulos.length];
			tablaArticulos.setModel(m);
			while(resulSql.next()){
				filaArticulo[0]=resulSql.getString("articulo.titulo");//Titulo del artículo
				filaArticulo[1]=resulSql.getString("articulo.autor");// Autor del artículo
				filaArticulo[2]=resulSql.getString("material.titulo");// Título de la revista
				filaArticulo[3]=resulSql.getString("articulo.ano");//Año del artículo
				filaArticulo[4]=resulSql.getString("articulo.pagina_ini");
				filaArticulo[5]=resulSql.getString("articulo.pagina_fin");	
				filaArticulo[6]=resulSql.getString("material.cod_material");	
				filaArticulo[7]=resulSql.getString("articulo.cod_articulo");	
				m.addRow(filaArticulo);
			}
			//Centrar datos de la tabla
			int i;
			i=0;
			while(i<titulos.length){
				tablaArticulos.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
			
			//Oculta la columna cógido de material y código de artículo
			tablaArticulos.getColumnModel().getColumn(6).setMaxWidth(0);
			tablaArticulos.getColumnModel().getColumn(6).setMinWidth(0);
			tablaArticulos.getColumnModel().getColumn(6).setPreferredWidth(0);
			tablaArticulos.getColumnModel().getColumn(7).setMaxWidth(0);
			tablaArticulos.getColumnModel().getColumn(7).setMinWidth(0);
			tablaArticulos.getColumnModel().getColumn(7).setPreferredWidth(0);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	//Acción del botón de insertar notas de artículos
	protected void btnInsertarNotasArticulosPerformed(ActionEvent arg0) {
			int fsel=tablaArticulos.getSelectedRow();
			String datosFilaArticulo[]=new String[4];
			if(fsel==-1){
				JOptionPane.showMessageDialog(null, "Selecciona una fila");
			}else{
				datosFilaArticulo[0]=(String)tablaArticulos.getValueAt(fsel,2);//Titulo REvista
				datosFilaArticulo[1]=(String)tablaArticulos.getValueAt(fsel,6);//Cod material
				datosFilaArticulo[2]=(String)tablaArticulos.getValueAt(fsel,7);//Cod Artículo
				datosFilaArticulo[3]=(String)tablaArticulos.getValueAt(fsel,0);//Nombre Artículo
			
				JOptionPane.showMessageDialog(null,"Titulo REvista: "+datosFilaArticulo[0]+"\n Codigo Material:"+datosFilaArticulo[1]+"\n Código artículo:"+datosFilaArticulo[2]+"\n Articulo: "+datosFilaArticulo[3]);
				InsertarNotaArticulo ina=new InsertarNotaArticulo(datosFilaArticulo);
				ina.setVisible(true);
			}
	}
	
}

