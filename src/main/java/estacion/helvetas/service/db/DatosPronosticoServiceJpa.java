package estacion.helvetas.service.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import estacion.helvetas.model.Cultivo;
import estacion.helvetas.model.DatosPronostico;
import estacion.helvetas.model.Fenologia;
import estacion.helvetas.model.Umbrales;
import estacion.helvetas.model.Zona;
import estacion.helvetas.repository.CultivoRepository;
import estacion.helvetas.repository.DatosPronosticoRepository;
import estacion.helvetas.repository.FenologiaRepository;
import estacion.helvetas.repository.UmbralRepository;
import estacion.helvetas.repository.ZonaRepository;
import estacion.helvetas.services.IDatosPronosticoService;
//import estacion.helvetas.ResourceNotFoundException2;
import jakarta.persistence.EntityNotFoundException;

@Service
@Primary
public class DatosPronosticoServiceJpa implements IDatosPronosticoService {

    @Autowired
    private DatosPronosticoRepository datosPronosticoRepo;

    @Autowired
    private CultivoRepository cultivoRepository;

    @Autowired
    private FenologiaRepository fenologiaRepository;

    @Autowired
    private UmbralRepository umbralRepository;

    @Autowired
    private DatosPronosticoRepository pronosticoRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    public List<String> generarAlertas(int cultivoId) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new ResourceNotFoundException2("Cultivo no encontrado"));
        Date fechaSiembra = cultivo.getFechaSiembra();

        List<Fenologia> fenologias = fenologiaRepository.findByIdCultivo(cultivoId);
        List<DatosPronostico> pronosticos = pronosticoRepository.findByIdZona(cultivo.getIdZona());

        List<String> alertas = new ArrayList<>();

        for (DatosPronostico pronostico : pronosticos) {
            Fenologia faseActual = null;
            Date fechaInicioFase = fechaSiembra;

            // Determinar la fase fenológica actual
            for (Fenologia fenologia : fenologias) {
                // Usar Calendar para sumar días a la fecha
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaInicioFase);
                calendar.add(Calendar.DAY_OF_YEAR, fenologia.getNroDias());
                Date fechaFinFase = calendar.getTime();

                if (!pronostico.getFecha().before(fechaInicioFase) && !pronostico.getFecha().after(fechaFinFase)) {
                    faseActual = fenologia;
                    break;
                }

                fechaInicioFase = fechaFinFase;
            }

            if (faseActual != null) {
                // Obtener los umbrales de la fase actual
                Optional<Umbrales> optionalUmbral = umbralRepository.findByIdFenologia(faseActual.getIdFenologia());
                if (optionalUmbral.isPresent()) {
                    Umbrales umbral = optionalUmbral.get();

                    // Comparar con el pronóstico actual
                    if (pronostico.getTempMax() > umbral.getTempMax()
                            || pronostico.getTempMin() < umbral.getTempMin()
                            || pronostico.getPcpn() > umbral.getPcpn()) {
                        alertas.add("Alerta para el cultivo " + cultivo.getNombre() + " en la fase "
                                + faseActual.getFase() + " el día " + pronostico.getFecha() + " ID ZONA "
                                + pronostico.getIdZona());
                    }
                } else {
                    // Manejar el caso donde no se encuentra un umbral para la fenología
                    alertas.add("No se encontrooo2ó umbral para la fenología " + faseActual.getFase());
                }
            } else {
                alertas.add(
                        "No se encontr222ó fase fenológica para el pronóstico en la fecha " + pronostico.getFecha());
            }
        }

        return alertas;
    }

    //////////////////////
    public String generarUltimaAlerta(int cultivoId) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new ResourceNotFoundException2("Cultivo no encontrado"));
        Date fechaSiembra = cultivo.getFechaSiembra();

        List<Fenologia> fenologias = fenologiaRepository.findByIdCultivo(cultivoId);
        List<DatosPronostico> pronosticos = pronosticoRepository.findByIdZona(cultivo.getIdZona());

        String ultimaAlerta = null;
        Date fechaInicioFase = fechaSiembra;

        for (DatosPronostico pronostico : pronosticos) {
            Fenologia faseActual = null;
            fechaInicioFase = fechaSiembra; // Reiniciar fechaInicioFase para cada pronóstico

            // Determinar la fase fenológica actual
            for (int i = 0; i < fenologias.size(); i++) {
                Fenologia fenologia = fenologias.get(i);
                Date fechaFinFase;

                // Usar Calendar para sumar días a la fecha de inicio de fase
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaInicioFase);
                calendar.add(Calendar.DAY_OF_YEAR, fenologia.getNroDias());
                fechaFinFase = calendar.getTime();

                if (!pronostico.getFecha().before(fechaInicioFase) && !pronostico.getFecha().after(fechaFinFase)) {
                    faseActual = fenologia;
                    break;
                }

                fechaInicioFase = fechaFinFase;
            }

            if (faseActual != null) {
                // Obtener los umbrales de la fase actual
                Optional<Umbrales> optionalUmbral = umbralRepository.findByIdFenologia(faseActual.getIdFenologia());
                if (optionalUmbral.isPresent()) {
                    Umbrales umbral = optionalUmbral.get();

                    // Comparar con el pronóstico actual y construir la alerta con detalles
                    StringBuilder alerta = new StringBuilder();
                    boolean alertaGenerada = false;

                    if (pronostico.getTempMax() > umbral.getTempMax()) {
                        alerta.append("TempMax pronóstico: ").append(pronostico.getTempMax())
                                .append(" supera umbral: ").append(umbral.getTempMax()).append(". ");
                        alertaGenerada = true;
                    }

                    if (pronostico.getTempMin() < umbral.getTempMin()) {
                        alerta.append("TempMin pronóstico: ").append(pronostico.getTempMin())
                                .append(" está por debajo del umbral: ").append(umbral.getTempMin()).append(". ");
                        alertaGenerada = true;
                    }

                    if (pronostico.getPcpn() > umbral.getPcpn()) {
                        alerta.append("Pcpn pronóstico: ").append(pronostico.getPcpn())
                                .append(" supera umbral: ").append(umbral.getPcpn()).append(". ");
                        alertaGenerada = true;
                    }

                    if (alertaGenerada) {
                        ultimaAlerta = "Alerta para el cultivo " + cultivo.getNombre() + " en la fase "
                                + faseActual.getFase() + " el día " + pronostico.getFecha() + " ID ZONA "
                                + pronostico.getIdZona() + ". " + alerta.toString();
                    }
                } else {
                    // Manejar el caso donde no se encuentra un umbral para la fenología
                    ultimaAlerta = "No se encontró umbral para la fenología " + faseActual.getFase();
                }
            } else {
                ultimaAlerta = "No se encontró fase fenológica para el pronóstico en la fecha "
                        + pronostico.getFecha();
            }
        }

        return ultimaAlerta;
    }

    //////////////////
    public List<String> generarAlertas2(int municipioId) {
        List<Zona> zonas = zonaRepository.findByIdMunicipio(municipioId);
        List<String> alertas = new ArrayList<>();

        for (Zona zona : zonas) {
            List<Cultivo> cultivos = cultivoRepository.findByIdZona(zona.getIdZona());
            List<DatosPronostico> pronosticos = pronosticoRepository.findByIdZona(zona.getIdZona());

            for (Cultivo cultivo : cultivos) {
                Date fechaSiembra = cultivo.getFechaSiembra();
                List<Fenologia> fenologias = fenologiaRepository.findByIdCultivo(cultivo.getIdCultivo());

                for (DatosPronostico pronostico : pronosticos) {
                    Fenologia faseActual = null;
                    Date fechaInicioFase = fechaSiembra;

                    // Determinar la fase fenológica actual
                    for (Fenologia fenologia : fenologias) {
                        // Usar Calendar para sumar días a la fecha
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(fechaInicioFase);
                        calendar.add(Calendar.DAY_OF_YEAR, fenologia.getNroDias());
                        Date fechaFinFase = calendar.getTime();

                        if (!pronostico.getFecha().before(fechaInicioFase)
                                && !pronostico.getFecha().after(fechaFinFase)) {
                            faseActual = fenologia;
                            break;
                        }

                        fechaInicioFase = fechaFinFase;
                    }

                    if (faseActual != null) {
                        // Obtener los umbrales de la fase actual
                        Optional<Umbrales> optionalUmbral = umbralRepository
                                .findByIdFenologia(faseActual.getIdFenologia());
                        if (optionalUmbral.isPresent()) {
                            Umbrales umbral = optionalUmbral.get();

                            // Comparar con el pronóstico actual
                            if (pronostico.getTempMax() > umbral.getTempMax()
                                    || pronostico.getTempMin() < umbral.getTempMin()
                                    || pronostico.getPcpn() > umbral.getPcpn()) {
                                alertas.add("Alerta para el cultivo " + cultivo.getNombre() + " en la fase "
                                        + faseActual.getFase() + " el día " + pronostico.getFecha() + " en la zona "
                                        + zona.getNombre());
                            }
                        } else {
                            // Manejar el caso donde no se encuentra un umbral para la fenología
                            alertas.add("No se encontróoo umbral para la fenología " + faseActual.getFase()
                                    + " en la zona " + zona.getNombre());
                        }
                    } else {
                        alertas.add("No se encontr33333ó fase fenológica para el pronóstico en la fecha "
                                + pronostico.getFecha() + " en la zona " + zona.getNombre());
                    }
                }
            }
        }

        return alertas;
    }

    public DatosPronostico buscarPorIdDatosPronostico(int idDatosPronostico) {
        Optional<DatosPronostico> datosPronostico = datosPronosticoRepo.findById(idDatosPronostico);
        return datosPronostico.get();
    }

    public void guardarDatosPronostico(DatosPronostico datosPronostico) {
        datosPronosticoRepo.save(datosPronostico);
    }

    @Override
    public void guardar(DatosPronostico pronostico) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public DatosPronostico buscarPorId(int idPronostico) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    public void editarPronostico(DatosPronostico request) {
        // Buscar el registro existente por ID
        Optional<DatosPronostico> existingRecord = datosPronosticoRepo.findByIdPronostico(request.getIdPronostico());

        if (existingRecord.isPresent()) {
            DatosPronostico pronostico = existingRecord.get();
            // Actualizar los campos con los valores del request
            pronostico.setTempMax(request.getTempMax());
            pronostico.setTempMin(request.getTempMin());
            pronostico.setPcpn(request.getPcpn());
            pronostico.setFecha(request.getFecha());
            pronostico.setEdit(true); // Establecer el campo editar en true
            pronostico.setDelete(false); // Asumiendo que getDelete devuelve Boolean

            // Guardar la entidad actualizada en la base de datos
            datosPronosticoRepo.save(pronostico);
        } else {
            throw new EntityNotFoundException("Registro no encontrado con el ID: " + request.getIdPronostico());
        }
    }

    public boolean eliminarPronostico(int idpronostico) {
        Optional<DatosPronostico> datosPronosticoOptional = datosPronosticoRepo.findByIdPronostico(idpronostico);
        if (datosPronosticoOptional.isPresent()) {
            DatosPronostico datosPronostico = datosPronosticoOptional.get();
            // Realizar el marcado de eliminación lógica aquí
            datosPronostico.setDelete(true); // Suponiendo un campo 'eliminado' en tu entidad
            datosPronosticoRepo.save(datosPronostico);
            return true;
        } else {
            return false;
        }
    }

}
