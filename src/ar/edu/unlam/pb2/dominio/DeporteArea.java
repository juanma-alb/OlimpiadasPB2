package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class DeporteArea {
	private Deporte deporte;
	private Area area;
	
	public DeporteArea(Deporte deporte, Area area) {
		this.deporte = deporte;
		this.area = area;
	}

	public Deporte getDeporte() {
		return deporte;
	}

	public void setDeporte(Deporte deporte) {
		this.deporte = deporte;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		return Objects.hash(area);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeporteArea other = (DeporteArea) obj;
		return area == other.area;
	}	
	
	
	
	
}
