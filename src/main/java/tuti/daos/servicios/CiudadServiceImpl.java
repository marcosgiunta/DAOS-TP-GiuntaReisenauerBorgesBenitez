package tuti.daos.servicios;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.daos.Excepciones.Excepcion;
import tuti.daos.accesoDatos.CiudadRepositorio;
import tuti.daos.entidades.Ciudad;

@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    private CiudadRepositorio ciudadRepo;

    @Override
    public Ciudad buscarPorId(Integer id) throws Excepcion {

            return ciudadRepo.findById(id)
            .orElseThrow(() -> new Excepcion("Ciudad", "Error, Ciudad no encontrada: ", 404));
            

    }

}
