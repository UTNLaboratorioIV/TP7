package negocioImpl;

import dao.SeguroDao;
import daoImpl.SeguroDaoImpl;

import entidad.Seguro;
import negocio.SeguroNegocio;

public class SeguroNegocioImpl implements SeguroNegocio{

	SeguroDao seguroDao = new SeguroDaoImpl();
	
	@Override
	public boolean insert(Seguro seguro) {
		
		boolean estado=false;
		if(seguro.getDescripcion().trim().length()>0 && seguro.getTipoSeguro().getId()>0
			&& seguro.getCostoContratacion()>0 && seguro.getCostoAsegurado()>0)
		{
			estado=seguroDao.insert(seguro);
		}
		return estado;
	}
	public int obtenerProximoIdSeguro() {
	    SeguroDao seguroDao = new SeguroDaoImpl();
	    return seguroDao.obtenerProximoId();
	}

	
}
