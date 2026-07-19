package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Contacto;
import model.Direccion;
import model.Empleado;
import model.Persona;
import model.Proveedor;
import model.Rut;
import utils.ValidacionException;
import utils.Validador;

/**
 * Clase utilitaria que lee un archivo de texto (.txt) con datos de personas y
 * los convierte en objetos del modelo. Las líneas con datos inválidos se
 * descartan de forma controlada, informando el motivo por consola.
 *
 * <p>Formato esperado (campos separados por ';'):</p>
 * <pre>tipo;nombre;rut;email;telefono;calle;comuna;region;extra1;extra2</pre>
 */
public class CargadorPersonas {

    private static final String SEPARADOR = ";";
    private static final int CAMPOS_ESPERADOS = 10;

    /**
     * Lee el archivo indicado y devuelve la lista de personas válidas.
     * @param rutaArchivo ruta del archivo .txt
     * @return lista de personas cargadas
     */
    public List<Persona> cargar(String rutaArchivo) {
        List<Persona> personas = new ArrayList<>();
        int numeroLinea = 0;

        try (BufferedReader lector = new BufferedReader(
                new FileReader(rutaArchivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                // Se ignoran el encabezado, las líneas vacías y los comentarios.
                if (numeroLinea == 1 || linea.trim().isEmpty() || linea.trim().startsWith("#")) {
                    continue;
                }
                try {
                    personas.add(parsearLinea(linea));
                } catch (ValidacionException e) {
                    System.out.println("  [Línea " + numeroLinea + " descartada] " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo '" + rutaArchivo + "': " + e.getMessage());
        }

        return personas;
    }

    /**
     * Convierte una línea del archivo en una persona validada.
     * @param linea línea del archivo
     * @return la persona construida
     * @throws ValidacionException si algún campo no es válido
     */
    private Persona parsearLinea(String linea) throws ValidacionException {
        String[] campos = linea.split(SEPARADOR);
        if (campos.length != CAMPOS_ESPERADOS) {
            throw new ValidacionException("Se esperaban " + CAMPOS_ESPERADOS
                    + " campos y se encontraron " + campos.length + ".");
        }

        String tipo = Validador.validarTextoNoVacio("tipo", campos[0]).toUpperCase();
        String nombre = Validador.validarTextoNoVacio("nombre", campos[1]);
        Rut rut = new Rut(campos[2]);
        Contacto contacto = new Contacto(
                Validador.validarEmail(campos[3]),
                Validador.validarTextoNoVacio("telefono", campos[4]));
        Direccion direccion = new Direccion(
                Validador.validarTextoNoVacio("calle", campos[5]),
                Validador.validarTextoNoVacio("comuna", campos[6]),
                Validador.validarTextoNoVacio("region", campos[7]));
        String extra1 = Validador.validarTextoNoVacio("extra1", campos[8]);
        String extra2 = campos[9].trim();

        return switch (tipo) {
            case "CLIENTE" -> new Cliente(nombre, rut, direccion, contacto,
                    extra1, Validador.validarEnteroPositivo("numeroReservas", extra2));
            case "EMPLEADO" -> new Empleado(nombre, rut, direccion, contacto,
                    extra1, Validador.validarTextoNoVacio("idiomas", extra2));
            case "PROVEEDOR" -> new Proveedor(nombre, rut, direccion, contacto,
                    extra1, Validador.validarTextoNoVacio("rubro", extra2));
            default -> throw new ValidacionException("Tipo de persona desconocido: '" + tipo + "'.");
        };
    }
}
