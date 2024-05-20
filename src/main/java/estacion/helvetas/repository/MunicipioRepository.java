package estacion.helvetas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    // List<persona> findAll();

    Optional<Municipio> findById(int idMunicipio);
}
