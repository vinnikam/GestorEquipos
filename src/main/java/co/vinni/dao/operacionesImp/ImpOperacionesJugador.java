package co.vinni.dao.operacionesImp;

import co.vinni.dao.GestorConexion;
import co.vinni.dao.OperacionesGeneric;
import co.vinni.datos.Equipo;
import co.vinni.datos.Jugador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ImpOperacionesJugador implements OperacionesGeneric<Jugador> {
    @Override
    public void crear(Jugador entidad) {
        String sql = "insert into jugadores " +
                "(nombre, fechaNacimiento,numeroTelefono,numero,posicion, equipo_codigo) " +
                "values (?, ?,?,?,?,?)";

        try(Connection con = GestorConexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, entidad.getNombre());
            ps.setDate(2, java.sql.Date.valueOf(entidad.getFechaNacimiento()) );
            ps.setLong(3,entidad.getNumeroTelefono());
            ps.setLong(4,entidad.getNumero());
            ps.setString(5,entidad.getPosicion());
            ps.setLong(6,entidad.getEquipo().getCodigo());

            ps.executeUpdate();
            System.out.println("Inserto ");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Jugador> obtenerTodos() {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "select * from  jugadores ";
        try(Connection con = GestorConexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs =  ps.executeQuery();
            while (rs.next()){
                String nombre = rs.getString("nombre");
                Long codigo = rs.getLong("codigo");
                LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate();
                Long numeroTelefono = rs.getLong("numeroTelefono");
                Long numero = rs.getLong("numero");
                String posicion = rs.getString("posicion");
                Long codEquipo = rs.getLong("equipo_codigo");

                Jugador jugador = Jugador
                        .builder()
                        .nombre(nombre)
                        .codigo(codigo)
                        .numeroTelefono(numeroTelefono)
                        .numero(numero.intValue())
                        .fechaNacimiento(fechaNacimiento)
                        .posicion(posicion)
                        .codigoEquipo(codEquipo)
                        .build();
                jugadores.add(jugador);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return jugadores;
    }
}
