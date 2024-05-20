package estacion.helvetas.services;

import estacion.helvetas.model.Estacion;

public interface IEstacionService {

    void guardar(Estacion estacion);

    void deleteById(int idEstacion);

}
