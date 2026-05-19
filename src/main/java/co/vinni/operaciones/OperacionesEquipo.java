package co.vinni.operaciones;

import co.vinni.dao.operacionesImp.ImpOperacionesEquipo;
import co.vinni.datos.Equipo;
import co.vinni.datos.Jugador;
import co.vinni.datos.Pais;
import co.vinni.datos.Posicion;
import co.vinni.util.UtilidadArchivos;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OperacionesEquipo {
    private List<Equipo> listaEquipos;

    private ImpOperacionesEquipo impOperacionesEquipo;

    public OperacionesEquipo(ImpOperacionesEquipo impOperacionesEquipo){
        this.impOperacionesEquipo = new ImpOperacionesEquipo();
        // carga los equipos desde la base de datos.
        listaEquipos = impOperacionesEquipo.obtenerTodos();

    }

    /**
     * Metodo agregar verifica que el equipo no se repita por nombre y pais.
     * @param nombre
     * @param pais
     * @param direccion
     * @param telefono
     */
    public void agregar(String nombre, String pais, String direccion, String telefono){
        Optional<Equipo> equipoExistente = buscarEquipo(nombre, pais);
        if (!equipoExistente.isPresent()) {
            Equipo equipo =  Equipo
                    .builder()
                    .nombre(nombre)
                    .pais(pais)
                    .direccion(direccion)
                    .telefono(telefono)
                    .build();
            this.listaEquipos.add(equipo);
            // se modifico para almacenar en la base de datos.
            impOperacionesEquipo.crear(equipo);
        }
    }


    /**
     * Verifica que el equipo exista por las condiciones nombre y equipo.
     * @param nombre
     * @param pais
     * @return
     */
    public Optional<Equipo> buscarEquipo(String nombre, String pais) {
        return listaEquipos.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre)
                        && e.getPais().equalsIgnoreCase(pais))
                .findFirst();
    }
    public List<Equipo> obtenerTodos(){
        return this.listaEquipos;
    }

}
