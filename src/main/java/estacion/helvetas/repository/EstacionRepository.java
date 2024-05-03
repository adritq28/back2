package estacion.helvetas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.Estacion;

public interface EstacionRepository extends JpaRepository<Estacion, Integer> {

    // List<persona> findAll();

    Optional<Estacion> findById(int idEstacion);
}
