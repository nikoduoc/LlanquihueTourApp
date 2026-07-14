package model;

/**
 * Superclase de la jerarquía de servicios turísticos de Llanquihue Tour.
 * Define los atributos comunes a todo servicio (nombre y duración) y declara
 * el comportamiento polimórfico mediante el método abstracto
 * {@link #mostrarInformacion()}.
 */
public abstract class ServicioTuristico {

    private String nombre;
    private int duracionHoras;

    /**
     * Crea un servicio turístico con sus datos comunes.
     * @param nombre nombre del servicio
     * @param duracionHoras duración del servicio en horas
     */
    public ServicioTuristico(String nombre, int duracionHoras) {
        this.nombre = nombre;
        this.duracionHoras = duracionHoras;
    }

    /** Getters y setters */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    /**
     * Muestra por consola la información del servicio.
     * Método abstracto: cada subclase lo sobrescribe con su despliegue
     * específico, permitiendo recorrer la colección de forma polimórfica.
     */
    public abstract void mostrarInformacion();

    /** @return los datos comunes del servicio como texto */
    @Override
    public String toString() {
        return nombre + " (" + duracionHoras + " h)";
    }
}
