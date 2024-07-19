package estacion.helvetas.service.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.DatosEstacionHidrologica;
import estacion.helvetas.repository.DatosEstacionHidrologicaRepository;
import estacion.helvetas.services.IDatosEstacionHidrologicaService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Primary
public class DatosEstacionHidrologicaServiceJpa implements IDatosEstacionHidrologicaService {

    @Autowired
    private DatosEstacionHidrologicaRepository datosEstacionRepo;

    // @Autowired
    // private DatosEstacionDTORepository datosEstacionRepo2;

    @Override
    public void guardar(DatosEstacionHidrologica estacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminarDatosEstacionId(int idDatosEstacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarDatosEstacionId'");
    }

    public DatosEstacionHidrologica obtenerDatosEstacionPorId(int id) {
        Optional<DatosEstacionHidrologica> datosEstacionOptional = datosEstacionRepo.findById(id);
        return datosEstacionOptional.orElse(null);
    }

    public void guardarDatosEstacion(DatosEstacionHidrologica datosEstacion) {
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

    public void editarHidrologica(DatosEstacionHidrologica request) {
        // Buscar el registro existente por ID
        Optional<DatosEstacionHidrologica> existingRecord = datosEstacionRepo
                .findByIdHidrologica(request.getIdHidrologica());

        if (existingRecord.isPresent()) {
            DatosEstacionHidrologica Hidrologica = existingRecord.get();
            // Actualizar los campos con los valores del request
            Hidrologica.setLimnimetro(request.getLimnimetro());
            Hidrologica.setFechaReg(request.getFechaReg());
            Hidrologica.setEdit(true); // Establecer el campo editar en true
            Hidrologica.setDelete(false); // Asumiendo que getDelete devuelve Boolean

            // Guardar la entidad actualizada en la base de datos
            datosEstacionRepo.save(Hidrologica);
        } else {
            throw new EntityNotFoundException("Registro no encontrado con el ID: " + request.getIdHidrologica());
        }
    }

    public boolean eliminarDatosHidrologicaEstacion(int idHidrologica) {
        Optional<DatosEstacionHidrologica> datosEstacionOptional = datosEstacionRepo.findByIdHidrologica(idHidrologica);
        if (datosEstacionOptional.isPresent()) {
            DatosEstacionHidrologica datosEstacion = datosEstacionOptional.get();
            // Realizar el marcado de eliminación lógica aquí
            datosEstacion.setDelete(true); // Suponiendo un campo 'eliminado' en tu entidad
            datosEstacionRepo.save(datosEstacion);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DatosEstacionHidrologica buscarPorIdHidrologica(int idDatosEstacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorIdHidrologica'");
    }

}
