package tuti.daos.servicios;

import java.util.List;
import java.util.Optional;

import tuti.daos.Excepciones.Excepcion;
import tuti.daos.entidades.EntregaAsistencia;
import tuti.daos.presentacion.asistencias.AsistenciasRequestDTO;


public interface AsistenciaService {
	
	List<EntregaAsistencia> getAll();

	void delete(Integer id);

	Optional<EntregaAsistencia> getById(int idAsistencia) throws Excepcion;
    
	public EntregaAsistencia add(AsistenciasRequestDTO request);
    
	public EntregaAsistencia update(Integer id, AsistenciasRequestDTO request);
	
	public List<EntregaAsistencia> findByAsistidoId(Integer id);

}
