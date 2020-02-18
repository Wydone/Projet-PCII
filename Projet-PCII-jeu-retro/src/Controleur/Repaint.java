package Controleur;

public class Repaint implements Runnable{
	
	private boolean stop = false ; 
	private Controleur monControleur; 
	
	public Repaint(Controleur monControleur) {
		this.monControleur = monControleur;
		
	}
	
	public void run() {
		while(!stop) {
			//System.out.println("Start thread");
			monControleur.getMonAffichage().repaint(); 
       
	      try { Thread.sleep((int)1000/24); }
	      catch (Exception e) { e.printStackTrace(); }
    }
    
  }
		

}
