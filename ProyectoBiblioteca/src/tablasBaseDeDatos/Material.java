package tablasBaseDeDatos;

public class Material {
	private static int cod_material, isbn, issn, pag_duracion;
	private static String autor_director, titulo, editorial, ano, genero, tipo;
	private static boolean disponible;
	
	public int getCod_material() {
		return cod_material;
	}
	public void setCod_material(int cod_material) {
		Material.cod_material = cod_material;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		Material.isbn = isbn;
	}
	public int getIssn() {
		return issn;
	}
	public void setIssn(int issn) {
		Material.issn = issn;
	}
	public int getPag_duracion() {
		return pag_duracion;
	}
	public void setPag_duracion(int pag_duracion) {
		Material.pag_duracion = pag_duracion;
	}
	public String getAutor_director() {
		return autor_director;
	}
	public void setAutor_director(String autor_director) {
		Material.autor_director = autor_director;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		Material.titulo = titulo;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		Material.editorial = editorial;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		Material.ano = ano;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		Material.genero = genero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		Material.tipo = tipo;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		Material.disponible = disponible;
	}

}
