package sv.edu.udb.www.controllers.ClienteControllers;

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
import sv.edu.udb.www.models.SesionModel;
import sv.edu.udb.www.models.ClienteModels.CuponModel;
import sv.edu.udb.www.models.ClienteModels.OfertaModel;

@WebServlet(name = "/ClienteController", urlPatterns = {"/Cliente"})
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	OfertaModel modelo = new OfertaModel();
	CuponModel modelo2 = new CuponModel();
	
	
    public ClienteController() {
        super();
    }
    
    protected void listarOfertas(HttpServletRequest request, HttpServletResponse response, List<Opcion> opciones) {
    	try {
    		request.setAttribute("Opciones", opciones);
    		request.setAttribute("listaOfertas", modelo.ListarOfertas());
    		request.getRequestDispatcher("/Cliente/listaofertas.jsp").forward(request, response);
    		 } catch (SQLException | ServletException | IOException ex) {
    		 Logger.getLogger(OfertaModel.class.getName()).log(Level.SEVERE, null, ex);
    		 }

    	
    }
    
    protected void listarCupones(HttpServletRequest request, HttpServletResponse response, List<Opcion> opciones) throws SQLException {
    	try {
    		request.setAttribute("Opciones", opciones);
    		request.setAttribute("listaCupones", modelo2.ListarCupones());
    		request.getRequestDispatcher("/Cliente/CuponesCliente.jsp").forward(request, response);
    		 } catch (ServletException | IOException ex) {
    		 Logger.getLogger(CuponModel.class.getName()).log(Level.SEVERE, null, ex);
    		 }

    	
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccessRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccessRequest(request, response);
	}
	protected void ProccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Opcion> Opciones = new ArrayList<Opcion>();
		Opciones.add(new Opcion("DashBoard","/DesafioMVC/Cliente","fas fa-chart-pie",true));
		Opciones.add(new Opcion("Comprar Cupones","/DesafioMVC/Cliente?op=Comprar","fas fa-percent",false));
		Opciones.add(new Opcion("Ver Cupones","/DesafioMVC/Cliente?op=Ver","fas fa-list",false));
		Opciones.add(new Opcion("Cambiar Contrase�a","/DesafioMVC/Cliente?op=Change","fas fa-lock",false));
		Opciones.add(new Opcion("Cerrar Sesion","/DesafioMVC/Login?op=cerrar","fas fa-sign-out-alt red-text",false));
		response.setContentType("text/html;charset=UTF-8");
		try{
				String Operacion =  "";
				if(request.getParameter("op") != null){
					Operacion = request.getParameter("op");
				}
				//En caso de no encontrarse op ira al default, Agregar casos necesarios y sus metodos
				switch (Operacion) {
				//Implementar el comprar y Ver Aqui
				case "Change":
					Opcion temp = Opciones.get(3);
					Opcion temp1 = Opciones.get(0);
					temp.setIsActive(true);
					temp1.setIsActive(false);
					request.setAttribute("Opciones", Opciones);
					request.setAttribute("Path", "/DesafioMVC/Cliente?op=CambiarDB");
					request.getRequestDispatcher("CambiarPassword.jsp").forward(request, response);
					break;
				case "CambiarDB":
						CambiarContraseña(request,response, Opciones);
						break;
				case "Comprar":
					listarOfertas(request, response,Opciones);
					break;
				case "Ver":
					listarCupones(request, response, Opciones);
					break;
				default:
					request.setAttribute("Opciones", Opciones);
					request.getRequestDispatcher("/Cliente/index.jsp").forward(request, response);
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
		request.setAttribute("Path", "/DesafioMVC/Cliente?op=CambiarDB");
		if(!(request.getParameter("NewPass1").equals(request.getParameter("NewPass2")))){
			request.setAttribute("message", "Contrase�as no coinciden");
			request.getRequestDispatcher("CambiarPassword.jsp").forward(request,response);
		}
		String Message = model.CambiarContraseña(Integer.parseInt(request.getParameter("id")),request.getParameter("OldPass"),request.getParameter("NewPass1"));
		request.setAttribute("message",Message);
		request.getRequestDispatcher("CambiarPassword.jsp").forward(request,response);
	}

}
