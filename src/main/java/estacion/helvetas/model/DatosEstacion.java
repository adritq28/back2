package estacion.helvetas.model;

//import java.sql.Date;
//import java.sql.Date;
import java.sql.Timestamp;
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
@Table(name = "datos_estacion")
public class DatosEstacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_est")
    private Integer idDatosEst;
    @Column(name = "coordenada")
    private String coordenada;
    @Column(name = "dir_vel_viento")
    private Float dirVelViento;
    @Column(name = "fecha_datos")
    private Timestamp fechaDatos;
    @Column(name = "pcpn")
    private Float pcpn;
    @Column(name = "taevap")
    private Float taevap;
    @Column(name = "temp_amb")
    private Float tempAmb;
    @Column(name = "temp_max")
    private Float tempMax;
    @Column(name = "temp_min")
    private Float tempMin;

    @Column(name = "fechados")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fechados = new Date(System.currentTimeMillis());

    @Override
    public String toString() {
        return "DatosEstacion [idDatosEstacion=" + idDatosEst +
                ", coordenada=" + coordenada +
                ", dirVelViento=" + dirVelViento +
                ", fechaDatos=" + fechaDatos +
                ", pcpn=" + pcpn +
                ", taevap=" + taevap +
                ", tempAmb=" + tempAmb +
                ", tempMax=" + tempMax +
                ", tempMin=" + tempMin + "]";
    }

    // public void setFechados(Date fechados) {
    // this.fechados = new Date(); // Establecer la fecha y hora de creación al
    // momento de asignar el valor
    // }

}
