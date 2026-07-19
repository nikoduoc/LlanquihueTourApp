package model;

/**
 * Datos de contacto de una persona. Se utiliza por composición dentro de
 * {@link Persona}.
 */
public class Contacto {

    private String email;
    private String telefono;

    /**
     * Crea un contacto.
     * @param email correo electrónico
     * @param telefono número de teléfono
     */
    public Contacto(String email, String telefono) {
        this.email = email;
        this.telefono = telefono;
    }

    /** Getters y setters */

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /** @return el contacto como texto */
    @Override
    public String toString() {
        return email + " / " + telefono;
    }
}
