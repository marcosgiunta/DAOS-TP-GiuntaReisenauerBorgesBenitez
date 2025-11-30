package tuti.daos.servicios;

import java.util.List;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.daos.Excepciones.Excepcion;
import tuti.daos.accesoDatos.AsistenciasRepositorio;
import java.util.Optional;
import tuti.daos.entidades.EntregaAsistencia;
import tuti.daos.entidades.Asistido;
import tuti.daos.presentacion.asistencias.AsistenciasRequestDTO;


@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciasRepositorio repo;

    @Autowired
    private AsistidoService asistidoService;

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

        // Buscar el asistido por ID
        Asistido asistido = asistidoService.getById(request.getAsistido())
                .orElseThrow(() -> new Excepcion("Asistencias", "No existe un asistido con el ID proporcionado", 404));

        if (request.getFechaEntrega() == null) {
            throw new Excepcion("Asistencia", "La fecha de entrega no puede ser nula", 400);
        }

        if (request.getFechaEntrega().isAfter(LocalDate.now())) {
            throw new Excepcion("Asistencia", "La fecha de entrega no puede ser futura", 400);
        }

        // Crear entidad
        EntregaAsistencia nuevo = new EntregaAsistencia();
        nuevo.setAsistido(asistido);
        nuevo.setIdRacionEntregada(request.getIdRacionEntregada());
        nuevo.setFechaEntrega(request.getFechaEntrega());

        return repo.save(nuevo);
    }


    @Override
    public EntregaAsistencia update(Integer id, AsistenciasRequestDTO request) {

        EntregaAsistencia existente = repo.findById(id)
                .orElseThrow(() -> new Excepcion("Asistencias", "No existe una asistencia con ese id", 404));

        // Buscar el asistido por ID
        Asistido asistido = asistidoService.getById(request.getAsistido())
                .orElseThrow(() -> new Excepcion("Asistencias", "No existe un asistido con el ID proporcionado", 404));

        existente.setAsistido(asistido);
        existente.setIdRacionEntregada(request.getIdRacionEntregada());
        existente.setFechaEntrega(request.getFechaEntrega());

        if (request.getFechaEntrega() == null) {
            throw new Excepcion("Asistencia", "La fecha de entrega no puede ser nula", 400);
        }

        if (request.getFechaEntrega().isAfter(LocalDate.now())) {
            throw new Excepcion("Asistencia", "La fecha de entrega no puede ser futura", 400);
        }

        return repo.save(existente);
    }

    public List<EntregaAsistencia> findByAsistidoId(Integer id) {
    return repo.findByAsistidoId(id);
    }
}
