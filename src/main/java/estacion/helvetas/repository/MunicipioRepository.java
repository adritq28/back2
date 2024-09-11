package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

        // List<persona> findAll();

        Optional<Municipio> findById(int idMunicipio);

        @Query("SELECT m.idMunicipio, m.nombre, m.imagen FROM Municipio m")
        List<Object[]> obtenerIdNombreMunicipios();

        // @Query("SELECT m.idMunicipio, m.nombre, z.idZona, z.nombre " +
        // "FROM Municipio m " +
        // "JOIN Zona z ON m.idMunicipio = z.idMunicipio " +
        // "WHERE m.idMunicipio = : idMunicipio")
        // List<Object[]> obtenerZonasMunicipio(@Param("idMunicipio") int idMunicipio);

        // @Query("SELECT m.idMunicipio, m.nombre, z.idZona, z.nombre FROM Municipio m
        // JOIN Zona z ON m.idMunicipio = z.idMunicipio WHERE m.idMunicipio =
        // :idMunicipio")
        // List<Object[]> obtenerZonasMunicipio(@Param("idMunicipio") int idMunicipio);

        @Query("SELECT m.idMunicipio, m.nombre, z.idZona, z.nombre, c.nombre, c.idCultivo, z.latitud, z.longitud " +
                        "FROM Municipio m JOIN Zona z ON m.idMunicipio = z.idMunicipio " +
                        "JOIN Cultivo c ON z.idZona = c.idZona WHERE m.idMunicipio = :idMunicipio")
        List<Object[]> obtenerZonasMunicipio(@Param("idMunicipio") int idMunicipio);

        @Query("select c.nombre, z.nombre from Zona z " +
                        "join Cultivo c on z.idZona=c.idZona " +
                        "join Municipio m on z.idMunicipio=m.idMunicipio where z.idMunicipio=:idMunicipio order by c.idCultivo asc")
        List<Object[]> obtenerZonaPorCultivo(@Param("idMunicipio") int idMunicipio);

        @Query("select m.nombre, c.nombre from Zona z " +
                        "join Cultivo c on z.idZona=c.idZona " +
                        "join Municipio m on z.idMunicipio=m.idMunicipio where z.idMunicipio=:idMunicipio order by c.idCultivo asc")
        List<Object[]> obtenerCultivoPorMunicipio(@Param("idMunicipio") int idMunicipio);

}
