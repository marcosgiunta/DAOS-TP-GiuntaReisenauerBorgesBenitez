package tuti.daos.accesoDatos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tuti.daos.entidades.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Integer> {
	Optional<Receta> findByNombre(String nombre);
	boolean existsByNombre(String nombre);
}
