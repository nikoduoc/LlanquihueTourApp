package model;

/**
 * Operador turístico. Hereda de Colaborador y agrega empresa y experiencia.
 */
public class Operador extends Colaborador {

    private String empresa;
    private int anosExperiencia;

    /**
     * Crea un operador con sus datos y atributos propios.
     * @param nombre
     * @param rut
     * @param contacto
     * @param ubicacion
     * @param empresa
     * @param anosExperiencia 
     */
    public Operador(String nombre, String rut, Contacto contacto, Ubicacion ubicacion,
                    String empresa, int anosExperiencia) {
        super("Operador", nombre, rut, contacto, ubicacion);
        this.empresa = empresa;
        this.anosExperiencia = anosExperiencia;
    }

    /** Getter y Setters  */

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }
    
    /** @return el operador como texto */
    @Override
    public String toString() {
        return super.toString() + " | Empresa: " + empresa + " | Experiencia: " + anosExperiencia + " años";
    }

    /** Muestra la información del operador con su despliegue propio. */
    @Override
    public void mostrarInformacion() {
        System.out.println("OPERADOR TURÍSTICO: " + getNombre() + " (RUT: " + getRut() + ")");
        System.out.println("   Empresa: " + empresa + " | Experiencia: " + anosExperiencia + " años");
        System.out.println("   Ubicación: " + getUbicacion() + " | Contacto: " + getContacto());
    }
}
