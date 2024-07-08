package estacion.helvetas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.Umbrales;

public interface UmbralRepository extends JpaRepository<Umbrales, Integer> {

        // Optional<Umbrales> findByIdPronostico(Long id);

        Optional<Umbrales> findByIdFenologia(int integer);

        // Optional<Umbrales> findByFenologiaId(Long fenologiaId);

}
