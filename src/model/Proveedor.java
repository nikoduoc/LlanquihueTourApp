package model;

/**
 * Proveedor de servicios. Hereda de Colaborador y agrega rubro y servicio.
 */
public class Proveedor extends Colaborador {

    private String rubro;
    private String servicio;

    /**
     * Crea un proveedor con sus datos y atributos propios.
     * @param nombre
     * @param rut
     * @param contacto
     * @param ubicacion
     * @param rubro
     * @param servicio 
     */
    public Proveedor(String nombre, String rut, Contacto contacto, Ubicacion ubicacion,
                     String rubro, String servicio) {
        super("Proveedor", nombre, rut, contacto, ubicacion);
        this.rubro = rubro;
        this.servicio = servicio;
    }

    /** Getter y Setters  */
    
    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /** @return el proveedor como texto */
    @Override
    public String toString() {
        return super.toString() + " | Rubro: " + rubro + " | Servicio: " + servicio;
    }

    /** Muestra la información del proveedor con su despliegue propio. */
    @Override
    public void mostrarInformacion() {
        System.out.println("PROVEEDOR: " + getNombre() + " (RUT: " + getRut() + ")");
        System.out.println("   Rubro: " + rubro + " | Servicio: " + servicio);
        System.out.println("   Ubicación: " + getUbicacion() + " | Contacto: " + getContacto());
    }
}
