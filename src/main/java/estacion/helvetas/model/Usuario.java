package estacion.helvetas.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(name = "usuario")
public class Usuario implements UserDetails {
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
    @Column(name = "imagen")
    private String imagen;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date fechaCreacion = new Date(System.currentTimeMillis());
    @Column(name = "ultimo_acceso")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date ultimoAcceso = new Date(System.currentTimeMillis());
    @Column(name = "estado")
    private boolean estado;
    @Column(name = "rol")
    private String rol;
    @Column(name = "delete")
    private Boolean delete = false;
    @Column(name = "edit")
    private Boolean edit = false;

    @Override
    public String toString() {
        return "Persona [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", nombre=" + nombre
                + ", apePat=" + apePat + ", apeMat=" + apeMat + ", telefono=" + telefono + ", ci=" + ci + ", password="
                + password + "]";
    }

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String nombre, String apePat, String apeMat,
            String telefono, String ci, String password, boolean admin, String imagen, Date fechaCreacion,
            Date ultimoAcceso,
            boolean estado, String rol, boolean delete, boolean edit) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apePat = apePat;
        this.apeMat = apeMat;
        this.telefono = telefono;
        this.ci = ci;
        this.password = password;
        this.admin = admin;
        this.imagen = imagen;
        this.fechaCreacion = fechaCreacion;
        this.ultimoAcceso = ultimoAcceso;
        this.estado = estado;
        this.rol = rol;
        this.delete = delete;
        this.edit = edit;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retornar un solo rol basado en el atributo isAdmin
        if (admin) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes cambiar esto según tus requisitos
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Puedes cambiar esto según tus requisitos
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Puedes cambiar esto según tus requisitos
    }

    @Override
    public boolean isEnabled() {
        return true; // Puedes cambiar esto según tus requisitos
    }

}
