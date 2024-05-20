package estacion.helvetas.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.persona;
import estacion.helvetas.repository.personaRepository;
import estacion.helvetas.services.IPersonaServices;

@Service
@Primary
public class personaServiceJpa implements IPersonaServices {

    @Autowired
    private personaRepository personaRepo;

    @Override
    public persona buscarPorIdPersona(int idPersona) {
        Optional<persona> persona = personaRepo.findById(idPersona);
        return persona.get();
    }

    @Override
    public void deletePersona(int idPersona) {
        // TODO Auto-generated method stub
        personaRepo.deleteById(idPersona);
    }

    @Override
    public List<persona> buscarPorCompleto(String completo) {
        return null;
    }

    @Override
    public void eliminarPersonaId(int idPersona) {

    }

    @Override
    public void guardar(persona persona) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

}
