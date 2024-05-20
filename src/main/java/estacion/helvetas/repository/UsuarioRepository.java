package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import estacion.helvetas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNombre(String nombre);

    Optional<Usuario> findById(int idUsuario);

    @Query("SELECT u.idUsuario AS idUsuario, " +
            "m.nombre AS minucipio, e.nombre AS estacion, e.tipoEstacion AS tipoestacion, " +
            "CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')) AS nombre, " +
            "u.telefono AS telefono " +
            "FROM Usuario u " +
            "JOIN Observador o ON u.idUsuario = o.idUsuario " +
            "JOIN Estacion e ON o.idEstacion = e.idEstacion " + // Agrega un espacio antes de JOIN
            "JOIN Municipio m ON e.idMunicipio = m.idMunicipio")

    List<Object[]> buscarUsuariosConEstacion();

    @Query("SELECT t1.idUsuario AS id, t5.nombre AS municipio, t3.nombre, t3.tipoEstacion, CONCAT(t1.nombre, ' ', t1.apePat, ' ', t1.apeMat) AS nombreCompleto, "
            +
            "t4.fechaReg, t4.tempMin, t4.tempMax " +
            "FROM Usuario t1 " +
            "JOIN Observador t2 ON t1.idUsuario = t2.idUsuario " +
            "JOIN Estacion t3 ON t2.idEstacion = t3.idEstacion " +
            "JOIN DatosEstacion t4 ON t3.idEstacion = t4.idEstacion " +
            "JOIN Municipio t5 ON t3.idMunicipio = t5.idMunicipio")
    List<Object[]> obtenerDatosEstacion();

}