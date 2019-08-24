package sv.edu.udb.www.beans;
import java.util.Date;


public class Oferta {
	
	private int Id;
	private String Titulo;
	private double PRegular;
	private double POferta;
	private Date FInicio;
	private Date FFinal;
	private Date FLimite;
	private int Limite;
	private String Descripcion;
	private String Detalles;
	private int IdEmpresa;
	private int IdEstado;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public double getPRegular() {
		return PRegular;
	}
	public void setPRegular(double pRegular) {
		PRegular = pRegular;
	}
	public double getPOferta() {
		return POferta;
	}
	public void setPOferta(double pOferta) {
		POferta = pOferta;
	}
	public Date getFInicio() {
		return FInicio;
	}
	public void setFInicio(Date fInicio) {
		FInicio = fInicio;
	}
	public Date getFFinal() {
		return FFinal;
	}
	public void setFFinal(Date fFinal) {
		FFinal = fFinal;
	}
	public Date getFLimite() {
		return FLimite;
	}
	public void setFLimite(Date fLimite) {
		FLimite = fLimite;
	}
	public int getLimite() {
		return Limite;
	}
	public void setLimite(int limite) {
		Limite = limite;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public String getDetalles() {
		return Detalles;
	}
	public void setDetalles(String detalles) {
		Detalles = detalles;
	}
	public int getIdEmpresa() {
		return IdEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		IdEmpresa = idEmpresa;
	}
	public int getIdEstado() {
		return IdEstado;
	}
	public void setIdEstado(int idEstado) {
		IdEstado = idEstado;
	}


}
