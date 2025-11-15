package tuti.daos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.daos.Excepciones.Excepcion;
import tuti.daos.accesoDatos.AsistidoRepositorio;
import java.util.Optional;
import tuti.daos.entidades.Asistido;
import tuti.daos.entidades.Ciudad;
import tuti.daos.presentacion.asistido.AsistidoRequestDTO;

@Service
public class AsistidoServiceImpl implements AsistidoService {

	@Autowired
	private AsistidoRepositorio asistidoRepositorio;

	@Autowired
	private CiudadService ciudadServicio;

	@Override
	public List<Asistido> getAll() {

		return asistidoRepositorio.findAll();
	}

	@Override
	public void delete(int id) {
		asistidoRepositorio.deleteById(id);
	}

	@Override
	public Optional<Asistido> getById(int idAsistido) throws Excepcion {

		return asistidoRepositorio.findById(idAsistido);
	}

	@Override
	public Boolean existeNombre(String nombreCompleto) {

		Asistido asistido = asistidoRepositorio.existeNombreCompleto(nombreCompleto);

		if (asistido != null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean existeDni(int dni) {

		Asistido asistido = asistidoRepositorio.existeDni(dni);

		if (asistido != null) {
			return true;
		}
		return false;
	}

	@Override
	public Asistido add(AsistidoRequestDTO request) {

		Asistido nuevo = new Asistido();

		if (existeNombre(request.getNombreCompleto())) {
			throw new Excepcion("Asistido", "Ya existe un asistido con ese nombre completo", 400);
		}
		if (existeDni(request.getDni())) {
			throw new Excepcion("Asistido", "Ya existe un asistido con ese DNI", 400);
		}
		nuevo.setNombre(request.getNombreCompleto());
		nuevo.setDni(request.getDni());
		nuevo.setDomicilio(request.getDomicilio());
		nuevo.setEdad(request.getEdad());
		nuevo.setFechaNacimiento(request.getFechaNacimiento());

		if (request.getCiudadId() != null) { 
			Ciudad ciudad = ciudadServicio.buscarPorId(request.getCiudadId());


			nuevo.setCiudad(ciudad);
		}
		else{
			throw new Excepcion("Ciudad", "El asistido debe estar asociado a una ciudad", 400);
		}

		Asistido guardado = asistidoRepositorio.save(nuevo);

		return guardado;

	}

	@Override
	public Asistido update(Integer id,AsistidoRequestDTO request) {
		
			Asistido existente;

			if (!getById(id).isPresent()){
				throw new Excepcion("Asistido", "No existe un asistido con ese id", 404);
			}
			else{
				existente = getById(id).get();
			}


		if (existeNombre(request.getNombreCompleto())) {
				throw new Excepcion("Asistido", "Ya existe un asistido con ese nombre completo", 400);
			}
		if (existeDni(request.getDni())) {
				throw new Excepcion("Asistido", "Ya existe un asistido con ese DNI", 400);
			}
		existente.setNombre(request.getNombreCompleto());
		existente.setDni(request.getDni());
		existente.setDomicilio(request.getDomicilio());
		existente.setFechaNacimiento(request.getFechaNacimiento());


        if (request.getCiudadId() != null) {

		    Ciudad ciudad = ciudadServicio.buscarPorId(request.getCiudadId());

		    existente.setCiudad(ciudad);
		}
		else{
			throw new Excepcion("Ciudad", "El asistido debe estar asociado a una ciudad", 400);
		}

		Asistido actualizado = asistidoRepositorio.save(existente);

		return actualizado;
	}

}
