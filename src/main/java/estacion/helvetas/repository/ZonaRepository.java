package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Zona;

public interface ZonaRepository extends JpaRepository<Zona, Integer> {

        Optional<Zona> findByIdZona(int integer);

        List<Zona> findByIdMunicipio(int idMunicipio);

        @Query("SELECT DISTINCT m.idMunicipio, m.nombre, m.imagen FROM Municipio m JOIN Zona z ON m.idMunicipio = z.idMunicipio")
        List<Object[]> obtenerIdNombreMunicipios();

        @Query("select m.nombre, z.nombre, m.idMunicipio, z.idZona from Zona z join Municipio m on z.idMunicipio=m.idMunicipio")
        List<Object[]> listaZonas();

        @Query("select c.nombre, c.idZona from ComunidadZona c join Zona z on c.idZona=z.idZona " +
                        "where z.idZona=:idZona")
        List<Object[]> listaComunidades(@Param("idZona") int idZona);

}
