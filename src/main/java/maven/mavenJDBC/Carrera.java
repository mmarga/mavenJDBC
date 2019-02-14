package maven.mavenJDBC;

public class Carrera extends Entidad {
	
	private String nombre;
	
	public Carrera() {
		
	}
	
	public Carrera(int id, String nombre) {
		super();
		this.nombre = nombre;
	}
	



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Carrera [nombre=" + nombre + "]";
	}





	
	
	
	
}
