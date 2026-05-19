package co.vinni.gui;

import co.vinni.datos.Equipo;
import co.vinni.datos.Jugador;
import co.vinni.datos.Posicion;
import co.vinni.operaciones.OperacionesEquipo;
import co.vinni.operaciones.OperacionesJugador;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class VentanaJugador extends JFrame {
    // Componentes
    private JTextField txtNombre, txtTelefono, txtCamiseta;
    private JDateChooser jdFechaNacimiento; // Recomendado para fechas
    private JComboBox<Posicion> cbPosicion;
    private JComboBox<Equipo> cbEquipos;
    private JButton btnGuardar, btnCrearEquipo;

    private OperacionesEquipo operacionesEquipo;
    private OperacionesJugador operacionesJugador;

    public VentanaJugador(OperacionesEquipo operacionesEquipo,
                          OperacionesJugador operacionesJugador ) {
        // inyeccion manual de dependencia
        this.operacionesEquipo = operacionesEquipo;
        this.operacionesJugador = operacionesJugador;

        setTitle("Registro de Jugador");
        setSize(450, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        int xLabel = 30, xCampo = 180, ancho = 200, alto = 25;

        // Nombre
        crearLabel("Nombre:", xLabel, 30);
        txtNombre = new JTextField();
        txtNombre.setBounds(xCampo, 30, ancho, alto);
        add(txtNombre);

        // Fecha Nacimiento libreria JDateChooser
        crearLabel("Fecha Nacimiento:", xLabel, 70);
        jdFechaNacimiento = new JDateChooser();
        jdFechaNacimiento.setBounds(xCampo, 70, ancho, alto);
        add(jdFechaNacimiento);

        // Teléfono
        crearLabel("Teléfono:", xLabel, 110);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(xCampo, 110, ancho, alto);
        add(txtTelefono);

        // Número Camiseta
        crearLabel("N° Camiseta:", xLabel, 150);
        txtCamiseta = new JTextField();
        txtCamiseta.setBounds(xCampo, 150, ancho, alto);
        add(txtCamiseta);

        // Posición (Enum)
        crearLabel("Posición:", xLabel, 190);
        cbPosicion = new JComboBox<>(Posicion.values());
        cbPosicion.setBounds(xCampo, 190, ancho, alto);
        add(cbPosicion);

        // Lista de Equipos
        crearLabel("Equipo:", xLabel, 230);
        cbEquipos = new JComboBox<>();
        this.cargarListaEquipos();
        cbEquipos.setBounds(xCampo, 230, ancho, alto);
        add(cbEquipos);

        // Botón Crear Equipo (Si no está en la lista)
        btnCrearEquipo = new JButton("+ Nuevo Equipo");
        btnCrearEquipo.setBounds(xCampo, 260, 150, 20);
        btnCrearEquipo.setFont(new Font("Arial", Font.ITALIC, 10));
        add(btnCrearEquipo);

        // Botón Guardar Jugador
        btnGuardar = new JButton("GUARDAR JUGADOR");
        btnGuardar.setBounds(120, 350, 200, 40);
        btnGuardar.setBackground(new Color(40, 167, 69));
        btnGuardar.setForeground(Color.WHITE);
        add(btnGuardar);

        // --- EVENTOS ---
        btnCrearEquipo.addActionListener(e -> {

            EquipoDialog dialog = new EquipoDialog(this, this.operacionesEquipo);
            dialog.setVisible(true);

            // Cuando el diálogo se cierra, verificamos si se guardó algo
            if (dialog.isGuardadoExitoso()) {
                // Refrescamos el JComboBox de equipos para que aparezca el nuevo
                cargarListaEquipos();
                JOptionPane.showMessageDialog(this, "Lista de equipos actualizada.");
            }
        });

        btnGuardar.addActionListener(e -> guardarJugador());
    }

    private void cargarListaEquipos() {
        List<Equipo> lista = operacionesEquipo.obtenerTodos();
        DefaultComboBoxModel<Equipo> modeloCombo = new DefaultComboBoxModel<>();

        for (Equipo equipo : lista) {
            modeloCombo.addElement(equipo);
        }

        cbEquipos.setModel(modeloCombo);
    }

    private void crearLabel(String texto, int x, int y) {
        JLabel lb = new JLabel(texto);
        lb.setBounds(x, y, 150, 25);
        add(lb);
    }

    private void guardarJugador() {
        try {
            // 1. Capturar datos de los campos de texto
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();
            int numero = Integer.parseInt(txtCamiseta.getText());

            // 2. Convertir fecha de JDateChooser (java.util.Date) a java.time.LocalDate
            java.util.Date dateSelected = jdFechaNacimiento.getDate();
            if (dateSelected == null) {
                throw new Exception("Por favor selecciona una fecha válida.");
            }
            LocalDate fechaNac = dateSelected.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // 3. Obtener valores de los JComboBox (Enums y Objetos)
            Posicion pos = (Posicion) cbPosicion.getSelectedItem();
            Equipo equipoSel = (Equipo) cbEquipos.getSelectedItem();

            if (equipoSel == null) {
                throw new Exception("Debes seleccionar un equipo.");
            }

            // 4. Construir el objeto con el Builder
            Jugador nuevoJugador = Jugador.builder()
                    .nombre(nombre)
                    .fechaNacimiento(fechaNac)
                    .numeroTelefono(Long.parseLong(telefono))
                    .numero(numero)
                    .posicion(pos.toString())
                    .equipo(equipoSel)
                    .build();

            operacionesJugador.agregar(nuevoJugador);

            JOptionPane.showMessageDialog(this, "¡Jugador guardado con éxito!");
            this.dispose(); // Cerrar formulario al terminar

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El número de camiseta debe ser numérico.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}