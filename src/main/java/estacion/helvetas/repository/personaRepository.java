package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.persona;

public interface personaRepository extends JpaRepository<persona, Integer> {

    List<persona> findByNombre(String nombre);

    Optional<persona> findById(int idPersona);

}