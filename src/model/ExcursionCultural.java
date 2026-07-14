package model;

/**
 * Excursión cultural. Hereda de ServicioTuristico y agrega el lugar histórico.
 */
public class ExcursionCultural extends ServicioTuristico {

    private String lugarHistorico;

    /**
     * Crea una excursión cultural con sus datos y atributo propio.
     * @param nombre nombre de la excursión
     * @param duracionHoras duración en horas
     * @param lugarHistorico lugar histórico que se visita
     */
    public ExcursionCultural(String nombre, int duracionHoras, String lugarHistorico) {
        super(nombre, duracionHoras);
        this.lugarHistorico = lugarHistorico;
    }

    /** Getter y setter */

    public String getLugarHistorico() {
        return lugarHistorico;
    }

    public void setLugarHistorico(String lugarHistorico) {
        this.lugarHistorico = lugarHistorico;
    }

    /** @return la excursión cultural como texto */
    @Override
    public String toString() {
        return super.toString() + " | Excursión cultural | Lugar: " + lugarHistorico;
    }

    /** Muestra la información de la excursión cultural con su despliegue propio. */
    @Override
    public void mostrarInformacion() {
        System.out.println("EXCURSIÓN CULTURAL: " + getNombre());
        System.out.println("   Duración: " + getDuracionHoras() + " horas | Lugar histórico: " + lugarHistorico);
    }
}
