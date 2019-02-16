package maven.mavenJDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CarreraDao extends DaoSupport<Carrera > {

	public CarreraDao(ConnectionManager connectionManager) {
		super(connectionManager);
		// TODO Auto-generated constructor stub
	}

	@Override //Devuelve el String para pasarle al metodo de la superClase que procesa el algoritmo
	protected String sqlInsert(Carrera entidad) {
		String sql = "insert into carrera (nombre) values (?)";
		return sql;
	}

	@Override
	protected Map<Integer, Object> getParameters(Carrera entidad) {
		Map<Integer, Object> parameters = new HashMap<>();
		parameters.put(1, entidad.getNombre());
		return parameters;
	}

	@Override
	protected String tableName() {		
		return "carrera";
	}

	@Override
	protected Carrera createEntity(ResultSet resultSet) throws SQLException {
		Carrera carrera = new Carrera();
		carrera.setId(resultSet.getInt(1));
		carrera.setNombre(resultSet.getString(2));
		return carrera;
	}

	@Override
	protected String sqlUdate(Carrera entidad) {
		String sql = "update carrera set nombre = ? where id = ?";
		return sql;
	}
}
