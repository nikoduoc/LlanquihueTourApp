package data;

import java.util.ArrayList;
import java.util.List;
import model.ColaboradorExterno;
import model.GuiaTuristico;
import model.Registrable;
import model.Vehiculo;

/**
 * Gestiona la colección genérica de entidades de la agencia. Almacena cualquier
 * objeto que implemente {@link Registrable} y recorre la colección aplicando el
 * operador {@code instanceof} para diferenciar el comportamiento según el tipo
 * concreto de cada entidad.
 */
public class GestorEntidades {

    private final List<Registrable> entidades;

    /** Crea el gestor con una colección vacía. */
    public GestorEntidades() {
        this.entidades = new ArrayList<>();
    }

    /** Agrega una entidad a la colección (ignora null).
     * @param entidad entidad a registrar */
    public void agregar(Registrable entidad) {
        if (entidad != null) {
            entidades.add(entidad);
        }
    }

    /** @return una copia de la colección de entidades */
    public List<Registrable> obtenerTodas() {
        return new ArrayList<>(entidades);
    }

    /** @return la cantidad de entidades registradas */
    public int cantidad() {
        return entidades.size();
    }

    /** Carga algunas entidades de ejemplo combinando las distintas clases. */
    public void cargarEjemplos() {
        agregar(new GuiaTuristico("María González", "Español/Inglés", 8));
        agregar(new Vehiculo("Mercedes Sprinter", "GHJK-45", 19));
        agregar(new ColaboradorExterno("Transporte Los Lagos", "TransLagos SpA", "Traslados privados"));
    }

    /**
     * Recorre la colección con un bucle for-each y construye un reporte,
     * usando {@code instanceof} para identificar el tipo específico de cada
     * entidad y agregar una línea de detalle diferenciada según su clase.
     * @return el reporte con el resumen y el detalle de cada entidad
     */
    public String generarReporte() {
        if (entidades.isEmpty()) {
            return "(No hay entidades registradas)";
        }

        StringBuilder reporte = new StringBuilder();
        int indice = 1;

        for (Registrable entidad : entidades) {
            reporte.append(indice).append(". ").append(entidad.mostrarResumen()).append("\n");

            // Diferenciación de comportamiento según el tipo concreto.
            if (entidad instanceof GuiaTuristico guia) {
                reporte.append("   -> Recurso humano: apto para liderar tours en ")
                        .append(guia.getIdiomas()).append(".\n");
            } else if (entidad instanceof Vehiculo vehiculo) {
                reporte.append("   -> Recurso de transporte para ")
                        .append(vehiculo.getCapacidad()).append(" pasajeros.\n");
            } else if (entidad instanceof ColaboradorExterno colaborador) {
                reporte.append("   -> Servicio tercerizado prestado por ")
                        .append(colaborador.getEmpresa()).append(".\n");
            }

            indice++;
        }

        return reporte.toString();
    }

    /** Imprime por consola el reporte generado con {@link #generarReporte()}. */
    public void imprimirEnConsola() {
        System.out.println(generarReporte());
    }
}
