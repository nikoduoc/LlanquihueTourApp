package model;

/**
 * Clase base de la App de una persona vinculada a la agencia.
 */
public abstract class Colaborador {

    private String tipo;
    private String nombre;
    private String rut;
    private Contacto contacto;
    private Ubicacion ubicacion;

    /**
     * Crea un colaborador con sus datos y objetos compuestos
     * @param tipo
     * @param nombre
     * @param rut
     * @param contacto
     * @param ubicacion 
     */
    public Colaborador(String tipo, String nombre, String rut, Contacto contacto, Ubicacion ubicacion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.rut = rut;
        this.contacto = contacto;
        this.ubicacion = ubicacion;
    }

    
    /**
     * Getter y Setters
     */
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Muestra por consola la información del colaborador.
     * Método abstracto: cada subclase lo sobrescribe con su despliegue
     * específico, permitiendo un recorrido polimórfico de la colección.
     */
    public abstract void mostrarInformacion();

    /** @return los datos comunes del colaborador como texto */
    @Override
    public String toString() {
        return String.format("[%s] %s (RUT: %s) - %s - %s",
                tipo, nombre, rut, ubicacion, contacto);
    }
}
