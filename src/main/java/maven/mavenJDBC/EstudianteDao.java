package maven.mavenJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Endpoint;


public class EstudianteDao extends DaoSupport<Estudiante> {

	public EstudianteDao(ConnectionManager connectionManager) {
		super(connectionManager);
	}

	@Override
	protected String sqlInsert(Estudiante entidad) {
		String sql = "insert into estudiante (nombre, apellido, padron) values (? , ? , ?)";
		return sql;
	}

	@Override
	protected Map<Integer, Object> getParameters(Estudiante entidad) {
		Map<Integer, Object> parameters = new HashMap<>();
		parameters.put(1, entidad.getNombre());
		parameters.put(2, entidad.getApellido());
		parameters.put(3, entidad.getPadron());
		return parameters;
	}

	@Override
	protected String tableName() {
		return "estudiante";
	}

	@Override
	protected Estudiante createEntity(ResultSet resultSet) throws SQLException {
		Estudiante estudiante = new Estudiante();
		estudiante.setId(resultSet.getInt(1));
		estudiante.setNombre(resultSet.getString(2));
		estudiante.setApellido(resultSet.getString(3));
		estudiante.setPadron(resultSet.getString(4));
		return estudiante;
	}
	
	
}

