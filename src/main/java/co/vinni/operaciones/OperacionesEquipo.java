package co.vinni.operaciones;

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
    private final String RUTA_ARCHIVO = "equipos.dat";

    public OperacionesEquipo(){
        this.cargarArchivo();
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
                    .pais(Pais.valueOf(pais))
                    .direccion(direccion)
                    .telefono(telefono)
                    .build();
            this.listaEquipos.add(equipo);
            try {
                UtilidadArchivos.guardar(this.listaEquipos,RUTA_ARCHIVO );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        }

    /**
     * Metodo que verifica si hay información de equipos.
     */
    private void cargarArchivo() {
        try {
            List<Equipo> datosCargados = UtilidadArchivos.leer(RUTA_ARCHIVO);
            if (datosCargados != null) {
                this.listaEquipos = datosCargados;
            }
        } catch (Exception e) {
            this.listaEquipos = new ArrayList<>();
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
                        && e.getPais().name().equalsIgnoreCase(pais))
                .findFirst();
    }
    public List<Equipo> obtenerTodos(){
        return this.listaEquipos;
    }

}
