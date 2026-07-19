package model;

/**
 * Empleado de la agencia (por ejemplo, un guía turístico). Hereda de
 * {@link Persona} y agrega su cargo y los idiomas que domina.
 */
public class Empleado extends Persona {

    private String cargo;
    private String idiomas;

    /**
     * Crea un empleado.
     * @param nombre nombre completo
     * @param rut RUT validado
     * @param direccion dirección
     * @param contacto datos de contacto
     * @param cargo cargo que desempeña
     * @param idiomas idiomas que domina
     */
    public Empleado(String nombre, Rut rut, Direccion direccion, Contacto contacto,
                    String cargo, String idiomas) {
        super(nombre, rut, direccion, contacto);
        this.cargo = cargo;
        this.idiomas = idiomas;
    }

    /** Getters y setters */

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    /** @return los datos del empleado */
    @Override
    public String mostrarDatos() {
        return "EMPLEADO   | " + getNombre()
                + " | RUT: " + getRut()
                + " | Cargo: " + cargo
                + " | Idiomas: " + idiomas
                + " | " + getContacto();
    }
}
