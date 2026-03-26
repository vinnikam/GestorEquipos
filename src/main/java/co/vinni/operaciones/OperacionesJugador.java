package co.vinni.operaciones;

import co.vinni.datos.Equipo;
import co.vinni.datos.Jugador;
import co.vinni.datos.Posicion;
import co.vinni.util.UtilidadArchivos;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperacionesJugador {
    private List<Jugador> listaJugadores;
    private final String RUTA_ARCHIVO = "jugadores.dat";

    public OperacionesJugador() {
        this.cargarArchivo();
    }
    public void agregar (String nombre, long numeroTelefono, int numero, LocalDate fechaNacimiento, String laPosicion,
                         Equipo equipo){
        Jugador jugador = Jugador
                .builder()
                .nombre(nombre)
                .numeroTelefono(numeroTelefono)
                .numero(numero)
                .fechaNacimiento(fechaNacimiento)
                .posicion( Posicion.valueOf(laPosicion))
                .equipo(equipo)
                .build();
        this.agregar(jugador);
    }
    public void agregar (Jugador jugador){

        this.listaJugadores.add(jugador);
        try {
            UtilidadArchivos.guardar(this.listaJugadores,RUTA_ARCHIVO );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo que verifica si hay información de jugadores.
     */
    private void cargarArchivo() {
        try {
            List<Jugador> datosCargados = UtilidadArchivos.leer(RUTA_ARCHIVO);
            if (datosCargados != null) {
                this.listaJugadores = datosCargados;
            }
        } catch (Exception e) {
            this.listaJugadores = new ArrayList<>();
        }
    }
    public List<Jugador> obtenerTodos(){
        return this.listaJugadores;
    }
}
