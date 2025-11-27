package tuti.daos.servicios;

import java.util.List;

import tuti.daos.presentacion.racion.RacionReqDTO;
import tuti.daos.presentacion.racion.RacionResDTO;

public interface RacionService {

    List<RacionResDTO> findAll();

    RacionResDTO findById(Integer id);

    RacionResDTO create(RacionReqDTO dto);

    RacionResDTO update(Integer id, RacionReqDTO dto);

    void delete(Integer id);

    List<RacionResDTO> findByRecetaId(Integer recetaId);
}
