package estacion.helvetas.service.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.Estacion;
import estacion.helvetas.repository.EstacionRepository;
import estacion.helvetas.services.IEstacionService;

@Service
@Primary
public class EstacionServiceJpa implements IEstacionService {

    @Autowired
    private EstacionRepository estacionRepo;

    @Override
    public Estacion buscarPorIdEstacion(int idEstacion) {
        Optional<Estacion> estacion = estacionRepo.findById(idEstacion);
        return estacion.get();
    }

    @Override
    public void guardar(Estacion estacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminarEstacionId(int idEstacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarEstacionId'");
    }

    @Override
    public void deleteEstacion(int idEstacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEstacion'");
    }

}
