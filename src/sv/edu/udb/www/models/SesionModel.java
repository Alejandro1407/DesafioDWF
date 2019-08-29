package sv.edu.udb.www.models;

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
	
	
}//Clase
