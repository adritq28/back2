package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Estacion;

public interface EstacionRepository extends JpaRepository<Estacion, Integer> {

    // List<persona> findAll();

    Optional<Estacion> findById(int idEstacion);

    @Query("SELECT e.nombre, e.idMunicipio, e.tipoEstacion FROM Estacion e WHERE idMunicipio = :idMunicipio")
    List<Object[]> obtenerEstacion(@Param("idMunicipio") int idMunicipio);

}
