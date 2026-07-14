package model;

/**
 * Contrato de comportamiento común para todas las entidades gestionables de la
 * agencia. Cualquier clase que implemente esta interfaz puede ser almacenada y
 * tratada de forma unificada dentro de una misma colección.
 */
public interface Registrable {

    /**
     * Devuelve un resumen textual de la entidad, personalizado según su tipo.
     * @return el resumen de la entidad
     */
    String mostrarResumen();
}
