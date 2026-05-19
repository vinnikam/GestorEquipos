package co.vinni.operaciones;

import co.vinni.dao.operacionesImp.ImpOperacionesJugador;
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

    private ImpOperacionesJugador impOperacionesJugador;

    public OperacionesJugador(ImpOperacionesJugador impOperacionesJugador)
    {
        this.impOperacionesJugador = impOperacionesJugador;

        listaJugadores = impOperacionesJugador.obtenerTodos();

    }
    public void agregar (String nombre, long numeroTelefono, int numero, LocalDate fechaNacimiento, String laPosicion,
                         Equipo equipo){
        Jugador jugador = Jugador
                .builder()
                .nombre(nombre)
                .numeroTelefono(numeroTelefono)
                .numero(numero)
                .fechaNacimiento(fechaNacimiento)
                .posicion( laPosicion)
                .equipo(equipo)
                .build();
        this.agregar(jugador);
    }
    public void agregar (Jugador jugador){

        this.listaJugadores.add(jugador);

        this.impOperacionesJugador.crear(jugador);
    }

    public List<Jugador> obtenerTodos(){
        return this.listaJugadores;
    }
}
