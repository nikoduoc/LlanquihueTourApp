package model;

/**
 * Ruta gastronómica. Hereda de ServicioTuristico y agrega el número de paradas.
 */
public class RutaGastronomica extends ServicioTuristico {

    private int numeroDeParadas;

    /**
     * Crea una ruta gastronómica con sus datos y atributo propio.
     * @param nombre nombre de la ruta
     * @param duracionHoras duración en horas
     * @param numeroDeParadas cantidad de paradas de la ruta
     */
    public RutaGastronomica(String nombre, int duracionHoras, int numeroDeParadas) {
        super(nombre, duracionHoras);
        this.numeroDeParadas = numeroDeParadas;
    }

    /** Getter y setter */

    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }

    public void setNumeroDeParadas(int numeroDeParadas) {
        this.numeroDeParadas = numeroDeParadas;
    }

    /** @return la ruta gastronómica como texto */
    @Override
    public String toString() {
        return super.toString() + " | Ruta gastronómica | Paradas: " + numeroDeParadas;
    }

    /** Muestra la información de la ruta gastronómica con su despliegue propio. */
    @Override
    public void mostrarInformacion() {
        System.out.println("RUTA GASTRONÓMICA: " + getNombre());
        System.out.println("   Duración: " + getDuracionHoras() + " horas | Paradas: " + numeroDeParadas);
    }
}
