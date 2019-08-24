package sv.edu.udb.www.beans;

public class Compra {
	
	private int Id;
	private String Fecha;
	private double Total;
	private int ClienteId;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public double getTotal() {
		return Total;
	}
	public void setTotal(double total) {
		Total = total;
	}
	public int getClienteId() {
		return ClienteId;
	}
	public void setClienteId(int clienteId) {
		ClienteId = clienteId;
	}
}//Clase
