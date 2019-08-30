package sv.edu.udb.www.models.AdministradorModels;

import sv.edu.udb.www.models.Conexion;
import sv.edu.udb.www.beans.Rubro;
import java.util.List;
import java.util.ArrayList;

public class RubrosModel extends Conexion {

	public Rubro ObtenerRubro(int id){
		Rubro r = new Rubro();
		try{
			this.Conectar();
			query =  conexion.prepareStatement("SELECT * FROM rubro WHERE idRubro = ?");
			query.setInt(1, id);
			resultSet = query.executeQuery();
			if(!resultSet.next()){
				return null;
			}
			r.setId(resultSet.getInt(1));
			r.setRubro(resultSet.getString(2));
			r.setDescripcion(resultSet.getString(3));
			return r;
		}catch (Exception e) {
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
	
	public boolean InsertarRubro(Rubro r){
		try{
			this.Conectar();
			query =  conexion.prepareStatement("INSERT INTO rubro VALUES (NULL,?,?)");
			query.setString(1, r.getRubro());
			query.setString(2, r.getDescripcion());
			query.executeUpdate();
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	public boolean ActualizarRubro(Rubro r){
		try{
			this.Conectar();
			query =  conexion.prepareStatement("UPDATE rubro SET rubro = ?, descripcion = ? WHERE idRubro = ?");
			query.setString(1, r.getRubro());
			query.setString(2, r.getDescripcion());
			query.setInt(3, r.getId());
			query.executeUpdate();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean EliminarRubro(int id){
		try{
			this.Conectar();
			query =  conexion.prepareStatement("DELETE FROM rubro WHERE idRubro = ?");
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
