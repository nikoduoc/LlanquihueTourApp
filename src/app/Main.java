package app;

import java.util.List;
import model.Colaborador;
import service.CargadorColaboradores;
import service.GestorColaboradores;

/**
 * Clase principal: carga los colaboradores desde el archivo, los muestra por
 * consola y ejecuta búsquedas y filtros simples.
 */
public class Main {

    /** Ruta del archivo de datos, relativa a la carpeta del proyecto. */
    private static final String ARCHIVO_DATOS = "files/colaboradores.txt";

    /** Constructor privado: solo se usa el método main. */
    private Main() {
    }

    /** Punto de entrada del programa.
     * @param args */
    public static void main(String[] args) {

        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        imprimirTitulo("LLANQUIHUE TOUR - GESTIÓN DE COLABORADORES");

        // 1. Carga de datos desde el archivo hacia la colección.
        CargadorColaboradores cargador = new CargadorColaboradores();
        List<Colaborador> cargados = cargador.cargarDesdeArchivo(ARCHIVO_DATOS);

        GestorColaboradores gestor = new GestorColaboradores();
        gestor.agregarTodos(cargados);

        System.out.println("\nSe cargaron " + gestor.cantidad() + " colaboradores desde '"
                + ARCHIVO_DATOS + "'.");

        // 2. Visualización de todos los colaboradores.
        imprimirTitulo("LISTADO COMPLETO DE COLABORADORES");
        mostrarLista(gestor.obtenerTodos());

        // 3. Filtro por tipo.
        imprimirTitulo("FILTRO POR TIPO: GUÍA");
        mostrarLista(gestor.filtrarPorTipo("Guía"));

        // 4. Filtro por comuna.
        imprimirTitulo("FILTRO POR COMUNA: PUERTO VARAS");
        mostrarLista(gestor.filtrarPorComuna("Puerto Varas"));

        // 5. Búsqueda por nombre.
        imprimirTitulo("BÚSQUEDA POR NOMBRE QUE CONTENGA: 'so'");
        mostrarLista(gestor.buscarPorNombre("so"));

        System.out.println("\nProceso finalizado correctamente.");
    }

    /** Imprime un título destacado en la consola. */
    private static void imprimirTitulo(String titulo) {
        String separador = "=".repeat(60);
        System.out.println("\n" + separador);
        System.out.println(titulo);
        System.out.println(separador);
    }

    /** Muestra por consola una lista de colaboradores, numerando cada elemento. */
    private static void mostrarLista(List<Colaborador> lista) {
        if (lista.isEmpty()) {
            System.out.println("(Sin resultados)");
            return;
        }
        int indice = 1;
        for (Colaborador c : lista) {
            System.out.println(indice + ". " + c);
            indice++;
        }
        System.out.println("Total: " + lista.size() + " colaborador(es).");
    }
}
