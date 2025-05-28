<%@ page import="entidad.Seguro" %>
<%@ page import="entidad.TipoSeguro" %>
<%@ page import="daoImpl.SeguroDaoImpl" %>
<%@ page import="dao.SeguroDao" %>
<%@ page import="dao.TipoSeguroDao" %>
<%@ page import="daoImpl.TipoSeguroDaoImpl" %>
<%@ page import="java.util.List" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Agregar Seguro</title>
</head>

<body>

	<header>
		<div>
			<a href="Inicio.jsp" style="margin-right: 10px;"> Inicio</a>
			<a href="AgregarSeguro.jsp" style="margin-right: 10px;"> Agregar Seguro</a>
			<a href="ListarSeguros.jsp">Listar Seguros</a>
		</div>
	</header>

<h2>Agregar Seguros</h2>

<%SeguroDaoImpl segDaoImpl1 = new SeguroDaoImpl();
int proximoId = segDaoImpl1.obtenerProximoId();


TipoSeguroDao tipoDao = new TipoSeguroDaoImpl();
List<TipoSeguro> listaTipos = tipoDao.listarTiposSeguro();

%>

<form method="get" action="AgregarSeguro.jsp" style="font-family: Arial;">
  <table style="border-collapse: separate; border-spacing: 10px;">
    <tr>
      <td style="text-align: left;"><label for="txtId" >ID Seguro:</label></td>
      <td><input type="text" name="txtId" id="txtId" style="width: 200px;" value="<%= proximoId %>" readonly></td>
    </tr>
    <tr>
      <td style="text-align: left;"><label for="txtDescripcion">Descripción:</label></td>
      <td><input type="text" name="txtDescripcion" id="txtDescripcion" style="width: 200px;"></td>
    </tr>
    <tr>
      <td style="text-align: left;"><label for="cbTipoSeguro">Tipo de Seguro:</label></td>
      <td>
        <select name="cbTipoSeguro" id="cbTipoSeguro" style="width: 204px;">
    <% for(TipoSeguro tipo : listaTipos) { %>
        <option value="<%= tipo.getId() %>"><%= tipo.getDescripcion() %></option>
    <% } %>
</select>
      </td>
    </tr>
    <tr>
      <td style="text-align: left;"><label for="txtCostoContrato">Costo contratación:</label></td>
      <td><input type="text" name="txtCostoContrato" id="txtCostoContrato" style="width: 200px;"></td>
    </tr>
    <tr>
      <td style="text-align: left;"><label for="txtCostoMaxAsegurado">Costo máximo asegurado:</label></td>
      <td><input type="text" name="txtCostoMaxAsegurado" id="txtCostoMaxAsegurado" style="width: 200px;"></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" name="btnAceptar" id="btnAceptar" value="Aceptar"></td>
    </tr>
    
  </table>
</form>

<%




	boolean filas = false;
	if(request.getParameter("btnAceptar")!=null)
	{
		Seguro seguro = new Seguro();
		seguro.setDescripcion(request.getParameter("txtDescripcion"));
		try {
		    float costoContratacion = Float.parseFloat(request.getParameter("txtCostoContrato"));
		    seguro.setCostoContratacion(costoContratacion);

		    float costoAsegurado = Float.parseFloat(request.getParameter("txtCostoMaxAsegurado"));
		    seguro.setCostoAsegurado(costoAsegurado);

		    int idTipoSeguro = Integer.parseInt(request.getParameter("cbTipoSeguro"));
		    TipoSeguro tipo = new TipoSeguro();
		    tipo.setId(idTipoSeguro);
		    seguro.setTipoSeguro(tipo);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}

		SeguroDaoImpl segDaoImpl = new SeguroDaoImpl();
		filas = segDaoImpl.insert(seguro);
	}

%>

<% if(filas){
	%>
	
	Seguro agregado con éxito

<%}%>


</body>
</html>