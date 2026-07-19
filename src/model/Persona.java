package model;

/**
 * Superclase abstracta de las personas del sistema (clientes, empleados y
 * proveedores). Implementa {@link Registrable} y aplica composición al contener
 * un {@link Rut}, una {@link Direccion} y un {@link Contacto}.
 */
public abstract class Persona implements Registrable {

    private String nombre;
    private Rut rut;
    private Direccion direccion;
    private Contacto contacto;

    /**
     * Crea una persona con sus datos comunes.
     * @param nombre nombre completo
     * @param rut RUT validado
     * @param direccion dirección
     * @param contacto datos de contacto
     */
    public Persona(String nombre, Rut rut, Direccion direccion, Contacto contacto) {
        this.nombre = nombre;
        this.rut = rut;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    /** Getters y setters */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rut getRut() {
        return rut;
    }

    public void setRut(Rut rut) {
        this.rut = rut;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    /** Deja constancia del registro de la persona en el sistema. */
    @Override
    public void registrar() {
        System.out.println("Registrando " + getClass().getSimpleName() + ": " + nombre
                + " (RUT: " + rut + ")");
    }

    /** Cada subclase entrega sus datos con el detalle propio de su tipo. */
    @Override
    public abstract String mostrarDatos();

    /** @return los datos comunes de la persona como texto */
    @Override
    public String toString() {
        return nombre + " (RUT: " + rut + ")";
    }
}
