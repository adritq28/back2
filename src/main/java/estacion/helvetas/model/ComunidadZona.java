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
@Table(name = "comunidad_zona")
public class ComunidadZona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comunidad")
    private Integer idComunidad;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "id_zona")
    private Integer idZona;
    @Column(name = "delete")
    private Boolean delete = false;

    @Column(name = "edit")

    @Override
    public String toString() {
        return "Comunida [idComunidad=" + idComunidad + ", nombre=" + nombre + ", idZona=" + idZona + "]";
    }

}
