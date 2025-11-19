package tuti.daos.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.daos.accesoDatos.RecetaRepository;
import tuti.daos.entidades.Receta;
import tuti.daos.presentacion.receta.RecetaReqDTO;
import tuti.daos.presentacion.receta.RecetaResDTO;

@Service
public class RecetaServiceImpl implements RecetaService {
	
	@Autowired
	private RecetaRepository recetaRepository;

	@Override
	public List<RecetaResDTO> findAll() {
		return recetaRepository.findAll().stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	
	// LEER
	@Override
	public RecetaResDTO findById(Integer id) {
	    Receta receta = recetaRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("No hay una receta con el id: " + id));
	    return convertToDTO(receta);
	}

	
	// CREAR
	@Override
	public RecetaResDTO create(RecetaReqDTO recetaReqDTO) {
		Receta receta = new Receta();
		
		receta.setNombre(recetaReqDTO.getNombre());
		receta.setDescripcion(recetaReqDTO.getDescripcion());
		receta.setEliminada(false);
		
		Receta guardarReceta = recetaRepository.save(receta);
		return convertToDTO(guardarReceta);
	}

	
	// ACTUALIZAR
	@Override
	public RecetaResDTO update(Integer id, RecetaReqDTO recetaReqDTO) {
		Receta receta = recetaRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("No hay una receta con el id: " + id));;
		
		receta.setNombre(recetaReqDTO.getNombre());
		receta.setDescripcion(recetaReqDTO.getDescripcion());
		receta.setEliminada(false);
		
		Receta actualizarReceta = recetaRepository.save(receta);
		return convertToDTO(actualizarReceta);
	}

	
	//ELIMINAR
	@Override
	public void delete(Integer id) {
		recetaRepository.deleteById(id);
	}
	
	
	private RecetaResDTO convertToDTO(Receta receta) {
		return new RecetaResDTO(
					receta.getId(),
					receta.getNombre(),
					receta.getDescripcion(),
					receta.getEliminada()
				);
	}
}
