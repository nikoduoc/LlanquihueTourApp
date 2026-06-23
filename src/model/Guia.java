package model;

/**
 * Guía turístico. Hereda de Colaborador y agrega idiomas y especialidad.
 */
public class Guia extends Colaborador {

    private String idiomas;
    private String especialidad;

    /**
     * Crea un guía con sus datos y atributos propios.
     * @param nombre
     * @param rut
     * @param contacto
     * @param ubicacion
     * @param idiomas
     * @param especialidad 
     */
    public Guia(String nombre, String rut, Contacto contacto, Ubicacion ubicacion,
                String idiomas, String especialidad) {
        super("Guía", nombre, rut, contacto, ubicacion);
        this.idiomas = idiomas;
        this.especialidad = especialidad;
    }

    /** Getter y Setters */
    
    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /** @return el guía como texto */
    @Override
    public String toString() {
        return super.toString() + " | Idiomas: " + idiomas + " | Especialidad: " + especialidad;
    }
}
