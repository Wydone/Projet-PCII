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
			
			monControleur.getMonEtat().setDistance(4);
			
	
			try {
				Thread.sleep(50);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
