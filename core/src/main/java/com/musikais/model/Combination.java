package com.musikais.model;

public class Combination {
	float energia;
	float valencia;
	int legal;
	int absurdo;
	public float getEnergia() {
		return energia;
	}
	public void setEnergia(float energia) {
		this.energia = energia;
	}
	public float getValencia() {
		return valencia;
	}
	public void setValencia(float valencia) {
		this.valencia = valencia;
	}
	public int getLegal() {
		return legal;
	}
	public void setLegal(int legal) {
		this.legal = legal;
	}
	public int getAbsurdo() {
		return absurdo;
	}
	public void setAbsurdo(int absurdo) {
		this.absurdo = absurdo;
	}
	@Override
	public String toString() {
		return "Combination [energia=" + energia + ", valencia=" + valencia
				+ ", legal=" + legal + ", absurdo=" + absurdo + "]";
	}	

}
