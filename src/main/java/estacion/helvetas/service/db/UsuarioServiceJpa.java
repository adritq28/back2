package estacion.helvetas.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.Usuario;
import estacion.helvetas.repository.UsuarioRepository;
import estacion.helvetas.services.IUsuarioService;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    public String obtenerTelefonoPorIdUsuario(int idUsuario) {
        return usuarioRepo.findTelefonoByIdUsuario(idUsuario);
    }

    @Override
    public Usuario buscarPorIdUsuario(int idUsuario) {
        Optional<Usuario> usuario = usuarioRepo.findById(idUsuario);
        return usuario.get();
    }

    @Override
    public void deleteUsuario(int idUsuario) {
        // TODO Auto-generated method stub
        usuarioRepo.deleteById(idUsuario);
    }

    @Override
    public List<Usuario> buscarPorCompleto(String completo) {
        return null;
    }

    @Override
    public void eliminarUsuarioId(int idUsuario) {

    }

    @Override
    public void guardar(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

}
