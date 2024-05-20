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

    @Override
    public String toString() {
        return "Municipio [idMunicipio=" + idMunicipio + ", nombre=" + nombre + ", idProvincia=" + idProvincia + "]";
    }

}
