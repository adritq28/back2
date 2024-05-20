package estacion.helvetas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {

    // List<persona> findAll();

    Optional<Provincia> findById(int idProvincia);
}
