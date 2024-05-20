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
@Table(name = "observador")
public class Observador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_observador")
    private Integer idObservador;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_estacion")
    private Integer idEstacion;
    @Column(name = "estado_datos")
    private boolean estadoDatos;

    @Override
    public String toString() {
        return "Persona [idObservador=" + idObservador + ", idUsuario=" + idUsuario + ", idEstacion=" + idEstacion
                + ", estadoDatos=" + estadoDatos + "]";
    }
}
