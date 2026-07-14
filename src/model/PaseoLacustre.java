package model;

/**
 * Paseo lacustre. Hereda de ServicioTuristico y agrega el tipo de embarcación.
 */
public class PaseoLacustre extends ServicioTuristico {

    private String tipoEmbarcacion;

    /**
     * Crea un paseo lacustre con sus datos y atributo propio.
     * @param nombre nombre del paseo
     * @param duracionHoras duración en horas
     * @param tipoEmbarcacion tipo de embarcación utilizada
     */
    public PaseoLacustre(String nombre, int duracionHoras, String tipoEmbarcacion) {
        super(nombre, duracionHoras);
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    /** Getter y setter */

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    /** @return el paseo lacustre como texto */
    @Override
    public String toString() {
        return super.toString() + " | Paseo lacustre | Embarcación: " + tipoEmbarcacion;
    }

    /** Muestra la información del paseo lacustre con su despliegue propio. */
    @Override
    public void mostrarInformacion() {
        System.out.println("PASEO LACUSTRE: " + getNombre());
        System.out.println("   Duración: " + getDuracionHoras() + " horas | Embarcación: " + tipoEmbarcacion);
    }
}
