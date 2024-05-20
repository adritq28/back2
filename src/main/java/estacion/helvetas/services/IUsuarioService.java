package estacion.helvetas.services;

import java.util.List;

import estacion.helvetas.model.Usuario;

public interface IUsuarioService {

    void guardar(Usuario usuario);

    List<Usuario> buscarPorCompleto(String completo);

    Usuario buscarPorIdUsuario(int idUsuario);

    void eliminarUsuarioId(int idUsuario);

    void deleteUsuario(int idUsuario);

}