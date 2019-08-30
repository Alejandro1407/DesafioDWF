package sv.edu.udb.www.beans;

public class Usuario {

	private int Id;
	private String Nombres;
	private String Apellidos;
	private String Correo;
	private String Constraseña;
	private Integer IdEmpresa;
	
	private String Telefono;
	private String DUI;
	
	public Usuario(){
		
	}
	
	public Usuario(int id, String nombres, String apellidos, String correo,String dUI,String telefono) {
		super();
		Id = id;
		Nombres = nombres;
		Apellidos = apellidos;
		Correo = correo;
		Telefono = telefono;
		DUI = dUI;
	}
	
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getDUI() {
		return DUI;
	}
	public void setDUI(String dUI) {
		DUI = dUI;
	}
	public Integer getIdEmpresa() {
		return IdEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		IdEmpresa = idEmpresa;
	}
	
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
	public String getConstraseña() {
		return Constraseña;
	}
	public void setConstraseña(String constraseña) {
		Constraseña = constraseña;
	}
	public int getIdTipo() {
		return IdTipo;
	}
	public void setIdTipo(int tipo) {
		IdTipo = tipo;
	}

}//Bean
