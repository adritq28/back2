package estacion.helvetas.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estacion.helvetas.repository.FenologiaRepository;

@CrossOrigin(origins = "*")
// @RestController
@RequestMapping("/fenologia")
// public class personaController {

@RestController
public class FenologiaController {

    @Autowired
    private FenologiaRepository fenologiaRepository;

    @GetMapping("/verFenologia/{idCultivo}")
    public List<Map<String, Object>> obtemerFenologia(@PathVariable int idCultivo) {
        List<Map<String, Object>> Fenologia = new ArrayList<>();
        List<Object[]> listaUsuariosConFenologia = fenologiaRepository.obtenerFenologia(idCultivo);

        for (Object[] usuarioConFenologia : listaUsuariosConFenologia) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("idMunicipio", usuarioConFenologia[0]);
            usuario.put("nombreCultivo", usuarioConFenologia[1]);
            usuario.put("tipoCultivo", usuarioConFenologia[2]);
            usuario.put("fechaSiembra", usuarioConFenologia[3]);
            usuario.put("idFenologia", usuarioConFenologia[4]);
            usuario.put("fase", usuarioConFenologia[5]);
            usuario.put("descripcion", usuarioConFenologia[6]);
            usuario.put("idCultivo", usuarioConFenologia[7]);
            usuario.put("nroDias", usuarioConFenologia[8]);
            usuario.put("tempMax", usuarioConFenologia[9]);
            usuario.put("tempMin", usuarioConFenologia[10]);
            usuario.put("pcpn", usuarioConFenologia[11]);
            usuario.put("tempOptMin", usuarioConFenologia[12]);
            usuario.put("umbInf", usuarioConFenologia[13]);
            usuario.put("umbSup", usuarioConFenologia[14]);
            usuario.put("imagen", usuarioConFenologia[15]);
            usuario.put("tempOptMax", usuarioConFenologia[16]);
            Fenologia.add(usuario);
        }
        return Fenologia;
    }

}
