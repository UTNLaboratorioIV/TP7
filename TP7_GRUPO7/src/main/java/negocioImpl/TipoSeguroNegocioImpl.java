package negocioImpl;

import entidad.TipoSeguro;
import negocio.TipoSeguroNegocio;
import dao.TipoSeguroDao;
import daoImpl.TipoSeguroDaoImpl;

public class TipoSeguroNegocioImpl implements TipoSeguroNegocio{

	TipoSeguroDao tSeguroDao = new TipoSeguroDaoImpl();
	
	public boolean insert(TipoSeguro t) {
		boolean estado=false;
		if(t.getDescripcion().trim().length()>0 && t.getId() > 0)
		{
			estado=tSeguroDao.insert(t);
		}
		return estado;
	}

}
