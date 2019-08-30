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
import sv.edu.udb.www.models.AdministradorModels.OfertasModel;

@WebServlet(name = "/AdministradorOfertasController", urlPatterns = "/Administrador/Ofertas")
public class AdministradorOfertasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	OfertasModel model = new OfertasModel();
	
    public AdministradorOfertasController() {
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
		Opciones.add(new Opcion("Ofertas","/DesafioMVC/Administrador/Ofertas","fas fa-percent",true));
		Opciones.add(new Opcion("Empresas","/DesafioMVC/Administrador/Empresa","fas fa-building",false));
		Opciones.add(new Opcion("Rubros","/DesafioMVC/Administrador/Rubros","fas fa-briefcase",false));
		Opciones.add(new Opcion("Clientes","/DesafioMVC/Administrador/Clientes","fas fa-users",false));
		Opciones.add(new Opcion("Cambiar Contraseña","/DesafioMVC/Administrador?op=change","fas fa-lock",false));
		Opciones.add(new Opcion("Cerrar Sesion","/DesafioMVC/Login?op=cerrar","fas fa-sign-out-alt red-text",false));
		response.setContentType("text/html;charset=UTF-8");
		try{
				String Operacion =  "";
				if(request.getParameter("op") != null){
					Operacion = request.getParameter("op");
				}
				switch (Operacion) {
					case "Aceptar":
						Aceptar(request,response,Opciones);
						break;
					case "Rechazar":
						Rechazar(request,response,Opciones);
						break;
					default:
						request.setAttribute("OfertasList", model.ObtenerOfertas());
						request.setAttribute("Opciones", Opciones);
						request.getRequestDispatcher("/Administrador/Ofertas/index.jsp").forward(request, response);
						break;
				}
			}catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
				request.getRequestDispatcher("/Error.jsp").forward(request,response);
			}
	} //ProccessRequest

	private void Aceptar(HttpServletRequest request,HttpServletResponse response,List<Opcion> opciones)throws ServletException, IOException{
		boolean answer = model.AceptarOferta(Integer.parseInt(request.getParameter("id")));
		if(answer){
			request.setAttribute("SuccessMsg", "Se Acepto satisfactoriamente");
		}else{
			request.setAttribute("ErrorMsg", "Ha ocurrido un error");
		}
		request.setAttribute("EmpresasList", model.ObtenerOfertas());
		request.setAttribute("Opciones", opciones);
		request.getRequestDispatcher("/Administrador/Ofertas/index.jsp").forward(request, response);
	}
	
	private void Rechazar(HttpServletRequest request,HttpServletResponse response,List<Opcion> opciones)throws ServletException, IOException{
		boolean answer = model.RechazarOferta(Integer.parseInt(request.getParameter("id")));
		if(answer){
			request.setAttribute("SuccessMsg", "Se Rechazo satisfactoriamente");
		}else{
			request.setAttribute("ErrorMsg", "Ha ocurrido un error");
		}
		request.setAttribute("EmpresasList", model.ObtenerOfertas());
		request.setAttribute("Opciones", opciones);
		request.getRequestDispatcher("/Administrador/Ofertas/index.jsp").forward(request, response);
		
	}
}
