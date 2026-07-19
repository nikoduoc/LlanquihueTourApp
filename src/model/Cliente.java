package model;

/**
 * Cliente de la agencia. Hereda de {@link Persona} y agrega su preferencia de
 * tour y la cantidad de reservas realizadas.
 */
public class Cliente extends Persona {

    private String preferencia;
    private int numeroReservas;

    /**
     * Crea un cliente.
     * @param nombre nombre completo
     * @param rut RUT validado
     * @param direccion dirección
     * @param contacto datos de contacto
     * @param preferencia tipo de tour preferido
     * @param numeroReservas cantidad de reservas realizadas
     */
    public Cliente(String nombre, Rut rut, Direccion direccion, Contacto contacto,
                   String preferencia, int numeroReservas) {
        super(nombre, rut, direccion, contacto);
        this.preferencia = preferencia;
        this.numeroReservas = numeroReservas;
    }

    /** Getters y setters */

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public int getNumeroReservas() {
        return numeroReservas;
    }

    public void setNumeroReservas(int numeroReservas) {
        this.numeroReservas = numeroReservas;
    }

    /** @return los datos del cliente */
    @Override
    public String mostrarDatos() {
        return "CLIENTE    | " + getNombre()
                + " | RUT: " + getRut()
                + " | Preferencia: " + preferencia
                + " | Reservas: " + numeroReservas
                + " | " + getContacto();
    }
}
