package maven.mavenJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainMavenJDBC {

	public static void main(String[] args) throws SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		/*
		Materia materia = new Materia(10, "Literatura Americada", 32);
		MateriaDao materiaDao = new MateriaDao(connectionManager);
		materiaDao.grabar(materia);

		
		Estudiante estudiante = new Estudiante("Raun enano", "Perez", "Programador");
		EstudianteDao estudianteDao = new EstudianteDao(connectionManager);
		estudianteDao.grabar(estudiante);
		*/
		Carrera carrera = new Carrera();
		carrera.setNombre("Administración");
		carrera.setId(5);
		CarreraDao carreraDao = new CarreraDao(connectionManager);
		System.out.println(carreraDao.creatingSqlInsert(carrera));
		System.out.println(carreraDao.creatingSqlUpdate(carrera));
		//carreraDao.grabar(carrera);
		
		Estudiante estudiante = new Estudiante("carlos", "Pelegrini", "presidente");
		EstudianteDao estudianteDao = new EstudianteDao(connectionManager);
		System.out.println(estudianteDao.creatingSqlInsert(estudiante));
		System.out.println(estudianteDao.creatingSqlUpdate(estudiante));
		//estudianteDao.grabar(estudiante);
		estudianteDao.creatingSqlInsertGeneral(estudiante);
		Estudiante estudiante2 = new Estudiante("Ricardito", "Alfonsín", "Hijo del otro");
		estudianteDao.grabar(estudiante2);
		
	}	

}
