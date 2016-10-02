package ClasesAdministrador;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.JButton;

import conexiones.Conexion;

import ClasesInterfaz.Validacion;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Toolkit;


public class VentanaAdmin extends JFrame {
	

	private JPanel contentPane;
	private Conexion c=new Conexion();

	public VentanaAdmin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/admin.png"));
		setUndecorated(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
//--------- zona menu Menú.
		JMenuBar barra = new JMenuBar();
		JMenu menu1 = new JMenu("Menú");
		
		JMenuItem menuitemSalir = new JMenuItem("Salir aplicación");
		menuitemSalir.setMnemonic('S');
		
		JMenuItem menuitemCerrarSesion = new JMenuItem("Cerrar sesión");
		menuitemCerrarSesion.setMnemonic('C');
		
		//acciones de items
		menuitemCerrarSesion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				JOptionPane.showMessageDialog(null,"Sesión finalizada exitosamente");
				dispose();
				Validacion valin = new Validacion();
				valin.setVisible(true);
			}
		});
		
		menuitemSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				System.exit(0);
			}
		});
	//---------
		JButton btnUsuarioMaterialesDoReservas = new JButton("Secci\u00F3n de usuarios, materiales y hacer reservas");
		btnUsuarioMaterialesDoReservas.setMnemonic('u');
		btnUsuarioMaterialesDoReservas.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnUsuarioMaterialesDoReservas.setIcon(new ImageIcon("src/images/usuariosvarios.png"));
		btnUsuarioMaterialesDoReservas.setBounds(163, 56, 327, 39);
		contentPane.add(btnUsuarioMaterialesDoReservas);
		btnUsuarioMaterialesDoReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuariosMaterialesDoReservas umdr=new UsuariosMaterialesDoReservas();
				umdr.setVisible(true);
			}
		});
		
		JButton btnNotas = new JButton("Secci\u00F3n de notas");
		btnNotas.setMnemonic('n');
		btnNotas.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnNotas.setIcon(new ImageIcon("src/images/Notas.png"));
		btnNotas.setBounds(163, 115, 327, 32);
		contentPane.add(btnNotas);
		btnNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notas n=new Notas();
				n.setVisible(true);
			}
		});
		
		JButton btnArticulos = new JButton("Secci\u00F3n art\u00EDculos");
		btnArticulos.setMnemonic('a');
		btnArticulos.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnArticulos.setIcon(new ImageIcon("src/images/prestamo.png"));
		btnArticulos.setBounds(163, 233, 327, 32);
		contentPane.add(btnArticulos);
		btnArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Articulos a=new Articulos();
				a.setVisible(true);
			}
		});
		
		JButton btnSeccionReserva = new JButton("Secci\u00F3n de reservas");
		btnSeccionReserva.setMnemonic('r');
		btnSeccionReserva.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnSeccionReserva.setIcon(new ImageIcon("src/images/materiales.png"));
		btnSeccionReserva.setBounds(163, 176, 327, 32);
		contentPane.add(btnSeccionReserva);
		btnSeccionReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservas r=new Reservas();
				r.setVisible(true);
			}
		});
		
		
		JButton btnImportarMateriales = new JButton("Importar materiales");
		btnImportarMateriales.setMnemonic('m');
		btnImportarMateriales.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnImportarMateriales.setIcon(new ImageIcon("src/images/insertar.png"));
		btnImportarMateriales.setBounds(553, 56, 172, 39);
		contentPane.add(btnImportarMateriales);
		btnImportarMateriales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnImportarMaterialesPerformed(e);
			}
		});
		
		JButton btnInfoApli = new JButton("Informaci\u00F3n de la aplicaci\u00F3n");
		btnInfoApli.setMnemonic('p');
		btnInfoApli.setIcon(new ImageIcon("src/images/alquiler.png"));
		btnInfoApli.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnInfoApli.setBounds(163, 348, 327, 32);
		contentPane.add(btnInfoApli);
		btnInfoApli.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					btnInfoApliPerformed(e);
				} catch (SQLException e1) {
					
				} catch (IOException e1) {
					
				}
		}
		});
		
		
		JButton btnRevistasAddArticulos = new JButton("Agregar art\u00EDculos a revistas");
		btnRevistasAddArticulos.setMnemonic('i');
		btnRevistasAddArticulos.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnRevistasAddArticulos.setIcon(new ImageIcon("src/images/crearnote.png"));
		btnRevistasAddArticulos.setBounds(163, 288, 327, 32);
		contentPane.add(btnRevistasAddArticulos);
		
		JLabel fondoadmin = new JLabel("");
		fondoadmin.setIcon(new ImageIcon("src/images/fondoazul.jpg"));
		fondoadmin.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		fondoadmin.setBounds(-23, 0, 840, 481);
		contentPane.add(fondoadmin);
		setResizable(false);
		setLocationRelativeTo(null);
		btnRevistasAddArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RevistasAddArticulos ada=new RevistasAddArticulos();
				ada.setVisible(true);
			}
		});
		
		menu1.add(menuitemCerrarSesion);
		menu1.add(menuitemSalir);
		barra.add(menu1);
		
		this.setJMenuBar(barra);
	}

	protected void btnInfoApliPerformed(ActionEvent e) throws SQLException, IOException {
		InformacionAdmin i=new InformacionAdmin();
		i.setVisible(true);
		
	}

	protected void btnImportarMaterialesPerformed(ActionEvent e) {
		JFileChooser filechooser=new JFileChooser();
		
		String ruta1="";
		
		try{
			
			if(filechooser.showOpenDialog(null)==filechooser.APPROVE_OPTION){
				
				ruta1=filechooser.getSelectedFile().getAbsolutePath();
				
				
			}

			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
			
				try{
					
					Statement stmt=c.getConectado().createStatement();
					File fe=new File(ruta1);
					FileReader fr1=new FileReader(fe);
					BufferedReader br1=new BufferedReader(fr1);
					String s;
					while((s=br1.readLine())!=null){
						StringTokenizer str;
						str=new StringTokenizer(s,"_");
						String comando="INSERT INTO material VALUES ("+
						str.nextToken()+"," //COD MATERIAL
						+str.nextToken()+"," //ISBN
						+str.nextToken()+"," //ISSN
						+"'"+str.nextToken()+"'," //AUTOR
						+"'"+str.nextToken()+"',"//TITULO
						+"'"+str.nextToken()+"',"//EDITORIAL
						+"'"+str.nextToken()+"',"//AÑO
						+str.nextToken()+","//PAGINAS
						+"'"+str.nextToken()+"',"//GENERO
						+"'"+str.nextToken()+"'," //TIPO
						+str.nextToken() //DISPONIBLE
						+") ON DUPLICATE KEY UPDATE cod_material=LAST_INSERT_ID(cod_material)";
						stmt.executeUpdate(comando);
						
					}
					
					if(fr1!=null)fr1.close();
					JOptionPane.showMessageDialog(null, "Exportado con éxito");
					
				}catch (FileNotFoundException fnf) {
					
				}catch(IOException ioe){
					ioe.printStackTrace();
				}catch (Throwable ex) {
					ex.printStackTrace();
				}

	}
}
