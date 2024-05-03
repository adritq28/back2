package estacion.helvetas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.DatosEstacion;

public interface DatosEstacionRepository extends JpaRepository<DatosEstacion, Integer> {

    Optional<DatosEstacion> findByIdDatosEst(Long id);

}
