<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="sv.edu.udb.www.beans.Opcion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar Contraseña</title>
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
  <!--Main layout-->
  <main class="pt-5 mx-lg-5 bg-white">
    <div class="container-fluid w-50 mt-5">
    
    	<c:if test="${empty requestScope.Error}">
      	<form action="/DesafioMVC/Login?op=RecuperarDB" method="post">
            <table border="0" cellspacing="2" cellpadding="5">
                       <h2>Cambiar contraseña</h2>
                  <div class="md-form">
	                   <label for="email">Nueva contraseña:</label>
	                   <input type="password" class="form-control" id="email" name="NewPass1">
                 </div>
                 <div class="md-form">
                   <label for="pwd">Vuelva a escribir la nueva contraseña:</label>
                   <input type="password" class="form-control" id="pwd" name="NewPass2">
                 </div>
                    <tr>
                        <td><input type="submit" class="btn btn-success" value="Cambiar"/></td>
                        <td><input type="reset" class="btn btn-warning" value="Limpiar"/></td>
                        
                    </tr>
                    <tr>
                        <td><label></label></td>
                        <td><input  type="text" value="<c:out value="${requestScope.Email}"/>" style="visibility:hidden" name="email"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
      </c:if>
      <c:if test="${not empty requestScope.Error}">
      	<p class="alert alert-danger mr-auto ml-auto d-block w-50 mt-5"><c:out value="${requestScope.Error}"/></p>
      </c:if>
    </div>
  </main>
<footer>
	<%@ include file='/Utils/ImportJS.jsp' %>
</footer>
    </body>
</html>