package estacion.helvetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import estacion.helvetas.controller.DatosEstacionController;
import estacion.helvetas.controller.UsuarioController;
import estacion.helvetas.model.DatosEstacion;
import estacion.helvetas.repository.UsuarioRepository;

@SpringBootApplication
public class HelvetasApplication implements CommandLineRunner {

	@Autowired
	private UsuarioController controller;
	@Autowired
	private DatosEstacionController controller2;
	@Autowired
	private UsuarioRepository usurepo;

	public static void main(String[] args) {
		SpringApplication.run(HelvetasApplication.class, args);
	}

	public void run(String... args) throws Exception {
		// buscarTodosDocenteJpa();
		// buscarTodosPersonaJpa();
		System.out.println("_____corre_________");
		mostrarUsuario();
		mostraDatosEstacion();

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
									// estación
		}
	}

	private void mostraDatosEstacion() {
		List<DatosEstacion> datosEstacion = controller2.mostrarlistarEstacion();
	}

}
