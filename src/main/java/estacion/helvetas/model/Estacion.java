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
@Table(name = "estacion")
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estacion")
    private Integer idEstacion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "latitud")
    private String latitud;
    @Column(name = "longitud")
    private String longitud;
    @Column(name = "altura")
    private String altura;
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "tipo_estacion")
    private String tipoEstacion;
    @Column(name = "id_municipio")
    private Integer idMunicipio;
    @Column(name = "cod_tipo_estacion")
    private boolean codTipoEstacion;
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
        return "Estacion [idEstacion=" + idEstacion + ", nombre=" + nombre + ", latitud=" + latitud
                + ", longitud=" + longitud + ", altura=" + altura + ", estado=" + estado
                + ", tipoEstacion=" + tipoEstacion + ", idMunicipio=" + idMunicipio + ", codTipoEstacion="
                + codTipoEstacion + "]";
    }

}