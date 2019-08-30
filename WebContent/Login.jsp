<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <title>La cuponera - Index</title>
  <%@ include file='/Utils/ImportCSS.jsp' %>
	<style type="text/css">
	    html,
	    body,
	    header,
	    .view {
	      height: 100%;
	    }
	
	    @media (max-width: 740px) {
	      html,
	      body,
	      header,
	      .view {
	        height: 1000px;
	      }
	    }
	
	    @media (min-width: 800px) and (max-width: 850px) {
	      html,
	      body,
	      header,
	      .view {
	        height: 650px;
	      }
	    }
	    @media (min-width: 800px) and (max-width: 850px) {
	              .navbar:not(.top-nav-collapse) {
	                  background: #1C2331!important;
	              }
	          }
	  </style>
</head>
<body>
<!-- NavBar -->
	<%@ include file='/Utils/Navbar.jsp' %>

  <!-- Full Page Intro -->
  <div class="view full-page-intro" style="background-image: url('https://mdbootstrap.com/img/Photos/Others/images/78.jpg'); background-repeat: no-repeat; background-size: cover;">
   
     
    <!-- Mask & flexbox options-->
    <div class="mask rgba-black-light d-flex justify-content-center align-items-center">
      
      <!-- Content -->
      <div class="container">
        <!--Grid row-->
        <div class="row wow fadeIn">

          <!--Grid column-->
          <div class="col-md-6 mb-4 white-text text-center text-md-left">

              <h1 class="display-4 font-weight-bold">La cuponera S.A</h1>

            <hr class="hr-light">

            <p>
              <strong>Disfrute de una aplicacion desarrollada en JAVA utilizando MVC con JDBC y Servlets</strong>
            </p>

            <p class="mb-4 d-none d-md-block">
              <strong></strong>
            </p>

          </div>
          <!--Grid column-->

          <!--Grid column-->
          <div class="col-md-6 col-xl-5 mb-4">

            <!--Card-->
            <div class="card">

              <!--Card content-->
              <div class="card-body">

                <!-- Form -->
                <form action="/DesafioMVC/Login?op=iniciar" method="POST">
                  <!-- Heading -->
                  <h3 class="dark-grey-text text-center">
                    <strong></strong>
                  </h3>
                  <hr>
		     <div class="md-form">
                    <i class="fas fa-envelope prefix grey-text"></i>
                    <input type="text" id="form2" class="form-control" name="Email" placeholder="alguien@example.com"/>
                  </div>

                  <div class="md-form">
                    <i class="fas fa-lock prefix grey-text"></i>
                    <input type="password" id="form8" class="form-control" name="Password" placeholder="********"></input>
                  </div>

                  <div class="text-center">
                      <%
                          if( (request.getAttribute("Error") != null) || (request.getParameter("Error") != null ) ){
                      %>
                        	<p class="alert alert-danger"><% if(request.getAttribute("Error") != null) { out.print(request.getAttribute("Error")); } else{ out.print(request.getParameter("Error")); } %></p>
                      <%
                           }
                      %>
                    <button class="btn btn-indigo">Entrar</button>
                    <br>
                    <hr>
                    <a href="" data-toggle="modal" data-target="#modalLoginForm">¿Olvido su contraseña?, </a> <a href="" data-toggle="modal" data-target="#modalRegisterForm">Registrarse</a>
                  </div>

                </form>
                <!-- Form -->
              </div>
            </div>
            <!--/.Card-->
          </div>
          <!--Grid column-->
        </div>
        <!--Grid row-->
      </div>
      <!-- Content -->
    </div>
    <!-- Mask & flexbox options-->
  </div>
  <!-- Full Page Intro -->
<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
    <form action="/DesafioMVC/Login?op=Recuperar" method="POST" >
      <div class="modal-header text-center">
        <h4 class="modal-title w-100 font-weight-bold">Recuperar contraseña</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body mx-3">
        <div class="md-form mb-5">
          <i class="fas fa-envelope prefix grey-text"></i>
          <input type="email" name="Email" class="form-control">
          <label data-error="wrong" data-success="right" for="defaultForm-email">Your email</label>
        </div>
        <p class="grey-text">Se te enviara tu nueva contraseña por Email</p>
      </div>
      <div class="modal-footer d-flex justify-content-center">
        <button type="submit" class="btn btn-default">Recuperar</button>
      </div>
      </form>
      
    </div>
  </div>
</div>


<div class="modal fade" id="modalRegisterForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header text-center">
        <h4 class="modal-title w-100 font-weight-bold">Registrarse</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body mx-3">
      <form action="/DesafioMVC/Login?op=Registro", method="POST">
        <div class="md-form mb-5">
          <i class="fas fa-user prefix grey-text"></i>
          <input type="text" class="form-control" name="Nombres">
          <label data-error="wrong" data-success="right" for="orangeForm-name" required>Nombres</label>
        </div>
        
        <div class="md-form mb-5">
          <i class="fas fa-user prefix grey-text"></i>
          <input type="text" class="form-control" name="Apellidos">
          <label data-error="wrong" data-success="right" for="orangeForm-name" required>Apellidos</label>
        </div>
        	
        <div class="md-form mb-5">
          <i class="fas fa-phone prefix grey-text"></i>
          <input type="text" class="form-control" name="Telefono">
          <label data-error="wrong" data-success="right" for="orangeForm-name" required>Telefono</label>
        </div>
        
        <div class="md-form mb-5">
          <i class="fas fa-envelope prefix grey-text"></i>
          <input type="email" class="form-control" name="Correo">
          <label data-error="wrong" data-success="right" for="orangeForm-email" required>Correo</label>
        </div>

        <div class="md-form mb-5">
          <i class="far fa-id-card prefix grey-text"></i>
          <input type="text" class="form-control" name="DUI">
          <label data-error="wrong" data-success="right" for="orangeForm-name" required>DUI</label>
        </div>
        
        <div class="md-form mb-5">
          <i class="fas fa-key prefix grey-text"></i>
          <input type="password" class="form-control" name="Contrasenia">
          <label data-error="wrong" data-success="right" for="orangeForm-name" required>Contraseña</label>
        </div>
      </div>
	      <div class="modal-footer d-flex justify-content-center">
	        <button class="btn btn-success">Registrarse</button>
	      </div>
      </form>
    </div>
  </div>
</div>

  </footer>
  <!--/.Footer-->
	<%@ include file='/Utils/ImportJS.jsp' %>
</body>

</html>
	