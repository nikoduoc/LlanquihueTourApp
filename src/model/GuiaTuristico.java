package model;

/**
 * Guía turístico de la agencia. Hereda de {@link RecursoAgencia} e implementa
 * {@link Registrable} mediante {@link #mostrarResumen()}.
 */
public class GuiaTuristico extends RecursoAgencia {

    private String idiomas;
    private int aniosExperiencia;

    /**
     * Crea un guía turístico.
     * @param nombre nombre del guía
     * @param idiomas idiomas que domina
     * @param aniosExperiencia años de experiencia
     */
    public GuiaTuristico(String nombre, String idiomas, int aniosExperiencia) {
        super(nombre);
        this.idiomas = idiomas;
        this.aniosExperiencia = aniosExperiencia;
    }

    /** Getters y setters */

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    /** @return el resumen especializado del guía turístico */
    @Override
    public String mostrarResumen() {
        return "Guía turístico: " + getNombre()
                + " | Idiomas: " + idiomas
                + " | Experiencia: " + aniosExperiencia + " años";
    }
}
