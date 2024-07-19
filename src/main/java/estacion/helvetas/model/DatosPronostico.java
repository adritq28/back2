package estacion.helvetas.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "datos_pronostico")
public class DatosPronostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pronostico")
    private Integer idPronostico;
    @Column(name = "temp_max")
    private Float tempMax;
    @Column(name = "temp_min")
    private Float tempMin;
    @Column(name = "pcpn")
    private Float pcpn;
    @Column(name = "id_zona")
    private Integer idZona;
    // @Column(name = "id_fenologia")
    // private Integer idFenologia;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fecha = new Date(System.currentTimeMillis());

    @Column(name = "delete")
    private Boolean delete;

    @Override
    public String toString() {
        return "DatosPronostico [idPronostico=" + idPronostico +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                ", pcpn=" + pcpn +
                ", idZona=" + idZona +
                // ", idFenologia=" + idFenologia +
                ", fecha=" + fecha +
                ", delete=" + delete +
                "]";
    }

}
