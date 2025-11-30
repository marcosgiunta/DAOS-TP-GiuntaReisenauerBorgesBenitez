package tuti.daos.servicios;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.daos.Excepciones.Excepcion;
import tuti.daos.accesoDatos.AsistenciasRepositorio;
import tuti.daos.accesoDatos.RacionRepositorio;
import java.util.Optional;
import tuti.daos.entidades.EntregaAsistencia;
import tuti.daos.entidades.Asistido;
import tuti.daos.entidades.Preparacion;
import tuti.daos.presentacion.asistencias.AsistenciasRequestDTO;
import tuti.daos.presentacion.racion.RacionResDTO;


@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    @Autowired
    private AsistenciasRepositorio repo;

    @Autowired
    private AsistidoService asistidoService;

    @Autowired
    private RacionService racionService;

    @Autowired
    private RacionRepositorio racionRepositorio;

    @Override
    public List<EntregaAsistencia> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Integer id) {
        EntregaAsistencia entrega = repo.findById(id)
                .orElseThrow(() -> new Excepcion("Asistencias", "No existe una asistencia con ese id", 404));

        // Sumar 1 al stock de la preparación cuando se elimina una entrega
        Integer idRacion = entrega.getIdRacionEntregada();
        Preparacion preparacion = racionRepositorio.findById(idRacion)
                .orElseThrow(() -> new Excepcion("Asistencia", "La preparación asociada no existe", 404));
        
        preparacion.setStockRacionesRestantes(preparacion.getStockRacionesRestantes() + 1);
        racionRepositorio.save(preparacion);

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

        if (request.getFechaEntrega().after(new Date())) {  
            throw new Excepcion("Asistencia", "La fecha de entrega no puede ser futura", 400);
        }

        // Validar que la fecha de entrega no sea posterior a la fecha de vencimiento de la preparación
        RacionResDTO racion = racionService.findById(request.getIdRacionEntregada());
        Date fechaVencimiento = racion.getFechaVencimiento();
        
        if (request.getFechaEntrega().after(fechaVencimiento)) {
            
            throw new Excepcion("Asistencia", "La fecha de entrega no puede ser posterior a la fecha de vencimiento de la preparación", 400);
        }

        // Validar y descontar stock de la preparación
        Preparacion preparacion = racionRepositorio.findById(request.getIdRacionEntregada())
                .orElseThrow(() -> new Excepcion("Asistencia", "La preparación indicada no existe", 404));
        
        if (preparacion.getStockRacionesRestantes() == null || preparacion.getStockRacionesRestantes() <= 0) {
            throw new Excepcion("Asistencia", "No hay stock disponible para esta preparación", 400);
        }

        // Descontar 1 del stock restante
        preparacion.setStockRacionesRestantes(preparacion.getStockRacionesRestantes() - 1);
        racionRepositorio.save(preparacion);

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

        if (request.getFechaEntrega() == null) {
            throw new Excepcion("Asistencia", "La fecha de entrega no puede ser nula", 400);
        }

        if (request.getFechaEntrega().after(new Date())) {  
            throw new Excepcion("Asistencia", "La fecha de entrega no puede ser futura", 400);
        }

        // Validar que la fecha de entrega no sea posterior a la fecha de vencimiento de la preparación
        RacionResDTO racion = racionService.findById(request.getIdRacionEntregada());
        Date fechaVencimiento = racion.getFechaVencimiento();
        
        if (request.getFechaEntrega().after(fechaVencimiento)) {
            throw new Excepcion("Asistencia", "La fecha de entrega no puede ser posterior a la fecha de vencimiento de la preparación", 400);
        }

        // Manejar cambio de ración: si cambió, sumar a la vieja y descontar de la nueva
        Integer idRacionVieja = existente.getIdRacionEntregada();
        Integer idRacionNueva = request.getIdRacionEntregada();

        if (!idRacionVieja.equals(idRacionNueva)) {
            // Sumar 1 al stock de la preparación vieja
            Preparacion preparacionVieja = racionRepositorio.findById(idRacionVieja)
                    .orElseThrow(() -> new Excepcion("Asistencia", "La preparación anterior no existe", 404));
            preparacionVieja.setStockRacionesRestantes(preparacionVieja.getStockRacionesRestantes() + 1);
            racionRepositorio.save(preparacionVieja);

            // Validar y descontar 1 del stock de la preparación nueva
            Preparacion preparacionNueva = racionRepositorio.findById(idRacionNueva)
                    .orElseThrow(() -> new Excepcion("Asistencia", "La preparación indicada no existe", 404));
            
            if (preparacionNueva.getStockRacionesRestantes() == null || preparacionNueva.getStockRacionesRestantes() <= 0) {
                throw new Excepcion("Asistencia", "No hay stock disponible para esta preparación", 400);
            }

            preparacionNueva.setStockRacionesRestantes(preparacionNueva.getStockRacionesRestantes() - 1);
            racionRepositorio.save(preparacionNueva);
        }

        existente.setAsistido(asistido);
        existente.setIdRacionEntregada(request.getIdRacionEntregada());
        existente.setFechaEntrega(request.getFechaEntrega());

        return repo.save(existente);
    }

    public List<EntregaAsistencia> findByAsistidoId(Integer id) {
    return repo.findByAsistidoId(id);
    }
}
