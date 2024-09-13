package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;

public class ComplejoSimple extends Complejo {
	private Deporte deporte;
	private HashSet<Evento> eventos;


	public ComplejoSimple(Integer codigo, String nombre, Double areaTotalOcupada, Deporte deporte) {
		super(codigo, nombre, areaTotalOcupada);
		this.deporte = deporte;
		this.eventos = new HashSet<>();
	}
	
	public Boolean agregarEvento(Evento evento) {
		return this.eventos.add(evento);
	}
	
	public Evento obtenerEvento (Evento evento) {
		for (Evento eventoActual : eventos) {
			if (eventoActual.equals(evento)) {
				return evento;
			}
		} return null;
	}

	public Integer obtenerTotalDeParticipantesDeTodosLosEventos() {

		Integer total = 0;
		
		for (Evento evento : eventos) {
			total += evento.getCantidadParticipantes();
		}
		
		return total;
	}

}
