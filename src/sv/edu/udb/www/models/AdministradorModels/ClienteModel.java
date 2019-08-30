package sv.edu.udb.www.models.AdministradorModels;

import java.util.ArrayList;
import java.util.List;
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.beans.Usuario;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.models.Conexion;
import sv.edu.udb.www.models.Mailer;

import java.security.SecureRandom;
import java.util.Random;

public class ClienteModel extends Conexion{
	

	
	public List<Usuario> ObtenerClientes(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try{
			this.Conectar();
			query = conexion.prepareStatement("SELECT u.idUsuario,u.nombres,u.apellidos,u.correo,c.dui,c.telefono FROM usuario u INNER JOIN  cliente c on c.usuario = u.idUsuario WHERE u.tipo = 3");
			resultSet = query.executeQuery();
			while(resultSet.next()){
				usuarios.add(new Usuario(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
			}
			return usuarios;
		}catch (Exception e) {
			return null;
		}
	}
	
	
}
