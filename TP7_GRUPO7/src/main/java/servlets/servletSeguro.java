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
import entidad.TipoSeguro;
import dao.SeguroDao;
import dao.TipoSeguroDao;
import daoImpl.SeguroDaoImpl;
import daoImpl.TipoSeguroDaoImpl;

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
        
        String param = request.getParameter("Param");

        if (param == null || !param.equals("1")) {
            response.sendRedirect("Inicio.jsp"); 
            return;
        }

        SeguroDao sDao = new SeguroDaoImpl();
        TipoSeguroDao tsDao = new TipoSeguroDaoImpl();

        ArrayList<TipoSeguro> listaTipos = tsDao.obtenerTodos();
        request.setAttribute("listaTipos", listaTipos);

        String tipoSeguroParam = request.getParameter("tipoSeguro");
        int tipoFiltro = 0;

        if (tipoSeguroParam != null) {
            try {
                tipoFiltro = Integer.parseInt(tipoSeguroParam);
            } catch (NumberFormatException e) {
                tipoFiltro = 0;
            }
        }

        ArrayList<Seguro> listaSeguros = (tipoFiltro == 0)
            ? sDao.obtenerSeguros()
            : sDao.obtenerSegurosPorTipo(tipoFiltro);

        request.setAttribute("listaS", listaSeguros);
        request.setAttribute("tipoSeguroSeleccionado", tipoFiltro);

        RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
        rd.forward(request, response);
    }
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
