package tablasBaseDeDatos;

public class Usuario {
	private static String dni,nombre,apellidos,nick, clave, direccion, telefono;
	private static int tipo;
	private static boolean penalizado, alquiler;
	
	public void setDni(String d) {
		dni = d;
	}
	public void setNombre(String n) {
		nombre = n;
	}
	public void setApellidos(String a) {
		apellidos = a;
	}
	public void setNick(String n) {
		nick = n;
	}
	public void setClave(String c) {
		clave = c;
	}
	public void setDireccion(String d) {
		direccion = d;
	}
	public void setTelefono(String t) {
		telefono = t;
	}
	public void setTipo(int t) {
		tipo = t;
	}
	public void setPenalizado(boolean p) {
		penalizado = p;
	}
	public void setAlquiler(boolean a) {
		alquiler = a;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	public String getNick(){
		return nick;
	}
	
	public String getDni(){
		return dni;
	}

}
