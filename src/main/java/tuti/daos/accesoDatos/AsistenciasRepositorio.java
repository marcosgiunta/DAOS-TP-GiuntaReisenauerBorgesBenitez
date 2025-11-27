package tuti.daos.accesoDatos;

import tuti.daos.entidades.EntregaAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository 
public interface AsistenciasRepositorio extends JpaRepository<EntregaAsistencia, Integer> {

}
