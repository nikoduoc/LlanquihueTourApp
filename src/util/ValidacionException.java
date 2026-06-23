package util;

/**
 * Excepción que se lanza cuando un dato no cumple las reglas de validación.
 */
public class ValidacionException extends Exception {

    /** Crea la excepción con un mensaje descriptivo.
     * @param mensaje */
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}
