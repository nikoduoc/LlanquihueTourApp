package model;

/**
 * Vehículo de la flota de la agencia. Hereda de {@link RecursoAgencia} e
 * implementa {@link Registrable} mediante {@link #mostrarResumen()}.
 */
public class Vehiculo extends RecursoAgencia {

    private String patente;
    private int capacidad;

    /**
     * Crea un vehículo.
     * @param nombre modelo o descripción del vehículo
     * @param patente patente del vehículo
     * @param capacidad cantidad de pasajeros que admite
     */
    public Vehiculo(String nombre, String patente, int capacidad) {
        super(nombre);
        this.patente = patente;
        this.capacidad = capacidad;
    }

    /** Getters y setters */

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /** @return el resumen especializado del vehículo */
    @Override
    public String mostrarResumen() {
        return "Vehículo: " + getNombre()
                + " | Patente: " + patente
                + " | Capacidad: " + capacidad + " pasajeros";
    }
}
