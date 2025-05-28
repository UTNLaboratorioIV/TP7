<%@page import="entidad.Seguro" %>
<%@page import="dao.SeguroDao" %>
<%@page import="java.util.ArrayList" %>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<header>
		<div>
			<a href="Inicio.jsp" style="margin-right: 10px;"> Inicio</a>
			<a href="AgregarSeguro.jsp" style="margin-right: 10px;"> Agregar Seguro</a>
			<a href="servletSeguro?Param=1">Listar Seguros</a>
		</div>
	</header>
	
	<b></b><h3>"Tipo de seguros de la base de datos"</h3></b>
	<form method="get" action="servletSeguro">
	Búsqueda por tipo de Seguros: <select name="tipoSeguro">
			<option value="1">Todos los seguros</option>
			<option value="2">Seguro de casas</option>
			<option value="3">Seguro de vida</option>
			<option value="4">Seguro de motos</option>
			</select> <input type="submit" name="btnFiltrar" value="Filtrar" style="height: 30px; text-align: center">
			<input type="hidden" name="Param" value="1">
			<br>
			<br>
		</form>	
			
<%
ArrayList<Seguro> listaSeguros = null;

if(request.getAttribute("listaS")!= null)
{
	
	listaSeguros = (ArrayList<Seguro>)request.getAttribute("listaS");
}
%>

<table border="double black">
<tr><th>ID seguro</th>   <th>Descripción seguro</th>    <th>Descripción tipo seguro</th> <th>Costo contratación</th> <th>Costo máximo asegurado</th></tr>

<% 
if(listaSeguros!= null)
for(Seguro seg : listaSeguros)
	{
	
 %>
<tr> <td><%= seg.getId() %></td>   <td><%=seg.getDescripcion() %></td>  <td><%= seg.getTipoSeguro() %></td> <td><%= seg.getCostoContratacion() %></td>  <td><%= seg.getCostoAsegurado() %></td></tr>

<% } %>

</table>
	
	






</body>
</html>