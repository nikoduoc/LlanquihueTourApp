package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import data.GestorEntidades;
import model.ColaboradorExterno;
import model.GuiaTuristico;
import model.Vehiculo;

/**
 * Interfaz gráfica básica de Llanquihue Tour. Permite ingresar distintos tipos
 * de entidades (guías turísticos, vehículos y colaboradores externos) mediante
 * cuadros de diálogo y visualizar el resumen de todos los registros existentes.
 */
public class VentanaPrincipal extends JFrame {

    private final GestorEntidades gestor;
    private final JTextArea areaResumen;

    /**
     * Construye la ventana asociada a un gestor de entidades.
     * @param gestor colección de entidades que se muestra y actualiza
     */
    public VentanaPrincipal(GestorEntidades gestor) {
        this.gestor = gestor;

        setTitle("Llanquihue Tour - Gestión de Entidades");
        setSize(820, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel superior con los botones de acción.
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));

        JButton botonGuia = new JButton("Agregar Guía");
        JButton botonVehiculo = new JButton("Agregar Vehículo");
        JButton botonColaborador = new JButton("Agregar Colaborador");
        JButton botonMostrar = new JButton("Mostrar Resúmenes");

        botonGuia.addActionListener(e -> agregarGuia());
        botonVehiculo.addActionListener(e -> agregarVehiculo());
        botonColaborador.addActionListener(e -> agregarColaborador());
        botonMostrar.addActionListener(e -> actualizarResumen());

        panelBotones.add(botonGuia);
        panelBotones.add(botonVehiculo);
        panelBotones.add(botonColaborador);
        panelBotones.add(botonMostrar);

        // Área central donde se muestran los resúmenes (inicia vacía).
        areaResumen = new JTextArea();
        areaResumen.setEditable(false);
        areaResumen.setMargin(new Insets(8, 8, 8, 8));
        areaResumen.setText("Presione «Mostrar Resúmenes» para ver las entidades registradas.");

        add(panelBotones, BorderLayout.NORTH);
        add(new JScrollPane(areaResumen), BorderLayout.CENTER);
    }

    /** Solicita los datos de un guía turístico y lo agrega a la colección. */
    private void agregarGuia() {
        String nombre = pedirTexto("Nombre del guía:");
        if (nombre == null) {
            return;
        }
        String idiomas = pedirTexto("Idiomas que domina:");
        if (idiomas == null) {
            return;
        }
        Integer experiencia = pedirEntero("Años de experiencia:");
        if (experiencia == null) {
            return;
        }
        gestor.agregar(new GuiaTuristico(nombre, idiomas, experiencia));
        confirmar("Guía turístico agregado correctamente.");
    }

    /** Solicita los datos de un vehículo y lo agrega a la colección. */
    private void agregarVehiculo() {
        String nombre = pedirTexto("Modelo del vehículo:");
        if (nombre == null) {
            return;
        }
        String patente = pedirTexto("Patente:");
        if (patente == null) {
            return;
        }
        Integer capacidad = pedirEntero("Capacidad (pasajeros):");
        if (capacidad == null) {
            return;
        }
        gestor.agregar(new Vehiculo(nombre, patente, capacidad));
        confirmar("Vehículo agregado correctamente.");
    }

    /** Solicita los datos de un colaborador externo y lo agrega a la colección. */
    private void agregarColaborador() {
        String nombre = pedirTexto("Nombre del colaborador:");
        if (nombre == null) {
            return;
        }
        String empresa = pedirTexto("Empresa:");
        if (empresa == null) {
            return;
        }
        String servicio = pedirTexto("Servicio que presta:");
        if (servicio == null) {
            return;
        }
        gestor.agregar(new ColaboradorExterno(nombre, empresa, servicio));
        confirmar("Colaborador externo agregado correctamente.");
    }

    /** Actualiza el área central con el reporte actual de la colección. */
    private void actualizarResumen() {
        areaResumen.setText(gestor.generarReporte());
    }

    /**
     * Pide un texto no vacío mediante un cuadro de diálogo.
     * @param mensaje etiqueta del campo solicitado
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
     * Pide un número entero positivo mediante un cuadro de diálogo, validando
     * el formato.
     * @param mensaje etiqueta del campo solicitado
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

    /**
     * Muestra un mensaje de confirmación tras registrar una entidad. No refresca
     * el área: el resumen se actualiza al presionar «Mostrar Resúmenes».
     */
    private void confirmar(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje + "\nPresione «Mostrar Resúmenes» para verlo en la lista.",
                "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
    }
}
