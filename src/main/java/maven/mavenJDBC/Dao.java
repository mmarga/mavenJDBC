package maven.mavenJDBC;
//hago una interfaz genérica

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	
	//como los métodos recíben y retornan elementos distintos necesito pasar por argumento un genérico
	T grabar(T entidad) throws SQLException;
	void actualizar(T entidad) throws SQLException;
	void borrar(Integer id) throws SQLException;
	List<T> obtenerTodos() throws SQLException;
	T obtenerPorId(Integer id)throws SQLException;
}
