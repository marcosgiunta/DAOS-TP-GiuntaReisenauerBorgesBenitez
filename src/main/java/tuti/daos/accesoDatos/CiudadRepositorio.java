package tuti.daos.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuti.daos.entidades.Ciudad;


@Repository
public interface CiudadRepositorio extends JpaRepository<Ciudad, Integer> {
    
}
