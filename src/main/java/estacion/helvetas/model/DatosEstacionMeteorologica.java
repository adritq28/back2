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
@Table(name = "datos_estacion_meteorologica")
public class DatosEstacionMeteorologica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_est")
    private Integer idDatosEst;
    @Column(name = "temp_max")
    private Float tempMax;
    @Column(name = "temp_min")
    private Float tempMin;
    @Column(name = "temp_amb")
    private Float tempAmb;
    @Column(name = "pcpn")
    private Float pcpn;
    @Column(name = "taevap")
    private Float taevap;
    @Column(name = "dir_viento")
    private String dirViento;
    @Column(name = "vel_viento")
    private Float velViento;
    @Column(name = "id_estacion")
    private Integer idEstacion;
    @Column(name = "edit")
    private Boolean editar = false;
    @Column(name = "delete")
    private Boolean delete = false;

    @Column(name = "fecha_reg")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fechaReg = new Date(System.currentTimeMillis());

    @Override
    public String toString() {
        return "DatosEstacionMeteorologica [idDatosEstacion=" + idDatosEst +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin +
                ", tempAmb=" + tempAmb +
                ", pcpn=" + pcpn +
                ", taevap=" + taevap +
                ", fechaReg=" + fechaReg +
                ", dirViento=" + velViento +
                ", velViento=" + velViento +
                ", idEstacion=" + idEstacion +
                ", edit=" + editar +
                ", delete=" + delete +
                "]";
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}
