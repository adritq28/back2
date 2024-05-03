package estacion.helvetas.services;

import java.util.List;

import estacion.helvetas.model.persona;

public interface IPersonaServices {

    void guardar(persona persona);

    List<persona> buscarPorCompleto(String completo);

    persona buscarPorIdPersona(int idPersona);

    // persona buscarPorCiPersona(int ciPersona);

    void eliminarPersonaId(int idPersona);

    void deletePersona(int idPersona);

}