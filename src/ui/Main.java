package ui;

import javax.swing.SwingUtilities;
import data.GestorEntidades;

/**
 * Clase principal del sistema Llanquihue Tour. Crea la colección de entidades,
 * muestra una demostración por consola del recorrido polimórfico con
 * {@code instanceof} y lanza la interfaz gráfica de gestión.
 */
public class Main {

    /** Constructor privado: solo se usa el método main. */
    private Main() {
    }

    /** Punto de entrada del programa.
     * @param args */
    public static void main(String[] args) {

        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        // Colección compartida entre la demostración por consola y la GUI.
        GestorEntidades gestor = new GestorEntidades();
        gestor.cargarEjemplos();

        // Demostración por consola del recorrido con diferenciación por tipo.
        System.out.println("============================================================");
        System.out.println("LLANQUIHUE TOUR - GESTIÓN DE ENTIDADES (consola)");
        System.out.println("============================================================");
        gestor.imprimirEnConsola();

        // Interfaz gráfica para ingresar y visualizar entidades.
        SwingUtilities.invokeLater(() -> new VentanaPrincipal(gestor).setVisible(true));
    }
}
