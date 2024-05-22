package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.DatosEstacion;

public interface DatosEstacionRepository extends JpaRepository<DatosEstacion, Integer> {

    Optional<DatosEstacion> findByIdDatosEst(Long id);

    // Optional<DatosEstacion> obtenerDatosEstacionPorId(int id);
    // Optional<DatosEstacionDTO> datosId(int id);

    @Query("SELECT t1.idUsuario AS id, t5.nombre AS municipio, t3.nombre AS nombreEstacion, t3.tipoEstacion, " +
            "CONCAT(t1.nombre, ' ', t1.apePat, ' ', COALESCE(t1.apeMat, '')) AS nombreCompleto, t4.fechaReg, " +
            "t4.tempMin, t4.tempMax, t4.tempAmb, t4.pcpn, t4.taevap, t4.dirViento, t4.velViento, t3.idEstacion " +
            "FROM Usuario t1 " +
            "JOIN Observador t2 ON t1.idUsuario = t2.idUsuario " +
            "JOIN Estacion t3 ON t2.idEstacion = t3.idEstacion " +
            "JOIN DatosEstacion t4 ON t3.idEstacion = t4.idEstacion " +
            "JOIN Municipio t5 ON t3.idMunicipio = t5.idMunicipio " +
            "WHERE t1.idUsuario = :idUsuario")

    List<Object[]> obtenerDatosEstacion(@Param("idUsuario") int idUsuario);
    // List<DatosEstacionDTO> obtenerDatos();

}
