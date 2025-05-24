package entidad;

public class Usuario {
	
	private String nombreUsuario;
	private String password;
	private int tipoUsuario;
	private String dni;
	private String nombre;
	private String apellido;
	
	public Usuario() {
		
	}
	
	

	public Usuario(String nombreUsuario, String password, int tipoUsuario, String dni, String nombre, String apellido) {

		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	@Override
	public String toString() {
		return "Usuario [nombreUsuario=" + nombreUsuario + ", password=" + password + ", tipoUsuario=" + tipoUsuario
				+ ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	
	
}
