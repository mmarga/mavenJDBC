package maven.mavenJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class EstudianteDao {
		
	public int grabar(Estudiante estudiante) throws SQLException {
		
		String sql = "insert into estudiante (nombre, apellido, padron) values (? , ? , ?)";
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, estudiante.getNombre());
		statement.setString(2, estudiante.getApellido());
		statement.setString(3, estudiante.getPadron());
		int rows = statement.executeUpdate();
		System.out.println("Registros ingresasdos: " + rows + "correspondientes a: " + estudiante );
		return rows;
	}
	
	
	public Estudiante grabar2(Estudiante estudiante) throws SQLException {
		
		String sql = "insert into estudiante (nombre, apellido, padron) values (? , ? , ?)";
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		PreparedStatement statement = connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, estudiante.getNombre());
		statement.setString(2, estudiante.getApellido());
		statement.setString(3, estudiante.getPadron());
		statement.executeUpdate(); //Hasta aquí ingreso el objeto creado
		
		ResultSet keys = statement.getGeneratedKeys(); //aquí consulto el id que le asignó la Base de Datos
		while (keys.next()) {
			estudiante.setId_estudiante(keys.getInt(1));			
		}
		System.out.println("El nuevo estudiante se ingresó con los siguientes datos:" + estudiante );
		return estudiante;
	}
	
	public List<Estudiante> select() throws SQLException{
		
		String sql = "Select * from estudiante";		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		Statement statement = connection.createStatement();		
		ResultSet resultSet = statement.executeQuery(sql);
		
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		while (resultSet.next()) {			
			Estudiante e = new Estudiante();			
			e.setId_estudiante(resultSet.getInt("id_estudiante"));
			e.setNombre(resultSet.getString("nombre"));
			e.setApellido(resultSet.getString("apellido"));
			e.setPadron(resultSet.getString("padron"));
			estudiantes.add(e);			
		}
		
		return estudiantes;
	}

	public Estudiante obtenerPorId(int id) throws SQLException{
		
		String sql = "Select * from estudiante where id = ?";		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		
		Estudiante estudiante = null;
		
		if (resultSet.next()) {
			estudiante.setId_estudiante(resultSet.getInt(1));
			estudiante.setNombre(resultSet.getString(2));
			estudiante.setApellido(resultSet.getString(3));
			estudiante.setPadron(resultSet.getString(4));
			
		}	
		
		return estudiante;
	}
	
	
	
}
