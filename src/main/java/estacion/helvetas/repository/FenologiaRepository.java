package estacion.helvetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Fenologia;

public interface FenologiaRepository extends JpaRepository<Fenologia, Integer> {

        @Query(

        "SELECT m.idMunicipio,c.nombre, c.tipo, c.fechaSiembra, " +
                        "f.idFenologia, f.fase, f.descripcion, f.idCultivo, f.nroDias, " +
                        "u.tempMax, u.tempMin, u.pcpn " +
                        "FROM Umbrales u " +
                        "JOIN Fenologia f ON  f.idFenologia = u.idFenologia " +
                        "JOIN Cultivo c ON  f.idCultivo = c.idCultivo " +
                        "JOIN Zona z ON c.idZona = z.idZona " +
                        "join Municipio m on m.idMunicipio = z.idMunicipio " +
                        "where z.idZona = :idZona")

        List<Object[]> obtenerFenologia(@Param("idZona") int idZona);

}
