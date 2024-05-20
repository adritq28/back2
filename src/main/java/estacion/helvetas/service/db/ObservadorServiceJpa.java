package estacion.helvetas.service.db;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.Observador;
import estacion.helvetas.services.IObservadorService;

@Service
@Primary
public class ObservadorServiceJpa implements IObservadorService {

    @Override
    public void guardar(Observador observador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public List<Observador> buscarPorCompleto(String completo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorCompleto'");
    }

    @Override
    public Observador buscarPorIdRole(int idObservador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorIdRole'");
    }

    @Override
    public void eliminarObservadorId(int idObservador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarObservadorId'");
    }

    @Override
    public void deleteObservador(int idObservador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteObservador'");
    }

}
