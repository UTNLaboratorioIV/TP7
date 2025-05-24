package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.TipoSeguroDao;
import entidad.TipoSeguro;

public class TipoSeguroDaoImpl implements TipoSeguroDao{

	private static String insert = "Insert into tipoSeguros (idTipo, descripcion) VALUES  (?, ?)";
	
	
	public TipoSeguroDaoImpl()
	{
		
	}
	
	
	public boolean insert(TipoSeguro t)
	{
		PreparedStatement statement;
		Connection cn = Conexion.getConexion().getSQLConexion();
		boolean insertResult = false;
		
		
		try {
			statement = cn.prepareStatement(insert);
			statement.setInt(1, t.getId());
			statement.setString(2, t.getDescripcion());
			
			if(statement.executeUpdate() > 0)
			{
				cn.commit();
				insertResult = true;
				System.out.println("tipo de seguro insertado");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				
				cn.rollback();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
		
		return insertResult;
	}
}
