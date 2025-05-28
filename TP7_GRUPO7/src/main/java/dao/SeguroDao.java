package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import entidad.Seguro;

public interface SeguroDao {
	
	public boolean insert(Seguro seguro);

	public ArrayList<Seguro> obtenerSeguros();

	

	
	

}
