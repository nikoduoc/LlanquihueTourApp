

package model;

/**
 * Clase base común de las entidades gestionables por la agencia (guías,
 * vehículos y colaboradores externos). Implementa {@link Registrable} y
 * concentra el atributo común {@code nombre}, dejando el método
 * {@link #mostrarResumen()} para que cada subclase lo especialice.
 */
public abstract class RecursoAgencia implements Registrable {

    private String nombre;

    /**
     * Crea un recurso de la agencia con su nombre identificador.
     * @param nombre nombre o descripción del recurso
     */
    public RecursoAgencia(String nombre) {
        this.nombre = nombre;
    }

    /** Getter y setter */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return el nombre del recurso como texto */
    @Override
    public String toString() {
        return nombre;
    }
}
