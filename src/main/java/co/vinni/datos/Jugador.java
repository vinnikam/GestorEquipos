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
    private Long codigo;
    private String nombre;
    private LocalDate fechaNacimiento;
    private Equipo equipo;
    private long numeroTelefono;
    private int numero;
    private String posicion;
    private Long codigoEquipo;

}
/**
 *
 create table jugadores(
    codigo serial primary key,
    nombre text,
    fechaNacimiento Date,
    numeroTelefono numeric(10, 0),
    numero numeric(2, 0),
    posicion text,
    equipo_codigo int  references equipos(codigo)
 );
 *
 *
 */

