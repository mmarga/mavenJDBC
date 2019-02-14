package maven.mavenJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.text.Position;

public abstract class DaoSupport<T extends Entidad> implements Dao<T> {
	
	private ConnectionManager connectionManager;
		
	public DaoSupport(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	//las subclases se encargarán solo de definir los siguientes dos métodos;
	//delega las partes que diferencian una clase de otra
	
	protected abstract String sqlInsert(T entidad);
	protected abstract Map<Integer, Object> getParameters(T entidad);
	protected abstract String tableName();
	protected abstract T createEntity(ResultSet resultSet) throws SQLException;
	// el metodo de la superClases realiza la operación con los valores abstractos que se definirarn
	//en la aplicación de cada Dao particular
	//lo común está en una super clase
	public T grabar(T entidad) throws SQLException {
		String sql = sqlInsert(entidad);
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		getParameters(entidad).forEach((Integer position , Object value) -> {
			try {
			statement.setObject(position, value);
		} catch (SQLException e) {
			e.printStackTrace();
		   }
		});
		statement.executeUpdate();
		
		//Busco el ID generado
		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			entidad.setId(generatedKeys.getInt(1));			
		}		
				return entidad;
	}

	public void actualizar(T entidad) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void borrar(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public List<T> obtenerTodos() throws SQLException {
		String sql = "select * from " + tableName();
		Statement statement = connectionManager.conectarse().createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<T> entidades = new ArrayList<>();
		while (resultSet.next()) {
			entidades.add(createEntity(resultSet));
			
		}
			
		return entidades;
	}

	public T obtenerPorId(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
