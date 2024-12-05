package estacion.helvetas.service.db;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsAppService {

    @Value("${whatsapp.api.url}")
    private String apiUrl;

    @Value("${whatsapp.api.token}")
    private String apiToken;

    @Value("${whatsapp.api.phoneNumber}")
    private String phoneNumber;

    private final UsuarioServiceJpa userService;

    public WhatsAppService(UsuarioServiceJpa userService) {
        this.userService = userService;
    }

    // Función para generar un código de 6 dígitos
    public String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    // Función para enviar el mensaje por WhatsApp
    public void sendVerificationCode(int userId) {
        // Obtener el número de teléfono del usuario desde la base de datos
        String userPhoneNumber = userService.obtenerTelefonoPorIdUsuario(userId);
        if (userPhoneNumber == null) {
            throw new RuntimeException("El número de teléfono del usuario no se encuentra.");
        }

        String verificationCode = generateVerificationCode();

        // Crear el cuerpo del mensaje con el código de verificación
        String body = String.format(
                "{\"messaging_product\": \"whatsapp\", \"to\": \"%s\", \"type\": \"template\", \"template\": {\"name\": \"hello_world\", \"language\": {\"code\": \"en_US\"}, \"components\": [{\"type\": \"body\", \"parameters\": [{\"type\": \"text\", \"text\": \"%s\"}]}]}}",
                userPhoneNumber, verificationCode);

        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/v21.0/" + phoneNumber + "/messages"; // Asegúrate de que `phoneNumber` esté configurado
                                                                     // correctamente

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            // Enviar el mensaje a través de la API de WhatsApp
            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el mensaje de verificación", e);
        }
    }
}
