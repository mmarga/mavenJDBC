package maven.mavenJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class MateriaDao extends DaoSupport<Materia>{

	public MateriaDao(ConnectionManager connectionManager) {
		super(connectionManager);		
	}
	
	@Override
	protected Map<Integer, Object> getParameters(Materia entidad) {
		Map<Integer, Object> parameters = new HashMap<>();
		parameters.put(1, entidad.getNombre());
		parameters.put(2, entidad.getCargaHoraria());
		return parameters;
	}

	@Override
	protected String tableName() {		
		return "materia";
	}

	@Override
	protected Materia createEntity(ResultSet resultSet) throws SQLException {
		Materia materia = new Materia();
		materia.setId(resultSet.getInt(1));
		materia.setNombre(resultSet.getString(2));
		materia.setCargaHoraria(resultSet.getInt(3));
		return materia;
	}
	
}
