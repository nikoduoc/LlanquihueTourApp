package model;

/**
 * Colaborador externo que presta servicios tercerizados a la agencia. Hereda de
 * {@link RecursoAgencia} e implementa {@link Registrable} mediante
 * {@link #mostrarResumen()}.
 */
public class ColaboradorExterno extends RecursoAgencia {

    private String empresa;
    private String servicio;

    /**
     * Crea un colaborador externo.
     * @param nombre nombre del colaborador o de la persona de contacto
     * @param empresa empresa a la que pertenece
     * @param servicio servicio que presta
     */
    public ColaboradorExterno(String nombre, String empresa, String servicio) {
        super(nombre);
        this.empresa = empresa;
        this.servicio = servicio;
    }

    /** Getters y setters */

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /** @return el resumen especializado del colaborador externo */
    @Override
    public String mostrarResumen() {
        return "Colaborador externo: " + getNombre()
                + " | Empresa: " + empresa
                + " | Servicio: " + servicio;
    }
}
