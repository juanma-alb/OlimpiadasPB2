package ar.edu.unlam.pb2.dominio;

import java.time.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.TreeSet;

public class Evento {
	
	private LocalDate fecha;
	private Duration duracion;
	private Integer cantidadParticipantes;
	private HashSet<Comisario> comisarios;
	
	public Evento(LocalDate fecha, Duration duracion, Integer cantidadParticipantes) {
		this.fecha = fecha;
		this.duracion = duracion;
		this.cantidadParticipantes = cantidadParticipantes;
		this.comisarios = new HashSet<>();
	}

	public Boolean agregarComisario(Comisario comisario) {
		return this.comisarios.add(comisario);
	}

	public Comisario obtenerComisario(Comisario comisario) {
		for (Comisario comisarioActual : comisarios) {
			if (comisarioActual.equals(comisario)) {
				return comisario;
			}
		} return null;
	}

	public Integer getCantidadParticipantes() {
		return cantidadParticipantes;
	}

	public void setCantidadParticipantes(Integer cantidadParticipantes) {
		this.cantidadParticipantes = cantidadParticipantes;
	}

	public Double obtenerPromedioDeEdadDeComisariosObservadores() {

		Double edadAcumulada = 0.0;
		Double cantidadObservadores = 0.0;
		
		for (Comisario comisario : comisarios) {
			if (comisario instanceof Observador) {
				edadAcumulada += comisario.getEdad();
				cantidadObservadores++;
			}
		}
		
		Double promedio = edadAcumulada / cantidadObservadores;
		
		return promedio;
	}

	public TreeSet<Comisario> obtenerJueces() {
		
		TreeSet<Comisario> auxiliar = new TreeSet<>();
		
		for (Comisario comisario : comisarios) {
			if (comisario instanceof Juez) {
				auxiliar.add(comisario);
			}
		}
		
		return auxiliar;
		
	}
	
	

}
