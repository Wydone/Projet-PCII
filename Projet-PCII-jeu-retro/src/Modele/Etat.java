package Modele;

import java.awt.Point;
import java.util.ArrayList;

import Vue.Affichage;
import Controleur.Controleur;
import Modele.CheckPoint;

public class Etat {
	
	private int positionX ; //postion x de la moto
	private int positionY ; //position y de la moto
	
	private int score; //score du joueur
	private int deplacement = 10; //nombre de pixels dont on ce déplace à chaque tick
	private int horloge ; // Compteur pour le timeur des checkpoint
	private int cptDistance_CheckPoint = 0 ; //compteur pour le calcul des distances avec le checkpoint
	
	private Controleur monControleur; 
	private static int distanceLimiteAcceleration ; //la distance limite jusqu'a laquelle la moto peut s'écarter avant de perdre en acceleration
	private double distanceMotoCentreRoute ; //Distance entre la moto et le centre de  la route 
	private int distance_parcourue ;  //Disance parcourue par la moto 
	private float vitesse ; //vitesse de la moto
	private float vitesseMax = 200 ; //vitesse maximal de la moto
	private float acceleration ; //Valeur de l'acceleration
	private boolean start ;// true => on lance les thread , false => thread en attente
	private boolean loose ; // true => coupe les threads 
	private int decalageBackGround; // valeur de décalage en pixels du backgroud pour donner un effect de déplacement
	
	//Boolean des touches
	private boolean UP ; 
	private boolean DOWN ; 
	private boolean LEFT ;
	private boolean RIGHT ; 

	//d�termine quand apparaitra le prochain osbstacle
	private int apparitionNext = 0;
	
	ArrayList<Obstacle> mesObstacles = new ArrayList<Obstacle>(); // Creation d'une liste d'obstacle
	ArrayList<CheckPoint> mesCheckPoint= new ArrayList<CheckPoint>(); // Creation d'une liste de check point


	/*
	 * CONSTRUCTEUR de classe
	 * Initialise les différentes valeur des attributs de la classe
	 * @param Controleur
	 */
	public Etat(Controleur monControleur) {
		this.monControleur = monControleur; 
		this.monControleur.setMonEtat(this); 
		
		this.positionX = monControleur.getMonAffichage().getPositionMotoCentre(); 
		this.positionY = monControleur.getMonAffichage().getHaut()- monControleur.getMonAffichage().getDimensionMotoHAUT(); 
	
		this.distance_parcourue = 0 ; 	
		this.score = 0;
		this.vitesse = 100 ;
		this.acceleration = 1; 
		
		this.distanceMotoCentreRoute = 0 ; 	
		this.distanceLimiteAcceleration = 150 ; 
		this.horloge = 20; 
		
		this.start = false;
		this.loose = false ; 
		
		this.UP = false; 
		this.DOWN = false; 
		this.LEFT = false; 
		this.RIGHT = false ; 
		
	    Obstacle newObstacle = new Obstacle(this.monControleur);
		this.mesObstacles.add(newObstacle);
		
	}	

