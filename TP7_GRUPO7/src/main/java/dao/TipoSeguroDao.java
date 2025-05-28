package dao;

import java.util.ArrayList;

import entidad.TipoSeguro;

public interface TipoSeguroDao {
	
	public boolean insert(TipoSeguro tipo);
	
	public ArrayList<TipoSeguro> obtenerTodos();

}