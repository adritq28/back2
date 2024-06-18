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

    @Override
    public String toString() {
        return "Zona [idZona=" + idZona + ", nombre=" + nombre + ", idMunicipio=" + idMunicipio + "]";
    }

}
