package model;

/**
 * Dirección de una persona. Se utiliza por composición dentro de
 * {@link Persona}.
 */
public class Direccion {

    private String calle;
    private String comuna;
    private String region;

    /**
     * Crea una dirección.
     * @param calle calle y número
     * @param comuna comuna
     * @param region región
     */
    public Direccion(String calle, String comuna, String region) {
        this.calle = calle;
        this.comuna = comuna;
        this.region = region;
    }

    /** Getters y setters */

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    /** @return la dirección como texto */
    @Override
    public String toString() {
        return calle + ", " + comuna + ", " + region;
    }
}
