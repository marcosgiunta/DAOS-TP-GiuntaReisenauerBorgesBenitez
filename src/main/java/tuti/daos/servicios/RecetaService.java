package tuti.daos.servicios;

import java.util.List;

import tuti.daos.presentacion.receta.RecetaReqDTO;
import tuti.daos.presentacion.receta.RecetaResDTO;

public interface RecetaService {
	List<RecetaResDTO> findAll();
	RecetaResDTO findById(Integer id);
	RecetaResDTO create(RecetaReqDTO recetaReqDTO);
	RecetaResDTO update(Integer id, RecetaReqDTO recetaReqDTO);
	void delete(Integer id);
}
