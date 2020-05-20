package it.polito.tdp.bar.model;

public class Tavolo implements Comparable <Tavolo>{
	int nPosti;
	boolean occupato;

	/**
	 * @param nPosti
	 * @param occupato
	 */
	public Tavolo(int nPosti, boolean occupato) {
		super();
		this.nPosti = nPosti;
		this.occupato = occupato;
	}

	
	public boolean isOccupato() {
		return occupato;
	}


	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}


	public int getnPosti() {
		return nPosti;
	}

	public void setnPosti(int nPosti) {
		this.nPosti = nPosti;
	}


	@Override
	public int compareTo(Tavolo o) {
		return this.nPosti-o.getnPosti();
	}
	
	

}
