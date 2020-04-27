package Controleur;

public class Avancer implements Runnable {
	
	private Controleur monControleur;
	private boolean stop ; //Etat du thread false = running, true = stop 

	/*
	 * CONSTRUCTEUR de classe
	 * Permet de set les attibut de classes 
	 * 
	 * @param Contrleur monControleur
	 */
	public Avancer(Controleur monControleur) {
		this.monControleur = monControleur; 
		this.stop = false ; 
	}
	
	/*
	 * Methode qui "actualise" l'etat du thread en mettant le boolean stop à true
	 */
	public void startAvancer() {
		this.stop = false; 
	}
	
	
	@Override
	public void run() {
		System.out.println("debut du thread : AVANCER");
		
		//Tant que le modele n'a pas été mis à jour alors on "Wait" dans la boucle
		while(!monControleur.getMonEtat().getStart()) {
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//Une fois sortie de la boucle plus haut, on peut lancer le contenu du thread
		while(!stop){
			//calculer l'atualisation de la distance 
			int actualisation = monControleur.getMonAffichage().map((int)monControleur.getMonEtat().getVitesse(), 200, 0,10,0);

			monControleur.getMonEtat().calculCentreRoute();	// On lance le calcul ppour savoir où ce trouve le centre de la route 
			
			monControleur.getMonEtat().setDistance(actualisation); //Changment de la distance pour donner l'effet de mouvement vers l'avant
			
			monControleur.getMonEtat().calculDistanceMotoCentreRoute(); //Calcul la distance entre le centre de la route et le centre de la moto (calcul qui servira pour le calcul de l'acceleration)
			
			monControleur.getMonEtat().calculAcceleration(); //Calcul de l'acceleration
			
			monControleur.getMonEtat().calculVitesse(); //Déduction de la vitesse de la moto en fonction de l'acceleration
			
			//Vérification des input directionnel de l'utilisateur
			monControleur.getMonEtat().goUp(); 
			monControleur.getMonEtat().goDown();
			monControleur.getMonEtat().goLeft();
			monControleur.getMonEtat().goRight();
			
			//Fin du thread si la moto n'avance plus 
			if( (monControleur.getMonEtat().getVitesse() <= 0.0) || (monControleur.getMonEtat().getHorloge() <= 0)) {
				
				stop = true; //faire stopper la boucle 
				monControleur.getMonEtat().setLoose(true); //Actualiser l'etat pour afficher le message de fin
			}
			
			//Atualisation du thread
			try {
				
				Thread.sleep(40);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
