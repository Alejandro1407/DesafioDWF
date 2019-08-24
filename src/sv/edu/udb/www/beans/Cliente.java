package sv.edu.udb.www.beans;

public class Cliente {
	
	private int IdUsuario;
	private String DUI;
	private String Telefono;

	
	public int getUsuario() {
		return IdUsuario;
	}
	public void setUsuario(int usuario) {
		IdUsuario = usuario;
	}
	public String getDUI() {
		return DUI;
	}
	public void setDUI(String dUI) {
		DUI = dUI;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
}
