package estacion.helvetas.service.db;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.Cultivo;
import estacion.helvetas.repository.CultivoRepository;
import estacion.helvetas.services.ICultivoService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Primary
public class CultivoServiceJpa implements ICultivoService {

    @Autowired
    private CultivoRepository cultivoRepository;

    public Cultivo actualizarFechaSiembra(int idCultivo, Date nuevaFechaSiembra) {
        Optional<Cultivo> optionalCultivo = cultivoRepository.findById(idCultivo);
        if (optionalCultivo.isPresent()) {
            Cultivo cultivo = optionalCultivo.get();
            cultivo.setFechaSiembra(nuevaFechaSiembra);
            return cultivoRepository.save(cultivo);
        } else {
            throw new NoSuchElementException("No se encontró el cultivo con ID " + idCultivo);
        }
    }

    public void editarCultivo(Cultivo request) {
        // Buscar el registro existente por ID
        Optional<Cultivo> existingRecord = cultivoRepository.findById(request.getIdCultivo());

        if (existingRecord.isPresent()) {
            Cultivo cultivo = existingRecord.get();
            // Actualizar los campos con los valores del request
            cultivo.setNombre(request.getNombre());
            cultivo.setFechaSiembra(request.getFechaSiembra());
            cultivo.setFechaReg(request.getFechaReg());
            cultivo.setTipo(request.getTipo());
            cultivo.setEdit(true); // Establecer el campo editar en true
            cultivo.setDelete(false); // Asumiendo que getDelete devuelve Boolean

            // Guardar la entidad actualizada en la base de datos
            cultivoRepository.save(cultivo);
        } else {
            throw new EntityNotFoundException("Registro no encontrado con el ID: " + request.getIdCultivo());
        }
    }

    public boolean eliminarCultivo(int idCultivo) {
        Optional<Cultivo> CultivoOptional = cultivoRepository.findById(idCultivo);
        if (CultivoOptional.isPresent()) {
            Cultivo Cultivo = CultivoOptional.get();
            // Realizar el marcado de eliminación lógica aquí
            Cultivo.setDelete(true); // Suponiendo un campo 'eliminado' en tu entidad
            cultivoRepository.save(Cultivo);
            return true;
        } else {
            return false;
        }
    }
}
