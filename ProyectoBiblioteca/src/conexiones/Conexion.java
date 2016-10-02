package conexiones;

import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class Conexion {

	static String conecta;
	static String bdname;
	static String user;
	static String pass="";
	static Connection conexion;
	private AdministrarConexiones a=new AdministrarConexiones();

	
	public String getConecta() {
		return conecta;
	}


	public void setConecta(String conecta) {
		Conexion.conecta = conecta;
	}


	public String getBdname() {
		return bdname;
	}


	public void setBdname(String bdname) {
		Conexion.bdname = bdname;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		Conexion.user = user;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		Conexion.pass = pass;
	}

	public void accesoArchivo() {
		
		try{
			
			File fe=new File("src/conexiones/conexion.txt");
			FileReader fr1=new FileReader(fe);
			BufferedReader br1=new BufferedReader(fr1);
			String s;
			while((s=br1.readLine())!=null){
				StringTokenizer str;
				str=new StringTokenizer(s,",");
				setConecta(str.nextToken());
				setBdname(str.nextToken());
				setUser(str.nextToken());
				setPass(str.nextToken());
			}
			
			if(fr1!=null) fr1.close();
		}catch (EOFException eof) {
			System.out.println(" --------------------------");
		}catch (FileNotFoundException fnf) {
			System.err.println("Fichero no encontrado " + fnf);
		}catch(IOException e){
			System.err.println("Se ha producido una IOException");
			e.printStackTrace();
		}catch (Throwable e) {
			System.err.println("Error de programa: " + e);
			e.printStackTrace();
		}
		
	}
	
	public void escribirArchivo(){
		
		File f=new File("src/conexiones/conexion.txt");
		
		try{
			
		FileWriter escritor=new FileWriter(f);
		BufferedWriter bufferEscritor=new BufferedWriter(escritor);
		
		bufferEscritor.write(a.getIp()+","+a.getBdname()+","+a.getUser()+","+a.getPass());
		
		bufferEscritor.close();
		
		}catch(Exception e){
			
			System.out.println("No se ha podido escribir el archivo");;
			
		}
		
	}
	
	public Connection getConectado()throws IOException, SQLException{
		
			
		accesoArchivo();
		conexion=DriverManager.getConnection("jdbc:mysql://"+getConecta()+"/"+getBdname()+"",""+getUser()+"",""+getPass()+"");
		
		return conexion;
		
		
		
	}
	
	public void getClose()throws IOException, SQLException{
			
		conexion.close();
		
	}
	
	
	
}