	//teste si la moto rentre en colision avec un checkPoint
	/*
	 * Methode qui test si la moto rentre en collison avec un checkpoint
	 * 
	 * @param compteur, position x du checkpoint, poisition y du checkpoint, dimension checkpoint Largeur, dimension checkpoint Hauteur
	 */
	public void collision_motoCheckPoint(int cpt, int checkPointX, int checkPointY, int checkPointDimX,int checkPointDimY) {
		Affichage aff = this.getMonControleur().getMonAffichage();
		int dimMotoX = this.getMonControleur().getMonAffichage().getDimensiontMotoLARG();
		int dimMotoY = this.getMonControleur().getMonAffichage().getDimensiontMotoHAUT();
		 		
		//Test de la collision
		 if	(	(this.positionX  + (int)(dimMotoX/2) >= checkPointX &&  this.positionX  + (int)(dimMotoX/2) <= (checkPointX + checkPointDimX))	&&	 (this.positionY  + (int)(dimMotoY/2) >= checkPointY &&  this.positionY  + (int)(dimMotoY/2) <= (checkPointY + checkPointDimY ))) {
			 this.setHorloge(20);
			 this.mesCheckPoint.remove(cpt);
			 
		//Supprime le check point s'il sort de l'�cran	 
		 }else if(checkPointY + checkPointDimY >  monControleur.getMonAffichage().HAUT) {
			 this.mesCheckPoint.remove(cpt);
		 }
 
	}
		
		
	/*
	 * Methode de test si la moto rentre en colision avec un obstacle
	 * @param compteur, position x de l'obstacle, position y de l'obstacle, dimension largeur, dimension hauteur
	 */
	public void collision_motoObstacle(int cpt, int obstacleX, int obstacleY, int obstacleDimX,int obstacleDimY) {
		
		int dimMotoX = this.getMonControleur().getMonAffichage().getDimensiontMotoLARG();
		int dimMotoY = this.getMonControleur().getMonAffichage().getDimensiontMotoHAUT();
			
		//Test de la collision
		 if	((this.positionX + (int)(dimMotoX/2) >= obstacleX &&  this.positionX + (int)(dimMotoX/2)   <= (obstacleX + obstacleDimX))	&&	 (this.positionY+ (int)(dimMotoY/2)  >= obstacleY &&  this.positionY + (int)(dimMotoY/2) <= (obstacleY  + obstacleDimY ))) {
			 divideVitesse();
			 this.mesObstacles.remove(cpt);
				
		 //Supprime le check point s'il sort de l'�cran	 
		 }else if(obstacleY + obstacleDimX >  monControleur.getMonAffichage().HAUT) {
			 this.mesObstacles.remove(cpt);
		 }
		 
	}
	
	/*
	 * Methode de déplacement du véhicule
	 * Actualise la position du la moto en fonction la touche enfoncé par l'utilisateur (ici monter)
	 */
	public void goUp() {
			
		if(UP) {
			if((positionY - deplacement) > 0) {
				
				positionY -= deplacement;
			}
		}		
	}
		
	/*
	 * Methode de déplacement du véhicule
	 * Actualise la position du la moto en fonction la touche enfoncé par l'utilisateur (ici descendre)
	 */
	public void goDown() {
			
		if(DOWN) {
			if(positionY + deplacement < monControleur.getMonAffichage().HAUT) {
				positionY += deplacement;
				
			}
		}		
	}
	
	/*
	 * Methode de déplacement du véhicule
	 * Actualise la position du la moto en fonction la touche enfoncé par l'utilisateur (ici aller à gauche)
	 */
	public void goLeft() {
		if(LEFT) {
			positionX -= deplacement;
			decalageBackGround -=1; // déplacement du backgroud
		}
	}
	
	/*
	 * Methode de déplacement du véhicule
	 * Actualise la position du la moto en fonction la touche enfoncé par l'utilisateur (ici aller à droite)
	 */
	public void goRight() {
		if(RIGHT) {
			positionX += deplacement;
			decalageBackGround += 1; //deplacement du backgroud
		}
	}
	
	/*
	 * Calcul de l'accelration de la moto en fonction de sa distance avec la route
	 */
	public void calculAcceleration() {
		
		float coef = (float) ((distanceLimiteAcceleration - distanceMotoCentreRoute) / distanceLimiteAcceleration); 
		
		acceleration = coef; 
	}
	
	/*
	 * Calcul la positon x de la route 
	 * return int étant la valeur X du bas du centre de la route
	 */
	public int calculCentreRoute() {
		
		ArrayList<Point> arraySelectionPoint = new ArrayList<Point>();
		arraySelectionPoint = monControleur.getMaRoute().getRoute();
		
		//System.out.println("Mon array de point : "+arraySelectionPoint);
		
		int i = 0; 
		while((arraySelectionPoint.get(i).y + distance_parcourue) >= monControleur.getMonAffichage().HAUT) {
			//System.out.println("Valeur de i : "+ i);
		//	System.out.println("Valeur de y du point : "+ arraySelectionPoint.get(i).y);
			i++; 
		}
		
		//System.out.println("Fin du while");
		//System.out.println("Valeur de i : "+ i);
		
		Point A = arraySelectionPoint.get(i-1); //Init du point ant�rieur � la HAUT
		Point B = arraySelectionPoint.get(i);
		
		//System.out.println("A : "+ A);
		//System.out.println("B : "+ B);

		
		float coef_pente_AB = (float) ((B.x) - (A.x))/(B.y - A.y);
		
		int current_x_centre_route = (int) (coef_pente_AB * ((A.y + distance_parcourue) - monControleur.getMonAffichage().HAUT ))+ A.x; 

		return  current_x_centre_route; 
	}
	
