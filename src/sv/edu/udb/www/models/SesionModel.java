package sv.edu.udb.www.models;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.beans.Usuario;

public class SesionModel extends Conexion {

	public Usuario CheckLogin(String Email,String Password){
		Usuario u =  new Usuario();
		try{
			this.Conectar(); //Intenta conectar
			proc = conexion.prepareCall("{call loguearse (?,?)}");
            proc.setString(1,Email);
            proc.setString(2,Password);
            resultSet = proc.executeQuery();
            if(!resultSet.next()){
                this.Desconectar();
                return null;
            }
            u.setId(resultSet.getInt(1));
            u.setNombres(resultSet.getString(2));
            u.setIdTipo(resultSet.getInt(3));
            u.setIdEmpresa(resultSet.getInt(4) );
            this.Desconectar();
            return u;
		}catch (Exception e) {
			return null;
		}
	}//CheckLogin
	
	public String CambiarContraseña(int id, String OldPass,String NewPass){
		try{
			this.Conectar();
			proc = conexion.prepareCall("{ call ChangePass(?,?,?)}");
			proc.setInt(1, id);
			proc.setString(2, OldPass);
			proc.setString(3,NewPass);
			resultSet = proc.executeQuery();
			if(!resultSet.next()){
	                this.Desconectar();
	                return "Error empty result";
	        }
			return resultSet.getString(1);
		}catch (Exception e) {
			return "Error conexion";
		}//UPDATE usuario SET contrasenia = SHA2("Password01",256)
	}
	
	
}//Clase
