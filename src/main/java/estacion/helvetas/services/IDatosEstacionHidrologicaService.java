package estacion.helvetas.services;

import estacion.helvetas.model.DatosEstacionHidrologica;

public interface IDatosEstacionHidrologicaService {

    void guardar(DatosEstacionHidrologica estacion);

    DatosEstacionHidrologica buscarPorIdHidrologica(int idDatosEstacion);

    void eliminarDatosEstacionId(int idDatosEstacion);

    void deleteById(int idDatosEstacion);

}
