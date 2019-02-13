package maven.mavenJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EstudianteDao implements Dao<Estudiante> {
	// creo dependencia a la Clase que hará la conexción
	private ConnectionManager connectionManager;
				
	public EstudianteDao(ConnectionManager connectionManager) {
		super();
		this.connectionManager = connectionManager;
	}
		
	public Estudiante grabar(Estudiante estudiante) throws SQLException {
		
		String sql = "insert into estudiante (nombre, apellido, padron) values (? , ? , ?)";
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
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
		String sql = "update estudiante set nombre = ?, apellido = ?, padron = ? where id_estudiante =?";
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
		statement.setString(1, estudiante.getNombre());;
		statement.setString(2, estudiante.getApellido());
		statement.setString(3, estudiante.getPadron());
		statement.setInt(4, estudiante.getId_estudiante());
		statement.executeUpdate();
	}
	
	public void borrar (Integer id)throws SQLException{
		String sql = "delete from estudiante where id_estudiante =?";
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
	}
	
	public List<Estudiante> obtenerTodos() throws SQLException{
		
		String sql = "Select * from estudiante";		
		Statement statement = connectionManager.conectarse().createStatement();		
		ResultSet resultSet = statement.executeQuery(sql);
		
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		while (resultSet.next()) {			
			estudiantes.add(construir(resultSet));			
		}
		
		return estudiantes;
	}
 
	public Estudiante obtenerPorId(Integer id) throws SQLException{
		
		String sql = "Select * from estudiante where id = ?";		
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
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

