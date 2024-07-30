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

        @Query(

        "SELECT m.idMunicipio,c.nombre, c.tipo, c.fechaSiembra, " +
                        "f.idFenologia, f.fase, f.descripcion, f.idCultivo, f.nroDias, " +
                        "u.tempMax, u.tempMin, u.pcpn, u.tempOpt, u.umbInf, u.umbSup " +
                        "FROM Umbrales u " +
                        "JOIN Fenologia f ON  f.idFenologia = u.idFenologia " +
                        "JOIN Cultivo c ON  f.idCultivo = c.idCultivo " +
                        "JOIN Zona z ON c.idZona = z.idZona " +
                        "join Municipio m on m.idMunicipio = z.idMunicipio " +
                        "where c.idCultivo = :idCultivo")

        List<Object[]> obtenerFenologia(@Param("idCultivo") int idCultivo);

        @Query("select normal from Precipitacion where idFenologia = :idFenologia")

        List<Object[]> obtenerNormal(@Param("idFenologia") int idFenologia);

}
