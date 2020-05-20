package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Event implements Comparable<Event>{
	//ha sempre
	// - tempo
	// - tipo di evento
	
	//eventi possibili
	public enum EventType {
		ARRIVO_GRUPPO_CLIENTI, USCITA_CLIENTI
	}
	
	private EventType type;
	private LocalTime time;
	private Tavolo tavolo;
	private int nPersone;
	private double tolleranza;
	/**
	 * @param type
	 * @param time
	 */
	public Event(EventType type, LocalTime time, Tavolo tavolo, int nPersone, double tolleranza) {
		super();
		this.type = type;
		this.time = time;
		this.tavolo=tavolo;
		this.nPersone=nPersone;
		this.tolleranza=tolleranza;
	}
	
	
	public double getTolleranza() {
		return tolleranza;
	}


	public void setTolleranza(double tolleranza) {
		this.tolleranza = tolleranza;
	}


	public int getnPersone() {
		return nPersone;
	}


	public void setnPersone(int nPersone) {
		this.nPersone = nPersone;
	}


	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public Tavolo getTavolo() {
		return tavolo;
	}
	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.time.compareTo(o.time);
	}
	@Override
	public String toString() {
		return "Event [type=" + type + ", time=" + time +  "]";
	}
	
	
	
	
	
	

}
