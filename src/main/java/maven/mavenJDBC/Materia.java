package maven.mavenJDBC;

public class Materia {

	private int id;
	private String nombre;
	private int cargaHoraria;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	public Materia(int id, String nombre, int cargaHoraria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cargaHoraria = cargaHoraria;
	}
	@Override
	public String toString() {
		return "Materia [id=" + id + ", nombre=" + nombre + ", cargaHoraria=" + cargaHoraria + "]";
	}
}
