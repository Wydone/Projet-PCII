package Controleur;


public class Avancer implements Runnable {
	
	private Controleur monControleur;
	private boolean stop ; 

	public Avancer(Controleur monControleur) {
		this.monControleur = monControleur; 
		this.stop = false ; 
	}
	
	public void startAvancer() {
		this.stop = false; 
	}
	
	@Override
	public void run() {
		System.out.println("debut du thread : AVANCER");
		
		while(!monControleur.getMonEtat().getStart()) {
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while(!stop){
			
			
			int actualisation = monControleur.getMonAffichage().map((int)monControleur.getMonEtat().getVitesse(), 200, 0,10,0);


			monControleur.getMonEtat().calculCentreRoute();	
			
			monControleur.getMonEtat().setDistance(actualisation);
			
			monControleur.getMonEtat().calculDistanceMotoCentreRoute(); 
			
			monControleur.getMonEtat().calculAcceleration();
			
			monControleur.getMonEtat().calculVitesse(); 
			
			
			monControleur.getMonEtat().goUp();
			monControleur.getMonEtat().goDown();
			monControleur.getMonEtat().goLeft();
			monControleur.getMonEtat().goRight();
			
			
			
			
			//Fin du thread si la moto n'avance plus 
			if( (monControleur.getMonEtat().getVitesse() <= 0.0) || (monControleur.getMonEtat().getHorloge() <= 0)) {
				
				stop = true; 
				
				//monControleur.getMonEtat().setStart(false);
				monControleur.getMonEtat().setLoose(true);
			}
			
	
			try {
				
				Thread.sleep(40);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
