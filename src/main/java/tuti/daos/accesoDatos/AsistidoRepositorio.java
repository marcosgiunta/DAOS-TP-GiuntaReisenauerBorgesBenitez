package tuti.daos.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import tuti.daos.entidades.Asistido;

@Repository 
public interface AsistidoRepositorio extends JpaRepository<Asistido, Integer> {

	Asistido findByDni(Integer dni);


    @Query("SELECT a FROM Asistido a WHERE a.nombreCompleto = :nombreCompleto")
	Asistido existeNombreCompleto(String nombreCompleto);


	@Query("SELECT a FROM Asistido a WHERE a.dni = :dni")
	Asistido existeDni(int dni);
}
