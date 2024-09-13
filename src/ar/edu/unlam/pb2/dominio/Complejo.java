package ar.edu.unlam.pb2.dominio;

import java.util.HashSet;
import java.util.Objects;

public abstract class Complejo {
	private Integer codigo;
	private String nombre;
	private Double areaTotalOcupada;
	
	
	public Complejo(Integer codigo, String nombre, Double areaTotalOcupada) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.areaTotalOcupada = areaTotalOcupada;
	}


	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complejo other = (Complejo) obj;
		return Objects.equals(codigo, other.codigo);
	}


	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public abstract Evento obtenerEvento (Evento evento);
	
	
}
