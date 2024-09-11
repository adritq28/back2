package estacion.helvetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Usuario;

public interface PromotorRepository extends JpaRepository<Usuario, Integer> {

        // @Query(value = "SELECT DISTINCT ON (u.id_usuario) u.id_usuario AS id,
        // m.nombre AS nombreMunicipio, z.nombre AS nombreZona, "
        // +
        // "CONCAT(u.nombre, ' ', u.ape_pat, ' ', COALESCE(u.ape_mat, '')) AS
        // nombreComp, u.telefono AS telefono, z.id_zona AS idZona "
        // +
        // "FROM usuario u " +
        // "JOIN promotor p ON u.id_usuario = p.id_usuario " +
        // "JOIN zona z ON z.id_zona = p.id_zona " +
        // "JOIN municipio m ON m.id_municipio = z.id_municipio " +
        // "ORDER BY u.id_usuario ASC", nativeQuery = true)
        // List<Object[]> buscarPromotores();

        @Query("SELECT u.idUsuario, m.nombre, CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')), u.telefono, m.idMunicipio, u.imagen "
                        +
                        "FROM Promotor p " +
                        "JOIN Municipio m ON p.idMunicipio = m.idMunicipio " +
                        "JOIN Usuario u ON u.idUsuario = p.idUsuario " +
                        "ORDER BY u.idUsuario ASC")
        List<Object[]> buscarPromotoresJPQL();

        @Query("select u.idUsuario, m.nombre, z.nombre, CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')), z.idZona, c.idCultivo, c.nombre, c.tipo, c.imagen, u.imagen "
                        +
                        "from Promotor p join Municipio m on p.idMunicipio=m.idMunicipio " +
                        "join Usuario u on u.idUsuario=p.idUsuario " +
                        "join Zona z on z.idMunicipio=m.idMunicipio " +
                        "join Cultivo c on c.idZona=z.idZona WHERE u.idUsuario = :idUsuario")
        List<Object[]> buscarZonas(@Param("idUsuario") int idUsuario);

        // @Query("SELECT u.idUsuario, m.nombre, " +
        // "CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')), u.telefono,
        // p.idZona " +
        // "FROM Usuario u " +
        // "JOIN Promotor p ON p.idUsuario = u.idUsuario " +
        // "JOIN Municipio m ON m.idMunicipio = p.idMunicipio ORDER BY u.idUsuario ASC")
        // List<Object[]> buscarPromotores();

        // @Query("SELECT u.idUsuario, m.nombre, z.nombre, " +
        // "CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')), u.telefono, "
        // +
        // "p.idZona, c.idCultivo, c.nombre, c.tipo from Cultivo c " +
        // "join Zona z on z.idZona=c.idZona " +
        // "join Promotor p on p.idZona=z.idZona " +
        // "join Usuario u on u.idUsuario=p.idUsuario " +
        // "join Municipio m on m.idMunicipio=z.idMunicipio")
        // List<Object[]> buscarPromotores();

}
