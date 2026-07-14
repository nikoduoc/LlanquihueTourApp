package data;

import java.util.ArrayList;
import java.util.List;
import model.ExcursionCultural;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.ServicioTuristico;

/**
 * Gestiona la colección polimórfica de servicios turísticos.
 * Declara una List&lt;ServicioTuristico&gt; que almacena instancias de las
 * distintas subclases y ofrece el acceso y recorrido de la colección.
 */
public class GestorServicios {

    private final List<ServicioTuristico> servicios;

    /** Crea el gestor y carga la colección con objetos de prueba. */
    public GestorServicios() {
        this.servicios = new ArrayList<>();
        cargarServicios();
    }

    /**
     * Carga en la lista al menos cinco servicios combinando instancias de las
     * distintas subclases. Los objetos se crean manualmente (no se leen desde
     * archivo).
     */
    private void cargarServicios() {
        servicios.add(new RutaGastronomica("Sabores de Llanquihue", 4, 5));
        servicios.add(new RutaGastronomica("Ruta del Chocolate", 3, 3));
        servicios.add(new PaseoLacustre("Travesía Lago Llanquihue", 2, "Catamarán"));
        servicios.add(new PaseoLacustre("Atardecer en el Lago", 3, "Lancha"));
        servicios.add(new ExcursionCultural("Colonos Alemanes", 5, "Museo Colonial Alemán de Frutillar"));
        servicios.add(new ExcursionCultural("Patrimonio de Chiloé", 6, "Iglesias de Chiloé"));
    }

    /** @return una copia de la lista de servicios turísticos */
    public List<ServicioTuristico> obtenerServicios() {
        return new ArrayList<>(servicios);
    }

    /** @return la cantidad de servicios en la colección */
    public int cantidad() {
        return servicios.size();
    }

    /**
     * Recorre la colección de forma polimórfica e invoca mostrarInformacion()
     * desde la referencia de la superclase ServicioTuristico. Cada objeto
     * ejecuta la versión sobrescrita según su tipo real.
     */
    public void mostrarTodos() {
        for (ServicioTuristico s : servicios) {
            s.mostrarInformacion();
            System.out.println();
        }
    }
}
