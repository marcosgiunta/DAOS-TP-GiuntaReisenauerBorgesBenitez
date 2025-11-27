package tuti.daos.accesoDatos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuti.daos.entidades.Preparacion;

@Repository
public interface RacionRepositorio extends JpaRepository<Preparacion, Integer> {

    // Todas las raciones NO eliminadas
    List<Preparacion> findByEliminadoFalse();

    // Raciones de una receta concreta NO eliminadas
    List<Preparacion> findByRecetaIdAndEliminadoFalse(Integer recetaId);
}