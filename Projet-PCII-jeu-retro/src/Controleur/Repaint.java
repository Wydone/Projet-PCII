package Controleur;

public class Repaint implements Runnable{
	
	private boolean stop = false ; 
	private Controleur monControleur; 
	
	public Repaint(Controleur monControleur) {
		this.monControleur = monControleur;
		
	}
	
	public void run() {
		System.out.println("Start thread repaint");
		while(!stop) {
			
			
			monControleur.getMonAffichage().revalidate(); 
			
			monControleur.getMonAffichage().repaint(); 
			
			
			if(!monControleur.getMonEtat().getStart()) {
				stop = true;
			}
       
	      try { Thread.sleep((int)1000/24); }
	      catch (Exception e) { e.printStackTrace(); }
    }
    
  }
		

}
