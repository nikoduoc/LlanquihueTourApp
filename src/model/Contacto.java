package model;

/**
 * Datos de contacto de un colaborador (composición dentro de Colaborador).
 */
public class Contacto {

    private String email;
    private String telefono;

    /**
     * Crea un contacto con su correo y teléfono.
     * @param email
     * @param telefono 
     */
    public Contacto(String email, String telefono) {
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * Getter y Setters
     */
    
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
        return email + " | Tel: " + telefono;
    }
}
