package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import estacion.helvetas.model.Cultivo;

public interface CultivoRepository extends JpaRepository<Cultivo, Integer> {

    Optional<Cultivo> findById(int idCultivo);

    List<Cultivo> findByIdZona(int idZona);

}
