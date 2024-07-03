package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.DatosPronostico;

public interface DatosPronosticoRepository extends JpaRepository<DatosPronostico, Integer> {

        Optional<DatosPronostico> findByIdPronostico(Long id);

        // @Query("SELECT u.idUmbrales FROM Umbrales u " +
        // "JOIN Fenologia f ON f.idFenologia = u.idFenologia " +
        // "JOIN Cultivo c ON f.idCultivo = c.idCultivo " +
        // "JOIN Zona z ON c.idZona = z.idZona " +
        // "WHERE z.idZona = :idZona")
        // List<Long> findUmbralesId(int idZona);

        @Query("SELECT u.idUsuario, m.nombre, z.nombre, " +
                        "CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')), d.fecha, " +
                        "d.tempMin, d.tempMax, d.pcpn, d.idFenologia " +
                        "FROM Usuario u " +
                        "JOIN Promotor p ON u.idUsuario = p.idUsuario " +
                        "JOIN Zona z ON p.idZona = z.idZona " +
                        "JOIN DatosPronostico d ON d.idZona = z.idZona " +
                        "JOIN Municipio m ON m.idMunicipio = z.idMunicipio " +
                        "WHERE u.idUsuario = :idUsuario AND z.idZona = :idZona")

        List<Object[]> obtenerDatosPronostico(@Param("idUsuario") int idUsuario, @Param("idZona") int idZona);

        @Query("select p.idPronostico, p.idFenologia, p.idZona, f.idCultivo, u.idUmbrales, f.fase, p.tempMax, p.tempMin, p.pcpn, p.fecha, "
                        +
                        "f.descripcion, f.nroDias, u.tempMax, u.tempMin, u.pcpn " +
                        "from DatosPronostico p " +
                        "join Fenologia f on p.idFenologia = f.idFenologia " +
                        "join Umbrales u on u.idFenologia = f.idFenologia " +
                        "where f.idFenologia = :idFenologia and p.idZona = :idZona")

        List<Object[]> comparacionDatosPronostico(@Param("idFenologia") int idFenologia, @Param("idZona") int idZona);

}
