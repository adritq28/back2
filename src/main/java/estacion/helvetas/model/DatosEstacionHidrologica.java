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
@Table(name = "datos_estacion_hidrologica")
public class DatosEstacionHidrologica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hidrologica")
    private Integer idHidrologica;
    @Column(name = "limnimetro")
    private Float limnimetro;
    @Column(name = "delete")
    private boolean delete;
    @Column(name = "id_estacion")
    private Integer idEstacion;
    @Column(name = "fecha_reg")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fechaReg = new Date(System.currentTimeMillis());

    @Override
    public String toString() {
        return "DatosEstacionMeteorologica [idDatosEstacionHidrologica=" + idHidrologica +
                ", limnimetro=" + limnimetro +
                ", delete=" + delete +
                ", fechaReg=" + fechaReg +
                ", idEstacion=" + idEstacion +

                "]";
    }
}
