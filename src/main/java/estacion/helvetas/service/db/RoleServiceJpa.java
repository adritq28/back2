package estacion.helvetas.service.db;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.Role;
import estacion.helvetas.services.IRoleService;

@Service
@Primary
public class RoleServiceJpa implements IRoleService {

    @Override
    public void guardar(Role role) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public List<Role> buscarPorCompleto(String completo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorCompleto'");
    }

    @Override
    public Role buscarPorIdRole(int idRole) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorIdRole'");
    }

    @Override
    public void eliminarRoleId(int idRole) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarRoleId'");
    }

    @Override
    public void deleteRole(int idRole) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRole'");
    }

}
