package estacion.helvetas.service.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Map<String, String> generarUltimaAlerta(int cultivoId) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new ResourceNotFoundException2("Cultivo no encontrado"));
        Date fechaSiembra = cultivo.getFechaSiembra();
        List<Fenologia> fenologias = fenologiaRepository.findByIdCultivoOrdered(cultivoId);
        List<DatosPronostico> pronosticos = pronosticoRepository.findByIdZona(cultivo.getIdZona());
        // Map para almacenar las alertas por cada parámetro
        Map<String, String> alertas = new HashMap<>();
        float pcpnAcumulada = 0;
        Map<Fenologia, Float> pcpnPorFase = new HashMap<>();

        for (DatosPronostico pronostico : pronosticos) {
            Fenologia faseActual = null;
            Date fechaInicioFase = fechaSiembra; // Reiniciar fechaInicioFase para cada pronóstico
            // Determinar la fase fenológica actual
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            for (int i = 0; i < fenologias.size(); i++) {
                Fenologia fenologia = fenologias.get(i);
                Date fechaFinFase;
                // Usar Calendar para sumar días a la fecha de inicio de fase
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fechaInicioFase);
                calendar.add(Calendar.DAY_OF_YEAR, fenologia.getNroDias());
                fechaFinFase = calendar.getTime();
                if (!pronostico.getFechaRangoDecenal().before(fechaInicioFase)
                        && !pronostico.getFechaRangoDecenal().after(fechaFinFase)) {

                    faseActual = fenologia;
                    System.out.println("faseee  " + faseActual.getFase() + " fecha pronostico "
                            + pronostico.getFechaRangoDecenal());
                    break;
                }
                fechaInicioFase = fechaFinFase;
            }

            if (faseActual != null) {
                pcpnAcumulada += pronostico.getPcpn();
                pcpnPorFase.put(faseActual, pcpnPorFase.getOrDefault(faseActual, 0f) + pronostico.getPcpn());
                System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
                // Obtener los umbrales de la fase actual
                Optional<Umbrales> optionalUmbral = umbralRepository.findByIdFenologia(faseActual.getIdFenologia());
                // Obtener la precipitación normal de la fase actual
                List<Object[]> normales = fenologiaRepository.obtenerNormal(faseActual.getIdFenologia());
                float pcpnNormal = 0;
                if (!normales.isEmpty()) {
                    // Asumiendo que la consulta devuelve un solo valor de precipitación normal
                    pcpnNormal = (float) normales.get(0)[0];
                }
                if (optionalUmbral.isPresent()) {
                    System.out.println("cccccccccccccccccc");
                    Umbrales umbral = optionalUmbral.get();
                    // Comparaciones para TempMax
                    if (pronostico.getTempMax() > umbral.getTempMax()) {
                        alertas.put("TempMax", "Alerta ROJA: TempMax pronostico: " + pronostico.getTempMax()
                                + " supera el TempMax del umbral: " + umbral.getTempMax() + " en la fase "
                                + faseActual.getFase());
                    } else if (pronostico.getTempMax() > umbral.getUmbSup()) {
                        alertas.put("TempMax", "Alerta AMARILLA: TempMax pronostico: " + pronostico.getTempMax()
                                + " supera el UmbSup del umbral: " + umbral.getUmbSup() + " en la fase "
                                + faseActual.getFase());
                    } else if (pronostico.getTempMax() <= umbral.getTempOptMax()) {
                        alertas.put("TempMax", "Alerta VERDE: TempMax pronostico: " + pronostico.getTempMax()
                                + " esta dentro del rango optimo: " + umbral.getTempOptMax() + " en la fase "
                                + faseActual.getFase());
                    }
                    // Comparaciones para TempMin
                    if (pronostico.getTempMin() < umbral.getTempMin()) {
                        alertas.put("TempMin", "Alerta ROJA: TempMin pronostico: " + pronostico.getTempMin()
                                + " esta por debajo del TempMin del umbral: " + umbral.getTempMin() + " en la fase "
                                + faseActual.getFase());
                    } else if (pronostico.getTempMin() < umbral.getUmbInf()) {
                        alertas.put("TempMin", "Alerta AMARILLA: TempMin pronostico: " + pronostico.getTempMin()
                                + " esta por debajo del UmbInf del umbral: " + umbral.getUmbInf() + " en la fase "
                                + faseActual.getFase());
                    } else if (pronostico.getTempMin() >= umbral.getTempOptMin()) {
                        alertas.put("TempMin", "Alerta VERDE: TempMin pronostico: " + pronostico.getTempMin()
                                + " esta dentro del rango óptimo: " + umbral.getTempOptMin() + " en la fase "
                                + faseActual.getFase());
                    }

                    // Comparaciones para Pcpn acumulada
                    float pcpnAcumuladaFaseActual = pcpnPorFase.get(faseActual);
                    if (pcpnAcumuladaFaseActual > pcpnNormal) {
                        alertas.put("Pcpn", "Alerta ROJA: Pcpn acumulada: " + pcpnAcumuladaFaseActual
                                + " supera la Pcpn normal: " + pcpnNormal + " en la fase "
                                + faseActual.getFase());
                    } else if (pcpnAcumuladaFaseActual > umbral.getPcpn()) {
                        alertas.put("Pcpn", "Alerta AMARILLA: Pcpn acumulada: " + pcpnAcumuladaFaseActual
                                + " supera el Pcpn del umbral: " + umbral.getPcpn() + " en la fase "
                                + faseActual.getFase());
                    }
                } else {
                    alertas.put("General", "No se encontro umbral para la fenologia " + faseActual.getFase());
                }
            } else {
                alertas.put("General", "No se encontro fase fenologica para el pronostico en la fecha "
                        + pronostico.getFechaRangoDecenal());
            }
        }

        // Añadir información de precipitación acumulada a las alertas
        for (Map.Entry<Fenologia, Float> entry : pcpnPorFase.entrySet()) {
            alertas.put("PcpnFase" + entry.getKey().getFase(), "Precipitacion acumulada en la fase "
                    + entry.getKey().getFase() + ": " + entry.getValue());
        }
        alertas.put("PcpnGeneral", "Precipitacion acumulada total hasta la fase actual: " + pcpnAcumulada);

        return alertas;
    }

    //////////////////
    public List<Map<String, Object>> generarPcpnFase(int cultivoId) {
        Cultivo cultivo = cultivoRepository.findById(cultivoId)
                .orElseThrow(() -> new ResourceNotFoundException2("Cultivo no encontrado"));
        Date fechaSiembra = cultivo.getFechaSiembra();
        List<Fenologia> fenologias = fenologiaRepository.findByIdCultivo(cultivoId);
        List<DatosPronostico> pronosticos = pronosticoRepository.findByIdZona(cultivo.getIdZona());
        float pcpnAcumulada = 0;
        Map<Fenologia, Float> pcpnPorFase = new HashMap<>();

        // Calcular la precipitación acumulada por fase
        for (DatosPronostico pronostico : pronosticos) {
            Fenologia faseActual = null;
            Date fechaInicioFase = fechaSiembra; // Reiniciar fechaInicioFase para cada pronóstico

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
                pcpnAcumulada += pronostico.getPcpn();
                pcpnPorFase.put(faseActual, pcpnPorFase.getOrDefault(faseActual, 0f) + pronostico.getPcpn());
            }
        }

        // Convertir el mapa a una lista de Map<String, Object>
        List<Map<String, Object>> pcpnFaseList = new ArrayList<>();
        for (Map.Entry<Fenologia, Float> entry : pcpnPorFase.entrySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("fase", entry.getKey().getFase());
            map.put("pcpnAcumulada", entry.getValue() != null ? entry.getValue() : 0f);
            pcpnFaseList.add(map);
            System.out.println("faseee " + entry.getKey().getFase());
            System.out.println("pcpnaccc " + entry.getValue());
        }
        // Añadir la precipitación acumulada total como un elemento adicional
        Map<String, Object> totalMap = new HashMap<>();
        totalMap.put("fase", "Total");
        totalMap.put("pcpnAcumulada", pcpnAcumulada);
        pcpnFaseList.add(totalMap);

        return pcpnFaseList;
    }

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

    public int contarDatosHoy(int idZona) {
        return datosPronosticoRepo.countDatosHoy(idZona);
    }

}
