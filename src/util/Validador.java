package util;

/**
 * Métodos estáticos para validar los datos de entrada.
 * Cada método lanza ValidacionException si el dato es inválido.
 */
public final class Validador {

    private static final String PATRON_EMAIL = "^[\\w.+-]+@[\\w-]+\\.[\\w.-]+$";
    private static final String PATRON_RUT = "^\\d{7,8}-[\\dkK]$";

    /** Constructor privado: clase de utilidad, no se instancia. */
    private Validador() {
    }

    /**
     * Valida que el texto no esté vacío y lo devuelve sin espacios.
     * @param campo
     * @param valor
     * @return
     * @throws ValidacionException 
     */
    public static String validarTextoNoVacio(String campo, String valor) throws ValidacionException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ValidacionException("El campo '" + campo + "' no puede estar vacío.");
        }
        return valor.trim();
    }

    /**
     * Valida el formato y el dígito verificador del RUT.
     * @param rut
     * @return
     * @throws ValidacionException 
     */
    public static String validarRut(String rut) throws ValidacionException {
        if (rut == null) {
            throw new ValidacionException("El RUT no puede ser nulo.");
        }
        String limpio = rut.trim().toUpperCase().replace(".", "");
        if (!limpio.matches(PATRON_RUT)) {
            throw new ValidacionException("RUT con formato inválido: '" + rut + "'.");
        }
        String[] partes = limpio.split("-");
        char dvCalculado = calcularDigitoVerificador(partes[0]);
        if (partes[1].charAt(0) != dvCalculado) {
            throw new ValidacionException("RUT inválido: el dígito verificador de '" + rut
                    + "' debería ser '" + dvCalculado + "'.");
        }
        return limpio;
    }

    /**
     * Calcula el dígito verificador del RUT.
     * @param cuerpo
     * @return 
     */
    private static char calcularDigitoVerificador(String cuerpo) {
        int suma = 0;
        int multiplicador = 2;
        for (int i = cuerpo.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(cuerpo.charAt(i)) * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
        }
        int resto = 11 - (suma % 11);
        if (resto == 11) {
            return '0';
        }
        if (resto == 10) {
            return 'K';
        }
        return (char) ('0' + resto);
    }

    /**
     * Valida el formato del correo electrónico y lo devuelve sin espacios.
     * @param email
     * @return
     * @throws ValidacionException 
     */
    public static String validarEmail(String email) throws ValidacionException {
        if (email == null || !email.trim().matches(PATRON_EMAIL)) {
            throw new ValidacionException("Correo electrónico inválido: '" + email + "'.");
        }
        return email.trim();
    }

    /**
     * Valida que el texto sea un entero mayor o igual a cero.
     * @param campo
     * @param valor
     * @return
     * @throws ValidacionException 
     */
    public static int validarEnteroPositivo(String campo, String valor) throws ValidacionException {
        try {
            int numero = Integer.parseInt(valor.trim());
            if (numero < 0) {
                throw new ValidacionException("El campo '" + campo + "' no puede ser negativo.");
            }
            return numero;
        } catch (NumberFormatException e) {
            throw new ValidacionException("El campo '" + campo + "' debe ser un número: '" + valor + "'.");
        }
    }
}
