package estacion.helvetas.service.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.DatosEstacionHidrologica;
import estacion.helvetas.model.DatosEstacionMeteorologica;
import estacion.helvetas.repository.DatosEstacionHidrologicaRepository;
import estacion.helvetas.repository.DatosEstacionMeteorologicaRepository;
import estacion.helvetas.services.IDatosEstacionService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Primary
public class DatosEstacionMeteorologicaServiceJpa implements IDatosEstacionService {

    @Autowired
    private DatosEstacionMeteorologicaRepository datosEstacionRepo;

    @Autowired
    private DatosEstacionHidrologicaRepository datosHidroRepo;

    // @Autowired
    // private DatosEstacionDTORepository datosEstacionRepo2;

    @Override
    public DatosEstacionMeteorologica buscarPorIdDatosEstacion(int idDatosEstacion) {
        Optional<DatosEstacionMeteorologica> datosEstacion = datosEstacionRepo.findById(idDatosEstacion);
        return datosEstacion.get();
    }

    @Override
    public void guardar(DatosEstacionMeteorologica estacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminarDatosEstacionId(int idDatosEstacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarDatosEstacionId'");
    }

    public DatosEstacionMeteorologica obtenerDatosEstacionPorId(int id) {
        Optional<DatosEstacionMeteorologica> datosEstacionOptional = datosEstacionRepo.findById(id);
        return datosEstacionOptional.orElse(null);
    }

    public void guardarDatosEstacion(DatosEstacionMeteorologica datosEstacion) {
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

    public void editarMeteorologica(DatosEstacionMeteorologica request) {
        // Buscar el registro existente por ID
        Optional<DatosEstacionMeteorologica> existingRecord = datosEstacionRepo
                .findByIdDatosEst(request.getIdDatosEst());

        if (existingRecord.isPresent()) {
            DatosEstacionMeteorologica meteorologica = existingRecord.get();
            // Actualizar los campos con los valores del request
            meteorologica.setTempMax(request.getTempMax());
            meteorologica.setTempMin(request.getTempMin());
            meteorologica.setPcpn(request.getPcpn());
            meteorologica.setTempAmb(request.getTempAmb());
            meteorologica.setDirViento(request.getDirViento());
            meteorologica.setVelViento(request.getVelViento());
            meteorologica.setTaevap(request.getTaevap());
            meteorologica.setFechaReg(request.getFechaReg());
            meteorologica.setEditar(true); // Establecer el campo editar en true
            meteorologica.setDelete(false); // Asumiendo que getDelete devuelve Boolean

            // Guardar la entidad actualizada en la base de datos
            datosEstacionRepo.save(meteorologica);
        } else {
            throw new EntityNotFoundException("Registro no encontrado con el ID: " + request.getIdDatosEst());
        }
    }

    public boolean eliminarDatosEstacion(int idDatosEst) {
        Optional<DatosEstacionMeteorologica> datosEstacionOptional = datosEstacionRepo.findByIdDatosEst(idDatosEst);
        if (datosEstacionOptional.isPresent()) {
            DatosEstacionMeteorologica datosEstacion = datosEstacionOptional.get();
            // Realizar el marcado de eliminación lógica aquí
            datosEstacion.setDelete(true); // Suponiendo un campo 'eliminado' en tu entidad
            datosEstacionRepo.save(datosEstacion);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarDatosHidrologicaEstacion(int idHidrologica) {
        Optional<DatosEstacionHidrologica> datosEstacionOptional = datosHidroRepo.findByIdHidrologica(idHidrologica);
        if (datosEstacionOptional.isPresent()) {
            DatosEstacionHidrologica datosEstacion = datosEstacionOptional.get();
            // Realizar el marcado de eliminación lógica aquí
            datosEstacion.setDelete(true); // Suponiendo un campo 'eliminado' en tu entidad
            datosHidroRepo.save(datosEstacion);
            return true;
        } else {
            return false;
        }
    }

}
