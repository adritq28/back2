package estacion.helvetas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "promotor")
public class Promotor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotor")
    private Integer idPromotor;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    // @Column(name = "id_zona")
    // private Integer idZona;
    @Column(name = "id_municipio")
    private Integer idMunicipio;

    public Promotor(Integer idPromotor, Integer idUsuario, Integer idZona, Integer idMunicipio) {
        this.idPromotor = idPromotor;
        this.idUsuario = idUsuario;
        // this.idZona = idZona;
        this.idMunicipio = idMunicipio;

    }

}
