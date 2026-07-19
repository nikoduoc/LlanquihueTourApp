package utils;

/**
 * Excepción personalizada que se lanza cuando un atributo no supera las reglas
 * de validación del sistema (por ejemplo, un RUT o un correo con formato
 * inválido).
 */
public class ValidacionException extends Exception {

    /**
     * Crea la excepción con un mensaje descriptivo.
     * @param mensaje detalle del error de validación
     */
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
