package daoImpl;



import java.sql.Connection;
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
			if (seguro.getTipoSeguro() != null && seguro.getTipoSeguro().getId() > 0) {
				statement.setInt(2, seguro.getTipoSeguro().getId());
			}
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
		
	public ArrayList<Seguro> obtenerSeguros() {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    ArrayList<Seguro> lSeguros = new ArrayList<Seguro>();
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();

	        String query = "SELECT s.idSeguro, s.descripcion, s.idTipo, t.descripcion AS descripcionTipo, s.costoContratacion, s.costoAsegurado " +
	                       "FROM segurosgroup.seguros s " +
	                       "JOIN segurosgroup.tiposeguros t ON s.idTipo = t.idTipo";

	        Statement st = cn.createStatement();
	        ResultSet rs = st.executeQuery(query);

	        while (rs.next()) {
	            Seguro s = new Seguro();
	            s.setId(rs.getInt("idSeguro"));
	            s.setDescripcion(rs.getString("descripcion"));

	            TipoSeguro tipoSeg = new TipoSeguro();
	            tipoSeg.setId(rs.getInt("idTipo"));
	            tipoSeg.setDescripcion(rs.getString("descripcionTipo"));
	            s.setTipoSeguro(tipoSeg);

	            s.setCostoContratacion(rs.getFloat("costoContratacion"));
	            s.setCostoAsegurado(rs.getFloat("costoAsegurado"));

	            lSeguros.add(s);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lSeguros;
	}
	
	public ArrayList<Seguro> obtenerSegurosPorTipo(int idTipo) {
	    ArrayList<Seguro> lista = new ArrayList<>();
	    Connection cn = null;

	    try {
	        cn = Conexion.getConexion().getSQLConexion();
	        String query = "SELECT s.idSeguro, s.descripcion, s.costoContratacion, s.costoAsegurado, " +
	                       "ts.idTipo, ts.descripcion AS descripcionTipo " +
	                       "FROM segurosgroup.seguros s " +
	                       "JOIN segurosgroup.tiposeguros ts ON s.idTipo = ts.idTipo " +
	                       "WHERE s.idTipo = ?";
	        PreparedStatement pst = cn.prepareStatement(query);
	        pst.setInt(1, idTipo);
	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            Seguro s = new Seguro();
	            s.setId(rs.getInt("idSeguro"));
	            s.setDescripcion(rs.getString("descripcion"));
	            s.setCostoContratacion(rs.getFloat("costoContratacion"));
	            s.setCostoAsegurado(rs.getFloat("costoAsegurado"));

	            TipoSeguro tipo = new TipoSeguro();
	            tipo.setId(rs.getInt("idTipo"));
	            tipo.setDescripcion(rs.getString("descripcionTipo"));

	            s.setTipoSeguro(tipo);
	            lista.add(s);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
	
}
