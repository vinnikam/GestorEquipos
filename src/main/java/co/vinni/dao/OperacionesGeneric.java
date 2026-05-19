package co.vinni.dao;

import java.util.List;
/**
 * Interface que define las operaciones
 * @author Vinni
 */
public interface OperacionesGeneric <T>{
    void crear(T entidad);
    List<T> obtenerTodos();
}