	/*
	 * Mehtode de calcul de la distance entre le centre de la route et du centre de la moto
	 */
	public void calculDistanceMotoCentreRoute() {
		
		int x_route = calculCentreRoute() ; 
		double pythagoreDistance =((positionX - x_route)*(positionX - x_route) )+( (monControleur.getMonAffichage().HAUT- positionY)*(monControleur.getMonAffichage().HAUT- positionY)) ; //pythagore 
		
		distanceMotoCentreRoute = Math.sqrt(pythagoreDistance); 
	}
	
	/*
	 * Methode qui calcul la vitesse actuelle de la moto en fonction de l'acceleration 
	 */
	public void calculVitesse() {
		
		if(vitesse > vitesseMax) {
			vitesse = vitesseMax; 
		}else if (vitesse < 0.0) {
			vitesse = 0; 
			
		}else {
			vitesse +=(float) (acceleration * 1); 
		}
		
	}
	
	/*
	 * Divise la valeur de la vitesse de la moto par 2
	 */
	public void divideVitesse() {
		this.vitesse = (int)(this.vitesse/2);
	}
	
	//---------------------------------------------------------------------------------
	// GETTERS & SETTERS
	//---------------------------------------------------------------------------------
	
	
	
	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public Controleur getMonControleur() {
		return monControleur;
	}

	public void setMonControleur(Controleur monControleur) {
		this.monControleur = monControleur;
	}

	public void setControleur(Controleur monControleur) {
	}


	public int getDistance() {
		return distance_parcourue;
	}


	public void setDistance(int distance) {
		this.distance_parcourue += distance;
		
		 cptDistance_CheckPoint += distance;
		 
		 
		 
		 //Cr�ation des check point
		 if(cptDistance_CheckPoint >= 1000) 
		 {
			 cptDistance_CheckPoint = 0;
			 CheckPoint newCheckPoint = new CheckPoint(this.monControleur);	
			 this.mesCheckPoint.add(newCheckPoint);
		 }
		 
		 
		 
			Obstacle monObstacle = this.mesObstacles.get(this.mesObstacles.size()-1);
			
		 //Cr�ation des obstacle al�atiorement
		if(monControleur.getMonEtat().getDistance() + monObstacle.getPosY()>= this.apparitionNext + this.getMonControleur().getMonAffichage().getHorizon()) 
		 {
			 Obstacle newObstacle = new Obstacle(this.monControleur);	
			 this.mesObstacles.add(newObstacle);
			 }
		 
		 
	}
	
	public ArrayList<Obstacle> getMesObstacles() {
		return mesObstacles;
	}


	public void setMesObstacles(ArrayList<Obstacle> mesObstacles) {
		this.mesObstacles = mesObstacles;
	}
	
	public float getVitesse() {
		return this.vitesse;
	}
	
	public boolean getStart() {
		return this.start; 
	}
	
	public void setStart(boolean b) {
		this.start = b ; 
	}
	
	public boolean getLoose() {
		return this.loose; 
	}
	
	public void setLoose(boolean b) {
		this.loose = b ; 
	}
	

	public void setUP(boolean uP) {
		UP = uP;
	}


	public void setDOWN(boolean dOWN) {
		DOWN = dOWN;
	}


	public void setLEFT(boolean lEFT) {
		LEFT = lEFT;
	}


	public void setRIGHT(boolean rIGHT) {
		RIGHT = rIGHT;
	}


	public int getHorloge() {
		return horloge;
	}

	public void actualiserHorloge() {
		 this.horloge--;

	}
	public ArrayList<CheckPoint> getMesCheckPoint() {
		return mesCheckPoint;
	}


	public void setMesCheckPoint(ArrayList<CheckPoint> mesCheckPoint) {
		this.mesCheckPoint = mesCheckPoint;
	}

	public void setHorloge(int newHorloge) {
		this.horloge = this.horloge + newHorloge;
	}
	
	
	public int getApparitionNext() {
		return apparitionNext;
	}


	public void setApparitionNext(int apparitionNext) {
		this.apparitionNext = apparitionNext;
	}
	
	public int getDecalageBackground() {
		return decalageBackGround; 
	}

}