package estacion.helvetas;

import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import estacion.helvetas.controller.DatosEstacionMeteorologicaController;
import estacion.helvetas.controller.UsuarioController;
import estacion.helvetas.model.DatosEstacionMeteorologica;
import estacion.helvetas.repository.DatosEstacionMeteorologicaRepository;
import estacion.helvetas.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class HelvetasApplication implements CommandLineRunner {
	// implements CommandLineRunner
	// implements WebMvcConfigurer

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // Configura la zona horaria UTC
	}

	@Autowired
	private UsuarioController controller;
	@Autowired
	private DatosEstacionMeteorologicaController controller2;
	@Autowired
	private UsuarioRepository usurepo;
	@Autowired
	private DatosEstacionMeteorologicaRepository datosrepo;

	public static void main(String[] args) {
		SpringApplication.run(HelvetasApplication.class, args);
	}

	// @Override
	// public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver>
	// resolvers) {
	// HandlerExceptionResolverComposite composite = new
	// HandlerExceptionResolverComposite();

	// SimpleMappingExceptionResolver simpleMappingExceptionResolver = new
	// SimpleMappingExceptionResolver();
	// simpleMappingExceptionResolver.setDefaultErrorView("error");

	// List<HandlerExceptionResolver> exceptionResolvers = new ArrayList<>();
	// exceptionResolvers.add(simpleMappingExceptionResolver);
	// composite.setExceptionResolvers(exceptionResolvers);

	// resolvers.add(composite);
	// }

	public void run(String... args) throws Exception {
		// buscarTodosDocenteJpa();
		// buscarTodosPersonaJpa();
		System.out.println("_____corre_________");
		// mostrarUsuario();
		// mostraDatosEstacion();
		// mostrardatos();

	}

	private void mostrarUsuario() {
		// Suponiendo que usuarioRepository está inyectado en tu clase o que puedes
		// acceder a él de alguna manera
		List<Object[]> l = usurepo.buscarUsuariosConEstacion();

		// Ahora puedes manipular la lista 'l' como lo desees
		for (Object[] usuario : l) {
			for (Object elemento : usuario) {
				System.out.print(elemento + " ");
			}
			System.out.println(); // Salto de línea después de imprimir todos los elementos de un usuario con
		}
	}

	// private void mostrardatos() {
	// // Suponiendo que usuarioRepository está inyectado en tu clase o que puedes
	// // acceder a él de alguna manera
	// List<Object[]> l = datosrepo.obtenerDatosEstacion();

	// // Ahora puedes manipular la lista 'l' como lo desees
	// for (Object[] usuario : l) {
	// for (Object elemento : usuario) {
	// System.out.print(elemento + " ");
	// }
	// System.out.println(); // Salto de línea después de imprimir todos los
	// elementos de un usuario con
	// // estación
	// }
	// }

	private void mostraDatosEstacion() {
		List<DatosEstacionMeteorologica> datosEstacion = controller2.mostrarlistarEstacion();
	}

}
