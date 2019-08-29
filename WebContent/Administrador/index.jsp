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
  <body class="grey lighten-3">
  
  	<div class="sidebar-fixed position-fixed">
  	  	<a class="logo-wrapper waves-effect">
        	<img src="https://mdbootstrap.com/img/logo/mdb-email.png" class="img-fluid" alt="">
      	</a>
	     <div class="list-group list-group-flush">
				<%  List<Opcion> opciones = (List<Opcion>) request.getAttribute("Opciones");
					for(Opcion opcion: opciones){
				%>
				 		<a href="<%=opcion.getURL() %>" class="list-group-item <% if(opcion.isIsActive()){ out.print("active");}else{ out.print("list-group-item-action");}%>  waves-effect">
			            	<i class="<%=opcion.getIcon()%> mr-3"></i><%=opcion.getNombre()%>
			        	</a>
				<%
					}
				%>
		
			  <a href="cambiar.jsp" class="list-group-item list-group-item-action waves-effect">
	          	<i class="fas fa-lock mr-3"></i>Cambiar Contraseña
	         </a>
	         <a href="../Servicios/cerrarsesion.jsp" class="list-group-item red-text list-group-item-action waves-effect">
	          	<i class="fas fa-sign-out-alt mr-3"></i>Cerrra Sesion
	          </a>
	     </div>
          
    </div>
  
  
  <!--Main layout-->
  <main class="pt-5 mx-lg-5">
    <div class="container-fluid">
      <!--Grid row-->
      <h1 class="text-center">Bienvenido <c:out value="${cookie.NombreUser.value}"/></h1>
        <br>
        
      <div class="row wow fadeIn">

        <!--Grid column-->
                        
        <div class="col-lg-6 col-md-6 mb-4">
          <!--Card-->
          <div class="card">

            <!-- Card header -->
            <div class="card-header text-center">Gestión de Empresas</div>
			<h5 class="text-center grey-text mt-3 mr-auto ml-auto d-block w-50">Registrarse, actualizar y eliminar la información referida a las empresas ofertantes</h5>
            <!--Card content-->
            <div class="card-body text-center">
                <a href="/DesafioMVC/Administrador/Empresa" class="btn btn-black"><i class="fas fa-building mr-3"></i>Gestionar Empresas</a>
            </div>
          </div>
          <!--/.Card-->
        </div>
        <!--Grid column-->
        
        <div class="col-lg-6 col-md-6 mb-4">
          <!--Card-->
          <div class="card">

            <!-- Card header -->
            <div class="card-header text-center">Gestión de Rubros</div>
				<h5 class="text-center grey-text mt-3 mr-auto ml-auto d-block w-50">Registro, actualización y eliminación de los distintos rubros (restaurantes, talleres, entretenimiento, etc.).</h5>
            <!--Card content-->
            <div class="card-body text-center">
                <a href="/DesafioMVC/Administrador/Rubros" class="btn btn-cyan"><i class="fas fa-users mr-3"></i>Gestionar Rubros</a>
            </div>

          </div>
        </div>
         <!--/.Card-->
         
        <div class="col-lg-6 col-md-6 mb-4">

          <!--Card-->
          <div class="card">

            <!-- Card header -->
            <div class="card-header text-center">Gestión de Clientes</div>
				<h5 class="text-center grey-text mt-3 mr-auto ml-auto d-block w-50">Visualizar la información de los clientes registrados en la plataforma.</h5>
            <!--Card content-->
            <div class="card-body text-center">
                <a href="/DesafioMVC/Administrador/Clientes" class="btn btn-success"><i class="fas fa-briefcase mr-3"></i>Gestionar Clientes</a>
            </div>
          </div>
        </div>
        <!--/.Card-->
        
        <div class="col-lg-6 col-md-6 mb-4">
          <!--Card-->
          <div class="card">
            <!-- Card header -->
            <div class="card-header text-center">Cambiar contraseña</div>
				<h5 class="text-center grey-text mt-3 mr-auto ml-auto d-block w-50">Actualizar la contraseña de inicio de sesión en la plataforma</h5>
            <!--Card content-->
            <div class="card-body text-center">
                <a href="cambiar.jsp" class="btn btn-orange"><i class="fas fa-lock mr-3"></i>Cambiar contraseña</a>
            </div>

          </div>
         </div>
          <!--/.Card-->
          
        <!--Grid column-->
  </main>
  <!--Main layout-->
<footer>
	<%@ include file='/Utils/ImportJS.jsp' %>
</footer>
    </body>
</html>
