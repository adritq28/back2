package estacion.helvetas.services;

import estacion.helvetas.model.DatosEstacion;

public interface IDatosEstacionService {

    void guardar(DatosEstacion estacion);

    DatosEstacion buscarPorIdDatosEstacion(int idDatosEstacion);

    void eliminarDatosEstacionId(int idDatosEstacion);

    void deleteById(int idDatosEstacion);

}
