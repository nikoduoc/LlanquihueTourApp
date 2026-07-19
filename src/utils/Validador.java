package utils;

/**
 * Utilidad de validación de atributos del sistema. Todos sus métodos lanzan
 * {@link ValidacionException} cuando el dato no cumple las reglas esperadas.
 */
public final class Validador {

    /** Clase utilitaria: no se instancia. */
    private Validador() {
    }

    /**
     * Valida que un texto no sea nulo ni vacío.
     * @param campo nombre del campo (para el mensaje de error)
     * @param valor valor a validar
     * @return el texto sin espacios sobrantes
     * @throws ValidacionException si el texto es nulo o vacío
     */
    public static String validarTextoNoVacio(String campo, String valor) throws ValidacionException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ValidacionException("El campo '" + campo + "' no puede estar vacío.");
        }
        return valor.trim();
    }

    /**
     * Valida que un valor represente un número entero positivo.
     * @param campo nombre del campo
     * @param valor valor a validar
     * @return el número convertido
     * @throws ValidacionException si no es numérico o es negativo
     */
    public static int validarEnteroPositivo(String campo, String valor) throws ValidacionException {
        try {
            int numero = Integer.parseInt(valor.trim());
            if (numero < 0) {
                throw new ValidacionException("El campo '" + campo + "' debe ser un número positivo.");
            }
            return numero;
        } catch (NumberFormatException e) {
            throw new ValidacionException("El campo '" + campo + "' debe ser numérico: '" + valor + "'.");
        }
    }

    /**
     * Valida el formato de un correo electrónico.
     * @param valor correo a validar
     * @return el correo sin espacios sobrantes
     * @throws ValidacionException si el formato no es válido
     */
    public static String validarEmail(String valor) throws ValidacionException {
        String correo = validarTextoNoVacio("email", valor);
        if (!correo.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.-]+$")) {
            throw new ValidacionException("El correo '" + correo + "' no tiene un formato válido.");
        }
        return correo;
    }

    /**
     * Valida el formato y el dígito verificador de un RUT (módulo 11).
     * @param valor RUT a validar (formato 12345678-9)
     * @return el RUT normalizado (sin puntos y en mayúscula)
     * @throws ValidacionException si el formato o el dígito verificador son inválidos
     */
    public static String validarRut(String valor) throws ValidacionException {
        String rut = validarTextoNoVacio("rut", valor).replace(".", "").toUpperCase();
        if (!rut.matches("^\\d{7,8}-[\\dK]$")) {
            throw new ValidacionException("El RUT '" + valor + "' no tiene el formato 12345678-9.");
        }
        String[] partes = rut.split("-");
        if (!calcularDigitoVerificador(partes[0]).equals(partes[1])) {
            throw new ValidacionException("El RUT '" + valor + "' tiene un dígito verificador inválido.");
        }
        return rut;
    }

    /**
     * Calcula el dígito verificador de un RUT según el algoritmo de módulo 11.
     * @param numero cuerpo del RUT (sin dígito verificador)
     * @return el dígito verificador ("0"-"9" o "K")
     */
    private static String calcularDigitoVerificador(String numero) {
        int suma = 0;
        int factor = 2;
        for (int i = numero.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(numero.charAt(i)) * factor;
            factor = (factor == 7) ? 2 : factor + 1;
        }
        int resto = 11 - (suma % 11);
        if (resto == 11) {
            return "0";
        }
        if (resto == 10) {
            return "K";
        }
        return String.valueOf(resto);
    }
}
