package ar.edu.d2s;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Habitacion {

	public final static String AMBIENTE = "ambiente";
	public final static String M2 = "m2";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String ambiente;
	private int m2;

	 @ManyToOne
	 private Casa casa;
	
	 public Casa getCasa() {
	 return casa;
	 }
	 public void setCasa(Casa casa) {
	 this.casa = casa;
	 }
	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public int getM2() {
		return m2;
	}

	public void setM2(int m2) {
		this.m2 = m2;
	}
}