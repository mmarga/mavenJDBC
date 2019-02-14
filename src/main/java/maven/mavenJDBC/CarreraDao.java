package maven.mavenJDBC;

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

	


}
