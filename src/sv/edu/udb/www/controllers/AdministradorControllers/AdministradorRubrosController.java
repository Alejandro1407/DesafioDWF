package sv.edu.udb.www.controllers.AdministradorControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sv.edu.udb.www.beans.Rubro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.beans.Opcion;
import sv.edu.udb.www.models.AdministradorModels.RubrosModel;

@WebServlet(name = "/AdministradorRubrosController", urlPatterns = "/Administrador/Rubros")
public class AdministradorRubrosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RubrosModel model = new RubrosModel();
	
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
		Opciones.add(new Opcion("Ofertas","/DesafioMVC/Administrador/Ofertas","fas fa-percent",false));
		Opciones.add(new Opcion("Empresa","/DesafioMVC/Administrador/Empresa","fas fa-building",false));
		Opciones.add(new Opcion("Rubros","/DesafioMVC/Administrador/Rubros","fas fa-briefcase",true));
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
				//Implementar sus caso Agregar,Eliminar,Editar,etc
				case "Agregar":
					request.setAttribute("Opciones",Opciones);
					request.getRequestDispatcher("/Administrador/Rubros/Insertar.jsp").forward(request, response);	
					break;
				case "AgregarDB":
					Insertar(request,response,Opciones);
					break;
				case "Eliminar":
					Eliminar(request,response,Opciones);
				case "Editar":
					request.setAttribute("Rubro", model.ObtenerRubro(Integer.parseInt(request.getParameter("id"))));
					request.setAttribute("Opciones",Opciones);
					request.getRequestDispatcher("/Administrador/Rubros/Editar.jsp").forward(request, response);	
					break;
				case "EditarDB":
					Actualizar(request,response,Opciones);
					break;
				default:
					List<Rubro> rubros =  new ArrayList<Rubro>();
					rubros =  model.ObtenerRubros();
					request.setAttribute("RubrosList", rubros);
					request.setAttribute("Opciones", Opciones);
					request.getRequestDispatcher("/Administrador/Rubros/index.jsp").forward(request, response);
					break;
				}
			}catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
				request.getRequestDispatcher("/Error.jsp").forward(request,response);
			}
	} //ProccessRequest
	
	private void Insertar(HttpServletRequest request, HttpServletResponse response,List<Opcion> opciones) throws ServletException, IOException{
		Rubro r = new Rubro();
		r.setRubro(request.getParameter("Nombre"));
		r.setDescripcion(request.getParameter("Descripcion"));
		boolean result = model.InsertarRubro(r);
		if(result){
			request.setAttribute("RubrosList", model.ObtenerRubros());
			request.setAttribute("Opciones", opciones);
			request.setAttribute("SuccessMsg", "Insertado correctamente");
			request.getRequestDispatcher("/Administrador/Rubros/index.jsp").forward(request,response);
		}else{
			request.setAttribute("RubrosList", model.ObtenerRubros());
			request.setAttribute("Opciones", opciones);
			request.setAttribute("ErrorMsg", "Ha ocurrido un error");
			request.getRequestDispatcher("/Administrador/Rubros/index.jsp").forward(request,response);
		}	
	}
	private void Actualizar(HttpServletRequest request, HttpServletResponse response,List<Opcion> opciones) throws ServletException, IOException{
		Rubro r = new Rubro();
		r.setId(Integer.parseInt(request.getParameter("id")));
		r.setRubro(request.getParameter("Nombre"));
		r.setDescripcion(request.getParameter("Descripcion"));
		boolean result = model.ActualizarRubro(r);
		if(result){
			request.setAttribute("SuccessMsg", "Actualizado correctamente");
			
		}else{
			request.setAttribute("ErrorMsg", "Ha ocurrido un error");
		}	
		request.setAttribute("RubrosList", model.ObtenerRubros());
		request.setAttribute("Opciones", opciones);
		request.getRequestDispatcher("/Administrador/Rubros/index.jsp").forward(request,response);
	}
	private void Eliminar(HttpServletRequest request, HttpServletResponse response,List<Opcion> opciones) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		boolean result = model.EliminarRubro(id);
		if(result){
			request.setAttribute("RubrosList", model.ObtenerRubros());
			request.setAttribute("Opciones", opciones);
			request.setAttribute("SuccessMsg", "Eliminado correctamente");
			request.getRequestDispatcher("/Administrador/Rubros/index.jsp").forward(request,response);
		}else{
			request.setAttribute("RubrosList", model.ObtenerRubros());
			request.setAttribute("Opciones", opciones);
			request.setAttribute("ErrorMsg", "Ha ocurrido un error");
			request.getRequestDispatcher("/Administrador/Rubros/index.jsp").forward(request,response);
		}	
	}
}
