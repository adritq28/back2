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
@Table(name = "zona")
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private Integer idZona;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "id_municipio")
    private Integer idMunicipio;
    @Column(name = "latitud")
    private Double latitud;
    @Column(name = "longitud")
    private Double longitud;
    @Column(name = "delete")
    private Boolean delete = false;
    @Column(name = "edit")
    private Boolean edit = false;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fechaCreacion = new Date(System.currentTimeMillis());

    @Override
    public String toString() {
        return "Zona [idZona=" + idZona + ", nombre=" + nombre + ", idMunicipio=" + idMunicipio + "]";
    }

}
