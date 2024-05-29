package estacion.helvetas.services;

import estacion.helvetas.model.DatosEstacionMeteorologica;

public interface IDatosEstacionService {

    void guardar(DatosEstacionMeteorologica estacion);

    DatosEstacionMeteorologica buscarPorIdDatosEstacion(int idDatosEstacion);

    void eliminarDatosEstacionId(int idDatosEstacion);

    void deleteById(int idDatosEstacion);

}
