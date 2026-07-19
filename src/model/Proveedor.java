package model;

/**
 * Proveedor externo de la agencia (por ejemplo, transporte o alojamiento).
 * Hereda de {@link Persona} y agrega la empresa y el rubro que representa.
 */
public class Proveedor extends Persona {

    private String empresa;
    private String rubro;

    /**
     * Crea un proveedor.
     * @param nombre nombre de contacto
     * @param rut RUT validado
     * @param direccion dirección
     * @param contacto datos de contacto
     * @param empresa empresa que representa
     * @param rubro rubro del servicio (transporte, alojamiento, etc.)
     */
    public Proveedor(String nombre, Rut rut, Direccion direccion, Contacto contacto,
                     String empresa, String rubro) {
        super(nombre, rut, direccion, contacto);
        this.empresa = empresa;
        this.rubro = rubro;
    }

    /** Getters y setters */

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    /** @return los datos del proveedor */
    @Override
    public String mostrarDatos() {
        return "PROVEEDOR  | " + getNombre()
                + " | RUT: " + getRut()
                + " | Empresa: " + empresa
                + " | Rubro: " + rubro
                + " | " + getContacto();
    }
}
