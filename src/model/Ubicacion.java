package model;

/**
 * Ubicación geográfica de un colaborador (composición dentro de Colaborador).
 */
public class Ubicacion {

    private String comuna;
    private String region;

    /**
     * Crea una ubicación con su comuna y región.
     * @param comuna
     * @param region 
     */
    public Ubicacion(String comuna, String region) {
        this.comuna = comuna;
        this.region = region;
    }

    /** Getter y Setters  */
    
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
    
    /** @return la ubicación como texto */
    @Override
    public String toString() {
        return comuna + ", " + region;
    }
}
