package model;

import utils.ValidacionException;
import utils.Validador;

/**
 * Representa el RUT de una persona. Valida su formato y dígito verificador al
 * construirse, lanzando una excepción personalizada si el dato no es válido.
 * Se utiliza por composición dentro de {@link Persona}.
 */
public class Rut {

    private final String valor;

    /**
     * Crea un RUT validado.
     * @param valor RUT en formato 12345678-9
     * @throws ValidacionException si el formato o el dígito verificador son inválidos
     */
    public Rut(String valor) throws ValidacionException {
        this.valor = Validador.validarRut(valor);
    }

    /** @return el RUT normalizado */
    public String getValor() {
        return valor;
    }

    /** @return el RUT como texto */
    @Override
    public String toString() {
        return valor;
    }
}
