package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Fenologia;

public interface FenologiaRepository extends JpaRepository<Fenologia, Integer> {

        Optional<Fenologia> findByIdFenologia(int id);

        // Optional<Fenologia> findByIdCultivo(int id);

        List<Fenologia> findByIdCultivo(int cultivoId);

        @Query("SELECT f FROM Fenologia f WHERE f.idCultivo = :cultivoId ORDER BY f.idFenologia ASC")
        List<Fenologia> findByIdCultivoOrdered(@Param("cultivoId") int cultivoId);

        @Query(

        "SELECT m.idMunicipio,c.nombre, c.tipo, c.fechaSiembra, " +
                        "f.idFenologia, f.fase, f.descripcion, f.idCultivo, f.nroDias, " +
                        "u.tempMax, u.tempMin, u.pcpn, u.tempOptMin, u.umbInf, u.umbSup, f.imagen, u.tempOptMax " +
                        "FROM Umbrales u " +
                        "JOIN Fenologia f ON  f.idFenologia = u.idFenologia " +
                        "JOIN Cultivo c ON  f.idCultivo = c.idCultivo " +
                        "JOIN Zona z ON c.idZona = z.idZona " +
                        "join Municipio m on m.idMunicipio = z.idMunicipio " +
                        "where c.idCultivo = :idCultivo order by f.fase asc")

        List<Object[]> obtenerFenologia(@Param("idCultivo") int idCultivo);

        @Query("select normal from Precipitacion where idFenologia = :idFenologia")

        List<Object[]> obtenerNormal(@Param("idFenologia") int idFenologia);

}
