package co.vinni.datos;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jugador implements Serializable {
    private String nombre;
    private LocalDate fechaNacimiento;
    private Equipo equipo;
    private long numeroTelefono;
    private int numero;
    private Posicion posicion;

}
