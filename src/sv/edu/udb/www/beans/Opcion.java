package sv.edu.udb.www.beans;

public class Opcion {

	public Opcion(String Nombre,String URL,String Icon,boolean IsActive){
		this.Nombre = Nombre;
		this.URL =  URL;
		this.Icon =  Icon;
		this.IsActive = IsActive;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getIcon() {
		return Icon;
	}
	public void setIcon(String icon) {
		Icon = icon;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public void setURL(String URL){
		this.URL = URL;
	}
	public String getURL(){
		return this.URL;
	}
	
	private String Nombre;
	private String URL;
	private String Icon;
	private boolean IsActive;
}
