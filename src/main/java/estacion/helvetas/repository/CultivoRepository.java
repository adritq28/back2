package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Cultivo;

public interface CultivoRepository extends JpaRepository<Cultivo, Integer> {

    Optional<Cultivo> findById(int idCultivo);

    List<Cultivo> findByIdZona(int idZona);

    @Query("select c.fechaSiembra, c.nombre, c.idCultivo, c.delete, c.fechaReg, c.tipo " +
            "from Cultivo c join Zona z on c.idZona = z.idZona " +
            "where z.idZona = :idZona order by c.idCultivo desc")
    List<Object[]> listaDatosFechaSiembra(@Param("idZona") int idZona);

}
