package sv.edu.udb.www.beans;

public class Empresa {

	private int id;
	private String Codigo;
	private String Nombre;
	private String Direccion;
	private String Contacto;
	private String Telefono;
	private int IdRubro;
	private double Cobro;
	private String NombreRubro;
	private String correo;
	
	public Empresa(int id, String codigo, String nombre, String direccion,String telefono,
			int idRubro, double cobro,String NombreRubro,String correo) {
		this.id = id;
		this.Codigo = codigo;
		this.Nombre = nombre;
		this.Direccion = direccion;
		this.Telefono = telefono;
		this.IdRubro = idRubro;
		this.Cobro = cobro;
		this.NombreRubro = NombreRubro;
		this.correo = correo;
	}
	public String getNombreRubro() {
		return NombreRubro;
	}
	public void setNombreRubro(String nombreRubro) {
		NombreRubro = nombreRubro;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Empresa(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getContacto() {
		return Contacto;
	}
	public void setContacto(String contacto) {
		Contacto = contacto;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public int getIdRubro() {
		return IdRubro;
	}
	public void setIdRubro(int idRubro) {
		IdRubro = idRubro;
	}
	public double getCobro() {
		return Cobro;
	}
	public void setCobro(double cobro) {
		Cobro = cobro;
	}
}//Clase
