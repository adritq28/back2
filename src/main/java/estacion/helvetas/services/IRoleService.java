package estacion.helvetas.services;

import java.util.List;

import estacion.helvetas.model.Role;

public interface IRoleService {

    void guardar(Role role);

    List<Role> buscarPorCompleto(String completo);

    Role buscarPorIdRole(int idRole);

    void eliminarRoleId(int idRole);

    void deleteRole(int idRole);

}