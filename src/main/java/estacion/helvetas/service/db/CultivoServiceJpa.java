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
}
