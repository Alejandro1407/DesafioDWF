package sv.edu.udb.www.models.EmpresaModels;
import java.sql.SQLException;
import sv.edu.udb.www.beans.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sv.edu.udb.www.models.Conexion;;

public class DependienteModel extends Conexion{
	
	public List<Usuario> listarGestores() throws SQLException {
		try {
			List<Usuario> lista = new ArrayList<>();
			
			this.Conectar();
			query = conexion.prepareStatement("select u.*e.nombre from usuario u join empresa e on e.id = usuario.empresa where usuario.tipo = 2");
			resultSet = query.executeQuery();
			while (resultSet.next()) {
				Usuario gestor = new Usuario();
				gestor.setId(resultSet.getInt("idUsuario"));
				gestor.setNombres(resultSet.getString("nombres"));
				gestor.setNombreEmpresa(resultSet.getString("nombre"));
				gestor.setApellidos(resultSet.getString("apellidos"));
				gestor.setCorreo(resultSet.getString("correo"));
				lista.add(gestor);
			}
			this.Desconectar();
			return lista;

		} catch (SQLException ex) {
			Logger.getLogger(DependienteModel.class.getName()).log(Level.SEVERE, null, ex);
			this.Desconectar();
			return null;
		}
	}
	
	public int insertarDependiente(Usuario user) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "insert into usuario (nombres,apellidos, correo, contrasenia, empresa, tipo,valid) values (?,?,?,?,?,4,1);";
			this.Conectar();
			query = conexion.prepareStatement(sql);
			query.setString(1, user.getNombres());
			query.setString(2, user.getApellidos());
			query.setString(3, user.getCorreo());
			query.setString(4, user.getConstraseña());
			query.setInt(5, user.getIdEmpresa());
			filasAfectadas = proc.executeUpdate();
			this.Desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(DependienteModel.class.getName()).log(Level.SEVERE, null, ex);
			this.Desconectar();
			return 0;

		}
	}
/*
	public Editorial obtenerEditorial(String codigo) throws SQLException {
		try {
			String sql = "SELECT * FROM editoriales WHERE codigo_editorial=?";
			this.conectar();
			proc = conexion.prepareStatement(sql);
			proc.setString(1, codigo);
			resultSet = proc.executeQuery();
			if (resultSet.next()) {
				Editorial editorial = new Editorial();
				editorial.setCodigoEditorial(resultSet.getString("codigo_editorial"));
				editorial.setNombreEditorial(resultSet.getString("nombre_editorial"));
				editorial.setContacto(resultSet.getString("contacto"));
				editorial.setTelefono(resultSet.getString("telefono"));

				this.desconectar();
				return editorial;
			}
			this.desconectar();
			return null;
		} catch (SQLException ex) {
			Logger.getLogger(EditorialesModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return null;
		}
	}

	public int modificarEditorial(Editorial editorial) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "UPDATE editoriales SET nombre_editorial = ?, contacto = ?, telefono = ? WHERE codigo_editorial = ?";
			this.conectar();
			proc = conexion.prepareStatement(sql);
			proc.setString(1, editorial.getNombreEditorial());
			proc.setString(2, editorial.getContacto());
			proc.setString(3, editorial.getTelefono());
			proc.setString(4, editorial.getCodigoEditorial());
			filasAfectadas = proc.executeUpdate();
			this.desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(EditorialesModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;

		}
	}
*/
	public int eliminarDependiente(int id) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "delete from usuario where usuario.idUsuario = ?";
			this.Conectar();
			query = conexion.prepareStatement(sql);
			query.setInt(1, id);
			filasAfectadas = proc.executeUpdate();
			this.Desconectar();
			return filasAfectadas;
		} catch (SQLException ex) {
			Logger.getLogger(DependienteModel.class.getName()).log(Level.SEVERE, null, ex);
			this.Desconectar();
			return 0;
		}
	}
/*
	public int totalEditoriales() throws SQLException {
		try {
			int total = 0;
			String sql = "SELECT COUNT(codigo_editorial) as totaledit FROM editoriales";
			this.conectar();
			proc = conexion.prepareStatement(sql);
			resultSet = proc.executeQuery();
			while (resultSet.next()) {
				total = resultSet.getInt("totaledit");
			}
			this.desconectar();
			return total;
		} catch (SQLException ex) {
			Logger.getLogger(AutoresModel.class.getName()).log(Level.SEVERE, null, ex);
			this.desconectar();
			return 0;
		}
	}*/
}
