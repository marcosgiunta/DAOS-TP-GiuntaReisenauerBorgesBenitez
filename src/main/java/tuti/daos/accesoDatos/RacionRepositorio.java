package tuti.daos.accesoDatos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuti.daos.entidades.Preparacion;

@Repository
public interface RacionRepositorio extends JpaRepository<Preparacion, Integer> {

    // Para obtener todas las preparaciones (raciones) de una receta concreta
    List<Preparacion> findByRecetaId(Integer recetaId);
}