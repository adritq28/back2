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
@Table(name = "municipio")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio")
    private Integer idMunicipio;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "id_provincia")
    private Integer idProvincia;
    @Column(name = "imagen")
    private String imagen;
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
        return "Municipio [idMunicipio=" + idMunicipio + ", nombre=" + nombre + ", idProvincia=" + idProvincia
                + ", imagen=" + imagen + "]";
    }

}
