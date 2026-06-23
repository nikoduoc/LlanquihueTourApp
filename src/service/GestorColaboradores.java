package service;

import java.util.ArrayList;
import java.util.List;
import model.Colaborador;

/**
 * Gestiona la colección de colaboradores (ArrayList) con búsquedas y filtros.
 */
public class GestorColaboradores {

    private final List<Colaborador> colaboradores;

    /** Crea un gestor con una colección vacía. */
    public GestorColaboradores() {
        this.colaboradores = new ArrayList<>();
    }

    /** Agrega un colaborador (ignora null).
     * @param colaborador */
    public void agregar(Colaborador colaborador) {
        if (colaborador != null) {
            colaboradores.add(colaborador);
        }
    }

    /** Agrega todos los colaboradores de una lista.
     * @param lista */
    public void agregarTodos(List<Colaborador> lista) {
        for (Colaborador c : lista) {
            agregar(c);
        }
    }

    /** @return una copia de la lista de colaboradores */
    public List<Colaborador> obtenerTodos() {
        return new ArrayList<>(colaboradores);
    }

    /** @return la cantidad de colaboradores */
    public int cantidad() {
        return colaboradores.size();
    }

    /** Busca colaboradores cuyo nombre contenga el texto (sin distinguir mayúsculas).
     * @param texto
     * @return Busqueda por nombre */
    public List<Colaborador> buscarPorNombre(String texto) {
        List<Colaborador> resultado = new ArrayList<>();
        String aBuscar = (texto == null) ? "" : texto.toLowerCase();
        for (Colaborador c : colaboradores) {
            if (c.getNombre().toLowerCase().contains(aBuscar)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /** Filtra los colaboradores por su tipo.
     * @param tipo
     * @return filtro por tipo */
    public List<Colaborador> filtrarPorTipo(String tipo) {
        List<Colaborador> resultado = new ArrayList<>();
        String aBuscar = (tipo == null) ? "" : tipo.trim();
        for (Colaborador c : colaboradores) {
            if (c.getTipo().equalsIgnoreCase(aBuscar)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /** Filtra los colaboradores por comuna.
     * @param comuna
     * @return  Filtrar por comuna*/
    public List<Colaborador> filtrarPorComuna(String comuna) {
        List<Colaborador> resultado = new ArrayList<>();
        String aBuscar = (comuna == null) ? "" : comuna.trim();
        for (Colaborador c : colaboradores) {
            if (c.getUbicacion().getComuna().equalsIgnoreCase(aBuscar)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}
