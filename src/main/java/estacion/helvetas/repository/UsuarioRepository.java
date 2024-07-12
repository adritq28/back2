package estacion.helvetas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNombre(String nombre);

    Optional<Usuario> findByIdUsuario(int idUsuario);

    @Query("SELECT u.idUsuario AS idUsuario, " +
            "m.nombre AS minucipio, e.nombre AS estacion, e.tipoEstacion AS tipoestacion, " +
            "CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')) AS nombre, " +
            "u.telefono AS telefono, e.idEstacion, e.codTipoEstacion " +
            "FROM Usuario u " +
            "JOIN Observador o ON u.idUsuario = o.idUsuario " +
            "JOIN Estacion e ON o.idEstacion = e.idEstacion " + // Agrega un espacio antes de JOIN
            "JOIN Municipio m ON e.idMunicipio = m.idMunicipio")

    List<Object[]> buscarUsuariosConEstacion();

    @Query("SELECT u.telefono FROM Usuario u WHERE u.idUsuario = :idUsuario")
    String findTelefonoByIdUsuario(@Param("idUsuario") int idUsuario);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}