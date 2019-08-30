package sv.edu.udb.www.models.ClienteModels;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sv.edu.udb.www.beans.Cupon;
import sv.edu.udb.www.beans.Oferta;
import sv.edu.udb.www.models.Conexion;

public class CuponModel extends Conexion{
	
	public List<Cupon> ListarCupones() {
		
		List<Cupon> lista = new ArrayList<>();
			try {
				Statement st = null;
				String query = "select cupon.codigoCupon, empresa.nombre, estado.estado, oferta.titulo, usuario.nombres, usuario.apellidos, cliente.dui, compra.fecha from cupon inner join oferta on oferta.idOferta = cupon.oferta INNER join empresa on empresa.id = oferta.empresa INNER join estado on estado.idEstado = oferta.estado INNER join compra on compra.idCompra = cupon.compra inner JOIN cliente on cliente.usuario = compra.cliente INNER join usuario on cliente.usuario = usuario.idUsuario where cupon.codigoCupon = ";
				this.Conectar();
				st = conexion.createStatement();
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Cupon cupon = new Cupon();
					
					cupon.setCodigo(rs.getString(2));
					cupon.setIdCompra(rs.getInt(1));
					cupon.setOferta(rs.getInt(3));
					
					lista.add(cupon);
					 
					System.out.print(cupon.getIdCompra());
					System.out.print(cupon.getCodigo());
					
				}
				this.Desconectar();
				return lista;
				
			} catch (Exception e) {
				return lista;
			}
			
		
		
		
		
		
		
	}

}
