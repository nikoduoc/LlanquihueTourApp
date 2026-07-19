package app;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import data.GestorPersonas;
import data.GestorServicios;
import model.Cliente;
import model.Contacto;
import model.Direccion;
import model.Empleado;
import model.Persona;
import model.Proveedor;
import model.Rut;
import model.ServicioTuristico;
import utils.ValidacionException;
import utils.Validador;

/**
 * Interfaz gráfica principal del sistema Llanquihue Tour. Permite ingresar
 * personas (clientes, empleados y proveedores), listarlas, buscarlas y
 * filtrarlas, además de consultar el catálogo de servicios turísticos. Los
 * resultados se muestran en el área central.
 */
public class VentanaPrincipal extends JFrame {

    /** Región fija de la agencia, para simplificar el ingreso de direcciones. */
    private static final String REGION = "Región de Los Lagos";

    private final GestorPersonas gestorPersonas;
    private final GestorServicios gestorServicios;
    private final JTextArea areaResultado;

    /**
     * Construye la ventana asociada a los gestores del sistema.
     * @param gestorPersonas gestor de personas
     * @param gestorServicios gestor del catálogo de servicios
     */
    public VentanaPrincipal(GestorPersonas gestorPersonas, GestorServicios gestorServicios) {
        this.gestorPersonas = gestorPersonas;
        this.gestorServicios = gestorServicios;

        setTitle("Llanquihue Tour - Sistema de Gestión");
        setSize(1000, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Cuadrícula de 2 filas para que todos los botones queden visibles.
        JPanel panelBotones = new JPanel(new GridLayout(2, 5, 6, 6));
        panelBotones.add(crearBoton("Agregar Cliente", e -> agregarCliente()));
        panelBotones.add(crearBoton("Agregar Empleado", e -> agregarEmpleado()));
        panelBotones.add(crearBoton("Agregar Proveedor", e -> agregarProveedor()));
        panelBotones.add(crearBoton("Mostrar Todos", e -> mostrarTodos()));
        panelBotones.add(crearBoton("Buscar por Nombre", e -> buscarPorNombre()));
        panelBotones.add(crearBoton("Filtrar por Tipo", e -> filtrarPorTipo()));
        panelBotones.add(crearBoton("Buscar por RUT", e -> buscarPorRut()));
        panelBotones.add(crearBoton("Eliminar por RUT", e -> eliminarPorRut()));
        panelBotones.add(crearBoton("Catálogo Servicios", e -> mostrarServicios()));

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setMargin(new Insets(8, 8, 8, 8));
        areaResultado.setText("Bienvenido. Use los botones para gestionar personas y consultar servicios.\n"
                + "Se cargaron " + gestorPersonas.cantidad() + " personas desde el archivo.");

        add(panelBotones, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);
    }

    /** Crea un botón con su acción asociada. */
    private JButton crearBoton(String texto, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(accion);
        return boton;
    }

    /** Solicita los datos de un cliente y lo agrega. */
    private void agregarCliente() {
        Persona base = pedirDatosComunes("cliente");
        if (base == null) {
            return;
        }
        String preferencia = pedirTexto("Preferencia de tour:");
        if (preferencia == null) {
            return;
        }
        Integer reservas = pedirEntero("Número de reservas:");
        if (reservas == null) {
            return;
        }
        if (gestorPersonas.agregar(new Cliente(base.getNombre(), base.getRut(),
                base.getDireccion(), base.getContacto(), preferencia, reservas))) {
            confirmar("Cliente agregado correctamente.");
        } else {
            avisarDuplicado(base.getRut());
        }
    }

    /** Solicita los datos de un empleado y lo agrega. */
    private void agregarEmpleado() {
        Persona base = pedirDatosComunes("empleado");
        if (base == null) {
            return;
        }
        String cargo = pedirTexto("Cargo:");
        if (cargo == null) {
            return;
        }
        String idiomas = pedirTexto("Idiomas:");
        if (idiomas == null) {
            return;
        }
        if (gestorPersonas.agregar(new Empleado(base.getNombre(), base.getRut(),
                base.getDireccion(), base.getContacto(), cargo, idiomas))) {
            confirmar("Empleado agregado correctamente.");
        } else {
            avisarDuplicado(base.getRut());
        }
    }

    /** Solicita los datos de un proveedor y lo agrega. */
    private void agregarProveedor() {
        Persona base = pedirDatosComunes("proveedor");
        if (base == null) {
            return;
        }
        String empresa = pedirTexto("Empresa:");
        if (empresa == null) {
            return;
        }
        String rubro = pedirTexto("Rubro (transporte, alojamiento, etc.):");
        if (rubro == null) {
            return;
        }
        if (gestorPersonas.agregar(new Proveedor(base.getNombre(), base.getRut(),
                base.getDireccion(), base.getContacto(), empresa, rubro))) {
            confirmar("Proveedor agregado correctamente.");
        } else {
            avisarDuplicado(base.getRut());
        }
    }

    /**
     * Pide los datos comunes a toda persona (nombre, RUT, contacto y dirección).
     * Valida cada campo en el momento de su ingreso: el RUT (formato y dígito
     * verificador), la unicidad del RUT y el formato del correo, informando de
     * inmediato si alguno es inválido. Devuelve una persona anónima que solo
     * transporta esos datos, o {@code null} si el ingreso se cancela o falla.
     */
    private Persona pedirDatosComunes(String tipo) {
        String nombre = pedirTexto("Nombre del " + tipo + ":");
        if (nombre == null) {
            return null;
        }
        String rutTexto = pedirTexto("RUT (formato 12345678-9):");
        if (rutTexto == null) {
            return null;
        }
        // Se valida el RUT al ingresarlo: formato y dígito verificador.
        Rut rut;
        try {
            rut = new Rut(rutTexto);
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "RUT inválido",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        // Se verifica el RUT duplicado de inmediato, antes de pedir el resto.
        if (gestorPersonas.buscarPorRut(rut.getValor()) != null) {
            avisarDuplicado(rut);
            return null;
        }
        // Se valida el correo al ingresarlo.
        String email = pedirTexto("Correo electrónico:");
        if (email == null) {
            return null;
        }
        try {
            email = Validador.validarEmail(email);
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Correo inválido",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }
        String telefono = pedirTexto("Teléfono:");
        if (telefono == null) {
            return null;
        }
        String calle = pedirTexto("Calle y número:");
        if (calle == null) {
            return null;
        }
        String comuna = pedirTexto("Comuna:");
        if (comuna == null) {
            return null;
        }
        Contacto contacto = new Contacto(email, telefono);
        Direccion direccion = new Direccion(calle, comuna, REGION);
        // Cliente concreto usado solo como contenedor temporal de datos comunes.
        return new Cliente(nombre, rut, direccion, contacto, "", 0);
    }

    /** Muestra el reporte de todas las personas. */
    private void mostrarTodos() {
        areaResultado.setText(gestorPersonas.generarReporte());
    }

    /** Busca personas por nombre y muestra los resultados. */
    private void buscarPorNombre() {
        String texto = pedirTexto("Nombre a buscar:");
        if (texto == null) {
            return;
        }
        List<Persona> resultado = gestorPersonas.buscarPorNombre(texto);
        areaResultado.setText(gestorPersonas.reporteDe(resultado));
    }

    /** Filtra personas por tipo y muestra los resultados. */
    private void filtrarPorTipo() {
        String[] tipos = {"CLIENTE", "EMPLEADO", "PROVEEDOR"};
        String tipo = (String) JOptionPane.showInputDialog(this, "Seleccione el tipo:",
                "Filtrar por tipo", JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        if (tipo == null) {
            return;
        }
        List<Persona> resultado = gestorPersonas.filtrarPorTipo(tipo);
        areaResultado.setText(gestorPersonas.reporteDe(resultado));
    }

    /** Busca una persona por su RUT y muestra el resultado. */
    private void buscarPorRut() {
        String rut = pedirTexto("RUT a buscar (formato 12345678-9):");
        if (rut == null) {
            return;
        }
        Persona persona = gestorPersonas.buscarPorRut(rut);
        if (persona == null) {
            areaResultado.setText("No se encontró ninguna persona con el RUT " + rut + ".");
        } else {
            areaResultado.setText(persona.mostrarDatos());
        }
    }

    /** Elimina una persona por su RUT y muestra el resultado. */
    private void eliminarPorRut() {
        String rut = pedirTexto("RUT a eliminar (formato 12345678-9):");
        if (rut == null) {
            return;
        }
        boolean eliminado = gestorPersonas.eliminarPorRut(rut);
        if (eliminado) {
            areaResultado.setText("Persona con RUT " + rut + " eliminada correctamente.\n"
                    + "Quedan " + gestorPersonas.cantidad() + " personas registradas.");
        } else {
            areaResultado.setText("No se encontró ninguna persona con el RUT " + rut + ".");
        }
    }

    /** Muestra el catálogo de servicios turísticos. */
    private void mostrarServicios() {
        StringBuilder catalogo = new StringBuilder("CATÁLOGO DE SERVICIOS TURÍSTICOS\n\n");
        int indice = 1;
        for (ServicioTuristico servicio : gestorServicios.obtenerServicios()) {
            catalogo.append(indice++).append(". ").append(servicio).append("\n");
        }
        areaResultado.setText(catalogo.toString());
    }

    /**
     * Pide un texto no vacío mediante un cuadro de diálogo.
     * @param mensaje etiqueta del campo
     * @return el texto ingresado, o {@code null} si se cancela o queda vacío
     */
    private String pedirTexto(String mensaje) {
        String valor = JOptionPane.showInputDialog(this, mensaje);
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }
        return valor.trim();
    }

    /**
     * Pide un número entero positivo mediante un cuadro de diálogo.
     * @param mensaje etiqueta del campo
     * @return el número ingresado, o {@code null} si se cancela o es inválido
     */
    private Integer pedirEntero(String mensaje) {
        String valor = JOptionPane.showInputDialog(this, mensaje);
        if (valor == null) {
            return null;
        }
        try {
            int numero = Integer.parseInt(valor.trim());
            if (numero < 0) {
                JOptionPane.showMessageDialog(this, "El número debe ser positivo.",
                        "Dato inválido", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            return numero;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un número válido.",
                    "Dato inválido", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    /** Avisa que el RUT ya está registrado (no se permiten duplicados). */
    private void avisarDuplicado(Rut rut) {
        JOptionPane.showMessageDialog(this,
                "Ya existe una persona registrada con el RUT " + rut + ".",
                "RUT duplicado", JOptionPane.WARNING_MESSAGE);
    }

    /** Muestra un mensaje de confirmación tras registrar una persona. */
    private void confirmar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje + "\nPresione «Mostrar Todos» para verlo en la lista.",
                "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
    }
}
