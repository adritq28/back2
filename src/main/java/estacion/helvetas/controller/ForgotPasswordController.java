package estacion.helvetas.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import estacion.helvetas.service.db.WhatsAppService;

@RestController
@RequestMapping("/api")
public class ForgotPasswordController {

    @Value("${whatsapp.api.url}")
    private String apiUrl;

    @Value("${whatsapp.api.token}")
    private String apiToken;

    @Value("${whatsapp.api.phoneNumber}")
    private String phoneNumber;

    // private final UsuarioServiceJpa userService;
    private final WhatsAppService whatsAppService;

    ForgotPasswordController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    public String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    @PostMapping("/sendVerificationCode")
    public ResponseEntity<String> sendVerificationCode(
            @RequestParam("userId") int userId,
            @RequestParam("phoneNumber") String phoneNumber) {

        // Asegurarse de que el número tiene el formato internacional
        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+591" + phoneNumber; // Asume que el código de país es 591 (Bolivia)
        }

        // Lógica para manejar el envío del código de verificación
        boolean success = sendVerificationCodeToPhone(phoneNumber);

        if (success) {
            // Retorna un mensaje de éxito si el código fue enviado
            return ResponseEntity.ok("Código de verificación enviado.");
        } else {
            // Retorna un error si hubo algún problema
            return ResponseEntity.status(500).body("Hubo un error al enviar el código.");
        }
    }

    // Método ficticio para simular el envío del código
    private boolean sendVerificationCodeToPhone(String phoneNumber) {
        // Generar el código de verificación
        String verificationCode = generateVerificationCode();

        // Crear el cuerpo del mensaje con el código de verificación
        String body = String.format(
                "{\"messaging_product\": \"whatsapp\", \"to\": \"%s\", \"type\": \"template\", \"template\": {\"name\": \"hello_world\", \"language\": {\"code\": \"es_ES\"}, \"components\": [{\"type\": \"body\", \"parameters\": [{\"type\": \"text\", \"text\": \"%s\"}]}]}}",
                phoneNumber, verificationCode); // Asegúrate de que el número esté en el formato correcto

        // Configuración de la solicitud
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://graph.facebook.com/v21.0/" + phoneNumber + "/messages"; // URL de la API de WhatsApp
                                                                                      // Business

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken); // Asegúrate de que tu apiToken sea correcto
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            // Enviar el mensaje a través de la API de WhatsApp
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Verificar si la solicitud fue exitosa
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Mensaje enviado correctamente.");
                return true;
            } else {
                System.err.println("Error al enviar mensaje: " + response.getBody());
                return false;
            }
        } catch (Exception e) {
            // Manejo de excepciones
            System.err.println("Error al enviar el mensaje de verificación: " + e.getMessage());
            return false;
        }
    }

}
