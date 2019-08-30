package sv.edu.udb.www.beans;

public class Rubro {
	
	private int id;
	private String Rubro;
	private String Descripcion;

	public Rubro(int id, String rubro, String descripcion) {
		this.id = id;
		this.Rubro = rubro;
		this.Descripcion = descripcion;
	}
	public Rubro(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRubro() {
		return Rubro;
	}
	public void setRubro(String rubro) {
		Rubro = rubro;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}


}
