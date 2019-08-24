<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                          if(request.getAttribute("Error") != null){
                      %>
                        	<p class="alert alert-danger"><%= request.getAttribute("Error") %></p>
                      <%
                           }
                      %>
                    <button class="btn btn-indigo">Entrar</button>
                    <br>
                    <hr>
                    <a href="#">¿Olvido su contraseña?</a>
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

  <!--Footer-->
  <footer class="page-footer text-center font-small wow fadeIn">

    <hr class="my-4">

    <!-- Social icons -->
    <div class="pb-4">
      <a href="https://www.facebook.com/mdbootstrap" target="_blank">
        <i class="fab fa-facebook-f mr-3"></i>
      </a>

      <a href="https://twitter.com/MDBootstrap" target="_blank">
        <i class="fab fa-twitter mr-3"></i>
      </a>

      <a href="https://www.youtube.com/watch?v=7MUISDJ5ZZ4" target="_blank">
        <i class="fab fa-youtube mr-3"></i>
      </a>

      <a href="https://plus.google.com/u/0/b/107863090883699620484" target="_blank">
        <i class="fab fa-google-plus-g mr-3"></i>
      </a>

      <a href="https://dribbble.com/mdbootstrap" target="_blank">
        <i class="fab fa-dribbble mr-3"></i>
      </a>

      <a href="https://pinterest.com/mdbootstrap" target="_blank">
        <i class="fab fa-pinterest mr-3"></i>
      </a>

      <a href="https://github.com/mdbootstrap/bootstrap-material-design" target="_blank">
        <i class="fab fa-github mr-3"></i>
      </a>

      <a href="http://codepen.io/mdbootstrap/" target="_blank">
        <i class="fab fa-codepen mr-3"></i>
      </a>
    </div>
    <!-- Social icons -->

    <!--Copyright-->
    <div class="footer-copyright py-3">
      � 2019 Copyright:
      <a href="https://mdbootstrap.com/education/bootstrap/" target="_blank"> DesafioMVC.com </a>
    </div>
    <!--/.Copyright-->

  </footer>
  <!--/.Footer-->


<footer>
	<%@ include file='/Utils/ImportJS.jsp' %>
</footer>
</body>

</html>
	