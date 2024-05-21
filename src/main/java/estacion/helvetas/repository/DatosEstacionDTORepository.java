// package estacion.helvetas.repository;

// import org.springframework.data.jpa.repository.JpaRepository;

// import estacion.helvetas.model.DatosEstacionDTO;

// public interface DatosEstacionDTORepository extends
// JpaRepository<DatosEstacionDTO, Integer> {

// // Optional<DatosEstacionDTO> datosId(int id);

// // @Query("SELECT t1.idUsuario AS id, t5.nombre AS municipio, t3.nombre,
// // t3.tipoEstacion, CONCAT(t1.nombre, ' ', t1.apePat, ' ', t1.apeMat)
// // ASnombreCompleto, "
// // +
// // "t4.fechaReg, t4.tempMin, t4.tempMax " +
// // "FROM Usuario t1 " +
// // "JOIN Observador t2 ON t1.idUsuario = t2.idUsuario " +
// // "JOIN Estacion t3 ON t2.idEstacion = t3.idEstacion " +
// // "JOIN DatosEstacion t4 ON t3.idEstacion = t4.idEstacion " +
// // "JOIN Municipio t5 ON t3.idMunicipio = t5.idMunicipio")
// // // List<Object[]> obtenerDatosEstacion();
// // List<DatosEstacionDTO> obtenerDatos();

// }
