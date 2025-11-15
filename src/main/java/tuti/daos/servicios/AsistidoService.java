package tuti.daos.servicios;



import java.util.List;
import java.util.Optional;

import tuti.daos.Excepciones.Excepcion;
import tuti.daos.entidades.Asistido;
import tuti.daos.presentacion.asistido.AsistidoRequestDTO;


public interface AsistidoService {
	


	List<Asistido> getAll();

	void delete(int id);

	Optional<Asistido> getById(int idAsistido) throws Excepcion;

	Boolean existeNombre(String nombreCompleto);

	Boolean existeDni(int dni);
    
	public Asistido add(AsistidoRequestDTO request);
    
	public Asistido update(Integer id,AsistidoRequestDTO request);










}
