package maven.mavenJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class DaoSupport<T extends Entidad> implements Dao<T> {
	
	private ConnectionManager connectionManager;
		
	public DaoSupport(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	//las subclases se encargarán solo de definir los siguientes dos métodos;
	//delega las partes que diferencian una clase de otra
	
	//protected abstract String sqlInsert(T entidad);
	//protected abstract String sqlUdate(T entidad); //reemplazarlos luego por un metodo que me devuelva las columnas 
	protected abstract Map<Integer, Object> getParameters(T entidad);
	protected abstract String tableName();
	protected abstract T createEntity(ResultSet resultSet) throws SQLException;
	
	// el metodo de la superClases realiza la operación con los valores abstractos que se definiran en cada Dao
	//Lo común está en una super clase
		
	
	public T grabar(T entidad) throws SQLException {
		String sql = creatingSqlInsert(entidad);
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		setParameters(entidad, statement);
		statement.executeUpdate();
		
		//Busco el ID generado
		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
			entidad.setId(generatedKeys.getInt(1));			
		}		
				return entidad;
	}

	////////// CODIGO GENERAL PARA LOS INSERT DE TODOS LOS DAO //////////
	
	public List<String> nombresColumnas() throws SQLException {
		
	String sql = "select * from " + tableName();
	PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
	ResultSet rs = statement.executeQuery(sql);
	ResultSetMetaData rsmd = rs.getMetaData();
	Integer columnCount = rsmd.getColumnCount();
	List<String> columnNames = new ArrayList<>();
	
	for (int i = 2; i <= columnCount; i++ ) {
	  String name = rsmd.getColumnName(i);
	  columnNames.add(name);	  
	}		
	return columnNames;	
	}
	
	public String recorrerList() throws SQLException {
				
		return nombresColumnas().get(0);
	}
	
	protected String creatingSqlInsert(T entidad) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into " + tableName() + " ");
		sb.append("(");
		
		for (int i = 0; i < nombresColumnas().size(); i++) {
			sb.append(nombresColumnas().get(i));
			sb.append(",");
		}
		 sb.deleteCharAt(sb.length()-1);
		 sb.append(")");
		 sb.append(" values (" );
		 
		for (int i = 0; i < nombresColumnas().size(); i++) {
			sb.append("?");
			sb.append(",");
		} 
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		 		 
		return sb.toString();		
	}
	
	protected String creatingSqlUpdate(T entidad) throws SQLException {
		//"update carrera set nombre = ? where id = ?";
		StringBuilder sb = new StringBuilder();
		sb.append("update " + tableName() + " ");
		sb.append("set ");
		
		for (int i = 0; i < nombresColumnas().size(); i++) {
			sb.append(nombresColumnas().get(i));
			sb.append(" = ?,");
			sb.append(" ");
		}
		 sb.deleteCharAt(sb.length()-2);
		 sb.append(" where id = ?");
	
		 		 
		return sb.toString();		
	}
	
	//////////    FIN CODIGO NUEVO ///////////////////////	
	
	public void actualizar(T entidad) throws SQLException {
		String sql = creatingSqlUpdate(entidad);
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
		setParameters(entidad, statement);
		statement.setInt(getParameters(entidad).size()+1, entidad.getId());
		statement.executeUpdate();		
	}

	private void setParameters(T entidad, PreparedStatement statement) {
		getParameters(entidad).forEach((Integer position , Object value) -> {
			try {
			statement.setObject(position, value);
		} catch (SQLException e) {
			e.printStackTrace();
		   }
		});
	}

	public void borrar(Integer id) throws SQLException {
		String sql = "delete * from " + tableName() + " where id = ?";
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();		
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
		String sql = "select * from " + tableName() + " where id = ?;";
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		
		T entidad = null;
		if (resultSet.next()) {
			entidad = createEntity(resultSet);
		}		
		return entidad;
	}
}
