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
      <h1 class="text-center">Gestión Ofertas</h1>
        <br>
        <c:if test="${not empty requestScope.SuccessMsg}">
        	<p class="alert alert-success">${requestScope.SuccessMsg}</p>
        </c:if>
        <c:if test="${not empty requestScope.ErrorMsg}">
        	<p class="alert alert-danger">${requestScope.ErrorMsg}</p>
        </c:if>
        
       <div class="row wow fadeIn">
		<table class="table" id="TheTable">
			<thead>	
				<tr>
					<th>#</th>
					<th>Nombres</th>
					<th>Apellidos</th>
					<th>Correo</th>
					<th>DUI</th>
					<th>Telefono</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.ClientesList}" var="Cliente">
					<tr>
						<td>${Cliente.id}</td>
						<td>${Cliente.nombres}</td>
						<td>${Cliente.apellidos}</td>
						<td>${Cliente.correo}</td>
						<td>${Cliente.dui}</td>
						<td>${Cliente.telefono}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${empty requestScope.OfertasList}">
					<p class="alert alert-danger ml-auto mr-auto d-block w-50 text-center">No hay ofertas pendientes</p>
		</c:if>
      </div>
  </main>
<footer>
<%@ include file='/Utils/ImportJS.jsp' %>
<script>
	$(document).ready(function () {
    	$('TheTable').DataTable();
	});
</script>
	
</footer>
    </body>
</html>
