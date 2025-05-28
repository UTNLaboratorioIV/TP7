<%@page import="entidad.Seguro" %>
<%@page import="entidad.TipoSeguro" %>
<%@page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Seguros</title>
</head>
<body>

	<header>
		<div>
			<a href="Inicio.jsp" style="margin-right: 10px;"> Inicio</a>
			<a href="AgregarSeguro.jsp" style="margin-right: 10px;"> Agregar Seguro</a>
			<a href="servletSeguro?Param=1">Listar Seguros</a>
		</div>
	</header>
	
	<h3>Tipo de seguros de la base de datos</h3>
	
	<%
		// Prevenir errores por atributos nulos
		Integer tipoSel = (Integer) request.getAttribute("tipoSeguroSeleccionado");
		if (tipoSel == null) tipoSel = 0;
		ArrayList<TipoSeguro> listaTipos = (ArrayList<TipoSeguro>) request.getAttribute("listaTipos");
	%>
	
	<form method="get" action="servletSeguro">
	    Búsqueda por tipo de Seguros: 
	    <select name="tipoSeguro">
	        <option value="0" <%= (tipoSel == 0) ? "selected" : "" %>>Todos los seguros</option>
	        <%
	        	if (listaTipos != null) {
		            for (TipoSeguro tipo : listaTipos) {
	        %>
	            <option value="<%= tipo.getId() %>" <%= (tipo.getId() == tipoSel) ? "selected" : "" %>>
	                <%= tipo.getDescripcion() %>
	            </option>
	        <%
		            }
		        }
	        %>
	    </select> 
	    <input type="submit" name="btnFiltrar" value="Filtrar" style="height: 30px; text-align: center">
	    <input type="hidden" name="Param" value="1">
	    <br><br>
	</form>
			
	<%
		ArrayList<Seguro> listaSeguros = (ArrayList<Seguro>) request.getAttribute("listaS");
	%>

	<table border="double black">
	<tr>
		<th>ID seguro</th>
		<th>Descripción seguro</th>
		<th>Descripción tipo seguro</th>
		<th>Costo contratación</th>
		<th>Costo máximo asegurado</th>
	</tr>
	
	<% 
		if(listaSeguros != null) {
			for(Seguro seg : listaSeguros) {
	%>
	<tr>
		<td><%= seg.getId() %></td>
		<td><%= seg.getDescripcion() %></td>
		<td><%= seg.getTipoSeguro().getDescripcion() %></td>
		<td><%= seg.getCostoContratacion() %></td>
		<td><%= seg.getCostoAsegurado() %></td>
	</tr>
	<%
			}
		}
	%>
	</table>

</body>
</html>