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
	protected String sqlInsert(Materia entidad) {
		String sql = "insert into materia (nombre, cargaHoraria) values ( ?, ?) ";
		return sql;
	}

	@Override
	protected Map<Integer, Object> getParameters(Materia entidad) {
		Map<Integer, Object> parameters = new HashMap<>();
		parameters.put(1, entidad.getNombre());
		parameters.put(2, entidad.getCargaHoraria());
		return parameters;
	}
	

}
