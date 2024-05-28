package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import estacion.helvetas.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

    // List<persona> findAll();

    Optional<Municipio> findById(int idMunicipio);

    @Query("SELECT m.idMunicipio, m.nombre FROM Municipio m")
    List<Object[]> obtenerIdNombreMunicipios();

}
