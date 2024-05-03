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
@Table(name = "persona")
public class persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "tipo_persona")
    private String tipoPersona;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ci")
    private String ci;
    @Column(name = "id_estacion")
    private Integer idEstacion;

    // getters and setteres

    // public String getTipoPersona() {
    // return tipoPersona;
    // }

    // public void setTipoPersona(String tipoPersona) {
    // this.nombre = tipoPersona;
    // }

    // public String getNombre() {
    // return nombre;
    // }

    // public void setNombre(String nombre) {
    // this.nombre = nombre;
    // }

    // public String getCi() {
    // return ci;
    // }

    // public void setCi(String ci) {
    // this.ci = ci;
    // }

    // public int getIdEstacion() {
    // return idEstacion;
    // }

    // public void setIdEstacion(int idEstacion) {
    // this.idEstacion = idEstacion;
    // }

    @Override
    public String toString() {
        return "Persona [idPersona=" + idPersona + ", tipo=" + tipoPersona + ", nombre=" + nombre
                + ", ci=" + ci + ", idEstacion=" + idEstacion + "]";
    }

}
