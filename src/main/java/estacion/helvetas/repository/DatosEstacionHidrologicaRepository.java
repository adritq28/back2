package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.DatosEstacionHidrologica;

public interface DatosEstacionHidrologicaRepository extends JpaRepository<DatosEstacionHidrologica, Integer> {

        Optional<DatosEstacionHidrologica> findByIdHidrologica(Long id);

        @Query(value = "SELECT t1.idUsuario AS id, " +
                        "t5.nombre AS municipio, " +
                        "t3.nombre AS estacion, " +
                        "t3.tipoEstacion, " +
                        "CONCAT(t1.nombre, ' ', t1.apePat, ' ', t1.apeMat) AS nombreCompleto, " +
                        "t4.fechaReg, " +
                        "t4.limnimetro, " +
                        "t4.idEstacion, " +
                        "t4.delete " +
                        "FROM Usuario t1 " +
                        "JOIN Observador t2 ON t1.idUsuario = t2.idUsuario " +
                        "JOIN Estacion t3 ON t2.idEstacion = t3.idEstacion " +
                        "JOIN DatosEstacionHidrologica t4 ON t3.idEstacion = t4.idEstacion " +
                        "JOIN Municipio t5 ON t3.idMunicipio = t5.idMunicipio " +
                        "WHERE t1.idUsuario = :idUsuario")

        List<Object[]> obtenerDatosHidrologica(@Param("idUsuario") int idUsuario);

}
