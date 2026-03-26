package co.vinni.gui;

import co.vinni.datos.Pais;
import co.vinni.operaciones.OperacionesEquipo;

import javax.swing.*;
import java.awt.*;

public class EquipoDialog extends JDialog {
    private JTextField txtNombre, txtDireccion, txtTelefono;
    private JComboBox<Pais> cbPais;
    private JButton btnGuardar;
    private OperacionesEquipo control;
    private boolean guardadoExitoso = false; // Para avisar al formulario padre

    // El constructor recibe al padre (JFrame) y al controlador inyectado
    public EquipoDialog(Frame padre, OperacionesEquipo control) {
        super(padre, "Crear Nuevo Equipo", true); // true = Modal
        this.control = control;

        setSize(350, 300);
        setLayout(null);
        setLocationRelativeTo(padre); // Se centra respecto al formulario de jugador

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        int xL = 20, xC = 120, ancho = 180, alto = 25;

        // Nombre
        JLabel lbNombre = new JLabel("Nombre:");
        lbNombre.setBounds(xL, 30, 80, alto);
        add(lbNombre);
        txtNombre = new JTextField();
        txtNombre.setBounds(xC, 30, ancho, alto);
        add(txtNombre);

        // Dirección
        JLabel lbDir = new JLabel("Dirección:");
        lbDir.setBounds(xL, 70, 80, alto);
        add(lbDir);
        txtDireccion = new JTextField();
        txtDireccion.setBounds(xC, 70, ancho, alto);
        add(txtDireccion);

        // Teléfono
        JLabel lbTel = new JLabel("Teléfono:");
        lbTel.setBounds(xL, 110, 80, alto);
        add(lbTel);
        txtTelefono = new JTextField();
        txtTelefono.setBounds(xC, 110, ancho, alto);
        add(txtTelefono);

        // País (Enum)
        JLabel lbPais = new JLabel("País:");
        lbPais.setBounds(xL, 150, 80, alto);
        add(lbPais);
        cbPais = new JComboBox<>(Pais.values());
        cbPais.setBounds(xC, 150, ancho, alto);
        add(cbPais);

        // Botón Guardar
        btnGuardar = new JButton("Guardar Equipo");
        btnGuardar.setBounds(100, 210, 150, 30);
        add(btnGuardar);

        // Evento Lambda
        btnGuardar.addActionListener(e -> guardar());
    }

    private void guardar() {
        try {
            // 1. Validar que no existan duplicados
            String nombre = txtNombre.getText().trim();
            Pais pais = (Pais) cbPais.getSelectedItem();

            if(control.buscarEquipo(nombre, pais.toString()).isPresent()) {
                JOptionPane.showMessageDialog(this, "El equipo ya existe en ese país.");
                return;
            }

            control.agregar(nombre,pais.toString(),txtDireccion.getText(), txtTelefono.getText()  );
            guardadoExitoso = true;
            dispose(); // Cierra el diálogo

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public boolean isGuardadoExitoso() {
        return guardadoExitoso;
    }
}

