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
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ape_pat")
    private String apePat;
    @Column(name = "ape_mat")
    private String apeMat;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "ci")
    private String ci;
    @Column(name = "password")
    private String password;
    @Column(name = "admin")
    private boolean admin;

    @Override
    public String toString() {
        return "Persona [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", nombre=" + nombre
                + ", apePat=" + apePat + ", apeMat=" + apeMat + ", telefono=" + telefono + ", ci=" + ci + ", password="
                + password + "]";
    }

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String nombre, String apePat, String apeMat,
            String telefono, String ci, String password, boolean admin) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apePat = apePat;
        this.apeMat = apeMat;
        this.telefono = telefono;
        this.ci = ci;
        this.password = password;
        this.admin = admin;
    }

}
