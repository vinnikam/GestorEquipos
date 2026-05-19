package co.vinni.datos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Equipo implements Serializable {
    private Long codigo;
    private String nombre;
    private String direccion;
    private String telefono;
    private String pais;

    @Override
    public String toString() {
        return this.nombre;
    }
}
/*
SQL
create table equipos (
    codigo serial primary key,
    nombre text,
    direccion text,
    telefono text,
    pais text
);

*
*/

