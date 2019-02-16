package maven.mavenJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainMavenJDBC {

	public static void main(String[] args) throws SQLException {
		ConnectionManager connectionManager = new ConnectionManager();

		Materia materia = new Materia(10, "Literatura Americada", 32);
		MateriaDao materiaDao = new MateriaDao(connectionManager);
		materiaDao.grabar(materia);

		
		Estudiante estudiante = new Estudiante("Raun enano", "Perez", "Programador");
		EstudianteDao estudianteDao = new EstudianteDao(connectionManager);
		estudianteDao.grabar(estudiante);
		
		Carrera carrera = new Carrera();
		carrera.setNombre("Cocina");
		CarreraDao carreraDao = new CarreraDao(connectionManager);
		carreraDao.grabar(carrera);

		
	}
	

		

}
