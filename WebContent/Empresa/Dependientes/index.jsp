<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sv.edu.udb.www.beans.Opcion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				 		<a href="<%=opcion.getURL() %>" class="list-group-item <% if(opcion.isIsActive()){ out.print("active");}else{ out.print("list-group-item-action");}%>  waves-effect">
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
      <h1 class="text-center">Gestores de empresa</h1>
        <br>
      <div class="row wow fadeIn">
			
   		<c:if test="${not empty requestScope.SuccessMsg}">
        	<p class="alert alert-success">${requestScope.SuccessMsg}</p>
        </c:if>
        <c:if test="${not empty requestScope.ErrorMsg}">
        	<p class="alert alert-danger">${requestScope.ErrorMsg}</p>
        </c:if>
		
		<div class="row wow fadeIn">
			<a href="/DesafioMVC/Empresa/Dependientes?op=Agregar" class="btn btn-outline-success">Agregar</a>
			<br><br><br>
		<table class="table" id="TheTable">
			<thead>	
				<tr>
					<th>ID Usuario</th>
					<th>Nombres</th>
					<th>Apellidos</th>
					<th>Correo</th>
					<th>Empresa</th>
					<th colspan="2">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.UsuariosList}" var="Empresa">
					<tr>
						<td>${Empresa.id}</td>
						<td>${Empresa.nombres}</td>
						<td>${Empresa.apellidos}</td>
						<td>${Empresa.correo}</td>
						<td>${Empresa.nombreEmpresa}</td>
						<td class="p-0"><a href="/DesafioMVC/Administrador/Empresa?op=Editar&id=${Empresa.id}" class="btn btn-outline-warning">Editar</a></td>
						<td class="p-0"><a
									class="btn btn-danger"
									href="javascript:eliminar('${Empresa.id}')"><span
										class="glyphicon glyphicontrash"></span> Eliminar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${empty requestScope.UsuariosList}">
					<p class="alert alert-danger ml-auto mr-auto d-block w-50 text-center">No hay empresas que mostrar</p>
		</c:if>
      </div>
		
      </div>
  	</div>
  </main>
  
  <!--Main layout-->

<footer>
	<%@ include file='/Utils/ImportJS.jsp' %>
</footer>
    </body>
    <script>
    $(document).ready(function() {
			$('#tabla').DataTable();
		});
		<c:if test="${not empty exito}">
		alertify.success('${exito}');
		<c:set var="exito" value="" scope="session" />
		</c:if>
		<c:if test="${not empty fracaso}">
		alertify.error('${fracaso}');
		<c:set var="fracaso" value="" scope="session" />
		</c:if>
		function eliminar(id) {
			alertify.confirm("¿Realmente desea eliminar este editorial?", function(
					e) {
				if (e) {
					location.href = "Empresa/Dependientes?op=eliminar&id=" + id;
				}
			});
		}
		</script>
</html>
