package sv.edu.udb.www.beans;

public class Usuario {

	private int Id;
	private String Nombres;
	private String Apellidos;
	private String Correo;
	private String Constrase�a;
	private int IdTipo;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNombres() {
		return Nombres;
	}
	public void setNombres(String nombres) {
		Nombres = nombres;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getCorreo() {
		return Correo;
	}
	public void setCorreo(String correo) {
		Correo = correo;
	}
	public String getConstrase�a() {
		return Constrase�a;
	}
	public void setConstrase�a(String constrase�a) {
		Constrase�a = constrase�a;
	}
	public int getIdTipo() {
		return IdTipo;
	}
	public void setIdTipo(int tipo) {
		IdTipo = tipo;
	}

}//Bean
