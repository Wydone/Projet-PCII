package Controleur;


public class Avancer implements Runnable {
	
	private Controleur monControleur;
	private boolean stop = false; 

	public Avancer(Controleur monControleur) {
		this.monControleur = monControleur; 
	}
	
	@Override
	public void run() {
		System.out.println("debut du thread : AVANCER");
		

		while(!stop){
			
			int actualisation = monControleur.getMonAffichage().map((int)monControleur.getMonEtat().getVitesse(), 200, 0,10,0);

			monControleur.getMonEtat().setDistance(actualisation);
			
			monControleur.getMonEtat().calculDistanceMotoCentreRoute(); 
			
			monControleur.getMonEtat().calculAcceleration();
			
			monControleur.getMonEtat().calculVitesse(); 
			
			//Fin du thread si la moto n'avance plus 
			if(monControleur.getMonEtat().getVitesse() <= 0.0) {
				
				stop = true; 
				
				monControleur.getMonEtat().setStart(false);
			}
			
	
			try {
				
				Thread.sleep(40);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
