package estacion.helvetas.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "hist_fecha_siembra")
public class HistFechaSiembra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hist_fecha")
    private Integer idHistFecha;

    @Column(name = "fecha_siembra")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fechaSiembra;

    @Column(name = "id_cultivo")
    private Integer idCultivo;

    // Constructor sin argumentos requerido por Hibernate
    public HistFechaSiembra() {
        this.fechaSiembra = new Date(System.currentTimeMillis()); // Inicialización opcional aquí
    }

    // Getters y setters
    // toString() y otros métodos si es necesario
}
