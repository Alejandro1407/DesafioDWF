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
      <h1 class="text-center">Gestión Empresas</h1>
        <br>
        <a href="/DesafioMVC/Administrador/Empresa" class="btn btn-outline-success">Volver</a>
       <div class="row wow fadeIn d-flex justify-content-center">
       			<div class="shadow-lg w-50 p-5">
					<h3 class="grey-text text-center">Agregar Empresa</h3>
					<form action="/DesafioMVC/Administrador/Empresa?op=AgregarDB" method="POST">
					
						<div class="md-form">
			                   <label for="email">Nombre:</label>
			                   <input type="text" class="form-control" name="Nombre" required>
		                 </div>	
		                 <div class="md-form">
			                   <label for="email">Direccion:</label>
			                   <input type="text" class="form-control" name="Direccion" required>
		                 </div>
		                    <div class="md-form">
			                   <label for="email">Contacto:</label>
			                   <input type="text" class="form-control" name="Contacto" required>
		                 </div>
		                   <div class="md-form">
			                   <label for="email">Correo:</label>
			                   <input type="text" class="form-control" name="Correo" required>
		                 </div>
		                 
		                    <div class="md-form">
			                   <label for="email">Telefono:</label>
			                   <input type="text" class="form-control" name="Telefono" required>
		                 </div>
		                  
			                   <label for="email">Rubro:</label>
			                   <select name="idRubro" class="custom-select">
			                   	<c:forEach items="${requestScope.RubrosList}" var="Rubro">
		                 			<option value="${Rubro.id}">${Rubro.rubro}</option>
		                		 </c:forEach>
			                   </select>
		                 
		                  <div class="md-form">
			                   <label for="email">Cobro:</label>
			                   <input type="number" class="form-control" min="0" max="100" step="0.1" name="Cobro" required>
		                 </div>
		                 <c:if test="${not empty requestScope.RubrosList}">
							<button type="submit" class="btn btn-outline-success">Añadir</button>
						 </c:if>
						   <c:if test="${empty requestScope.RubrosList}">
						   <br><br>
							<p class="grey-text">No puede agregarse una empresa sin rubro</p>
						 </c:if>
					</form>
				</div>
			</div>
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
