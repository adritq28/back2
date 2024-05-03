package estacion.helvetas.services;

import estacion.helvetas.model.Estacion;

public interface IEstacionService {

    void guardar(Estacion estacion);

    // List<Estacion> buscarPorCompleto(String completo);

    Estacion buscarPorIdEstacion(int idEstacion);

    void eliminarEstacionId(int idEstacion);

    void deleteEstacion(int idEstacion);

}
