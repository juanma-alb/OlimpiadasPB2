package ar.edu.unlam.pb2.dominio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ComplejoPolideportivo extends Complejo {
	private HashSet<DeporteArea> deportesConArea;
	private HashMap<Area, HashSet<Evento>> eventosPorArea;

	public ComplejoPolideportivo(Integer codigo, String nombre, Double areaTotalOcupada) {
		super(codigo, nombre, areaTotalOcupada);
		this.deportesConArea = new HashSet<>();
		this.eventosPorArea = new HashMap<>();
	}
	
	public Boolean agregarDeporteConSuArea (Deporte deporte, Area area) throws IndicadorAreaException {
		
	if (verificarSiElAreaTieneDeporteAsignado(area)) {
		throw new IndicadorAreaException("El area ya está ocupada con un deporte");
	}
		
	DeporteArea	deporteArea = new DeporteArea (deporte, area);
	return this.deportesConArea.add(deporteArea);
	}

	public Boolean verificarSiElAreaTieneDeporteAsignado (Area area) {
		for (DeporteArea deporteArea : deportesConArea) {
			if (deporteArea.getArea().equals(area)) {
				return true;
			}
		} return false;
	}
	
	public Boolean agregarEvento(Evento evento, Area area) {
		
		if (!this.verificarSiElAreaEstaCreada(area)) {
			this.eventosPorArea.put(area, new HashSet<>());
		}		
		
		for (Map.Entry<Area, HashSet<Evento>> entry : this.eventosPorArea.entrySet()) {
			if (entry.getKey().equals(area)) {
				return entry.getValue().add(evento);
			}
		}
		
		return false;
	}

	private Boolean verificarSiElAreaEstaCreada( Area area) {
		for (Map.Entry<Area, HashSet<Evento>> entry : this.eventosPorArea.entrySet()) {
			if (entry.getKey().equals(area)) {
				return true;
			}
		} return false;
	}

	@Override
	public Evento obtenerEvento(Evento evento) {
		for (Map.Entry<Area, HashSet<Evento>> entry : this.eventosPorArea.entrySet()) {
			for (Evento eventoActual : entry.getValue()) {
				if (eventoActual.equals(evento)) {
					return evento;
				}
			}
		} return null;
	}
	
	public Area obtenerAreaDeUnEvento(Evento evento) {
		for (Map.Entry<Area, HashSet<Evento>> entry : this.eventosPorArea.entrySet()) {
			for (Evento eventoActual : entry.getValue()) {
				if (eventoActual.equals(evento)) {
					return entry.getKey();
				}
			}
		} return null;
	}
	
	
}
