package co.vinni.datos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Equipo implements Serializable {
    private String nombre;
    private String direccion;
    private String telefono;
    private Pais pais;

    @Override
    public String toString() {
        return this.nombre;
    }
}
