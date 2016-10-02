package ClasesAdministrador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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

public class UsuariosMaterialesDoReservas extends JFrame {

	private JPanel contentPane;
	private JTable tablaUsuario;
	private JTable tablaMateriales;
	Calendar c1 = Calendar.getInstance();//para Reservas
	private Conexion c=new Conexion();
	JButton btnBloqueoUsuario;
	
	public UsuariosMaterialesDoReservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 947, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		///---------------------///
		
		
		JButton btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.setMnemonic('x');
		btnAtrs.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnAtrs.setIcon(new ImageIcon("src/images/return.png"));
		btnAtrs.setBounds(796, 506, 112, 37);
		contentPane.add(btnAtrs);
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
 //----------------- Zona Usuario ------------------
		
		JLabel lblUsuarios = new JLabel("Usuarios ");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Rockwell Condensed", Font.BOLD | Font.ITALIC, 16));
		lblUsuarios.setBounds(42, 11, 666, 23);
		contentPane.add(lblUsuarios);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBackground(new Color(255, 250, 205));
		scrollPane3.setBounds(42, 45, 666, 170);
		contentPane.add(scrollPane3);
		

		tablaUsuario = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
		}};
		tablaUsuario.setBackground(new Color(255, 250, 205));
		tablaUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane3.setViewportView(tablaUsuario);
		tablaUsuario.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseClicked(MouseEvent arg0) {
				int filSel=tablaUsuario.getSelectedRow();
		     	int estado=Integer.parseInt(String.valueOf(tablaUsuario.getValueAt(filSel, 2)));
					if(estado==0){
						btnBloqueoUsuario.setText("Bloquear usuario");
						btnBloqueoUsuario.setIcon(new ImageIcon("src/images/confirm.png"));
						estado=1;
					}else{
						btnBloqueoUsuario.setText("Desbloquear usuario");
					    estado=0;
					}
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
		prepararTablaUsuario();
		
		JButton btnCrearUsuario = new JButton("Crear");
		btnCrearUsuario.setMnemonic('c');
		btnCrearUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCrearUsuario.setIcon(new ImageIcon("src/images/confirm.png"));
	
		btnCrearUsuario.setBounds(736, 58, 117, 29);
		contentPane.add(btnCrearUsuario);
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCrearUsuarioPerformed(arg0);
			}
			private void btnCrearUsuarioPerformed(ActionEvent arg0) {
				CrearUsuario cu=new CrearUsuario();
				cu.setVisible(true);
			}
		});
		
		JButton btnModificarUsuario = new JButton("Modificar");
		btnModificarUsuario.setMnemonic('m');
		btnModificarUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModificarUsuario.setIcon(new ImageIcon("src/images/modify.png"));
		btnModificarUsuario.setBounds(736, 98, 117, 37);
		contentPane.add(btnModificarUsuario);
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnModificarUsuarioPerformed(arg0);
			}
		});
		
		JButton btnRefrescarUsuario = new JButton("Refrescar");
		btnRefrescarUsuario.setMnemonic('r');
		btnRefrescarUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRefrescarUsuario.setIcon(new ImageIcon("src/images/refresh.png"));
		btnRefrescarUsuario.setBounds(736, 186, 117, 29);
		contentPane.add(btnRefrescarUsuario);
		setLocationRelativeTo(null);
		
		// Actualizar los datos cuando se hayan modificado o creado usuarios
		btnRefrescarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRefrescarPerformed(arg0);
			}
			private void btnRefrescarPerformed(ActionEvent arg0) {
				prepararTablaUsuario();
			}
		});
		
		
		JButton btnEliminarUs = new JButton("Eliminar");
		btnEliminarUs.setMnemonic('e');
		btnEliminarUs.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnEliminarUs.setIcon(new ImageIcon("src/images/adiooos.png"));
		btnEliminarUs.setBounds(736, 146, 117, 29);
		contentPane.add(btnEliminarUs);
		btnEliminarUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEliminarUsPerformed(arg0);
			}
		});
		
 //--------------- Zona Materiales -----------------------------------------------------------------------
		
		JLabel lblMateriales = new JLabel("Materiales");
		lblMateriales.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateriales.setFont(new Font("Rockwell Condensed", Font.BOLD | Font.ITALIC, 16));
		lblMateriales.setBounds(42, 270, 666, 23);
		contentPane.add(lblMateriales);
		
		JButton btnCrearMaterial = new JButton("Crear");
		btnCrearMaterial.setMnemonic('a');
		btnCrearMaterial.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnCrearMaterial.setIcon(new ImageIcon("src/images/confirm.png"));
		btnCrearMaterial.setBounds(736, 321, 117, 29);
		contentPane.add(btnCrearMaterial);
		btnCrearMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearMaterial cm=new CrearMaterial();
				cm.setVisible(true);
			}
		});
		
		JButton btnModificarMaterial = new JButton("Modificar");
		btnModificarMaterial.setMnemonic('o');
		btnModificarMaterial.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnModificarMaterial.setIcon(new ImageIcon("src/images/modify.png"));
		btnModificarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnModificarMaterialPerformed(e);
			}
		});
		btnModificarMaterial.setBounds(736, 361, 117, 29);
		contentPane.add(btnModificarMaterial);
		
		JButton btnEliminarMat = new JButton("Eliminar");
		btnEliminarMat.setMnemonic('m');
		btnEliminarMat.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnEliminarMat.setIcon(new ImageIcon("src/images/adiooos.png"));
		btnEliminarMat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEliminarMatPerformed(arg0);
			}
		});
		btnEliminarMat.setBounds(736, 401, 117, 23);
		contentPane.add(btnEliminarMat);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBackground(new Color(255, 250, 205));
		scrollPane2.setBounds(48, 307, 660, 170);
		contentPane.add(scrollPane2);
		
		
		tablaMateriales = new JTable(){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false; //Deshabilitación de Celdas
		}};
		tablaMateriales.setBackground(new Color(255, 250, 205));
		tablaMateriales.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane2.setViewportView(tablaMateriales);
		
		prepararTablaMateriales();
		
		JButton btnRefrescarMaterial = new JButton("Refrescar");
		btnRefrescarMaterial.setMnemonic('s');
		btnRefrescarMaterial.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRefrescarMaterial.setIcon(new ImageIcon("src/images/refresh.png"));
		btnRefrescarMaterial.setBounds(736, 435, 117, 37);
		contentPane.add(btnRefrescarMaterial);
		btnRefrescarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prepararTablaMateriales();
			}
		});
		
		
 //--------------- Zona Reservas -----------------------------------------------------------------------
		
	     // --------- Botón reserva ------  	
	        JButton btnRealizarReserva = new JButton("Realizar reserva");
	        btnRealizarReserva.setMnemonic('v');
	        btnRealizarReserva.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	        btnRealizarReserva.setIcon(new ImageIcon("src/images/Notas.png"));
			btnRealizarReserva.setBackground(new Color(0, 255, 102));
			btnRealizarReserva.setBounds(708, 236, 145, 29);
			contentPane.add(btnRealizarReserva);
	  
			
			btnRealizarReserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnRealizarReservaPerformed(arg0);
				}
				private void btnRealizarReservaPerformed(ActionEvent arg0) {
					int filSelUs, filSelMat;
					
					filSelUs=tablaUsuario.getSelectedRow();
					filSelMat=tablaMateriales.getSelectedRow();
				
				    String dniUsuario;
				  
				    int codMaterial;	
				   
					if(filSelUs==-1 || filSelMat==-1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario y un material.");
					}else{
						int disponible=Integer.parseInt((String)tablaMateriales.getValueAt(filSelMat, 3));
						dniUsuario=(String)tablaUsuario.getValueAt(filSelUs, 0);
						int penalizado=Integer.parseInt(String.valueOf(tablaUsuario.getValueAt(filSelUs, 2)));
					    int alquiler=Integer.parseInt(String.valueOf(tablaUsuario.getValueAt(filSelUs, 3)));
						if((penalizado==1 || alquiler==1) || disponible==0){
							if(penalizado==1){
								JOptionPane.showMessageDialog(null, "Este usuario está penalizado");
							}else if(alquiler==1){
								JOptionPane.showMessageDialog(null, "Este usuario dispone de una reserva");
							}else if(penalizado==1 || alquiler==1){
								JOptionPane.showMessageDialog(null, "Este usuario dispone de una reserva o está penalizado");
							}
							if(disponible==0){
								JOptionPane.showMessageDialog(null, "Este material no está disponible");
							}
						}else{
									codMaterial=Integer.parseInt((String)tablaMateriales.getValueAt(filSelMat, 0));
									
									int dia = c1.get(Calendar.DATE);
									int mes = c1.get(Calendar.MONTH)+1;
									int ano = c1.get(Calendar.YEAR);
									int mesDev =mes+2;
									
									String fechaRes=ano+"/"+mes+"/"+dia;
									String fechaDev=ano+"/"+mesDev+"/"+dia;;
									JOptionPane.showMessageDialog(null,"Fecha de Reserva: "+fechaRes+" \n\n Fecha devolución: "+fechaDev);
									
									try{
										Statement stm=c.getConectado().createStatement();
										stm.executeUpdate("INSERT INTO reserva (cod_material,dni,fecha_res,fecha_dev) VALUES" + "("+codMaterial+",'"+dniUsuario+"','"+fechaRes+"','"+fechaDev+"')");//Consulta preparada para introducir los valores posteriormente
										stm.executeUpdate("UPDATE usuario SET alquiler=1 WHERE dni='"+dniUsuario+"'");
										stm.executeUpdate("UPDATE material SET disponible=0 WHERE cod_material="+codMaterial+"");
										JOptionPane.showMessageDialog(null, "Reserva realizada con éxito");
										}catch(SQLException exSql){
											exSql.printStackTrace();
											JOptionPane.showMessageDialog(null, "Error de conexión");
										}catch(Exception e){
											e.printStackTrace();
											System.out.print("2");
											JOptionPane.showMessageDialog(null, "Introduce los parámetros correctos");
									}
									prepararTablaMateriales();
									prepararTablaUsuario();
						  }
						
					}
				}
			});		
			
			
			// ----- Botón bloqueo usuario -----
			btnBloqueoUsuario = new JButton("Bloquear usuario");
			btnBloqueoUsuario.setMnemonic('b');
			btnBloqueoUsuario.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			
			btnBloqueoUsuario.setBounds(65, 222, 174, 37);
			contentPane.add(btnBloqueoUsuario);
			
			JLabel label = new JLabel("");
			label.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			label.setIcon(new ImageIcon("src/images/fondogrande.jpg"));
			label.setBounds(0, 0, 947, 568);
			contentPane.add(label);
			btnBloqueoUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						int filSel=tablaUsuario.getSelectedRow();
						if(filSel==-1){
							JOptionPane.showMessageDialog(null, "Selecciona un usuario");
						}else{
							int estado=Integer.parseInt(String.valueOf(tablaUsuario.getValueAt(filSel, 2)));
							String dni=(String)tablaUsuario.getValueAt(filSel, 0);
							if(estado==0){
								estado=1;
							}else{
								estado=0;
							}
							try {
								Statement stm = c.getConectado().createStatement();
								stm.executeUpdate("UPDATE usuario SET penalizado="+estado+" WHERE dni='"+dni+"'");
								if(estado==0){
									JOptionPane.showMessageDialog(null, "Usuario desbloqueado");
									btnBloqueoUsuario.setIcon(new ImageIcon("src/images/Ok.png"));
								
								}else{
									JOptionPane.showMessageDialog(null, "Usuario bloqueado");
									btnBloqueoUsuario.setIcon(new ImageIcon("src/images/bloqueo.png"));
								}
								prepararTablaUsuario();
							} catch (SQLException exs) {
								exs.printStackTrace();
							} catch (IOException ex) {
								ex.printStackTrace();
							}
						}
				}
			});
			
	}
	//-------- Fin Constructor ---------
	
	//-------- Zona Acciones Usuario ------------
	
	private void btnModificarUsuarioPerformed(ActionEvent arg0) {
		int fsel;
		String datosFila[]= new String[tablaUsuario.getColumnCount()]; 
		fsel=tablaUsuario.getSelectedRow();
		if(fsel==-1){
			JOptionPane.showMessageDialog(null, "Selecciona una fila");
		}else{
			
			//Recojo los datos de las columnas que necesito de la fila seleccionada, los paso
			//a un array y lo paso al constructor del Jdialog ModificarUsuario para iniciarlizarlos para la modificación
			// en sus textfields Correspondientes
			datosFila[0]=(String)tablaUsuario.getValueAt(fsel, 0);//DNI
			datosFila[1]=(String)tablaUsuario.getValueAt(fsel, 1);//Nombre
			datosFila[2]=(String)tablaUsuario.getValueAt(fsel, 7);//Apellidos
			datosFila[3]=(String)tablaUsuario.getValueAt(fsel, 5);//Nick
			datosFila[4]=(String)tablaUsuario.getValueAt(fsel, 6);//Clave
			datosFila[5]=(String)tablaUsuario.getValueAt(fsel, 8);//Dirección
			datosFila[6]=(String)tablaUsuario.getValueAt(fsel, 9);//Teléfono*/

			//dni nombre apellidos nick clave direccion telefono tipo --> Datos que necesito para el Jdialog de ModificarUsuario
			
			// Distribución de la tabla Usuario
			//"DNI", "Nombre","Penalizado","Con Alquiler", "Puntos", "Nick","Clave","Apellidos","Dirección","Teléfono","Tipo"
	        // 0         1         2            3             4         5       6        7          8           9        10
			ModificarUsuario mu=new ModificarUsuario(datosFila);
			mu.setVisible(true);
			dispose();
		}
	}
	
	protected void btnEliminarUsPerformed(ActionEvent arg0) {
		try{
			String sql="DELETE FROM usuario WHERE dni=?";
			PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
			
			int filsel;
			String dni;
			int r;
			filsel=tablaUsuario.getSelectedRow();
			
			if(filsel==-1){
				JOptionPane.showMessageDialog(null, "Selecciona el usuario");
			}else{
				int conAlquiler=Integer.parseInt((String)tablaUsuario.getValueAt(filsel, 3));
				if(conAlquiler==1){
					JOptionPane.showMessageDialog(null, "Este usuario tiene una reserva en activo y no debe ser eliminado");
				}
				dni=(String) tablaUsuario.getValueAt(filsel, 0);
				r=JOptionPane.showConfirmDialog(null, "¿Desea eliminar el trabajador seleccionado con Dni: "+dni+"?","Eliminar",JOptionPane.YES_NO_OPTION);
				if(r==JOptionPane.YES_OPTION){
					prest.setString(1,dni);
					prest.executeUpdate();
					prepararTablaUsuario();
				}
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null,"Error al borrar la tupla.");
			JOptionPane.showMessageDialog(null, e.getCause());
		}catch(Exception ex){
			 JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos");
		}
		
	}
	

	public void prepararTablaUsuario() {
		try{
			Statement sql=c.getConectado().createStatement();
			ResultSet resulSql=sql.executeQuery("SELECT * FROM usuario");
			DefaultTableModel m;
			
			String titulos[] = {"DNI", "Nombre","Penalizado","Con Alquiler", "Nick","Clave","Apellidos","Dirección","Teléfono","Tipo"};
			m = new DefaultTableModel(null,titulos);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m);
			tablaUsuario.setRowSorter(sorter);
	     	
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
		
			String fila[]=new String[titulos.length];
			tablaUsuario.setModel(m);
			while(resulSql.next()){
					fila[0]=resulSql.getString("dni");
					fila[1]=resulSql.getString("nombre");
					fila[2]=resulSql.getString("penalizado");
					fila[3]=resulSql.getString("alquiler");
					fila[4]=resulSql.getString("nick");
					fila[5]=resulSql.getString("clave");
					fila[6]=resulSql.getString("apellidos");
					fila[7]=resulSql.getString("direccion");
					fila[8]=resulSql.getString("telefono");
					fila[9]=resulSql.getString("tipo");
					m.addRow(fila);
			}
			
			int i=0;
			while(i<titulos.length){
				tablaUsuario.getColumnModel().getColumn(i).setCellRenderer(tcr);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla");
		}
	}
	
	//-------- Zona acciones Materiales ----------
		protected void btnModificarMaterialPerformed(ActionEvent e) {
			int fselMateriales=tablaMateriales.getSelectedRow();
			String datosFilaMat[]= new String[tablaMateriales.getColumnCount()]; 
			
			if(fselMateriales==-1){
				JOptionPane.showMessageDialog(null, "Selecciona una fila");
			}else{
				
				//Recojo los datos de las columnas que necesito de la fila seleccionada, los paso
				//a un array y lo paso al constructor del Jdialog ModificarMaterial para iniciarlizarlos para la modificación
				// en sus textfields Correspondientes
				datosFilaMat[0]=(String)tablaMateriales.getValueAt(fselMateriales, 0);//cod_material
				datosFilaMat[1]=(String)tablaMateriales.getValueAt(fselMateriales, 1);//titulo
				datosFilaMat[2]=(String)tablaMateriales.getValueAt(fselMateriales, 2);//tipo
				datosFilaMat[3]=(String)tablaMateriales.getValueAt(fselMateriales, 3);//disponible
				datosFilaMat[4]=(String)tablaMateriales.getValueAt(fselMateriales, 4);//isbn
				datosFilaMat[5]=(String)tablaMateriales.getValueAt(fselMateriales, 5);//issn
				datosFilaMat[6]=(String)tablaMateriales.getValueAt(fselMateriales, 6);//autor-director
				datosFilaMat[7]=(String)tablaMateriales.getValueAt(fselMateriales, 7);//editorial
				datosFilaMat[8]=(String)tablaMateriales.getValueAt(fselMateriales, 8);//año
				datosFilaMat[9]=(String)tablaMateriales.getValueAt(fselMateriales, 9);//paginas/duracion
				datosFilaMat[10]=(String)tablaMateriales.getValueAt(fselMateriales, 10);//genero
				//"Código","Título","Tipo","Disponible","ISBN","ISSN","Autor/Director","Editorial","Año","Páginas/Duración (min)","Género"
				
				int j=0;
				do{
					System.out.println(datosFilaMat[j]);
					j++;
				}while(j<=10);
				ModificarMaterial mmat=new ModificarMaterial(datosFilaMat);
				mmat.setVisible(true);
				dispose();
			}
		}
		
		protected void btnEliminarMatPerformed(ActionEvent arg0) {
			try{

				String sql="DELETE FROM material WHERE cod_material=?";
				PreparedStatement prest=(PreparedStatement) c.getConectado().prepareStatement(sql);
				
				String codMat;
				int filSelTm=tablaMateriales.getSelectedRow();
				int res;
				
				if(filSelTm==-1){
					JOptionPane.showMessageDialog(null, "Selecciona una fila");
				}else{
					int disponible=Integer.parseInt((String)tablaMateriales.getValueAt(filSelTm, 3));
					codMat=(String) tablaMateriales.getValueAt(filSelTm, 0);
					if(disponible==0){
						JOptionPane.showMessageDialog(null, "Este material no debería ser eliminado ya que está en reserva. Solo debería ser eliminado por causas de robo, deterioro del material u otras consideraciones.");
					}
						res=JOptionPane.showConfirmDialog(null, "¿Desea eliminar el material seleccionado con código: "+codMat+"?","Eliminar",JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION){
							prest.setInt(1,Integer.parseInt(codMat));
							prest.executeUpdate();
							prepararTablaMateriales();
						}
				}
			}catch(SQLException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Error al borrar la tupla.");
				JOptionPane.showMessageDialog(null, e.getCause());
			}catch(Exception ex){
				ex.printStackTrace();
				 JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos");
			}
		}
		

		
			
		public void prepararTablaMateriales() {
			try{
				Statement sql=c.getConectado().createStatement();
				ResultSet resulSql=sql.executeQuery("SELECT * FROM material");
				DefaultTableModel m;
				
				String titulos[] = {"Código","Título","Tipo","Disponible","ISBN","ISSN","Autor/Director","Editorial","Año","Páginas/Duración (min)","Género"};
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
						fila[3]=resulSql.getString("disponible");
						fila[4]=resulSql.getString("isbn");
						fila[5]=resulSql.getString("issn");
						fila[6]=resulSql.getString("autor_director");
						fila[7]=resulSql.getString("editorial");
						fila[8]=resulSql.getString("ano");
						fila[9]=resulSql.getString("paginas_duracion");
						fila[10]=resulSql.getString("genero");
						m.addRow(fila);
				}
				
				int i;
				i=0;
				while(i<titulos.length){
					tablaMateriales.getColumnModel().getColumn(i).setCellRenderer(tcr);
					i++;
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla");
			}
		}
}