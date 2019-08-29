package sv.edu.udb.www.controllers.EmpresaControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.beans.Opcion;
import sv.edu.udb.www.models.SesionModel;

@WebServlet(name = "/EmpresaController", urlPatterns = {"/Empresa"})
public class EmpresaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public EmpresaController() {
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
		Opciones.add(new Opcion("DashBoard","/DesafioMVC/Empresa","fas fa-chart-pie",true));
		Opciones.add(new Opcion("Ofertas","/DesafioMVC/Empresa/Ofertas","fas fa-percent",false));
		Opciones.add(new Opcion("Dependientes","/DesafioMVC/Empresa/Dependientes","fas fa-users-cog",false));
		Opciones.add(new Opcion("Cambiar Contraseña","/DesafioMVC/Empresa?op=Change","fas fa-lock",false));
		Opciones.add(new Opcion("Cerrar Sesion","/DesafioMVC/Login?op=cerrar","fas fa-sign-out-alt red-text",false));
		response.setContentType("text/html;charset=UTF-8");
		try{
				String Operacion =  "";
				if(request.getParameter("op") != null){
					Operacion = request.getParameter("op");
				}
				//En caso de no encontrarse op ira al default, Agregar casos necesarios y sus metodos
				switch (Operacion) {
				case "Change":
					Opcion temp = Opciones.get(3);
					Opcion temp1 = Opciones.get(0);
					temp.setIsActive(true);
					temp1.setIsActive(false);
					request.setAttribute("Opciones", Opciones);
					request.setAttribute("Path", "/DesafioMVC/Empresa?op=CambiarDB");
					request.getRequestDispatcher("CambiarPassword.jsp").forward(request, response);
					break;
					case "CambiarDB":
						CambiarContraseña(request,response, Opciones);
						break;
				default:
					request.setAttribute("Opciones", Opciones);
					request.getRequestDispatcher("/Empresa/index.jsp").forward(request, response);
					break;
				}
			}catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
				request.getRequestDispatcher("/Error.jsp").forward(request,response);
			}
	} //ProccessRequest
	
	private void CambiarContraseña(HttpServletRequest request, HttpServletResponse response, List<Opcion> opciones) throws ServletException, IOException{
		SesionModel model = new SesionModel();
		Opcion temp = opciones.get(3);
		Opcion temp1 = opciones.get(0);
		temp.setIsActive(true);
		temp1.setIsActive(false);
		request.setAttribute("Opciones", opciones);
		request.setAttribute("Path", "/DesafioMVC/Empresa?op=CambiarDB");
		if(!(request.getParameter("NewPass1").equals(request.getParameter("NewPass2")))){
			request.setAttribute("message", "Contraseñas no coinciden");
			request.getRequestDispatcher("CambiarPassword.jsp").forward(request,response);
		}
		String Message = model.CambiarContraseña(Integer.parseInt(request.getParameter("id")),request.getParameter("OldPass"),request.getParameter("NewPass1"));
		request.setAttribute("message",Message);
		request.getRequestDispatcher("CambiarPassword.jsp").forward(request,response);
	}
}
