package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.DatosPronostico;

public interface DatosPronosticoRepository extends JpaRepository<DatosPronostico, Integer> {

        Optional<DatosPronostico> findByIdPronostico(Integer id);

        List<DatosPronostico> findByIdCultivo(int idCultivo);

        // @Query("select p.tempMax, p.tempMin, p.pcpn, p.fecha, p.fechaRangoDecenal
        // from Cultivo c " +
        // "join DatosPronostico p on c.idCultivo = p.idCultivo " +
        // "where c.idCultivo = :idCultivo ORDER BY p.fecha desc limit 10")
        // List<Object[]> pronosticoCultivo(@Param("idCultivo") int idCultivo);

        @Query(value = "SELECT temp_max, temp_min, pcpn, fecha, fecha_rango_decenal " +
                        "FROM ( " +
                        "SELECT p.temp_max, p.temp_min, p.pcpn, p.fecha, p.fecha_rango_decenal " +
                        "FROM cultivo c " +
                        "JOIN datos_pronostico p ON c.id_cultivo = p.id_cultivo " +
                        "WHERE c.id_cultivo = :idCultivo " +
                        "ORDER BY p.id_pronostico DESC " +
                        "LIMIT 10 " +
                        ") AS subquery " +
                        "ORDER BY subquery.fecha_rango_decenal ASC", nativeQuery = true)
        List<Object[]> pronosticoCultivo(@Param("idCultivo") int idCultivo);

        @Query("SELECT u.idUsuario, m.nombre, z.nombre, " +
                        "CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')), d.fecha, " +
                        "d.tempMin, d.tempMax, d.pcpn " +
                        "FROM Usuario u " +
                        "JOIN Promotor p ON u.idUsuario = p.idUsuario " +
                        "JOIN Municipio m ON m.idMunicipio = p.idMunicipio " +
                        "JOIN Zona z ON m.idMunicipio = z.idMunicipio " +
                        "JOIN DatosPronostico d ON d.idZona = z.idZona " +
                        "WHERE u.idUsuario = :idUsuario AND z.idZona = :idZona")

        List<Object[]> obtenerDatosPronostico(@Param("idUsuario") int idUsuario, @Param("idZona") int idZona);

        @Query("select p.idPronostico, f.idFenologia, p.idZona, f.idCultivo, u.idUmbrales, f.fase, p.tempMax, p.tempMin, p.pcpn, p.fecha, "
                        +
                        "f.descripcion, f.nroDias, u.tempMax, u.tempMin, u.pcpn " +
                        "from DatosPronostico p " +
                        "join Cultivo c on c.idCultivo=p.idCultivo " +
                        "join Fenologia f on f.idCultivo = c.idCultivo " +
                        "join Umbrales u on u.idFenologia = f.idFenologia " +
                        "where f.idFenologia = :idFenologia and p.idCultivo = :idCultivo")
        List<Object[]> comparacionDatosPronostico(@Param("idFenologia") int idFenologia,
                        @Param("idCultivo") int idCultivo);

        @Query("select z.idZona, m.nombre, z.nombre from Municipio m join Zona z on m.idMunicipio=z.idMunicipio")
        List<Object[]> listaZonas();

        @Query("select d.tempMax, d.tempMin, d.pcpn, d.fecha, d.idPronostico, d.delete, d.fechaRangoDecenal " +
                        "from DatosPronostico d join Cultivo c on d.idCultivo = c.idCultivo " +
                        "where c.idCultivo = :idCultivo order by d.fecha desc")
        List<Object[]> listaDatosZona(@Param("idCultivo") int idZona);

        @Query("SELECT COUNT(d) FROM DatosPronostico d WHERE FUNCTION('DATE', d.fecha) = CURRENT_DATE AND d.idZona = :idZona")
        int countDatosHoy(@Param("idZona") int idZona);

}
