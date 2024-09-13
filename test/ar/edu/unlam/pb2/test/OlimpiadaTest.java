package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.*;;

public class OlimpiadaTest {

	private SedeOlimpica sede;
	
	@Before
	public void init () {
		this.sede = new SedeOlimpica();
	}
	
	
	@Test
	public void queSePuedaCrearUnComplejoSimpleEnUnaSedeOlimpica() {	
		Integer codigoComplejo = 1112;
		String nombre = "Estadio Velez";
		Double areaTotalOcupada = 500.3;
		Deporte deporte = Deporte.FUTBOL;
		Complejo copia = new ComplejoSimple(codigoComplejo, nombre, areaTotalOcupada, deporte);

		assertTrue(this.sede.crearUnComplejoSimple(codigoComplejo, nombre, areaTotalOcupada, deporte));
		assertEquals(copia, this.sede.obtenerUnComplejo(codigoComplejo));	
	}
	
	@Test
	public void queSePuedaCrearUnComplejoPolideportivoConUnAreaEnUnaSedeOlimpica() throws Exception {
		Integer codigoComplejo = 1113;
		String nombre = "Monumental";
		Double areaTotalOcupada = 580.7;
		this.sede.crearUnComplejoPolideportivo(codigoComplejo, nombre, areaTotalOcupada);
		
		Deporte deporte = Deporte.FUTBOL;
		Area areaDesignada = Area.ESQUINA_NORESTE;
		assertTrue(this.sede.agregarDeporteConSuAreaAUnComplejo(codigoComplejo, deporte, areaDesignada));
	}
	
	@Test
	public void queSePuedaCrearUnComplejoPolideportivoConUnAreaYUnEventoEnUnaSedeOlimpica() throws Exception {
	
		Integer codigoComplejo = 1113;
		String nombre = "Monumental";
		Double areaTotalOcupada = 580.7;
		this.sede.crearUnComplejoPolideportivo(codigoComplejo, nombre, areaTotalOcupada);
		
		Deporte deporte = Deporte.FUTBOL;
		Area areaDesignada = Area.ESQUINA_NORESTE;
		assertTrue(this.sede.agregarDeporteConSuAreaAUnComplejo(codigoComplejo, deporte, areaDesignada));
		
		Evento evento = new Evento (LocalDate.of(2024, 12, 2), Duration.ofHours(2), 50);
		assertTrue(this.sede.agregarEventoAUnComplejoPolideportivo(codigoComplejo, areaDesignada, evento));		
		
		Area areaObtenida = this.sede.obtenerAreaDeUnEventoDeUnComplejoPolideportivo(codigoComplejo, evento);
		assertEquals(areaDesignada, areaObtenida);

	}
	
	@Test (expected = IndicadorAreaException.class)
	public void queAlAgregarUnAreaAUnPolideportivoConIndicadorYaExistenteLanceUnaExcepcionIndicadorAreaException() throws IndicadorAreaException {
		Integer codigoComplejo = 1113;
		String nombre = "Monumental";
		Double areaTotalOcupada = 580.7;
		this.sede.crearUnComplejoPolideportivo(codigoComplejo, nombre, areaTotalOcupada);
		
		Deporte deporte = Deporte.FUTBOL;
		Area areaDesignada = Area.ESQUINA_NORESTE;
		this.sede.agregarDeporteConSuAreaAUnComplejo(codigoComplejo, deporte, areaDesignada);
		
		Deporte deporte2 = Deporte.ATLETISMO;
		Area areaDesignada2 = Area.ESQUINA_NORESTE;
		this.sede.agregarDeporteConSuAreaAUnComplejo(codigoComplejo, deporte2, areaDesignada2);
	}
	
