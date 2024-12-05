package estacion.helvetas.service.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.Estacion;
import estacion.helvetas.model.Zona;
import estacion.helvetas.repository.ZonaRepository;
import estacion.helvetas.services.IEstacionService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Primary
public class ZonaServiceJpa implements IEstacionService {

    @Autowired
    private ZonaRepository zonaRepo;

    public void eliminarEstacion(int id) {
        zonaRepo.deleteById(id);
    }

    @Override
    public void guardar(Estacion estacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void deleteById(int idEstacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    public void editarZona(Zona request) {
        // Buscar el registro existente por ID
        Optional<Zona> existingRecord = zonaRepo.findByIdZona(request.getIdZona());

        if (existingRecord.isPresent()) {
            Zona estacion = existingRecord.get();
            estacion.setNombre(request.getNombre());
            estacion.setIdMunicipio(request.getIdMunicipio());
            // estacion.setLatitud(request.getLatitud());
            // estacion.setLongitud(request.getLongitud());

            // estacion.setCul(request.getTipoEstacion());
            // estacion.setEditar(true); // Establecer el campo editar en true
            // estacion.setDelete(false); // Asumiendo que getDelete devuelve Boolean

            // Guardar la entidad actualizada en la base de datos
            zonaRepo.save(estacion);
        } else {
            throw new EntityNotFoundException("Registro no encontrado con el ID: " + request.getIdZona());
        }
    }

}
