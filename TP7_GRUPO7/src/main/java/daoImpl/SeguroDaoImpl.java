package daoImpl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.SeguroDao;
import entidad.Seguro;

public class SeguroDaoImpl implements SeguroDao{

	private static final String insert = "INSERT INTO seguros (descripcion,idTipo,costoContratacion,"
			+ "costoAsegurado) VALUES(?, ?, ?, ?)";
	
	public SeguroDaoImpl()
	{
		
	}
	
	public boolean insert(Seguro seguro)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, seguro.getDescripcion());
			/*if (seguro.getTipoSeguro() != null && seguro.getTipoSeguro().getId() > 0) {
				statement.setInt(2, seguro.getTipoSeguro().getId());
			}*/
			statement.setInt(2, 1); // Testeo sin desarrollar clase TipoSeguro
			statement.setFloat(3, seguro.getCostoContratacion());
			statement.setFloat(4, seguro.getCostoAsegurado());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}

	
	@Override
	public int obtenerProximoId() {
		int proximoId = 1;  //  id por defecto
	    String sqlConsulta = "SELECT COALESCE(MAX(idSeguro), 0) + 1 AS proximo_id FROM seguros";

	    Connection conexion = Conexion.getConexion().getSQLConexion();

	    try (PreparedStatement ps = conexion.prepareStatement(sqlConsulta);
	         ResultSet rs = ps.executeQuery()) {

	        if (rs.next()) {
	            proximoId = rs.getInt("proximo_id");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return proximoId;  
	}
	
	
	
	
	
}
