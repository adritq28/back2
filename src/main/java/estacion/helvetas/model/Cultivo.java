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
@Table(name = "cultivo")
public class Cultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cultivo")
    private Integer idCultivo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "fecha_siembra")
    private java.sql.Timestamp fechaSiembra = new java.sql.Timestamp(System.currentTimeMillis());
    @Column(name = "id_zona")
    private Integer idZona;

    @Override
    public String toString() {
        return "Cultivo [idcultivo=" + idCultivo + ", nombre=" + nombre + ", tipo=" + tipo + ", fechaSiembra="
                + fechaSiembra + ", idZona=" + idZona + "]";
    }

}
