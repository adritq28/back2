package estacion.helvetas.services;

import java.util.List;

import estacion.helvetas.model.Observador;

public interface IObservadorService {

    void guardar(Observador observador);

    List<Observador> buscarPorCompleto(String completo);

    Observador buscarPorIdRole(int idObservador);

    void eliminarObservadorId(int idObservador);

    void deleteObservador(int idObservador);

}