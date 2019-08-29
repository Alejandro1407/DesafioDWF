package sv.edu.udb.www.controllers.SesionControllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import com.mysql.fabric.Response;

import sv.edu.udb.www.models.SesionModel;
import sv.edu.udb.www.beans.*;
import sv.edu.udb.www.models.Mailer;
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
				case "SendMail":
					SendMail(request, response);
					break;
				case "Recuperar":
					Recuperar(request,response);
					break;
				case "RecuperarDB":
					RecuperarDB(request,response);
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
	
	private void SendMail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID Token = UUID.randomUUID();
		String Message = "Hola\nParace que quieres recuperar tu contraseña ingresa al siguiente link para recuperla\n"
						+ "http://localhost:8080/DesafioMVC/Login?op=Recuperar&Token=" + Token
						+ "\n\n\nSi no hiciste dicha solicitud ignora este mensaje"
						+ "\n\nDepartamento de Administración - La Cuponera SV";
		String Email = request.getParameter("Email");
		boolean send = model.SolicitarRecuperacion(Token, Email);
		if(!send){
			request.setAttribute("Error","Ha ocurrio un error");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		Mailer mailer = new Mailer();
		mailer.send(Email, "Solicitud de recuperacion", Message);
		request.setAttribute("Error", "Solicitud de recuperacion realizada");
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
		//response.sendRedirect("/DesafioMVC/Login");
		return;
	}
	private void Recuperar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String Token =  request.getParameter("Token");
		String Email = model.ValidarToken(Token);
		if(Email.equals("false")){
			request.setAttribute("Error","Token ingresado es invalido o ya fue utilizado");
			request.getRequestDispatcher("/Recuperar.jsp").forward(request,response);
		}
		request.setAttribute("Email", Email);
		request.getRequestDispatcher("/Recuperar.jsp").forward(request, response);
	}
	private void RecuperarDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(!request.getParameter("NewPass1").equals(request.getParameter("NewPass2"))){
			request.setAttribute("Error","Contraseñas no coindicen\nDebe reiniciar el proceso");
			request.getRequestDispatcher("/Recuperar.jsp").forward(request,response);
		}
		boolean Sucess =  model.RecuperarContraseña(request.getParameter("NewPass1"),request.getParameter("email"));
			if(Sucess){
				request.setAttribute("Error","Contraseña recuperada con exito");
			}else{
				request.setAttribute("Error","Ocurrio un error");
			}
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
			
		}
	
}//Clase
