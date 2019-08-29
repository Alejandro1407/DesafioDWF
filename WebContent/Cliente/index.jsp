<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sv.edu.udb.www.beans.Opcion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
     //Para evitar el acceso no authorizado
     HttpSession sesion = request.getSession();
     
     if(sesion.getAttribute("Cliente") == null){
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
	          	<i class="fas fa-sign-out-alt mr-3"></i>Cerrar Sesion
	          </a>
	     </div>
          
    </div>
  
  
  <!--Main layout-->
  <main class="pt-5 mx-lg-5">
    <div class="container-fluid">
      <!--Grid row-->
      <h1 class="text-center">Bienvenido <c:out value="${cookie.NombreUser.value }"/> </h1>
        <br>
        
      <div class="row wow fadeIn">

        <!--Grid column-->
                        
        <div class="col-lg-6 col-md-6 mb-4">
          <!--Card-->
          <div class="card">

            <!-- Card header -->
            <div class="card-header text-center">Compra de cupones</div>
			<h5 class="text-center grey-text mt-3 mr-auto ml-auto d-block w-50">Se podrá comprar uno o varios cupones de promociones que se encuentren activas. </h5>
            <!--Card content-->
            <div class="card-body text-center">
                <a href="/DesafioMVC/Cliente?op=Comprar" class="btn btn-black"><i class="fas fa-percent mr-3"></i>Comprar Cupones</a>
            </div>
          </div>
          <!--/.Card-->
        </div>
        <!--Grid column-->
        
        <div class="col-lg-6 col-md-6 mb-4">
          <!--Card-->
          <div class="card">

            <!-- Card header -->
            <div class="card-header text-center">Visualizar Cupones</div>
				<h5 class="text-center grey-text mt-3 mr-auto ml-auto d-block w-50">podrá ver sus cupones categorizados de la siguiente manera: cupones disponibles, cupones canjeados y cupones vencidos.</h5>
            <!--Card content-->
            <div class="card-body text-center">
                <a href="/DesafioMVC/Cliente?op=Ver" class="btn btn-cyan"><i class="fas fa-user-cog mr-3"></i>Ver Cupones</a>
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
