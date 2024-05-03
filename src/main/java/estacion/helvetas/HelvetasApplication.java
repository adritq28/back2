package estacion.helvetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import estacion.helvetas.controller.DatosEstacionController;
import estacion.helvetas.controller.personaController;
import estacion.helvetas.model.DatosEstacion;
import estacion.helvetas.model.persona;

@SpringBootApplication
public class HelvetasApplication implements CommandLineRunner {

	@Autowired
	private personaController controller;
	@Autowired
	private DatosEstacionController controller2;

	public static void main(String[] args) {
		SpringApplication.run(HelvetasApplication.class, args);
	}

	public void run(String... args) throws Exception {
		// buscarTodosDocenteJpa();
		// buscarTodosPersonaJpa();
		System.out.println("_____corre_________");
		mostrarper();
		mostraDatosEstacion();
		// buscarPorIdFacultad();
		// buscarTodosFacultadesJpa2();
		// existeId();
		// buscarTodosCarrera();
		// buscarUltimoPersona();
		// buscarTodosAdministrativosJpa();
		// buscarporCI(); no funca :''v

	}

	private void mostrarper() {
		List<persona> persona = controller.mostrarlistarPersonas();
	}

	private void mostraDatosEstacion() {
		List<DatosEstacion> datosEstacion = controller2.mostrarlistarEstacion();
	}

}
