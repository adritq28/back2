package estacion.helvetas.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.Usuario;
import estacion.helvetas.repository.UsuarioRepository;
import estacion.helvetas.services.IUsuarioService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // @Autowired
    // private IUsuarioService usuarioService;

    public String obtenerCiIdUsuario(int idUsuario) {
        return usuarioRepo.findCiByIdUsuario(idUsuario);
    }

    public String obtenerPasswordIdUsuario(int idUsuario) {
        return usuarioRepo.findPasswordByIdUsuario(idUsuario);
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

    public void editarUsuario(Usuario request) {
        // Buscar el registro existente por ID
        Optional<Usuario> existingRecord = usuarioRepo.findByIdUsuario(request.getIdUsuario());

        if (existingRecord.isPresent()) {
            Usuario usuario = existingRecord.get();
            // Actualizar los campos con los valores del request
            usuario.setImagen(request.getImagen());
            usuario.setNombre(request.getNombre());
            usuario.setApePat(request.getApePat());
            usuario.setApeMat(request.getApeMat());
            usuario.setCi(request.getCi());
            usuario.setAdmin(request.isAdmin());
            usuario.setTelefono(request.getTelefono());
            usuario.setPassword(request.getPassword());
            // usuario.setNombreUsuario(request.getNombreUsuario());

            // usuario.setEditar(true); // Establecer el campo editar en true
            // usuario.setDelete(false); // Asumiendo que getDelete devuelve Boolean

            // Guardar la entidad actualizada en la base de datos
            usuarioRepo.save(usuario);
        } else {
            throw new EntityNotFoundException("Registro no encontrado con el ID: " + request.getIdUsuario());
        }
    }

    public String obtenerRolPorIdUsuario(Long idUsuario) {
        List<Object[]> result = usuarioRepo.findRolByIdUsuario(idUsuario);
        if (!result.isEmpty()) {
            Object rol = result.get(0)[1];
            System.out.println("Valor del rol: " + rol); // Debugging
            return rol.toString(); // Conversión a String
        }
        return null; // O lanzar una excepción si no se encuentra
    }

    public boolean eliminarUsuario(int idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByIdUsuario(idUsuario);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            // Realizar el marcado de eliminación lógica aquí
            usuario.setDelete(true); // Suponiendo un campo 'eliminado' en tu entidad
            usuarioRepo.save(usuario);
            return true;
        } else {
            return false;
        }
    }

    public boolean actualizarContrasena(int usuarioId, String nuevaContrasena) {
        Optional<Usuario> usuarioOptional = usuarioRepo.findByIdUsuario(usuarioId);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            String contrasenaEncriptada = passwordEncoder.encode(nuevaContrasena);
            usuario.setPassword(nuevaContrasena);
            usuarioRepo.save(usuario);
            return true;
        }
        return false;
    }

    // Método para obtener el número de teléfono del usuario
    // public String getUserPhoneNumber(int userId) {
    // Optional<Usuario> optionalUsuario = usuarioRepo.findByIdUsuario(userId);
    // return optionalUsuario.map(Usuario::getTelefono).orElse(null);
    // }

    public String obtenerTelefonoPorIdUsuario(int idUsuario) {
        return usuarioRepo.findTelefonoByIdUsuario(idUsuario);
    }

}
