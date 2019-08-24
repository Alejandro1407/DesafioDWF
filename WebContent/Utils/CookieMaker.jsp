<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
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
		response.sendRedirect("/DesafioMVC/Administrador");
		return;
	}
	if(idTipoval.equals("2")){
		response.sendRedirect("/DesafioMVC/Empresa");
		return;
	}
	if(idTipoval.equals("3")){
		response.sendRedirect("/DesafioMVC/Cliente");
		return;
	}
%>
