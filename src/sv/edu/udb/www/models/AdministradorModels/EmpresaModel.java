package sv.edu.udb.www.models.AdministradorModels;

import java.util.ArrayList;
import java.util.List;
import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.models.Conexion;
import sv.edu.udb.www.models.Mailer;

import java.security.SecureRandom;
import java.util.Random;

public class EmpresaModel extends Conexion{
	
	 private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		
	public static String generatePassword(int length) {
	        StringBuilder returnValue = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
	        }
	        return new String(returnValue);
	}
	
	public Empresa ObtenerEmpresa(int id){
		Empresa e = new Empresa();
		try{
			this.Conectar();
			query =  conexion.prepareStatement("SELECT e.*,r.rubro,u.correo FROM empresa e INNER JOIN rubro r on e.rubro = r.idRubro INNER JOIN usuario u on u.empresa = e.id WHERE e.id = ?");
			query.setInt(1, id);
			resultSet = query.executeQuery();
			if(!resultSet.next()){
				return null;
			}
			e.setId(resultSet.getInt(1));
			e.setCodigo(resultSet.getString(2));
			e.setNombre(resultSet.getString(3));
			e.setDireccion(resultSet.getString(4));
			e.setTelefono(resultSet.getString(5));
			e.setIdRubro(resultSet.getInt(6));
			e.setCobro(resultSet.getDouble(7));
			e.setNombreRubro(resultSet.getString(8));
			e.setCorreo(resultSet.getString(9));
			return e;
		}catch (Exception ex) {
			return null;
		}
	}
	
	public List<Rubro> ObtenerRubros(){
		List<Rubro> rubros = new ArrayList<Rubro>();
		try{
			this.Conectar();
			query = conexion.prepareStatement("SELECT * FROM rubro");
			resultSet = query.executeQuery();
			while(resultSet.next()){
				rubros.add(new Rubro(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));
			}
			return rubros;
		}catch (Exception e) {
			return null;
		}
	}
	
	public List<Empresa> ObtenerEmpresas(){
		List<Empresa> empresas = new ArrayList<Empresa>();
		try{
			this.Conectar();
			query = conexion.prepareStatement("SELECT e.*,r.rubro,u.correo FROM empresa e INNER JOIN rubro r on e.rubro = r.idRubro INNER JOIN usuario u on u.empresa = e.id");
			resultSet = query.executeQuery();
			while(resultSet.next()){
				empresas.add(new Empresa(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getDouble(7),resultSet.getString(8),resultSet.getString(9) ));
			}
			return empresas;
		}catch (Exception e) {
			return null;
		}
	}
	
	public boolean InsertarEmpresa(Empresa e){

		try{
			String Password = generatePassword(8);
			this.Conectar();
			proc =  conexion.prepareCall("{call insertarEmpresa(?,?,?,?,?,?,?,?)}");
			proc.setString(1, e.getNombre());
			proc.setString(2, e.getDireccion());
			proc.setString(3, e.getContacto());
			proc.setString(4, e.getTelefono());
			proc.setInt(5, e.getIdRubro());
			proc.setDouble(6, e.getCobro());
			proc.setString(7, e.getCorreo());
			proc.setString(8, Password);
			proc.executeUpdate();
			String Message = "Hola\nSe ha registrado satisfactoriamente en la Cuponera SV"
					+ "\nSus credenciales de acceso son : "
					+ "\nCorreo: " + e.getCorreo()
					+ "\nContraseña: " + Password
					+ "\n\n\nSi no hiciste dicha solicitud ignora este mensaje"
					+ "\n\nDepartamento de Administración - La Cuponera SV";
			Mailer mailer = new Mailer();
			mailer.send(e.getCorreo(), "Solicitud de Registro", Message);
			return true;
		}catch (Exception ex) {
			return false;
		}
		
	}
	
	public boolean ActualizarEmpresa(Empresa e){
		try{
			this.Conectar();
			query =  conexion.prepareStatement("UPDATE empresa SET nombre = ?, direccion = ?, telefono = ?,rubro = ?,cobro = ? WHERE id = ?");
			query.setString(1, e.getNombre());
			query.setString(2, e.getDireccion());
			query.setString(3, e.getTelefono());
			query.setInt(4,e.getIdRubro());
			query.setDouble(5, e.getCobro());
			query.setInt(6, e.getId());
			query.executeUpdate();
			return true;
		}catch (Exception ex) {
			return false;
		}
	}
	
	public boolean EliminarEmpresa(int id){
		try{
			this.Conectar();
			query =  conexion.prepareStatement("DELETE FROM usuario WHERE empresa = ?;DELETE FROM empresa WHERE id = ?");
			query.setInt(1, id);
			query.setInt(2, id);
			query.executeUpdate();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
