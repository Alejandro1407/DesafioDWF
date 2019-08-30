package sv.edu.udb.www.models.AdministradorModels;

import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.www.beans.Rubro;
import sv.edu.udb.www.beans.Empresa;
import sv.edu.udb.www.models.Conexion;

public class EmpresaModel extends Conexion{
	
	public Empresa ObtenerEmpresa(int id){
		Empresa e = new Empresa();
		try{
			this.Conectar();
			query =  conexion.prepareStatement("SELECT * FROM empresa WHERE id = ?");
			query.setInt(1, id);
			resultSet = query.executeQuery();
			if(!resultSet.next()){
				return null;
			}
			e.setId(resultSet.getInt(1));
			e.setCodigo(resultSet.getString(2));
			e.setNombre(resultSet.getString(3));
			e.setDireccion(resultSet.getString(4));
			e.setContacto(resultSet.getString(5));
			e.setTelefono(resultSet.getString(6));
			e.setIdRubro(resultSet.getInt(7));
			e.setCobro(resultSet.getDouble(8));
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
			query = conexion.prepareStatement("SELECT * FROM empresa");
			resultSet = query.executeQuery();
			while(resultSet.next()){
				empresas.add(new Empresa(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getInt(7),resultSet.getDouble(8)));
			}
			return empresas;
		}catch (Exception e) {
			return null;
		}
	}
	
	public boolean InsertarEmpresa(Empresa e){
		try{
			this.Conectar();
			proc =  conexion.prepareCall("{call insertarEmpresa(?,?,?,?,?,?)}");
			proc.setString(1, e.getNombre());
			proc.setString(2, e.getDireccion());
			proc.setString(3, e.getContacto());
			proc.setString(4, e.getTelefono());
			proc.setInt(5, e.getIdRubro());
			proc.setDouble(6, e.getCobro());
			proc.executeUpdate();
			return true;
		}catch (Exception ex) {
			return false;
		}
		
	}
	public boolean ActualizarEmpresa(Empresa e){
		try{
			this.Conectar();
			query =  conexion.prepareStatement("UPDATE empresa SET nombre = ?, direccion = ?, contacto = ?, telefono = ?,rubro = ?,cobro = ? WHERE id = ?");
			query.setString(1, e.getNombre());
			query.setString(2, e.getDireccion());
			query.setString(3, e.getContacto());
			query.setString(4, e.getTelefono());
			query.setInt(5,e.getIdRubro());
			query.setDouble(6, e.getCobro());
			query.setInt(7, e.getId());
			query.executeUpdate();
			return true;
		}catch (Exception ex) {
			return false;
		}
	}
	
	public boolean EliminarEmpresa(int id){
		try{
			this.Conectar();
			query =  conexion.prepareStatement("DELETE FROM empresa WHERE id = ?");
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
