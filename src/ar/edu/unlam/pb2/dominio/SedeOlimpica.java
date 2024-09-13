package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.TreeSet;

public class SedeOlimpica {
	private HashSet<Complejo> complejos;
	
	public SedeOlimpica() {
		this.complejos = new HashSet<>();
	}

	
	public Complejo obtenerUnComplejo(Integer codigoComplejo) {
		
		for (Complejo complejoActual : complejos) {
			if (complejoActual.getCodigo().equals(codigoComplejo)) {
				return complejoActual;				
			}
		} return null;
		
	}
	
	
	public Boolean crearUnComplejoSimple(Integer codigo, String nombre, Double areaTotalOcupada, Deporte deporte) {
		Complejo complejoSimple = new ComplejoSimple (codigo, nombre, areaTotalOcupada, deporte);
		return this.complejos.add(complejoSimple);
	}


	public boolean crearUnComplejoPolideportivo(Integer codigo, String nombre, Double areaTotalOcupada) {
		Complejo complejoPolideportivo = new ComplejoPolideportivo (codigo, nombre, areaTotalOcupada);
		return this.complejos.add(complejoPolideportivo);
	}


	public Boolean agregarDeporteConSuAreaAUnComplejo(Integer codigoComplejo, Deporte deporte, Area areaDesignada) throws IndicadorAreaException {
		
		if (this.obtenerUnComplejo(codigoComplejo) instanceof ComplejoPolideportivo) {
			ComplejoPolideportivo complejo = (ComplejoPolideportivo) this.obtenerUnComplejo(codigoComplejo);
			return complejo.agregarDeporteConSuArea(deporte, areaDesignada);
		}
		
		return false;
	}
	
	public Boolean agregarEventoAUnComplejoSimple(Integer codigoComplejo, Evento evento) {
		
		if (this.obtenerUnComplejo(codigoComplejo) instanceof ComplejoSimple) {
			ComplejoSimple complejo = (ComplejoSimple) this.obtenerUnComplejo(codigoComplejo);
			return complejo.agregarEvento(evento);				
		}
		
		return false;
	}

	public Boolean agregarEventoAUnComplejoPolideportivo(Integer codigoComplejo, Area area, Evento evento) {
		
		if (this.obtenerUnComplejo(codigoComplejo) instanceof ComplejoPolideportivo) {
			ComplejoPolideportivo complejo = (ComplejoPolideportivo) this.obtenerUnComplejo(codigoComplejo);
			return complejo.agregarEvento(evento, area);		
		}
		
		return false;
	}

	
	public Evento obtenerUnEventoDeUnComplejo (Integer codigoComplejo, Evento evento) {
		
		Complejo complejo = this.obtenerUnComplejo(codigoComplejo);
		return complejo.obtenerEvento(evento);
		
	}
	
	public Area obtenerAreaDeUnEventoDeUnComplejoPolideportivo (Integer codigoComplejo, Evento evento) {
		
		if (this.obtenerUnComplejo(codigoComplejo) instanceof ComplejoPolideportivo) {
			ComplejoPolideportivo complejo = (ComplejoPolideportivo) this.obtenerUnComplejo(codigoComplejo);
			return complejo.obtenerAreaDeUnEvento(evento);	
		}
		
		return null;
		
	}


	public Boolean agregarUnComisarioAUnEventoDeUnComplejo(Evento evento, Comisario comisario) throws ComisarioException {
		
		if (comisario == null) {
			throw new ComisarioException("Comisario inexistente");
		}

		return evento.agregarComisario(comisario);
	}

	public Comisario obtenerComisarioDeUnEvento(Evento evento, Comisario comisario) {
		return evento.obtenerComisario(comisario);
	}


	public Integer obtenerElTotalDeParticipantesDeLosEventosDeUnComplejoSimple(Integer codigoComplejo) {
		
		if (this.obtenerUnComplejo(codigoComplejo) instanceof ComplejoSimple) {
			ComplejoSimple complejo = (ComplejoSimple) this.obtenerUnComplejo(codigoComplejo);
			return complejo.obtenerTotalDeParticipantesDeTodosLosEventos();	
		}
		
		return 0;
	}


	public Double obtenerPromedioDeEdadDeComisariosObservadoresDeUnEvento(Integer codigoComplejo,
			Evento evento) {
		
		return evento.obtenerPromedioDeEdadDeComisariosObservadores();

	}


	public TreeSet<Comisario> obtenerTodosLosJuecesDeUnEvento(Evento evento) {
		return evento.obtenerJueces();
	}
	
	
	
}
