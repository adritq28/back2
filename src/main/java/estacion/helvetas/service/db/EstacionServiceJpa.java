package estacion.helvetas.service.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.Estacion;
import estacion.helvetas.repository.EstacionRepository;
import estacion.helvetas.services.IEstacionService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Primary
public class EstacionServiceJpa implements IEstacionService {

    @Autowired
    private EstacionRepository estacionRepo;

    public void eliminarEstacion(int id) {
        estacionRepo.deleteById(id);
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

    public void editarEstacion(Estacion request) {
        // Buscar el registro existente por ID
        Optional<Estacion> existingRecord = estacionRepo.findByIdEstacion(request.getIdEstacion());

        if (existingRecord.isPresent()) {
            Estacion estacion = existingRecord.get();
            estacion.setNombre(request.getNombre());
            estacion.setIdMunicipio(request.getIdMunicipio());
            estacion.setTipoEstacion(request.getTipoEstacion());
            // estacion.setEditar(true); // Establecer el campo editar en true
            // estacion.setDelete(false); // Asumiendo que getDelete devuelve Boolean

            // Guardar la entidad actualizada en la base de datos
            estacionRepo.save(estacion);
        } else {
            throw new EntityNotFoundException("Registro no encontrado con el ID: " + request.getIdEstacion());
        }
    }

}
