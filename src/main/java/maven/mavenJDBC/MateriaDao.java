package maven.mavenJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MateriaDao implements Dao<Materia>{
	
	private ConnectionManager connectionManager;	
	
	public MateriaDao(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public Materia grabar(Materia materia) throws SQLException {
		String sql = "insert into materia (nombre, cargaHoraria) values (? , ?)";
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, materia.getNombre());
		statement.setInt(2, materia.getCargaHoraria());
		
		statement.executeUpdate(); //Hasta aquí ingreso el objeto creado
		
		ResultSet keys = statement.getGeneratedKeys(); //aquí consulto el id que le asignó la Base de Datos
		while (keys.next()) {
			materia.setCargaHoraria(keys.getInt(1));			
		}
		System.out.println("El nuevo estudiante se ingresó con los siguientes datos:" + materia );
		return materia;
	}

	public void actualizar(Materia entidad) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void borrar(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public List<Materia> obtenerTodos() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Materia obtenerPorId(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
