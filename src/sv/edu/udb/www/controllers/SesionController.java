package sv.edu.udb.www.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		
		SesionModel Model =  new SesionModel();
		Usuario u = Model.CheckLogin(request.getParameter("Email"),request.getParameter("Password"));
		if(u == null){
			request.setAttribute("Error", "Usuario y/o contraseña invalidos");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		request.setAttribute("id", u.getId());
		request.setAttribute("nombres", u.getNombres());
		request.setAttribute("idTipo", u.getIdTipo());
		request.getRequestDispatcher("/Utils/CookieMaker.jsp").forward(request, response);
	}
	
}//Clase
