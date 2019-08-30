package sv.edu.udb.www.models.AdministradorModels;

import java.util.List;
import java.util.ArrayList;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.models.Conexion;

public class OfertasModel extends Conexion {

	public List<Oferta> ObtenerOfertas(){
		List<Oferta> ofertas =  new ArrayList<Oferta>();
		try{
			this.Conectar();
			query = conexion.prepareStatement("SELECT of.idOferta ,of.titulo,of.precioRegular,of.precioOferta,of.fechaInicio,of.fechaFin,of.limiteCupones,of.descripcion,of.otrosDetalles,e.nombre FROM oferta of INNER JOIN empresa e WHERE of.estado = 1");
			resultSet = query.executeQuery();
			while(resultSet.next()){
				ofertas.add(new Oferta( resultSet.getInt(1),resultSet.getString(2),resultSet.getDouble(3),resultSet.getDouble(4), resultSet.getDate(5),resultSet.getDate(6), resultSet.getInt(7),resultSet.getString(8), resultSet.getString(9), resultSet.getString(10)));
			}
			return ofertas;
		}catch (Exception e) {
			return null;
		}
	}
	
	public boolean AceptarOferta(int id){
		try{
			this.Conectar();
			query = conexion.prepareStatement("UPDATE oferta SET estado = 2 WHERE idOferta = ?");
			query.setInt(1, id);
			query.executeUpdate();
			this.Desconectar();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public boolean RechazarOferta(int id){
		try{
			this.Conectar();
			query = conexion.prepareStatement("UPDATE oferta SET estado = 3 WHERE idOferta = ?");
			query.setInt(1, id);
			query.executeUpdate();
			this.Desconectar();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
