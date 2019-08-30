package sv.edu.udb.www.controllers.AdministradorControllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.beans.Opcion;
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.models.AdministradorModels.EmpresaModel;

@WebServlet(name = "/AdministradorEmpresaController", urlPatterns = "/Administrador/Empresa")
public class AdministradorEmpresaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    EmpresaModel model = new EmpresaModel();
    public AdministradorEmpresaController() {
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
		Opciones.add(new Opcion("Empresas","/DesafioMVC/Administrador/Empresa","fas fa-building",true));
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
					case "Agregar":
						request.setAttribute("RubrosList", model.ObtenerRubros());
						request.setAttribute("Opciones",Opciones);
						request.getRequestDispatcher("/Administrador/Empresa/Insertar.jsp").forward(request, response);	
						break;
					case "AgregarDB":
						Insertar(request,response,Opciones);
						break;
					case "Eliminar":
						Eliminar(request,response,Opciones);
					case "Editar":
						request.setAttribute("RubrosList", model.ObtenerRubros());
						request.setAttribute("Empresa", model.ObtenerEmpresa(Integer.parseInt(request.getParameter("id"))));
						request.setAttribute("Opciones",Opciones);
						request.getRequestDispatcher("/Administrador/Empresa/Editar.jsp").forward(request, response);	
						break;
					case "EditarDB":
						Actualizar(request,response,Opciones);
						break;
					default:
						request.setAttribute("EmpresasList", model.ObtenerEmpresas());
						request.setAttribute("Opciones", Opciones);
						request.getRequestDispatcher("/Administrador/Empresa/index.jsp").forward(request, response);
						break;
				}
			}catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
				request.getRequestDispatcher("/Error.jsp").forward(request,response);
			}
	} //ProccessRequest
	
	private void Insertar(HttpServletRequest request, HttpServletResponse response,List<Opcion> opciones) throws ServletException, IOException{
		Empresa e = new Empresa();
		e.setNombre(request.getParameter("Nombre"));
		e.setDireccion(request.getParameter("Direccion"));
		e.setContacto(request.getParameter("Contacto"));
		e.setTelefono(request.getParameter("Telefono"));
		e.setIdRubro(Integer.parseInt(request.getParameter("idRubro")));
		e.setCobro(Double.parseDouble(request.getParameter("Cobro")));
		boolean result = model.InsertarEmpresa(e);
		if(result){
			request.setAttribute("SuccessMsg", "Insertado correctamente");
		}else{
			request.setAttribute("ErrorMsg", "Ha ocurrido un error");
		}
		request.setAttribute("EmpresasList", model.ObtenerEmpresas());
		request.setAttribute("Opciones", opciones);
		request.getRequestDispatcher("/Administrador/Empresa/index.jsp").forward(request,response);
	}
	private void Actualizar(HttpServletRequest request, HttpServletResponse response,List<Opcion> opciones) throws ServletException, IOException{
		Empresa e = new Empresa();
		e.setId(Integer.parseInt(request.getParameter("id")));
		e.setNombre(request.getParameter("Nombre"));
		e.setDireccion(request.getParameter("Direccion"));
		e.setContacto(request.getParameter("Contacto"));
		e.setTelefono(request.getParameter("Telefono"));
		e.setIdRubro(Integer.parseInt(request.getParameter("idRubro")));
		e.setCobro(Double.parseDouble(request.getParameter("Cobro")));
		boolean result = model.ActualizarEmpresa(e);
		if(result){
			request.setAttribute("SuccessMsg", "Actualizado correctamente");
			
		}else{
			request.setAttribute("ErrorMsg", "Ha ocurrido un error");
		}	
		request.setAttribute("EmpresasList", model.ObtenerEmpresas());
		request.setAttribute("Opciones", opciones);
		request.getRequestDispatcher("/Administrador/Empresa/index.jsp").forward(request,response);
	}
	private void Eliminar(HttpServletRequest request, HttpServletResponse response,List<Opcion> opciones) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		boolean result = model.EliminarEmpresa(id);
		if(result){
			request.setAttribute("SuccessMsg", "Eliminado correctamente");
		}else{
			request.setAttribute("ErrorMsg", "Ha ocurrido un error");
		}	
		request.setAttribute("EmpresasList", model.ObtenerEmpresas());
		request.setAttribute("Opciones", opciones);
		request.getRequestDispatcher("/Administrador/Empresa/index.jsp").forward(request,response);
	}
	
	
}//EmpresaController
