package tuti.daos.Excepciones;
import java.util.List;

public class ErrorResponse {

    private String codigo;
    private String mensaje;
    private List<String> detalles;

    public ErrorResponse(String codigo, String mensaje, List<String> detalles) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public String getCodigo() {
        return codigo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public List<String> getDetalles() {
        return detalles;
    }

}
