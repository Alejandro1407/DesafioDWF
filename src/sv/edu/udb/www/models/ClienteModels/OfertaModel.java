package sv.edu.udb.www.models.ClienteModels;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sv.edu.udb.www.beans.*;
import sv.edu.udb.www.models.Conexion;

public class OfertaModel extends Conexion {
	
	public List<Oferta> ListarOfertas() throws SQLException {
		List<Oferta> lista = new ArrayList<>();
		
		try {
			Statement st = null;
			String query = "select * from oferta where estado = 2";
			this.Conectar();
			st = conexion.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Oferta oferta = new Oferta(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getDate(5),rs.getDate(6),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(12));
				
				lista.add(oferta);
				
				System.out.print(oferta.getTitulo());
				System.out.print(oferta.getDescripcion());
				
			}
			this.Desconectar();
			return lista;
		} catch (SQLException e) {	
			Logger.getLogger(Oferta.class.getName()).log(Level.SEVERE, null, e);
			 this.Desconectar();
			 return null;
			
		}
		
		
		
	}

}
