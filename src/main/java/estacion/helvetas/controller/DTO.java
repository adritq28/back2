// package estacion.helvetas.controller;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

// import estacion.helvetas.repository.DatosEstacionRepository;
// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;

// @RequestMapping("/datosEstacion2")
// public class DTO {

// @PersistenceContext
// private EntityManager entityManager;

// @Autowired
// private DatosEstacionRepository datosRepository;

// @GetMapping("/verDatosEstacion")
// public List<Map<String, Object>> obtenerDatosEstacion2() {

// List<Map<String, Object>> datosEst = new ArrayList<>();
// List<Object[]> listadatos = datosRepository.obtenerDatosEstacion();

// // List<Object[]> results = query.getResultList();
// // List<Map<String, Object>> datosEstacion = new ArrayList<>();

// for (Object[] result : listadatos) {
// Map<String, Object> datos = new HashMap<>();
// datos.put("id", result[0]);
// datos.put("idUsuario", result[1]);
// datos.put("nombreMunicipio", result[2]);
// datos.put("nombreEstacion", result[3]);
// datos.put("tipoEstacion", result[4]);
// datos.put("nombreCompleto", result[5]);
// datos.put("fechaReg", result[6]);
// datos.put("tempMin", result[7]);
// datos.put("tempMax", result[8]);
// datosEst.add(datos);
// }

// return datosEst;
// }
// }
