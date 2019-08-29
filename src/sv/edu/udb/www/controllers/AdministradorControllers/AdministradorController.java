package sv.edu.udb.www.controllers.AdministradorControllers;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import sv.edu.udb.www.beans.Opcion;
import sv.edu.udb.www.models.SesionModel;

@WebServlet(name = "/AdministradorController",urlPatterns = {"/Administrador"})
public class AdministradorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	List<Opcion> Opciones = new ArrayList<Opcion>();

    public AdministradorController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccessRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccessRequest(request, response);
	}
 
	protected void ProccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Opcion> Opciones = new ArrayList<Opcion>();
		Opciones.add(new Opcion("DashBoard","/DesafioMVC/Administrador","fas fa-chart-pie",true));
		Opciones.add(new Opcion("Empresas","/DesafioMVC/Administrador/Empresa","fas fa-building",false));
		Opciones.add(new Opcion("Rubros","/DesafioMVC/Administrador/Rubros","fas fa-briefcase",false));
		Opciones.add(new Opcion("Clientes","/DesafioMVC/Administrador/Clientes","fas fa-users",false));
		Opciones.add(new Opcion("Cambiar Contraseña","/DesafioMVC/Administrador?op=Change","fas fa-lock",false));
		Opciones.add(new Opcion("Cerrar Sesion","/DesafioMVC/Login?op=cerrar","fas fa-sign-out-alt red-text",false));
		response.setContentType("text/html;charset=UTF-8");
		try{
				String Operacion =  "";
				if(request.getParameter("op") != null){
					Operacion = request.getParameter("op");
				}
				switch (Operacion) {
					case "Change":
					Opcion temp = Opciones.get(4);
					Opcion temp1 = Opciones.get(0);
					temp.setIsActive(true);
					temp1.setIsActive(false);
					request.setAttribute("Opciones", Opciones);
					request.setAttribute("Path", "/DesafioMVC/Administrador?op=CambiarDB");
					request.getRequestDispatcher("CambiarPassword.jsp").forward(request, response);
					break;
					case "CambiarDB":
						CambiarContraseña(request,response, Opciones);
						break;
					default:
						request.setAttribute("Opciones", Opciones);
						request.getRequestDispatcher("/Administrador/index.jsp").forward(request, response);
						break;
				}
			}catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
				request.getRequestDispatcher("/Error.jsp").forward(request,response);
			}
	} //ProccessRequest
	
	private void CambiarContraseña(HttpServletRequest request, HttpServletResponse response, List<Opcion> opciones) throws ServletException, IOException{
		SesionModel model = new SesionModel();
		Opcion temp = opciones.get(4);
		Opcion temp1 = opciones.get(0);
		temp.setIsActive(true);
		temp1.setIsActive(false);
		request.setAttribute("Opciones", opciones);
		request.setAttribute("Path", "/DesafioMVC/Administrador?op=CambiarDB");
		if(!(request.getParameter("NewPass1").equals(request.getParameter("NewPass2")))){
			request.setAttribute("message", "Contraseñas no coinciden");
			request.getRequestDispatcher("CambiarPassword.jsp").forward(request,response);
		}
		String Message = model.CambiarContraseña(Integer.parseInt(request.getParameter("id")),request.getParameter("OldPass"),request.getParameter("NewPass1"));
		request.setAttribute("message",Message);
		request.getRequestDispatcher("CambiarPassword.jsp").forward(request,response);
	}

}