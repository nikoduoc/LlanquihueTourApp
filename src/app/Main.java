package app;

import javax.swing.SwingUtilities;
import data.CargadorPersonas;
import data.GestorPersonas;
import data.GestorServicios;

/**
 * Clase principal del sistema Llanquihue Tour. Carga las personas desde un
 * archivo de texto hacia la colección, prepara el catálogo de servicios y lanza
 * la interfaz gráfica de gestión.
 */
public class Main {

    /** Ruta del archivo de datos, relativa a la carpeta del proyecto. */
    private static final String ARCHIVO_PERSONAS = "files/personas.txt";

    /** Constructor privado: solo se usa el método main. */
    private Main() {
    }

    /** Punto de entrada del programa.
     * @param args */
    public static void main(String[] args) {

        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        // 1. Carga de personas desde el archivo hacia la colección.
        GestorPersonas gestorPersonas = new GestorPersonas();
        gestorPersonas.agregarTodas(new CargadorPersonas().cargar(ARCHIVO_PERSONAS));
        System.out.println("Se cargaron " + gestorPersonas.cantidad()
                + " personas desde '" + ARCHIVO_PERSONAS + "'.");

        // 2. Catálogo de servicios turísticos (paquetes de la agencia).
        GestorServicios gestorServicios = new GestorServicios();

        // 3. Lanzamiento de la interfaz gráfica.
        SwingUtilities.invokeLater(()
                -> new VentanaPrincipal(gestorPersonas, gestorServicios).setVisible(true));
    }
}
