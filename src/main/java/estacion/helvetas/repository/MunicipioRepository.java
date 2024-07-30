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

    @Query("SELECT m.idMunicipio, m.nombre FROM Municipio m")
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

    @Query("SELECT m.idMunicipio, m.nombre, z.idZona, z.nombre, c.nombre, c.idCultivo " +
            "FROM Municipio m JOIN Zona z ON m.idMunicipio = z.idMunicipio " +
            "JOIN Cultivo c ON z.idZona = c.idZona WHERE m.idMunicipio = :idMunicipio")
    List<Object[]> obtenerZonasMunicipio(@Param("idMunicipio") int idMunicipio);

}
