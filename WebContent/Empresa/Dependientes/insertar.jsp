<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sv.edu.udb.www.beans.Opcion"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<sql:setDataSource var = "empresas" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost/desafiomvc"
         user = "root"  password = ""/>
<%
     //Para evitar el acceso no authorizado
     HttpSession sesion = request.getSession();
     
     if(sesion.getAttribute("Empresa") == null){
         response.sendRedirect("/DesafioMVC/Login.jsp?Error=Debe iniciar sesion");
         return;
     }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel-Empresa</title>
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
  <body class="grey lighten-3">
  
  	<div class="sidebar-fixed position-fixed">
  	  	<a class="logo-wrapper waves-effect">
        	<img src="https://mdbootstrap.com/img/logo/mdb-email.png" class="img-fluid" alt="">
      	</a>
	     <div class="list-group list-group-flush">
			
				<%  List<Opcion> opciones = (List<Opcion>)request.getAttribute("Opciones");
 					for(Opcion opcion : opciones){
 				%>
				 		<a href="<%=opcion.getURL() %>" class="list-group-item <% if(opcion.isIsActive()){ out.print("active");}else{ out.print("list-group-item-action");}%>  waves-effect"> --%>
 			            	<i class="<%=opcion.getIcon()%> mr-3"></i><%=opcion.getNombre()%> --%>
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
        <a href="/DesafioMVC/Empresa/Dependientes" class="btn btn-outline-success">Volver</a>
       <div class="row wow fadeIn d-flex justify-content-center">
       			<div class="shadow-lg w-50 p-5">
					<h3 class="grey-text text-center">Agregar Gestor</h3>
					<form action="/DesafioMVC/Empresa/Dependientes?op=insertar" method="POST">
					
						<div class="md-form">
			                   <label for="email">Nombre:</label>
			                   <input type="text" class="form-control" name="nombres" required>
		                 </div>	
		                 <div class="md-form">
			                   <label for="email">Apellidos:</label>
			                   <input type="text" class="form-control" name="apellidos" required>
		                 </div>
		                    <div class="md-form">
			                   <label for="email">Correo:</label>
			                   <input type="text" class="form-control" name="correo" required>
		                 </div>
		                   <div class="md-form">
			                   <label for="email">Contraseña:</label>
			                   <input type="text" class="form-control" name="contrasenia" required>
		                 </div>
		                 
		                    <div class="md-form">
			                   <label for="email">Empresa:</label>
			                   
			                   <select name='id'>
			                   <sql:query dataSource = "${empresas}" var = "result">
						            SELECT id, nombre from empresa;  
						         </sql:query>
								    <c:forEach items="${result.rows}" var="row">
								    
								            <option value="${row.id}">${row.nombre}</option>
								    </c:forEach>
								</select>
		                 </div>
		                 
		                 
						<button type="submit" class="btn btn-outline-success">Añadir</button>
						 
					</form>
				</div>
			</div>
		</div>
  </main>
  
  <!--Main layout-->

<footer>
	<%@ include file='/Utils/ImportJS.jsp' %>
</footer>
    </body>
</html>
