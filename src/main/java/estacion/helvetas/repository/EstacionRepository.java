package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Estacion;

public interface EstacionRepository extends JpaRepository<Estacion, Integer> {

    // List<persona> findAll();

    Optional<Estacion> findById(int idEstacion);

    @Query("SELECT e.idMunicipio, e.nombre, e.tipoEstacion, e.codTipoEstacion, e.idEstacion FROM Estacion e WHERE idMunicipio = :idMunicipio")
    List<Object[]> obtenerEstacion(@Param("idMunicipio") int idMunicipio);

    @Query("select e.idEstacion, m.nombre, e.nombre, e.tipoEstacion, e.codTipoEstacion " +
            "from Municipio m join Estacion e on m.idMunicipio = e.idMunicipio")
    List<Object[]> listaEstacionMeteorogica();

    @Query("select m.tempMax, m.tempMin, m.pcpn, m.tempAmb, m.dirViento, m.velViento, m.taevap, m.fechaReg, m.idDatosEst from DatosEstacionMeteorologica m join Estacion e on e.idEstacion=m.idEstacion "
            +
            "where e.idEstacion = :idEstacion order by m.idDatosEst desc")
    List<Object[]> listaDatosMeteorologica(@Param("idEstacion") int idEstacion);

}
