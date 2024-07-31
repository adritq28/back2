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
@Table(name = "fenologia")
public class Fenologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fenologia")
    private Integer idFenologia;
    @Column(name = "fase")
    private Integer fase;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "id_cultivo")
    private Integer idCultivo;
    @Column(name = "nro_dias")
    private Integer nroDias;
    @Column(name = "imagen")
    private String imagen;

    @Override
    public String toString() {
        return "DatosPronostico [idFenologia=" + idFenologia +
                ", fase=" + fase +
                ", descripcion=" + descripcion +
                ", idCultivo=" + idCultivo +
                ", nroDias=" + nroDias +
                ", imagen=" + imagen +
                "]";
    }

}
