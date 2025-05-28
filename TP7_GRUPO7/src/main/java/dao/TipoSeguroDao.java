package dao;

import java.util.List;
import java.util.ArrayList;

import entidad.TipoSeguro;

public interface TipoSeguroDao {
	
	public boolean insert(TipoSeguro tipo);
	
	List<TipoSeguro> listarTiposSeguro();

	public ArrayList<TipoSeguro> obtenerTodos();

}