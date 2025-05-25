package entidad;

public class Seguro {
	
	private int id;
	private String descripcion;
	private float costoContratacion;
	private float costoAsegurado;
	private TipoSeguro tipoSeguro;
	
	public Seguro() {

	}

	public Seguro(int id, String descripcion, TipoSeguro tiposeguro, float costoContratacion, float costoAsegurado) {

		this.id = id;
		this.descripcion = descripcion;
		this.tipoSeguro = tiposeguro;
		this.costoContratacion = costoContratacion;
		this.costoAsegurado = costoAsegurado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public float getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(float costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public float getCostoAsegurado() {
		return costoAsegurado;
	}

	public void setCostoAsegurado(float costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro [id=" + id + ", descripcion=" + descripcion + ", idTipoSeguro=" + (tipoSeguro != null ? tipoSeguro.getId() : "Tipo Seguro invalido") + ", costoContratacion="
				+ costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
	
	
	
	
	
}
