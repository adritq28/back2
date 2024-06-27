package estacion.helvetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import estacion.helvetas.model.Usuario;

public interface PromotorRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u.idUsuario AS id, m.nombre AS nombreMunicipio, z.nombre AS nombreZona, " +
            "CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')) AS nombreComp, u.telefono AS telefono, z.idZona AS idzona "
            +
            "FROM Usuario u " +
            "JOIN Promotor p ON u.idUsuario = p.idUsuario " +
            "JOIN Zona z ON z.idZona = p.idZona " +
            "JOIN Municipio m ON m.idMunicipio = z.idMunicipio ORDER BY u.idUsuario ASC")
    List<Object[]> buscarPromotores();

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
