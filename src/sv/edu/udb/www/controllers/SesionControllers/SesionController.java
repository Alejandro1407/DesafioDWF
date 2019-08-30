package sv.edu.udb.www.controllers.SesionControllers;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import com.mysql.fabric.Response;

import sv.edu.udb.www.models.SesionModel;
import sv.edu.udb.www.beans.*;
import sv.edu.udb.www.models.Mailer;

import java.util.Random;
import java.util.UUID;

@WebServlet(name = "/SesionController", urlPatterns = {"/Login"})
public class SesionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SesionModel model =  new SesionModel();
	    
    public SesionController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccessRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccessRequest(request, response);
	}
	
	protected void ProccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		
		try{
				String Operacion =  "";
				if(request.getParameter("op") != null){
					Operacion = request.getParameter("op");
				}
				switch (Operacion) {
				case "iniciar":
					IniciarSesion(request,response);
					break;
				case "cerrar":
					CerrarSesion(request,response);
					break;
				case "Registro":
					Registro(request, response);
					break;
				case "Recuperar":
					Recuperar(request,response);
					break;
				case "Validar":
					ValidarToken(request,response);
					break;
				default:
					request.getRequestDispatcher("/Login.jsp").forward(request, response);
					break;
				}
			}catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
				request.getRequestDispatcher("/Error.jsp").forward(request,response);
			}
	} //ProccessRequest
	
	private void IniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession sesion = request.getSession();
	
		Usuario u = model.CheckLogin(request.getParameter("Email"),request.getParameter("Password"));
		
		if(u == null){
			request.setAttribute("Error", "Usuario y/o contraseña invalidos");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
			return;
		}
		
		Cookie idEmpleado = new Cookie("idEmpleado",String.valueOf(u.getId()));
		Cookie NombreUser = new Cookie("NombreUser", u.getNombres());
		Cookie idTipo = new Cookie("idTipo",String.valueOf(u.getIdTipo()));
		Cookie idEmpresa =  new Cookie("idEmpresa",String.valueOf(u.getIdEmpresa()));
		
		idEmpleado.setPath("/");
		NombreUser.setPath("/");
		idTipo.setPath("/");
		idEmpresa.setPath("/");
		
		response.addCookie(idEmpleado);
		response.addCookie(NombreUser);
		response.addCookie(idTipo);
		response.addCookie(idEmpresa);
		
		String URL = "";
		
		switch (u.getIdTipo()) {
		case 1:
			sesion.setAttribute("Administrador",u.getNombres());
			URL = "/DesafioMVC/Administrador";
			break;
		case 2:
			sesion.setAttribute("Empresa",u.getNombres());
			URL = "/DesafioMVC/Empresa";
			break;
		case 3:
			sesion.setAttribute("Cliente",u.getNombres());
			URL = "/DesafioMVC/Cliente";
			break;
		default:
			request.setAttribute("Error", "Mejor me paso a una licenciatura >:v");
			request.getRequestDispatcher("/Error.jsp").forward(request,response);
			break;
		}
		response.sendRedirect(URL);
		return;
	}
	
	private void CerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		  HttpSession sesion = request.getSession();
		  sesion.invalidate();
		  Cookie[] cookieArray = request.getCookies();
		  for(int i=0; i < cookieArray.length; i++)
		    {
		            //Deleting the cookie
		            cookieArray[i].setMaxAge(0);
		            response.addCookie(cookieArray[i]);
		    }
		  request.setAttribute("Error", "Sesion Finalizada");
		  request.getRequestDispatcher("/Login.jsp").forward(request, response);
		  return;
		
	}
	
	private void Recuperar(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String Email = request.getParameter("Email");
		boolean result = model.RecuperarContraseña(Email);
		if(result){
			request.setAttribute("Error", "Solicitud de recuperacion realizada");
		}else{
			request.setAttribute("Error", "Ha ocurrido un error");
		}
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
		
	}
	
	private void Registro(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Usuario u = new Usuario();
		u.setNombres(request.getParameter("Nombres"));
		u.setApellidos(request.getParameter("Apellidos"));
		u.setTelefono(request.getParameter("Telefono"));
		u.setCorreo(request.getParameter("Correo"));
		u.setDUI(request.getParameter("DUI"));
		u.setConstraseña(request.getParameter("Contrasenia"));
		boolean result = model.RegistrarUsuario(u);
		if(result){
			UUID Token = UUID.randomUUID();
			String Message = "Hola\nBienvenido a la Cuponera SV\n"
							+ "\nPara validar su correo entre al siguiente link"
							+ "\nhttp://localhost:8080/DesafioMVC/Login?op=Validar&Token=" + Token
							+ "\n\nSi no hiciste dicha solicitud ignora este mensaje"
							+ "\n\nDepartamento de Administración - La Cuponera SV";
			
			boolean send = model.AlmacenarToken(Token, u.getCorreo());
			if(!send){
				request.setAttribute("Error","Ha ocurrio un error");
				request.getRequestDispatcher("/Login.jsp").forward(request, response);
				return;
			}
			Mailer mailer = new Mailer();
			mailer.send(u.getCorreo(), "Comprobar correo", Message);
		}
		request.setAttribute("Error","Registro Exitoso");
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}
	private void ValidarToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String Token =  request.getParameter("Token");
		String Email = model.ValidarToken(Token);
		if(Email.equals("false")){
			request.setAttribute("Error","Token ingresado es invalido");
		}else{
			request.setAttribute("Error","Cuenta verificada");
		}
		request.getRequestDispatcher("/Login.jsp").forward(request,response);
	}
	
}//Clase
