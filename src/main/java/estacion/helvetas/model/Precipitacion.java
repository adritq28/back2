package estacion.helvetas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "precipitacion")
public class Precipitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precipitacion")
    private Integer idPrecipitacion;
    @Column(name = "normal")
    private Float normal;
    @Column(name = "id_fenologia")
    private Integer idFenologia;

    private String fase;
    @Transient
    private float pcpnAcumulada;

    @Override
    public String toString() {
        return "DatosPronostico [idprecipitacion=" + idPrecipitacion +
                ", normal=" + normal +
                ", idFenologia=" + idFenologia +
                "]";
    }

}
