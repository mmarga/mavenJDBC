package maven.mavenJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;



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
	
	public void actualizar (Estudiante estudiante) throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		String sql = "update estudiante set nombre = ?, apellido = ?, padron = ? where id =?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, estudiante.getNombre());;
		statement.setString(2, estudiante.getApellido());
		statement.setString(3, estudiante.getPadron());
		statement.setInt(4, estudiante.getId_estudiante());
		statement.executeUpdate();
	}
	
	public void borrar (int id)throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		String sql = "delete from estudiante where id =?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
	}
	
	public List<Estudiante> select() throws SQLException{
		
		String sql = "Select * from estudiante";		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavenjdbc", "root", "");
		Statement statement = connection.createStatement();		
		ResultSet resultSet = statement.executeQuery(sql);
		
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		while (resultSet.next()) {			
			estudiantes.add(construir(resultSet));			
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
			estudiante = construir(resultSet); //ver método común a todos
		}	
		
		return estudiante;
	}
	
	//código común a todos los métodos.
	
	private Estudiante construir(ResultSet resultSet) throws SQLException {
		Estudiante estudiante = new Estudiante();
		estudiante.setId_estudiante(resultSet.getInt(1));
		estudiante.setNombre(resultSet.getString(2));
		estudiante.setApellido(resultSet.getString(3));
		estudiante.setPadron(resultSet.getString(4));
		return estudiante;
	}
	
	
}

