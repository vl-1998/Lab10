package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	//coda priopritaria fatta di eventi di simulazione
	private PriorityQueue <Event> queue = new PriorityQueue <>();
	
	//Parametri di SIMULAZIONE
	//lista di tavoli disponibili
	private List <Tavolo> tavoli = new ArrayList <>();
	
	
	
	//imposto i parametri di apertura e chiusura del locale
	private final LocalTime oraApertura = LocalTime.of(16, 00);
	private final LocalTime oraChiusura = LocalTime.of(23, 00);
	
	//MODELLO DEL MONDO
	//numero di tavoli disponibili
	private int nTavoli;
	private Duration intervallo;
	private double tolleranza;
	private int nPersone;
	//private List <Tavolo> tavoliOccupati;
	
	//VALORI DA CALCOLARE
	private int nClienti;
	private int nClientiInsoddisfatti;
	private int nClientiSoddisfatti;
	
	//METODI PER RESTITUIRE I RISULTATI
	public int getnClienti() {
		return nClienti;
	}

	public int getnClientiInsoddisfatti() {
		return nClientiInsoddisfatti;
	}

	public int getnClientiSoddisfatti() {
		return nClientiSoddisfatti;
	}

	
	//IMPOSTO I PARAMETRI
	public void setTavolo(int tDieci, int tOtto, int tSei, int tQuattro) {
		Tavolo tdieci = new Tavolo(10, false);
		Tavolo totto = new Tavolo(8, false);
		Tavolo tsei = new Tavolo(6, false);
		Tavolo tquattro = new Tavolo(4, false);
		
		for (int i=0; i<=tDieci; i++) {
			tavoli.add(i, tdieci);
		}
		for (int i=0; i<=tOtto; i++) {
			tavoli.add(i, totto);
		}
		for (int i=0; i<=tSei; i++) {
			tavoli.add(i, tsei);
		}
		for (int i=0; i<=tQuattro; i++) {
			tavoli.add(i, tquattro);
		}
	}
	
	
	//SIMULAZIONE VERA E PROPRIA
	public void run() {
		this.nClienti=0;
		this.nClientiInsoddisfatti=0;
		this.nClientiSoddisfatti=0;
		this.popolaTavolo();
		this.nTavoli=tavoli.size();
		
		
		
		this.queue.clear();
		LocalTime oraArrivoCliente = this.oraApertura;
		
		do {
			nPersone=(int)(Math.random()*11);
			tolleranza= Math.random();
			Event e = new Event (EventType.ARRIVO_GRUPPO_CLIENTI, oraArrivoCliente, null, nPersone, tolleranza);
			this.queue.add(e);
			
			intervallo=Duration.of((long)(Math.random()*11), ChronoUnit.MINUTES);
			
			oraArrivoCliente = oraArrivoCliente.plus(this.intervallo);
			
		} while (oraArrivoCliente.isBefore(this.oraChiusura));
		
		//estraggo gli eventi dalla coda da controllare fin quando ho degli eventi
		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			System.out.println(e);
			
			processEvent(e);
			
		}
	}
	
	
	//PROCESS EVENT
	private void processEvent (Event e) {
		switch(e.getType()) {
		case ARRIVO_GRUPPO_CLIENTI:
			if (this.nTavoli>0) {//if (this.tavoliTemp.size()>0) {
				//controlli sul tavolo 
				for (Tavolo t : tavoli) {
					if (t.isOccupato()==false) {
					if (e.getnPersone() <= t.getnPosti()) {
						if (e.getnPersone() < t.getnPosti()/2) {
							if (e.getTolleranza() >= Math.random()) {
								nClienti = nClienti+e.getnPersone();
								nClientiSoddisfatti = nClientiSoddisfatti+e.getnPersone();
								break;
							} else {
								nClienti = nClienti+e.getnPersone();
								nClientiInsoddisfatti= nClientiInsoddisfatti+e.getnPersone();	
								break;
							}
						} else {
							//posso metterli a questo tavolo 
							t.setOccupato(true);
							this.nTavoli--;
							//this.tavoliOccupati.add(t);
							nClienti = nClienti+nPersone;
							nClientiSoddisfatti = nClientiSoddisfatti+nPersone;
								
							//creo un nuovo evento USCITA CLIENTI
							Duration permanenza = Duration.of((long)(Math.random()*61)+60, ChronoUnit.MINUTES);
								
							Event nuovo = new Event (EventType.USCITA_CLIENTI, e.getTime().plus(permanenza), t, e.getnPersone(), e.getTolleranza());
							this.queue.add(nuovo);
							break;
						}
					}}
				}	
			}
				
			if (this.nTavoli<0) {//if (this.tavoliTemp.size()<0) {
				if (e.getTolleranza() >= Math.random()) {
					nClienti = nClienti+e.getnPersone();
					nClientiSoddisfatti = nClientiSoddisfatti+e.getnPersone();
				} else {
					nClienti = nClienti+e.getnPersone();
					nClientiInsoddisfatti= nClientiInsoddisfatti+e.getnPersone();				
				}
			}
			break;
		
			
		case USCITA_CLIENTI:
			e.getTavolo().setOccupato(false);
			this.nTavoli++;
			break;
		}
	}
	
	private List<Tavolo> popolaTavolo(){
		Tavolo tdieci = new Tavolo(10, false);
		Tavolo totto = new Tavolo(8, false);
		Tavolo tsei = new Tavolo(6, false);
		Tavolo tquattro = new Tavolo(4, false);
		
		this.tavoli.add(tdieci);
		this.tavoli.add(tdieci);
		this.tavoli.add(totto);
		this.tavoli.add(totto);
		this.tavoli.add(totto);
		this.tavoli.add(totto);
		this.tavoli.add(tsei);
		this.tavoli.add(tsei);
		this.tavoli.add(tsei);
		this.tavoli.add(tsei);
		this.tavoli.add(tquattro);
		this.tavoli.add(tquattro);
		this.tavoli.add(tquattro);
		this.tavoli.add(tquattro);
		this.tavoli.add(tquattro);
		
		return tavoli;
	}

}
