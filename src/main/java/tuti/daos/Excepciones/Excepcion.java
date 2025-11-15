package tuti.daos.Excepciones;

public class Excepcion extends RuntimeException {

	private String codigo;
	private String mensaje;
	private int status;

	public Excepcion() {
		super();
	}

	public Excepcion(String codigo, String mensaje, int status) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
		this.status = status;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public int getStatus() {
		return status;
	}

}
