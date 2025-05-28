package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

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

	@Override
	public List<TipoSeguro> listarTiposSeguro() {
		List<TipoSeguro> lista = new ArrayList<>();
        String sql = "SELECT idTipo, descripcion FROM tiposeguros";
        Connection con = Conexion.getConexion().getSQLConexion();

        try (
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoSeguro tipo = new TipoSeguro();
                tipo.setId(rs.getInt("idTipo"));
                tipo.setDescripcion(rs.getString("descripcion"));
                lista.add(tipo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


	
    public ArrayList<TipoSeguro> obtenerTodos() {
        ArrayList<TipoSeguro> lista = new ArrayList<>();
        Connection cn = null;

        try {
            cn = Conexion.getConexion().getSQLConexion();
            String query = "SELECT idTipo, descripcion FROM segurosgroup.tiposeguros";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                TipoSeguro tipo = new TipoSeguro();
                tipo.setId(rs.getInt("idTipo"));
                tipo.setDescripcion(rs.getString("descripcion"));
                lista.add(tipo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
