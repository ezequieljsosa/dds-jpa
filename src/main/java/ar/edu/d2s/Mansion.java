package ar.edu.d2s;

import javax.persistence.Entity;

@Entity
public class Mansion extends Casa {

	private Integer cocheras;
	private Integer canchasDeTenis;
	
	public Mansion() {
		
	}
	
	public Mansion(String string, int i, int j) {
		super(string);
		this.canchasDeTenis = i;
		this.cocheras = j;
	}
	
	public Integer getCocheras() {
		return cocheras;
	}
	public void setCocheras(Integer cocheras) {
		this.cocheras = cocheras;
	}
	public Integer getCanchasDeTenis() {
		return canchasDeTenis;
	}
	public void setCanchasDeTenis(Integer canchasDeTenis) {
		this.canchasDeTenis = canchasDeTenis;
	}
}
