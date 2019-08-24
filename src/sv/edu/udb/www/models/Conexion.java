package sv.edu.udb.www.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {

    protected static Connection conexion=null;
    protected PreparedStatement query;
    protected CallableStatement proc;
    protected ResultSet resultSet;

    public Conexion() {
        this.query = null;
        this.proc = null;
    }

    public void Conectar() {
        try {
            if(conexion==null || conexion.isClosed()){
	            Context init = new InitialContext();
	            Context context = (Context) init.lookup("java:comp/env");
	            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
	            conexion = dataSource.getConnection();
            }
        } catch (NamingException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Desconectar() throws SQLException {
        if (query != null) {
           query.close();
        }
        if (proc != null) {
            proc.close();
        }
    }//Desconectar
    
}//Clase
