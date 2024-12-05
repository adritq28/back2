package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Estacion;

public interface EstacionRepository extends JpaRepository<Estacion, Integer> {

        // List<persona> findAll();

        Optional<Estacion> findByIdEstacion(int idEstacion);

        @Query("SELECT e.idMunicipio, e.nombre, e.tipoEstacion, e.codTipoEstacion, e.idEstacion FROM Estacion e WHERE idMunicipio = :idMunicipio")
        List<Object[]> obtenerEstacion(@Param("idMunicipio") int idMunicipio);

        @Query("select e.idEstacion, m.nombre, e.nombre, e.tipoEstacion, e.codTipoEstacion " +
                        "from Municipio m join Estacion e on m.idMunicipio = e.idMunicipio where e.codTipoEstacion = true")
        List<Object[]> listaEstacionMeteorogica();

        @Query("select m.tempMax, m.tempMin, m.pcpn, m.tempAmb, m.dirViento, m.velViento, m.taevap, m.fechaReg, m.idDatosEst, m.delete from DatosEstacionMeteorologica m join Estacion e on e.idEstacion=m.idEstacion "
                        +
                        "where e.idEstacion = :idEstacion and e.codTipoEstacion = true order by m.idDatosEst desc")
        List<Object[]> listaDatosMeteorologica(@Param("idEstacion") int idEstacion);

        @Query("select CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')) from Observador o join Estacion e on o.idEstacion=e.idEstacion "
                        +
                        "join Usuario u on u.idUsuario=o.idUsuario where e.idEstacion = :idEstacion")
        List<Object[]> obtNombreObservador(@Param("idEstacion") int idEstacion);

        @Query("select m.limnimetro, m.fechaReg, m.idHidrologica, m.delete " +
                        "from DatosEstacionHidrologica m join Estacion e on e.idEstacion=m.idEstacion " +
                        "where e.idEstacion = :idEstacion and e.codTipoEstacion = false order by m.idHidrologica desc")
        List<Object[]> listaDatosHidrologica(@Param("idEstacion") int idEstacion);

        @Query("select e.idEstacion, m.nombre, e.nombre, e.tipoEstacion, e.codTipoEstacion " +
                        "from Municipio m join Estacion e on m.idMunicipio = e.idMunicipio where e.codTipoEstacion = false")
        List<Object[]> listaEstacionHidrologica();

        @Query("select m.nombre, e.nombre, e.tipoEstacion, e.idEstacion, m.idMunicipio from Estacion e " +
                        "join Municipio m ON e.idMunicipio=m.idMunicipio")
        List<Object[]> listaEstaciones();

}
