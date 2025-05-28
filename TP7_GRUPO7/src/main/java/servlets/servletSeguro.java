package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Seguro;
import dao.SeguroDao;
import daoImpl.SeguroDaoImpl;




@WebServlet("/servletSeguro")//NOMBRE PARA SER LOCALIZADO
public class servletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletSeguro() {
        super();
        // TODO Auto-generated constructor stub
    }

	//DOS METODOS DOGET Y DOPOST
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!= null)
		{
			
			SeguroDao sDao = new SeguroDaoImpl();
			ArrayList<Seguro> lista = sDao.obtenerSeguros();
			
			//REQUESTDISPATCHER  
			request.setAttribute("listaS",lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
			
			rd.forward(request,response);
		}
	}
		
		
		
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
			
			
		
	}

}
