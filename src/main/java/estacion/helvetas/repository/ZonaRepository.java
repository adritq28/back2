package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.Zona;

public interface ZonaRepository extends JpaRepository<Zona, Integer> {

        // Optional<Umbrales> findByIdPronostico(Long id);

        Optional<Zona> findByIdZona(int integer);

        List<Zona> findByIdMunicipio(int idMunicipio);

        // Optional<Umbrales> findByFenologiaId(Long fenologiaId);

}
