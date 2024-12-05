package estacion.helvetas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import estacion.helvetas.model.Promotor;

public interface PromotorRepository extends JpaRepository<Promotor, Integer> {

        @Query("SELECT u.idUsuario, m.nombre, CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')), u.telefono, m.idMunicipio, u.imagen "
                        +
                        "FROM Promotor p " +
                        "JOIN Municipio m ON p.idMunicipio = m.idMunicipio " +
                        "JOIN Usuario u ON u.idUsuario = p.idUsuario " +
                        "ORDER BY u.idUsuario ASC")
        List<Object[]> buscarPromotoresJPQL();

        @Query("select u.idUsuario, m.nombre, z.nombre, CONCAT(u.nombre, ' ', u.apePat, ' ', COALESCE(u.apeMat, '')), z.idZona, c.idCultivo, c.nombre, c.tipo, c.imagen, u.imagen, c.nombreFechaSiembra "
                        +
                        "from Promotor p join Municipio m on p.idMunicipio=m.idMunicipio " +
                        "join Usuario u on u.idUsuario=p.idUsuario " +
                        "join Zona z on z.idMunicipio=m.idMunicipio " +
                        "join Cultivo c on c.idZona=z.idZona WHERE u.idUsuario = :idUsuario")
        List<Object[]> buscarZonas(@Param("idUsuario") int idUsuario);

}
