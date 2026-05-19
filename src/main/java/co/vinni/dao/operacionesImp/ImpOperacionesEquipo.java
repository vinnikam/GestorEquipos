package co.vinni.dao.operacionesImp;

import co.vinni.dao.GestorConexion;
import co.vinni.dao.OperacionesGeneric;
import co.vinni.datos.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpOperacionesEquipo implements OperacionesGeneric<Equipo> {
    @Override
    public void crear(Equipo entidad) {
        String sql = "insert into equipos " +
                "(nombre, direccion, telefono,pais ) " +
                " values (?, ?, ?, ?)";

        try(Connection con = GestorConexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getDireccion());
            ps.setString(3, entidad.getTelefono());
            ps.setString(4, entidad.getPais());


            ps.executeUpdate();
            System.out.println("Inserto ");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public List<Equipo> obtenerTodos() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "select * from  equipos";
        try(Connection con = GestorConexion.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet rs =  ps.executeQuery();
            while (rs.next()){
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String pais = rs.getString("pais");
                Long codigo = rs.getLong("codigo");
                Equipo equipo = Equipo
                        .builder()
                        .nombre(nombre)
                        .direccion(direccion)
                        .telefono(telefono)
                        .pais(pais)
                        .codigo(codigo)
                        .build();
                equipos.add(equipo);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return equipos;
    }
}
