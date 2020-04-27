package Controleur;

public class Repaint implements Runnable{
	
	private boolean stop = false ; //Etat du thread false = running, true = stop 
	private Controleur monControleur; 
	
	/*
	 * CONSTRUCTEUR de classe
	 * Permet de set les attibut de classes 
	 * 
	 * @param Contrleur monControleur
	 */

	public Repaint(Controleur monControleur) {
		this.monControleur = monControleur;
		
	}
	
	public void run() {
		System.out.println("Start thread repaint");
		
		//Tant que le thread tourne, on actualise l'affichage
		while(!stop) {
			
			monControleur.getMonAffichage().revalidate(); // garde le focus du cpu
			
			monControleur.getMonAffichage().repaint(); // on redessine tout le canvas avec des valeur d'objets différents et actualisé
			
			if(monControleur.getMonEtat().getLoose()) { //Si le modèle prévient que le joueur à perdu alors on arrete le repaint car c'est la fin du jeu 
				stop = true;
			}
		
		  //Acutisation du thread
	      try { Thread.sleep((int)1000/24); }
	      catch (Exception e) { e.printStackTrace(); }
    }
    
  }
		

}
