package estacion.helvetas.services;

import estacion.helvetas.model.DatosPronostico;

public interface IDatosPronosticoService {

    void guardar(DatosPronostico pronostico);

    DatosPronostico buscarPorId(int idPronostico);

}
