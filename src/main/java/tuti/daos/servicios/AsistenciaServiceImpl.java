package tuti.daos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.daos.Excepciones.Excepcion;
import tuti.daos.accesoDatos.AsistenciasRepositorio;
import java.util.Optional;
import tuti.daos.entidades.EntregaAsistencia;
import tuti.daos.presentacion.asistencias.AsistenciasRequestDTO;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciasRepositorio repo;

    @Override
    public List<EntregaAsistencia> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<EntregaAsistencia> getById(int id) {
        return repo.findById(id);
    }

    @Override
    public EntregaAsistencia add(AsistenciasRequestDTO request) {

        EntregaAsistencia nuevo = new EntregaAsistencia();
        nuevo.setIdAsistido(request.getIdAsistido());
        nuevo.setIdRacionEntregada(request.getIdRacionEntregada());
        nuevo.setFechaEntrega(request.getFechaEntrega());

        // VALIDACIÓN CON S04 
        /*
        PreparacionDTO prep = restTemplate.getForObject(
            "http://S04/preparaciones/" + request.getIdRacionEntregada(),
            PreparacionDTO.class
        );

        if (request.getFechaEntrega().after(prep.getFechaVencimiento())) {
            throw new Excepcion("Asistencias", "La fecha de entrega no puede superar el vencimiento", 400);
        }
        */

        return repo.save(nuevo);
    }

    @Override
    public EntregaAsistencia update(Integer id, AsistenciasRequestDTO request) {

        EntregaAsistencia existente = repo.findById(id)
                .orElseThrow(() -> new Excepcion("Asistencias", "No existe una asistencia con ese id", 404));

        existente.setIdAsistido(request.getIdAsistido());
        existente.setIdRacionEntregada(request.getIdRacionEntregada());
        existente.setFechaEntrega(request.getFechaEntrega());

        // VALIDACIÓN CON S04 
        /*
        PreparacionDTO prep = restTemplate.getForObject(
            "http://S04/preparaciones/" + request.getIdRacionEntregada(),
            PreparacionDTO.class
        );

        if (request.getFechaEntrega().after(prep.getFechaVencimiento())) {
            throw new Excepcion("Asistencias", "La fecha de entrega no puede superar el vencimiento", 400);
        }
        */

        return repo.save(existente);
    }
}
