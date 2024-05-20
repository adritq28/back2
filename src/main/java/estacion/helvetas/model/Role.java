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
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer idRole;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "descripcion")
    private String descripcion;

    @Override
    public String toString() {
        return "Role [idRole=" + idRole + ", roleName=" + roleName + ", descripcion=" + descripcion + "]";
    }
}
