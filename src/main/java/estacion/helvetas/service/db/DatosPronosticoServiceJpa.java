package estacion.helvetas.service.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.DatosPronostico;
import estacion.helvetas.repository.DatosPronosticoRepository;
import estacion.helvetas.services.IDatosPronosticoService;

@Service
@Primary
public class DatosPronosticoServiceJpa implements IDatosPronosticoService {

    @Autowired
    private DatosPronosticoRepository datosPronosticoRepo;

    public DatosPronostico buscarPorIdDatosPronostico(int idDatosPronostico) {
        Optional<DatosPronostico> datosPronostico = datosPronosticoRepo.findById(idDatosPronostico);
        return datosPronostico.get();
    }

    public void guardarDatosPronostico(DatosPronostico datosPronostico) {
        datosPronosticoRepo.save(datosPronostico);
    }

    @Override
    public void guardar(DatosPronostico pronostico) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public DatosPronostico buscarPorId(int idPronostico) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    // public List<Long> getUmbralesByZona(int idZona) {
    // return datosPronosticoRepo.findUmbralesId(idZona);
    // }
}
