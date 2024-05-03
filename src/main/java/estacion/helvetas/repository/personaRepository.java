package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.persona;

public interface personaRepository extends JpaRepository<persona, Integer> {
    // Buscar personas por nombre
    List<persona> findByNombre(String nombre);

    // List<persona> findAll();

    Optional<persona> findById(int idPersona);

    // persona findTopByOrderByIdPersonaDesc();
    // Optional<persona> findByCi(String ci);

}