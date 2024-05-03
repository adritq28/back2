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
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "tipo_estacion")
    private String tipoEstacion;
    @Column(name = "coordenadas")
    private String coordenadas;
    @Column(name = "imagen")
    private String imagen;
    @Column(name = "id_datos_est")
    private Integer idDatosEst;
    @Column(name = "id_observador")
    private Integer idObservador;
    @Column(name = "id_municipio")
    private Integer idMunicipio;

    @Override
    public String toString() {
        return "Estacion [idEstacion=" + idEstacion + ", nombre=" + nombre + ", estado=" + estado
                + ", tipoEstacion=" + tipoEstacion + ", coordenadas=" + coordenadas + ", imagen=" + imagen
                + ", idDatosEst=" + idDatosEst +
                ", idObservador=" + idObservador + ", idMunicipio=" + idMunicipio + "]";
    }

}