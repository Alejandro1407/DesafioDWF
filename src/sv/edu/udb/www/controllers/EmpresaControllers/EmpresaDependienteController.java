package sv.edu.udb.www.controllers.EmpresaControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.beans.Opcion;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.models.EmpresaModels.DependienteModel;
import sv.edu.udb.www.utils.Validaciones;

@WebServlet(name = "/EmpresaDependienteController", urlPatterns = {"/Empresa/Dependientes"})
public class EmpresaDependienteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ArrayList<String> listaErrores = new ArrayList<>();
	DependienteModel modelo = new DependienteModel();

    public EmpresaDependienteController() {
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
		Opciones.add(new Opcion("DashBoard","/DesafioMVC/Empresa","fas fa-chart-pie",false));
		Opciones.add(new Opcion("Ofertas","/DesafioMVC/Empresa/Ofertas","fas fa-percent",false));
		Opciones.add(new Opcion("Dependientes","/DesafioMVC/Empresa/Dependientes","fas fa-users-cog",true));
		Opciones.add(new Opcion("Cambiar Contrase�a","/DesafioMVC/Empresa?op=Change","fas fa-lock",false));
		Opciones.add(new Opcion("Cerrar Sesion","/DesafioMVC/Login?op=cerrar","fas fa-sign-out-alt red-text",false));
		response.setContentType("text/html;charset=UTF-8");
		try{
				String Operacion =  "";
				if(request.getParameter("op") != null){
					Operacion = request.getParameter("op");
				}
				switch (Operacion) {
				//Implementar aqui sus casos
				case "Agregar":
					request.getRequestDispatcher("/Empresa/Dependientes/insertar.jsp").forward(request, response);
					break;
				case "insertar":
					insertar(request, response,Opciones);
					break;
				case "eliminar":
					eliminar(request, response);
					break;
				default:
					request.setAttribute("UsuariosList", modelo.listarGestores());
					request.setAttribute("Opciones", Opciones);
					request.getRequestDispatcher("/Empresa/Dependientes/index.jsp").forward(request, response);
					break;
					
				}
			}catch (Exception e) {
				request.setAttribute("Error", e.getMessage());
				request.getRequestDispatcher("/Error.jsp").forward(request,response);
			}
	} //ProccessRequest
	
	private void insertar(HttpServletRequest request, HttpServletResponse response, List<Opcion> Opciones) throws ServletException, SQLException {
		try {
			listaErrores.clear();
			Usuario user = new Usuario();
			user.setNombres(request.getParameter("nombres"));
			user.setApellidos(request.getParameter("apellidos"));
			user.setCorreo(request.getParameter("correo"));
			user.setConstraseña(request.getParameter("contrasenia"));
			user.setIdEmpresa(Integer.parseInt(request.getParameter("id")));
			
			if (modelo.insertarDependiente(user) > 0) {
				request.setAttribute("SuccessMsg","Ingresado correctamente");
			} else {
				request.setAttribute("ErrorMsg","Ha ocurrido un error");
			}
			request.setAttribute("Opciones", Opciones);
			request.getRequestDispatcher("/Empresa/Dependientes/index.jsp").forward(request, response);
		} catch (IOException ex																																																																																																																																																																																																																																																																																																																																																																																																																																																															) {
			Logger.getLogger(EmpresaDependienteController.class.getName()).log(Level.SEVERE, null,
			ex);
		}
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			if (modelo.eliminarDependiente(id) > 0) {
				request.setAttribute("exito", "gestor eliminado exitosamente");

			} else {
				request.setAttribute("fracaso", "No se puede eliminar gestor");
			}
			request.getRequestDispatcher("/Empresa/Dependientes").forward(request, response);
		} catch (SQLException | ServletException | IOException ex) {
			// Logger.getLogger(GenerosController.class.getName()).log(Level.SEVERE, null,
			// ex);
		}
	}
	
}
