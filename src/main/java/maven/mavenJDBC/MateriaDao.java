package maven.mavenJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

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

	
	public void actualizar(Materia materia) throws SQLException {
		String sql = "update materia set nombre = ?, cargaHoraria = ? where id = ?";
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
		statement.setString(1, materia.getNombre());
		statement.setInt(2, materia.getCargaHoraria());
		statement.setInt(3, materia.getId());
		int rows = statement.executeUpdate();
		System.out.println("Registro actualizados:" + rows);		
	}

	
	public void borrar(Integer id) throws SQLException {
		String sql = "delete from materia where id = ?";
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
		statement.setInt(1, id);
		int rows = statement.executeUpdate();
		System.out.println("Registros borrados:" + rows);			
	}

	public List<Materia> obtenerTodos() throws SQLException {
		String sql = "Select * from materia";
		PreparedStatement statement = connectionManager.conectarse().prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		List<Materia> ListaMaterias = new ArrayList<Materia>();
		while (resultSet.next()) {			
			ListaMaterias.add(construir(resultSet));
		}
		return ListaMaterias;
	}

	public Materia obtenerPorId(Integer id) throws SQLException {
		String sql = "Select * from materia where id = ?";
		PreparedStatement statement =  connectionManager.conectarse().prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		Materia materia = null;
		while (resultSet.next()) {
			materia = construir(resultSet);
		}
		return materia;
	}
	
	private Materia construir(ResultSet resultSet) throws SQLException {
		Materia materia = new Materia();
		materia.setId(resultSet.getInt(1));
		materia.setNombre(resultSet.getString(2));
		materia.setCargaHoraria(resultSet.getInt(3));
		return materia;
	}

}
