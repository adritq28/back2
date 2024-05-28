package estacion.helvetas.service.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.DatosEstacion;
import estacion.helvetas.repository.DatosEstacionRepository;
import estacion.helvetas.services.IDatosEstacionService;

@Service
@Primary
public class DatosEstacionServiceJpa implements IDatosEstacionService {

    @Autowired
    private DatosEstacionRepository datosEstacionRepo;

    // @Autowired
    // private DatosEstacionDTORepository datosEstacionRepo2;

    @Override
    public DatosEstacion buscarPorIdDatosEstacion(int idDatosEstacion) {
        Optional<DatosEstacion> datosEstacion = datosEstacionRepo.findById(idDatosEstacion);
        return datosEstacion.get();
    }

    @Override
    public void guardar(DatosEstacion estacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminarDatosEstacionId(int idDatosEstacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarDatosEstacionId'");
    }

    public DatosEstacion obtenerDatosEstacionPorId(int id) {
        Optional<DatosEstacion> datosEstacionOptional = datosEstacionRepo.findById(id);
        return datosEstacionOptional.orElse(null);
    }

    public void guardarDatosEstacion(DatosEstacion datosEstacion) {
        datosEstacionRepo.save(datosEstacion);
    }

    public void eliminarEstacion(int id) {
        datosEstacionRepo.deleteById(id);
    }

    @Override
    public void deleteById(int idDatosEstacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    // public Optional<DatosEstacionDTO> obtenerDatosEstacionPorId2(int id) {
    // return datosEstacionRepo2.obtenerDatosEstacionPorId2(id);
    // }

    // public DatosEstacionDTO obtenerDatosEstacionPorId2(int id) {
    // Optional<DatosEstacionDTO> datosEstacionDTOOptional =
    // datosEstacionRepo.findById(id);
    // return datosEstacionOptional.orElse(null);
    // }
}
