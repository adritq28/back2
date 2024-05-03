package estacion.helvetas.services;

import estacion.helvetas.model.DatosEstacion;

public interface IDatosEstacion {

    void guardar(DatosEstacion estacion);

    // List<Estacion> buscarPorCompleto(String completo);

    DatosEstacion buscarPorIdDatosEstacion(int idDatosEstacion);

    void eliminarDatosEstacionId(int idDatosEstacion);

    void deleteDatosEstacion(int idDatosEstacion);

}
