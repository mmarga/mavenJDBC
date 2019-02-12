package maven.mavenJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.event.IIOReadWarningListener;

public class EstudianteDao2 {
	
	public int grabar (Estudiante estudiante) throws SQLException {
		
		String sql = "insert into estudiante (nombre, apellido, padron) values (? , ? , ?)";
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, estudiante.getNombre());
		statement.setString(2, estudiante.getApellido());
		statement.setString(3, estudiante.getPadron());
		int rows = statement.executeUpdate();
		
		System.out.println("Registros actualizados:" + rows + "DATOS: " + estudiante);
		return rows;
		
	}
	
	public Estudiante grabarConId (Estudiante estudiante) throws SQLException {
		
		String sql = "insert into estudiante (nombre, apellido, padron) values (? , ?, ?)";
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, estudiante.getNombre());
		statement.setString(2, estudiante.getApellido());
		statement.setString(3, estudiante.getPadron());
		int rows = statement.executeUpdate();
		
		ResultSet id = statement.getGeneratedKeys();
		
		while (id.next()) {
			
			estudiante.setId_estudiante(id.getInt(1));			
		}
		
		System.out.println("Registros agregados: " + rows + " DATOS: " + estudiante);
		
		return estudiante;
	}
	
	public List<Estudiante> seleccionar() throws SQLException{
		
		String sql = "Select * from estudiante";
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
		Estudiante estudiante = new Estudiante();
		while (resultSet.next()) {
			estudiante.setId_estudiante(resultSet.getInt(1));
			estudiante.setNombre(resultSet.getString(2));
			estudiante.setApellido(resultSet.getString(3));
			estudiante.setPadron(resultSet.getString(4));
			
			listaEstudiantes.add(estudiante);
				
		}
		
		return listaEstudiantes;
	}

}
