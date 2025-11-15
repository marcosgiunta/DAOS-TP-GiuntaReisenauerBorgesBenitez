package tuti.daos.Excepciones;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // Validaciones @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        ErrorResponse error = new ErrorResponse(
                "VALIDATION_ERROR",
                "Errores de validación",
                errores
        );

        return ResponseEntity.badRequest().body(error);
    }

    // Tu excepción personalizada
    @ExceptionHandler(Excepcion.class)
    public ResponseEntity<ErrorResponse> handleApi(Excepcion ex) {

        ErrorResponse error = new ErrorResponse(
                ex.getCodigo(),
                ex.getMensaje(),
                null
        );

        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    // Errores generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {

        ErrorResponse error = new ErrorResponse(
                "SERVER_ERROR",
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
