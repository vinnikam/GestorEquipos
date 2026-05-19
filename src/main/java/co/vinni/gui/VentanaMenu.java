package co.vinni.gui;

import co.vinni.dao.operacionesImp.ImpOperacionesEquipo;
import co.vinni.dao.operacionesImp.ImpOperacionesJugador;
import co.vinni.operaciones.OperacionesEquipo;
import co.vinni.operaciones.OperacionesJugador;

import javax.swing.*;

public class VentanaMenu extends JFrame {

    private JButton btnCrear;
    private JButton btnListar;

    public VentanaMenu() {
        setTitle("Gestión de Jugadores");
        setSize(400, 300); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        btnCrear = new JButton("Crear Jugador");
        btnCrear.setBounds(100, 60, 200, 40);
        add(btnCrear);

        // Botón Listar Jugadores
        btnListar = new JButton("Listar Jugadores");
        btnListar.setBounds(100, 130, 200, 40);
        add(btnListar);

        // --- EVENTOS ---

        btnCrear.addActionListener(e->{

            VentanaJugador ventanaJugador = new VentanaJugador(
                    new OperacionesEquipo(new ImpOperacionesEquipo()),
                    new OperacionesJugador(new ImpOperacionesJugador()));
            ventanaJugador.setVisible(true);
        });

        btnListar.addActionListener(e->{

            VentanaListaJugadores ventanaListaJugadores = new VentanaListaJugadores(new OperacionesJugador(new ImpOperacionesJugador()));
            ventanaListaJugadores.setVisible(true);
        });
    }

    public static void main(String[] args) {

        // Ejecutar la ventana
        SwingUtilities.invokeLater(() -> {
            new VentanaMenu().setVisible(true);
        });
    }
}