package tuti.daos.servicios;


import tuti.daos.Excepciones.Excepcion;
import tuti.daos.entidades.Ciudad;

public interface CiudadService {

    Ciudad buscarPorId(Integer id) throws Excepcion;
    
}
