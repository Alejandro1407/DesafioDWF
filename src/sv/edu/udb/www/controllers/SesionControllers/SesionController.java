package sv.edu.udb.www.controllers.SesionControllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import sv.edu.udb.www.models.SesionModel;
import sv.edu.udb.www.beans.*;

@WebServlet(name = "/SesionController", urlPatterns = {"/Login"})
public class SesionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SesionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccessRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccessRequest(request, response);
	}
	
	protected void ProccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//response.setContentType("text/html;charset=UTF-8");
		try{
				String Operacion =  "";
				if(request.getParameter("op") != null){
					Operacion = request.getParameter("op");
				}
				switch (Operacion) {
				case "iniciar":
					IniciarSesion(request,response);
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
		
		SesionModel Model =  new SesionModel();
		Usuario u = Model.CheckLogin(request.getParameter("Email"),request.getParameter("Password"));
		
		if(u == null){
			request.setAttribute("Error", "Usuario y/o contraseña invalidos");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
		Cookie idEmpleado = new Cookie("idEmpleado",String.valueOf(u.getId()));
		Cookie NombreUser = new Cookie("NombreUser", u.getNombres());
		Cookie idTipo = new Cookie("idTipo",String.valueOf(u.getIdTipo()));
		
		idEmpleado.setPath("/");
		NombreUser.setPath("/");
		idTipo.setPath("/");
		
		response.addCookie(idEmpleado);
		response.addCookie(NombreUser);
		response.addCookie(idTipo);
		
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
	
}//Clase
