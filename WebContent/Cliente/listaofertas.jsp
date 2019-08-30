<%@page import="sv.edu.udb.www.beans.Oferta"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sv.edu.udb.www.beans.Opcion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
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
	          </a>
	     </div>
          
    </div>
  
  
  <!--Main layout-->
  <form action="">
  <main class="pt-5 mx-lg-5">
    <div class="container-fluid">
		<c:forEach items="${requestScope.listaOfertas}" var="oferta">
		<div class="card" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title">${oferta.titulo}</h5>
    <p class="card-text">${oferta.descripcion}</p>
  </div>
  <ul class="list-group list-group-flush">
    <li class="list-group-item">Precio Regular: ${oferta.PRegular}</li>
    <li class="list-group-item">Precio Oferta: ${oferta.POferta}</li>
    <li class="list-group-item">Cupones disponibles: ${oferta.limite}</li>
  </ul>
  <div class="card-body">
  	<label>Seleccione la cantidad de cupones</label>
	<input class="form-control" type="number" min="1" max="${oferta.limite}" value="1">
	</div>
  <div class="card-body">
    <input data-toggle="modal" data-target="#exampleModal" type="button" class="btn btn-primary" value="Adquirir cupón">
  </div>
</div>
		 </c:forEach> 
		     
	</form>
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">CUPÓN ADQUIRIDO CON EXITO</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		
		<sql:setDataSource var="existentes" driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost/desafiomvc" user="root" password="" />
		

 					
 		
		
		
		
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>	     
      </main>
     
  </main>
  
  
  <!--Main layout-->
<footer>
	<%@ include file='/Utils/ImportJS.jsp' %>
</footer>
    </body>
</html>
