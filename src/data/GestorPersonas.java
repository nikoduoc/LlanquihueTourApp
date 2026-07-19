package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Cliente;
import model.Empleado;
import model.Persona;
import model.Proveedor;
import model.Registrable;

/**
 * Gestiona la colección de personas del sistema. Mantiene un {@link ArrayList}
 * para el recorrido ordenado y un {@link HashMap} indexado por RUT para las
 * búsquedas directas. Ofrece operaciones de alta, listado, búsqueda y filtrado,
 * aplicando polimorfismo e {@code instanceof} en la generación de reportes.
 */
public class GestorPersonas {

    private final List<Persona> personas;
    private final Map<String, Persona> personasPorRut;

    /** Crea el gestor con colecciones vacías. */
    public GestorPersonas() {
        this.personas = new ArrayList<>();
        this.personasPorRut = new HashMap<>();
    }

    /**
     * Agrega una persona a las colecciones, evitando RUT duplicados para
     * mantener la integridad de los datos.
     * @param persona persona a registrar
     * @return {@code true} si se agregó, {@code false} si es null o el RUT ya existe
     */
    public boolean agregar(Persona persona) {
        if (persona == null) {
            return false;
        }
        String rut = persona.getRut().getValor();
        if (personasPorRut.containsKey(rut)) {
            return false;
        }
        persona.registrar();
        personas.add(persona);
        personasPorRut.put(rut, persona);
        return true;
    }

    /** Agrega todas las personas de una lista, omitiendo las de RUT duplicado.
     * @param lista personas a registrar */
    public void agregarTodas(List<Persona> lista) {
        for (Persona persona : lista) {
            if (!agregar(persona)) {
                System.out.println("  [Omitida] Persona con RUT duplicado: " + persona.getRut());
            }
        }
    }

    /** @return una copia de la lista de personas */
    public List<Persona> obtenerTodas() {
        return new ArrayList<>(personas);
    }

    /** @return la cantidad de personas registradas */
    public int cantidad() {
        return personas.size();
    }

    /**
     * Busca personas cuyo nombre contenga el texto indicado.
     * @param texto texto a buscar (sin distinguir mayúsculas)
     * @return las personas coincidentes
     */
    public List<Persona> buscarPorNombre(String texto) {
        List<Persona> resultado = new ArrayList<>();
        String aBuscar = (texto == null) ? "" : texto.toLowerCase();
        for (Persona persona : personas) {
            if (persona.getNombre().toLowerCase().contains(aBuscar)) {
                resultado.add(persona);
            }
        }
        return resultado;
    }

    /**
     * Filtra las personas por su tipo concreto usando {@code instanceof}.
     * @param tipo tipo a filtrar: CLIENTE, EMPLEADO o PROVEEDOR
     * @return las personas del tipo indicado
     */
    public List<Persona> filtrarPorTipo(String tipo) {
        List<Persona> resultado = new ArrayList<>();
        String aBuscar = (tipo == null) ? "" : tipo.trim().toUpperCase();
        for (Persona persona : personas) {
            boolean coincide = switch (aBuscar) {
                case "CLIENTE" -> persona instanceof Cliente;
                case "EMPLEADO" -> persona instanceof Empleado;
                case "PROVEEDOR" -> persona instanceof Proveedor;
                default -> false;
            };
            if (coincide) {
                resultado.add(persona);
            }
        }
        return resultado;
    }

    /**
     * Busca una persona por su RUT usando el índice {@link HashMap}.
     * @param rut RUT a buscar
     * @return la persona encontrada, o {@code null} si no existe
     */
    public Persona buscarPorRut(String rut) {
        if (rut == null) {
            return null;
        }
        return personasPorRut.get(rut.trim().toUpperCase());
    }

    /**
     * Elimina una persona a partir de su RUT, tanto de la lista como del índice.
     * @param rut RUT de la persona a eliminar
     * @return {@code true} si se eliminó, {@code false} si no existía
     */
    public boolean eliminarPorRut(String rut) {
        if (rut == null) {
            return false;
        }
        Persona persona = personasPorRut.remove(rut.trim().toUpperCase());
        if (persona == null) {
            return false;
        }
        personas.remove(persona);
        return true;
    }

    /** @return el reporte de todas las personas registradas */
    public String generarReporte() {
        return construirReporte(new ArrayList<>(personas));
    }

    /**
     * Genera un reporte a partir de una lista de resultados (búsqueda o filtro).
     * @param lista personas a incluir
     * @return el reporte generado
     */
    public String reporteDe(List<Persona> lista) {
        return construirReporte(new ArrayList<>(lista));
    }

    /**
     * Recorre una lista polimórfica de {@link Registrable}, invoca
     * {@code mostrarDatos()} y usa {@code instanceof} para agregar una línea de
     * detalle diferenciada según el tipo concreto de cada persona.
     * @param lista lista polimórfica a recorrer
     * @return el reporte en texto
     */
    private String construirReporte(List<Registrable> lista) {
        if (lista.isEmpty()) {
            return "(Sin resultados)";
        }

        StringBuilder reporte = new StringBuilder();
        int indice = 1;

        for (Registrable registrable : lista) {
            reporte.append(indice).append(". ").append(registrable.mostrarDatos()).append("\n");

            if (registrable instanceof Cliente cliente) {
                reporte.append("   -> Cliente con ").append(cliente.getNumeroReservas())
                        .append(" reserva(s) registrada(s).\n");
            } else if (registrable instanceof Empleado empleado) {
                reporte.append("   -> Personal interno, cargo: ").append(empleado.getCargo())
                        .append(".\n");
            } else if (registrable instanceof Proveedor proveedor) {
                reporte.append("   -> Proveedor externo: ").append(proveedor.getEmpresa())
                        .append(".\n");
            }

            indice++;
        }

        return reporte.toString();
    }
}
