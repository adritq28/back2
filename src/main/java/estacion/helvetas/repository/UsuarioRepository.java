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
                        "u.telefono AS telefono, e.idEstacion, e.codTipoEstacion, u.imagen " +
                        "FROM Usuario u " +
                        "JOIN Observador o ON u.idUsuario = o.idUsuario " +
                        "JOIN Estacion e ON o.idEstacion = e.idEstacion " + // Agrega un espacio antes de JOIN
                        "JOIN Municipio m ON e.idMunicipio = m.idMunicipio")

        List<Object[]> buscarUsuariosConEstacion();

        @Query("SELECT u.telefono FROM Usuario u WHERE u.idUsuario = :idUsuario")
        String findTelefonoByIdUsuario(@Param("idUsuario") int idUsuario);

        @Query("SELECT u.ci FROM Usuario u WHERE u.idUsuario = :idUsuario")
        String findCiByIdUsuario(@Param("idUsuario") int idUsuario);

        @Query("SELECT u.password FROM Usuario u WHERE u.idUsuario = :idUsuario")
        String findPasswordByIdUsuario(@Param("idUsuario") int idUsuario);

        Optional<Usuario> findByNombreUsuario(String nombreUsuario);

        // @Query("SELECT CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')),
        // u.telefono, e.nombre, m.nombre " +
        // "FROM Usuario u JOIN Observador o ON u.idUsuario=o.idUsuario " +
        // "join Estacion e on o.idEstacion=e.idEstacion " +
        // "join Municipio m on e.idMunicipio=m.idMunicipio " +
        // "where u.idUsuario = :idUsuario")
        // List<Object[]> perfilObservador(@Param("idUsuario") int idUsuario);
        //////////////////// CONTINUARRRR

        @Query("select CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')), u.telefono, u.imagen from Usuario u "
                        +
                        "where u.idUsuario = :idUsuario")
        List<Object[]> perfilObservador(@Param("idUsuario") int idUsuario);

        @Query("select u.idUsuario, u.nombre, u.apePat, u.apeMat, u.ci, u.admin, u.telefono, u.imagen, u.rol, u.estado, u.password "
                        +
                        "from Usuario u where u.delete=false order by u.idUsuario")
        List<Object[]> listaUsuario();

        @Query("SELECT u.idUsuario, u.rol FROM Usuario u WHERE u.idUsuario = :idUsuario")
        List<Object[]> findRolByIdUsuario(@Param("idUsuario") Long idUsuario);

        @Query("select m.nombre, e.nombre, u.idUsuario, u.nombre, u.apePat, u.apeMat, u.ci, u.admin, u.telefono, u.imagen, u.rol, e.tipoEstacion, e.idEstacion, m.idMunicipio "
                        +
                        "from Usuario u join Observador o on u.idUsuario=o.idUsuario " +
                        "join Estacion e on e.idEstacion=o.idEstacion " +
                        "join Municipio m on m.idMunicipio=e.idMunicipio WHERE u.idUsuario = :idUsuario")
        List<Object[]> obtenerDatosObservador(@Param("idUsuario") Long idUsuario);

        @Query("SELECT u.idUsuario, u.nombre, u.apePat, u.apeMat, u.ci, u.telefono, u.imagen, u.rol, u.password " +
                        "FROM Usuario u " +
                        "WHERE u.idUsuario = :idUsuario ")
        List<Object[]> obtenerDatosAdministrador(@Param("idUsuario") Long idUsuario);

        @Query("select m.nombre, z.nombre, c.nombre, u.idUsuario, u.nombre, u.apePat, u.apeMat, u.ci, u.admin, u.telefono, u.imagen, c.imagen, u.rol, z.idZona, z.latitud, z.longitud, m.idMunicipio, c.tipo, c.fechaSiembra, c.idCultivo, c.edit, c.delete "
                        +
                        "from Promotor p join Municipio m on p.idMunicipio=m.idMunicipio " +
                        "join Usuario u on u.idUsuario=p.idUsuario " +
                        "join Zona z on z.idMunicipio=m.idMunicipio " +
                        "join Cultivo c on c.idZona=z.idZona WHERE u.idUsuario = :idUsuario")
        List<Object[]> obtenerDatosPromotor(@Param("idUsuario") Long idUsuario);

        /// String findTelefonoByIdUsuario(int userId);
        // String findByIdUsuario(int idUsuario);

}