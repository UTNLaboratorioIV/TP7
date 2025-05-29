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

@WebServlet("/servletSeguro")
public class servletSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletSeguro() {
        super();
    }

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
		
		Seguro seguro = new Seguro();

		try {
			SeguroDaoImpl segDaoImpl = new SeguroDaoImpl();
			int proximoId = segDaoImpl.obtenerProximoId();
			seguro.setId(proximoId);
			
			seguro.setDescripcion(request.getParameter("txtDescripcion"));
			
		    float costoContratacion = Float.parseFloat(request.getParameter("txtCostoContrato"));
		    float costoAsegurado = Float.parseFloat(request.getParameter("txtCostoMaxAsegurado"));

		    if (costoContratacion <= 0 || costoAsegurado <= 0) {
		        request.setAttribute("mensaje", "Los valores deben ser mayores a cero.");
		        request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
		        return;
		    }

		    seguro.setCostoContratacion(costoContratacion);
		    seguro.setCostoAsegurado(costoAsegurado);
			
			int idTipoSeguro = Integer.parseInt(request.getParameter("cbTipoSeguro"));
			TipoSeguro tipo = new TipoSeguro();
			tipo.setId(idTipoSeguro);
			seguro.setTipoSeguro(tipo);

			boolean insertado = segDaoImpl.insert(seguro);

			if (insertado) {
				request.setAttribute("mensaje", "Seguro agregado con éxito");
			} else {
				request.setAttribute("mensaje", "No se pudo agregar el seguro");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mensaje", "Error al procesar los datos: " + e.getMessage());
		}

		request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
	}
}
