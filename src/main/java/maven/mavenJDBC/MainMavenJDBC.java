package maven.mavenJDBC;

import java.sql.SQLException;

public class MainMavenJDBC {

	public static void main(String[] args) throws SQLException {
		ConnectionManager connectionManager = new ConnectionManager();
		//EstudianteDao estudianteDao = new EstudianteDao(connectionManager);
		//System.out.println(estudianteDao.select()); 
		/*
		Estudiante estudiante = new Estudiante();
		estudiante.setNombre("Pedro");
		estudiante.setApellido("Picapiedra");
		estudiante.setPadron("Fisica");
		
		estudianteDao.grabar(estudiante);
		
		System.out.println("Lista actualizada");
		System.out.println(estudianteDao.select()); 
	
		//con metodo de generated keys
		
		Estudiante estudiante2 = new Estudiante("Carlos", "Bala", "Comedia");
		System.out.println(estudiante2);
		
		
		estudianteDao.grabar2(estudiante2);
		
		System.out.println("Lista actualizada 2");
		System.out.println(estudianteDao.select()); 
		
		*/
		
		// Pruebas con EstudianteDao2
		
		//Estudiante estudiante3 = new Estudiante("Estudiante", "De Dao2", "Progamación");
		//EstudianteDao2 estudianteDao2 = new EstudianteDao2();
		//estudianteDao2.grabar(estudiante3);
		//estudianteDao2.seleccionar();
		
		//Estudiante estudiante4 = new Estudiante("Benito", "Musso", "Duce");
		//estudianteDao2.grabarConId(estudiante4);
		
		/*Estudiante estudiante6 = new Estudiante("Roberto", "Cocuso", "enano");
		estudiante6.setId_estudiante(11);
		estudianteDao.actualizar(estudiante6);
		System.out.println(estudianteDao.select());		
	*/
		
	/*	Estudiante estudiante7 = new Estudiante("Roberto", "Galan", "Viejo forro");
		estudiante7.setId_estudiante(11);
		estudianteDao.borrar(11);	
		
	*/
	/*	Materia materia = new Materia(2, "Litera", 30);
		MateriaDao materiaDao = new MateriaDao(connectionManager);
		
		materiaDao.borrar(2);
	*/
	/*	CarreraDao carreraDao = new CarreraDao(connectionManager);
		Carrera carrera = new Carrera(1 , "Abogacía");
		System.out.println(carreraDao.obtenerTodos());
	*/	
	/*	Materia materia = new Materia(3, "Lengua y literatura", 32);
		MateriaDao materiaDao = new MateriaDao(connectionManager);
		//materiaDao.grabar(materia);		
		System.out.println(materiaDao.obtenerPorId(1));
	
	*/	
		
		EstudianteDao estudianteDao = new EstudianteDao(connectionManager);
		System.out.println(estudianteDao.obtenerPorId(1));

		
		
		
	}
	

		

}
