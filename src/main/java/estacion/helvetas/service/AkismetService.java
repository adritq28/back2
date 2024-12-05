// package estacion.helvetas.service;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// @Service
// public class AkismetService {

// @Value("${akismet.api.key}") // Asegúrate de tener tu clave API en el archivo
// application.properties
// private String apiKey;

// private static final String AKISMET_URL =
// "https://rest.akismet.com/1.1/comment-check";

// public boolean isSpam(String userIp, String userAgent, String userComment) {
// String url = AKISMET_URL + "?blog=" + apiKey +
// "&user_ip=" + userIp +
// "&user_agent=" + userAgent +
// "&comment_content=" + userComment;

// RestTemplate restTemplate = new RestTemplate();
// ResponseEntity<String> response = restTemplate.getForEntity(url,
// String.class);

// // Si la respuesta es "true", entonces es un bot
// return "true".equals(response.getBody());
// }
// }
