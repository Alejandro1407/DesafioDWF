<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sv.edu.udb.www.beans.Opcion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
     //Para evitar el acceso no authorizado
     
     HttpSession sesion = request.getSession();
     
     if(sesion.getAttribute("Administrador") == null){
         response.sendRedirect("/DesafioMVC/Login.jsp?Error=Debe iniciar sesion");
         return;
     }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel-Administrador</title>
		<%@ include file='/Utils/ImportCSS.jsp' %>
        <style>
            .map-container{
                overflow:hidden;
                padding-bottom:56.25%;
                position:relative;
                height:0;
            }
            .map-container iframe{
                left:0;
                top:0;
                height:100%;
                width:100%;
                position:absolute;
            }
            </style>
    </head>
  <body class="lighten-3">
  
  	<div class="sidebar-fixed position-fixed">
  	  	<a class="logo-wrapper waves-effect">
        	<img src="https://mdbootstrap.com/img/logo/mdb-email.png" class="img-fluid" alt="">
      	</a>
	     <div class="list-group list-group-flush">
				<%  List<Opcion> opciones = (List<Opcion>) request.getAttribute("Opciones");
					for(Opcion opcion: opciones){
				%>
				 		<a href="<%=opcion.getURL() %>" class="list-group-item <% if(opcion.isIsActive()){ out.print("active");}else{ out.print("list-group-item-action");} %> waves-effect">
			            	<i class="<%=opcion.getIcon()%> mr-3"></i><%=opcion.getNombre()%>
			        	</a>
				<%
					}
				%>
	     </div>
          
    </div>
  
  
  <!--Main layout-->
  <main class="pt-5 mx-lg-5">
    <div class="container-fluid">
      <!--Grid row-->
      <h1 class="text-center">Gestión Rubros</h1>
        <br>
        <c:if test="${not empty requestScope.SuccessMsg}">
        	<p class="alert alert-success">${requestScope.SuccessMsg}</p>
        </c:if>
        <c:if test="${not empty requestScope.ErrorMsg}">
        	<p class="alert alert-danger">${requestScope.ErrorMsg}</p>
        </c:if>
        
       <div class="row wow fadeIn">
			<a href="/DesafioMVC/Administrador/Rubros?op=Agregar" class="btn btn-outline-success">Agregar</a>
			<br><br><br>
		<table class="table" id="TheTable">
			<thead>	
				<tr>
					<th>#</th>
					<th colspan="2">Nombre</th>
					<th colspan="4">Descripción</th>
					<th colspan="2">Acciones</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${requestScope.RubrosList}" var="Rubro">
					<tr>
						<td>${Rubro.id}</td>
						<td colspan="2">${Rubro.rubro}</td>
						<td colspan="4">${Rubro.descripcion}</td>
						<td><a href="/DesafioMVC/Administrador/Rubros?op=Editar&id=${Rubro.id}" class="btn btn-outline-warning">Editar</a></td>
						<td><a id="${Rubro.id}" onclick="Eliminar(this)" class="btn btn-outline-danger">Eliminar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${empty requestScope.RubrosList}">
					<p class="alert alert-danger ml-auto mr-auto d-block w-50 text-center">No hay rubros que mostrar</p>
		</c:if>
      </div>
  </main>
  
<footer>
<%@ include file='/Utils/ImportJS.jsp' %>
<script>
	$(document).ready(function () {
    	$('TheTable').DataTable();
	});
	function Eliminar(e) {
		var r = confirm("¿Seguro que desea Eliminar?");
		if (r == true) {
		  location.href = "/DesafioMVC/Administrador/Rubros?op=Eliminar&id=" + e.id;
		}
	}
</script>
	
</footer>
    </body>
</html>
