<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	HttpSession sesion = request.getSession(); //Para controlar las sesiones
	
	String id = request.getAttribute("id").toString();
	String idTipoval =  request.getAttribute("idTipo").toString();
	
	Cookie idEmpleado = new Cookie("idEmpleado",id);
	Cookie NombreUser = new Cookie("NombreUser", (String)request.getAttribute("nombres"));
	Cookie idTipo = new Cookie("idTipo", idTipoval);
	
	idEmpleado.setPath("/");
	NombreUser.setPath("/");
	idTipo.setPath("/");
	
	response.addCookie(idEmpleado);
	response.addCookie(NombreUser);
	response.addCookie(idTipo);
	
	if(idTipoval.equals("1")){
		sesion.setAttribute("Administrador",NombreUser);
		response.sendRedirect("/DesafioMVC/Administrador");
		return;
	}
	if(idTipoval.equals("2")){
		sesion.setAttribute("Empresa",NombreUser);
		response.sendRedirect("/DesafioMVC/Empresa");
		return;
	}
	if(idTipoval.equals("3")){
		sesion.setAttribute("Cliente",NombreUser);
		response.sendRedirect("/DesafioMVC/Cliente");
		return;
	}
%>
