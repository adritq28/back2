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
        Optional<Cultivo> existingRecord = cultivoRepository.findById(request.getIdCultivo());

        if (existingRecord.isPresent()) {
            Cultivo cultivo = existingRecord.get();
            cultivo.setNombre(request.getNombre());
            cultivo.setFechaSiembra(request.getFechaSiembra());
            cultivo.setFechaReg(request.getFechaReg());
            cultivo.setTipo(request.getTipo());
            cultivo.setEdit(request.getEdit() != null ? request.getEdit() : false);
            cultivo.setDelete(request.getDelete() != null ? request.getDelete() : false);

            cultivoRepository.save(cultivo);
        } else {
            throw new EntityNotFoundException("Registro no encontrado con el ID: " + request.getIdCultivo());
        }
    }

    public boolean eliminarCultivo(int idCultivo) {
        Optional<Cultivo> CultivoOptional = cultivoRepository.findById(idCultivo);
        if (CultivoOptional.isPresent()) {
            Cultivo Cultivo = CultivoOptional.get();
            Cultivo.setDelete(true);
            cultivoRepository.save(Cultivo);
            return true;
        } else {
            return false;
        }
    }
}
