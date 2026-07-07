package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Colaborador;
import model.Contacto;
import model.Guia;
import model.Operador;
import model.Proveedor;
import model.Ubicacion;
import util.ValidacionException;
import util.Validador;

/**
 * Carga los colaboradores desde un archivo de texto (.txt) separado por ';'.
 * Formato: tipo;nombre;rut;email;telefono;comuna;campoA;campoB
 */
public class CargadorColaboradores {

    private static final String SEPARADOR = ";";
    private static final int CAMPOS_ESPERADOS = 8;
    private static final String REGION = "Región de Los Lagos";

   /**
    * Lee el archivo y devuelve la lista de colaboradores válidos.
    * @param rutaArchivo
    * @return 
    */ 
    public List<Colaborador> cargarDesdeArchivo(String rutaArchivo) {
        List<Colaborador> colaboradores = new ArrayList<>();
        int numeroLinea = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                // Se ignoran el encabezado, las líneas vacías y los comentarios.
                if (numeroLinea == 1 || linea.trim().isEmpty() || linea.trim().startsWith("#")) {
                    continue;
                }
                try {
                    colaboradores.add(parsearLinea(linea));
                } catch (ValidacionException e) {
                    System.out.println("  [Línea " + numeroLinea + " descartada] " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo '" + rutaArchivo + "': " + e.getMessage());
        }

        return colaboradores;
    }

    /** Convierte una línea del archivo en un Colaborador validado. */
    private Colaborador parsearLinea(String linea) throws ValidacionException {
        String[] campos = linea.split(SEPARADOR);
        if (campos.length != CAMPOS_ESPERADOS) {
            throw new ValidacionException("Se esperaban " + CAMPOS_ESPERADOS
                    + " campos y se encontraron " + campos.length + ".");
        }

        String tipo = Validador.validarTextoNoVacio("tipo", campos[0]).toUpperCase();
        String nombre = Validador.validarTextoNoVacio("nombre", campos[1]);
        String rut = Validador.validarRut(campos[2]);
        String email = Validador.validarEmail(campos[3]);
        String telefono = Validador.validarTextoNoVacio("telefono", campos[4]);
        String comuna = Validador.validarTextoNoVacio("comuna", campos[5]);
        String campoA = Validador.validarTextoNoVacio("campoA", campos[6]);
        String campoB = campos[7].trim();

        Contacto contacto = new Contacto(email, telefono);
        Ubicacion ubicacion = new Ubicacion(comuna, REGION);

        switch (tipo) {
            case "GUIA" -> {
                return new Guia(nombre, rut, contacto, ubicacion,
                        campoA, Validador.validarTextoNoVacio("especialidad", campoB));
            }
            case "OPERADOR" -> {
                return new Operador(nombre, rut, contacto, ubicacion,
                        campoA, Validador.validarEnteroPositivo("anosExperiencia", campoB));
            }
            case "PROVEEDOR" -> {
                return new Proveedor(nombre, rut, contacto, ubicacion,
                        campoA, Validador.validarTextoNoVacio("servicio", campoB));
            }
            default -> throw new ValidacionException("Tipo de colaborador desconocido: '" + tipo + "'.");
        }
    }
}
