package sv.edu.udb.www.models;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sv.edu.udb.www.beans.Usuario;

public class SesionModel extends Conexion {
	
	 private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public static String generatePassword(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
	}
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
	
	public boolean AlmacenarToken(UUID Token,String Email){
		try{
			this.Conectar();
			query =  conexion.prepareStatement("INSERT INTO Token (Token,Email) VALUES (?,?)");
			query.setString(1, Token.toString());
			query.setString(2,Email);
			query.executeUpdate();
			this.Desconectar();
			return true;
		}catch (Exception e) {
			return false;
		}
	}// Solicitar
	
	public String ValidarToken(String Token){
		try{
			this.Conectar();
			proc = conexion.prepareCall("{ call ValidarToken(?)}");
			proc.setString(1, Token);
			resultSet = proc.executeQuery();
			if(!resultSet.next()){
				return null;
			}
			return resultSet.getString(1);	
		}catch (Exception e) {
			return null;
		}
	}
	public boolean SetUserValid(String Email){
		try{
			this.Conectar();
			query =  conexion.prepareStatement("UPDATE usuario SET valid = 1 WHERE correo = ?");
			query.setString(1, Email);
			query.executeUpdate();
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	public boolean RecuperarContraseña(String Email){
		String Password =generatePassword(8);
		try{
			this.Conectar();
			query = conexion.prepareStatement("UPDATE usuario SET contrasenia = SHA2(?,256) WHERE correo =  ?");
			query.setString(1, Password);
			query.setString(2, Email);
			query.executeUpdate();
			this.Desconectar();
			String Message = "Hola\nSu solicitud de restablecimiento de Contraseña tuvo exito\n"
							+ "\nSu nueva contraseña: \n"
							+ "\"" + Password + "\""
							+ "\n\nSi no hiciste dicha solicitud ignora este mensaje"
							+ "\n\nDepartamento de Administración - La Cuponera SV";
			Mailer mailer = new Mailer();
			mailer.send(Email, "Solicitud de recuperacion", Message);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean RegistrarUsuario(Usuario u){
		try{
			this.Conectar();
			query = conexion.prepareStatement("INSERT INTO usuario VALUES (NULL,?,?,?,SHA2(?,256),NULL,3);INSERT INTO cliente VALUES (LAST_INSERT_ID(),?,?)");
			query.setString(1, u.getNombres());
			query.setString(2, u.getApellidos());
			query.setString(3, u.getCorreo());
			query.setString(4, u.getConstraseña());
			query.setString(5,u.getDUI());
			query.setString(6, u.getTelefono());
			query.executeUpdate();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}//Clase
