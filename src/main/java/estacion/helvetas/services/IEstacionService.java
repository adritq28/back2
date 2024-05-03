package estacion.helvetas.services;

import estacion.helvetas.model.Estacion;

public interface IEstacionService {

    void guardar(Estacion estacion);

    // List<Estacion> buscarPorCompleto(String completo);

    void deleteById(int idEstacion);

}
