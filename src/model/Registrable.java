package model;

/**
 * Contrato de comportamiento común para toda entidad que pueda ser registrada y
 * consultada dentro del sistema de Llanquihue Tour. Permite tratar de forma
 * unificada (polimórfica) a personas y servicios turísticos.
 */
public interface Registrable {

    /** Registra la entidad en el sistema. */
    void registrar();

    /**
     * Devuelve los datos de la entidad en formato de texto, personalizados
     * según su tipo concreto.
     * @return los datos de la entidad
     */
    String mostrarDatos();
}
