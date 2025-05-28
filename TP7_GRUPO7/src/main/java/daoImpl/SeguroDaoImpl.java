package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.SeguroDao;

import entidad.Seguro;
import entidad.TipoSeguro;

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
	
	
public ArrayList<Seguro> obtenerSeguros(){
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
	
		ArrayList<Seguro> lSeguros = new ArrayList<Seguro>();
		Connection cn = null;
		
		try
		{	
			cn = Conexion.getConexion().getSQLConexion();

		 String query = "SELECT * FROM segurosgroup.seguros";
		 Statement st = cn.createStatement();
		 ResultSet rs = st.executeQuery(query);
		 while(rs.next()) 
		 {
			 Seguro s = new Seguro();
			 s.setId(rs.getInt("id"));
			 s.setDescripcion(rs.getString("descripcion"));
			 TipoSeguro tipo = new TipoSeguro();
			 tipo.setId(rs.getInt("tipoSeguro")); 
			 s.setTipoSeguro(tipo);
			 s.setCostoContratacion(rs.getFloat("costoContratacion"));
			 s.setCostoAsegurado(rs.getFloat("costoAsegurado"));
			 lSeguros.add(s);
			 
		 }
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lSeguros;
	}
	
}
