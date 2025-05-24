package entidad;

public class Contratacion {
	
	private int id;
	private Usuario usuario;
	private Seguro seguro;
	private float costo;
	
	public Contratacion() {

		
	}

	public Contratacion(int id, Usuario usuario, Seguro seguro, float costo) {

		this.id = id;
		this.usuario = usuario;
		this.seguro = seguro;
		this.costo = costo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "Contratacion [id=" + id + ", usuario=" + usuario != null ? usuario.getNombre() : "Usuario Invalido" +
				", seguro=" + seguro + ", costo=" + costo + "]";
	}
	
	
	
}