	@Test
	public void queSePuedaAgregarUnComisarioJuezAUnEvento() throws Exception {
		Integer codigoComplejo = 1113;
		String nombre = "Monumental";
		Double areaTotalOcupada = 580.7;
		assertTrue(this.sede.crearUnComplejoPolideportivo(codigoComplejo, nombre, areaTotalOcupada));
		
		Deporte deporte = Deporte.FUTBOL;
		Area areaDesignada = Area.ESQUINA_NORESTE;
		this.sede.agregarDeporteConSuAreaAUnComplejo(codigoComplejo, deporte, areaDesignada);
		
		Evento evento = new Evento (LocalDate.of(2024, 12, 2), Duration.ofHours(2), 50);
		assertTrue(this.sede.agregarEventoAUnComplejoPolideportivo(codigoComplejo, areaDesignada, evento));		
		
		Comisario juez = new Juez (45070730, "Jimena Gomez", 20);
		assertTrue(this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, juez));
		
		Comisario comisarioObtenido = this.sede.obtenerComisarioDeUnEvento(evento, juez);
		assertEquals(juez, comisarioObtenido);
		assertTrue(comisarioObtenido instanceof Juez);
	}
	
	@Test (expected = ComisarioException.class)
	public void queAlAgregarUnComisarioJuezInexistenteLanceUnaExcepcionComisarioException() throws ComisarioException, Exception {
		Integer codigoComplejo = 1113;
		String nombre = "Monumental";
		Double areaTotalOcupada = 580.7;
		assertTrue(this.sede.crearUnComplejoPolideportivo(codigoComplejo, nombre, areaTotalOcupada));
		
		Deporte deporte = Deporte.FUTBOL;
		Area areaDesignada = Area.ESQUINA_NORESTE;
		this.sede.agregarDeporteConSuAreaAUnComplejo(codigoComplejo, deporte, areaDesignada);
		
		Evento evento = new Evento (LocalDate.of(2024, 12, 2), Duration.ofHours(2), 50);
		assertTrue(this.sede.agregarEventoAUnComplejoPolideportivo(codigoComplejo, areaDesignada, evento));		
		
		assertTrue(this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, null));
	}
	
	@Test
	public void queSePuedaCalcularElTotalDeParticipantesDeLosEventosDeUnComplejoSimple() throws Exception {
		Integer codigoComplejo = 1113;
		String nombre = "Monumental";
		Double areaTotalOcupada = 580.7;
		Deporte deporte = Deporte.FUTBOL;
		assertTrue(this.sede.crearUnComplejoSimple(codigoComplejo, nombre, areaTotalOcupada, deporte));
		
		Evento evento1 = new Evento (LocalDate.of(2024, 12, 2), Duration.ofHours(2), 20);
		this.sede.agregarEventoAUnComplejoSimple(codigoComplejo, evento1);		
		
		Evento evento2 = new Evento (LocalDate.of(2024, 3, 21), Duration.ofHours(3), 15);
		this.sede.agregarEventoAUnComplejoSimple(codigoComplejo, evento2);		
		
		Evento evento3 = new Evento (LocalDate.of(2024, 6, 10), Duration.ofHours(4), 30);
		this.sede.agregarEventoAUnComplejoSimple(codigoComplejo, evento3);		
		
		Evento evento4 = new Evento (LocalDate.of(2024, 8, 13), Duration.ofHours(1), 25);
		this.sede.agregarEventoAUnComplejoSimple(codigoComplejo, evento4);		
		
		Integer totalEsperado = 20 + 15 + 30 + 25;
		Integer totalObtenido = this.sede.obtenerElTotalDeParticipantesDeLosEventosDeUnComplejoSimple(codigoComplejo);
		
		assertEquals(totalEsperado, totalObtenido);
		
		
	}
	
	@Test
	public void queSePuedaCalcularElPromedioDeEdadDeLosComisariosObservadoresDeUnEventoEspecifico() throws Exception {
		Integer codigoComplejo = 1113;
		String nombre = "Monumental";
		Double areaTotalOcupada = 580.7;
		this.sede.crearUnComplejoPolideportivo(codigoComplejo, nombre, areaTotalOcupada);
		
		Deporte deporte = Deporte.FUTBOL;
		Area areaDesignada = Area.ESQUINA_NORESTE;
		this.sede.agregarDeporteConSuAreaAUnComplejo(codigoComplejo, deporte, areaDesignada);
		
		Evento evento = new Evento (LocalDate.of(2024, 12, 2), Duration.ofHours(2), 50);
		this.sede.agregarEventoAUnComplejoPolideportivo(codigoComplejo, areaDesignada, evento);		
		
		Comisario observador1 = new Observador (45070730, "Jimena", 20);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, observador1);
		
		// NO DEBERIA TENER EN CUENTA EL SIGUIENTE
		Comisario juez1 = new Juez (1354865, "Lorena", 58);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, juez1);
		
		Comisario observador2 = new Observador (27263694, "Micaela", 45);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, observador2);
		
		// NO DEBERIA TENER EN CUENTA EL SIGUIENTE
		Comisario juez2 = new Juez (45747265, "Christian", 19);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, juez2);
		
		Comisario observador3 = new Observador (16752672, "Pedro", 60);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, observador3);
		
		Double promedioEsperado = ( 20.0 + 45.0 + 60.0 ) / 3.0;
		Double promedioObtenido = this.sede.obtenerPromedioDeEdadDeComisariosObservadoresDeUnEvento(codigoComplejo, evento);
		assertEquals(promedioEsperado, promedioObtenido);
		
	}
	
	@Test
	public void queSePuedaObtenerUnaListaDeComisariosJuecesDeUnEventoEspecificoSinRepeticiones() throws Exception {
		Integer codigoComplejo = 1113;
		String nombre = "Monumental";
		Double areaTotalOcupada = 580.7;
		this.sede.crearUnComplejoPolideportivo(codigoComplejo, nombre, areaTotalOcupada);
		
		Deporte deporte = Deporte.FUTBOL;
		Area areaDesignada = Area.ESQUINA_NORESTE;
		this.sede.agregarDeporteConSuAreaAUnComplejo(codigoComplejo, deporte, areaDesignada);
		
		Evento evento = new Evento (LocalDate.of(2024, 12, 2), Duration.ofHours(2), 50);
		this.sede.agregarEventoAUnComplejoPolideportivo(codigoComplejo, areaDesignada, evento);		
		
		// NO DEBERIA TENER EN CUENTA EL SIGUIENTE
		Comisario observador1 = new Observador (45070730, "Jimena", 20);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, observador1);
		
		Comisario juez1 = new Juez (13548465, "Lorena", 58);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, juez1);
		
		// NO DEBERIA TENER EN CUENTA EL SIGUIENTE YA QUE ESTA REPETIDO
		Comisario juez1Repetido = new Juez (13548465, "Lorena", 58);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, juez1Repetido);
		
		// NO DEBERIA TENER EN CUENTA EL SIGUIENTE
		Comisario observador2 = new Observador (27263694, "Micaela", 45);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, observador2);
		
		Comisario juez2 = new Juez (45747265, "Christian", 19);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, juez2);

		// NO DEBERIA TENER EN CUENTA EL SIGUIENTE
		Comisario observador3 = new Observador (16752672, "Pedro", 60);
		this.sede.agregarUnComisarioAUnEventoDeUnComplejo(evento, observador3);
		
		TreeSet<Comisario> juecesObtenidos = this.sede.obtenerTodosLosJuecesDeUnEvento(evento);
		
		Integer i = 0;
		for (Comisario comisario : juecesObtenidos) {
			switch (i) {
			case 0:
				assertEquals(juez1, comisario);
				break;
			case 1:
				assertEquals(juez2, comisario);
			} i++;
		}
		
	}

	@Test
	public void queSePuedaObtenerUnMapaDeUnComplejoPolideportivoConNombreDeComplejoYUbicacion() throws Exception {
		
		// tengo las pelotas llenas 
		
	}
	
	
}
