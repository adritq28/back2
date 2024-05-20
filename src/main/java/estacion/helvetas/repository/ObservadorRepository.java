package estacion.helvetas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.Observador;

public interface ObservadorRepository extends JpaRepository<Observador, Integer> {

    Optional<Observador> findById(int idObservador);

}
