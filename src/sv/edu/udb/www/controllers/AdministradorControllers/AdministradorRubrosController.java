package sv.edu.udb.www.controllers.AdministradorControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.beans.Opcion;

@WebServlet(name = "/AdministradorRubrosController", urlPatterns = "/Administrador/Rubros")
public class AdministradorRubrosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdministradorRubrosController() {
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
		Opciones.add(new Opcion("DashBoard","/DesafioMVC/Administrador","fas fa-chart-pie",false));
		Opciones.add(new Opcion("Gestión Empresa","/DesafioMVC/Administrador/Empresa","fas fa-building",false));
		Opciones.add(new Opcion("Gestión Rubros","/DesafioMVC/Administrador/Rubros","fas fa-briefcase",true));
		Opciones.add(new Opcion("Gestión Clientes","/DesafioMVC/Administrador/Clientes","fas fa-users",false));
		response.setContentType("text/html;charset=UTF-8");
		try{
				String Operacion =  "";
				if(request.getParameter("op") != null){
					Operacion = request.getParameter("op");
				}
				switch (Operacion) {
				//Implementar sus caso Agregar,Eliminar,Editar,etc
				
				default:
					request.setAttribute("Opciones", Opciones);
					request.getRequestDispatcher("/Administrador/Rubros/index.jsp").forward(request, response);
					break;
				}
			}catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
				request.getRequestDispatcher("/Error.jsp").forward(request,response);
			}
	} //ProccessRequest
}
