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

    @Override
    public String toString() {
        return "Estacion [idEstacion=" + idEstacion + ", nombre=" + nombre + ", latitud=" + latitud
                + ", longitud=" + longitud + ", altura=" + altura + ", estado=" + estado
                + ", tipoEstacion=" + tipoEstacion + ", idMunicipio=" + idMunicipio + "]";
    }

}