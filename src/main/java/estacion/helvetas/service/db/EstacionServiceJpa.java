package estacion.helvetas.service.db;

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

}
