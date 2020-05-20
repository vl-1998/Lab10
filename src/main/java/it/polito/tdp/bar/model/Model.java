package it.polito.tdp.bar.model;


public class Model {
	Simulator sim = new Simulator();

	public String simula() {
		sim.setTavolo(2, 4, 4, 5);
		
		sim.run();
		
		String risultato ="";
		
		risultato = "Numero totale clienti: "+ sim.getnClienti() + ". Numero totale clienti soddisfatti: "+ sim.getnClientiSoddisfatti()
		+ ". Numero totale clienti insoddisfatti: "+ sim.getnClientiInsoddisfatti();
		
		return risultato;
	}
	
	/*public String stampa() {
		String result="";
		sim.setClientFrequency(Duration.of((long)(Math.random()*11), ChronoUnit.MINUTES));
		sim.setNumPersone((int)(Math.random()*11));
		sim.setTolleranza(Math.random());
		sim.setTavolo(2, 4, 4, 5);
		
		sim.run();
		
		
		
		
	}*/
}
