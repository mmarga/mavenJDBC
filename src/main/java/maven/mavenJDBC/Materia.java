package maven.mavenJDBC;

public class Materia extends Entidad {

	private String nombre;
	private int cargaHoraria;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public Materia() {
		
	}
	
	public Materia(int id, String nombre, int cargaHoraria) {
		super();
		this.nombre = nombre;
		this.cargaHoraria = cargaHoraria;
	}
	
}
