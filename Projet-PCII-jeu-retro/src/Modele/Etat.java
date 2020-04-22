package Modele;

import java.awt.Point;
import java.util.ArrayList;

import Vue.Affichage;
import Controleur.Controleur;
import Modele.CheckPoint;

public class Etat {
	
	private int positionX ; //postion x de la moto
	private int positionY ; //position y de la moto
	
	private int score;
	private int deplacement = 10; 
	private int horloge ; 
	private int cptDistance_CheckPoint = 0 ; 


	private Controleur monControleur; 
	
	private static int distanceLimiteAcceleration ;
	
	private double distanceMotoCentreRoute ; 
	
	private int distance_parcourue ;  //Disance parcourue par la moto 
	
	private float vitesse ;
	
	private float vitesseMax = 200 ;
	
	private float acceleration ; 
	
	private boolean start ;
	
	private boolean loose ; 
	
	//Boolean des touches
	private boolean UP ; 
	private boolean DOWN ; 
	private boolean LEFT ;
	private boolean RIGHT ; 

	//d�termine quand apparaitra le prochain osbstacle
	private int apparitionNext = 0;
	
	ArrayList<Obstacle> mesObstacles = new ArrayList<Obstacle>(); // Creation d'une liste d'obstacle
	ArrayList<CheckPoint> mesCheckPoint= new ArrayList<CheckPoint>(); // Creation d'une liste de check point

	private int decalageBackGround; 
	
	public Etat(Controleur monControleur) {
		this.monControleur = monControleur; 
		this.monControleur.setMonEtat(this);
		
		this.positionX = monControleur.getMonAffichage().getPositionMotoCentre(); 
		this.positionY = monControleur.getMonAffichage().getHaut()- monControleur.getMonAffichage().getDimensionMotoHAUT(); 
	//	this.distanceLimiteAcceleration = 0; 
		
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
		
		System.out.println("Dans Etat");

	    Obstacle newObstacle = new Obstacle(this.monControleur);
		this.mesObstacles.add(newObstacle);
		
	}
	
	public ArrayList<CheckPoint> getMesCheckPoint() {
		return mesCheckPoint;
	}


	public void setMesCheckPoint(ArrayList<CheckPoint> mesCheckPoint) {
		this.mesCheckPoint = mesCheckPoint;
	}
	

	//teste si la moto rentre en colision avec un checkPoint

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
		
		
		//teste si la moto rentre en colision avec un obstacle
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
	public void goUp() {
			
		if(UP) {
			if((positionY - deplacement) > 0) {
				
				positionY -= deplacement;
			}
		}		
	}
		
	
	public void goDown() {
			
		if(DOWN) {
			if(positionY + deplacement < monControleur.getMonAffichage().HAUT) {
				positionY += deplacement;
				
			}
		}		
	}
	
	
	public void goLeft() {
		if(LEFT) {
			positionX -= deplacement;
			decalageBackGround -=1; 
		}
	}
	
	public void goRight() {
		if(RIGHT) {
			positionX += deplacement;
			decalageBackGround += 1; 
		}
	}
	
	public void calculAcceleration() {
		
		float coef = (float) ((distanceLimiteAcceleration - distanceMotoCentreRoute) / distanceLimiteAcceleration); 
		
		acceleration = coef; 
	}
	
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
	
	public void calculDistanceMotoCentreRoute() {
		int x_route = calculCentreRoute() ; 
		
		double pythagoreDistance =((positionX - x_route)*(positionX - x_route) )+( (monControleur.getMonAffichage().HAUT- positionY)*(monControleur.getMonAffichage().HAUT- positionY)) ;
		
		distanceMotoCentreRoute = Math.sqrt(pythagoreDistance); 
		
	}
	
	public void calculVitesse() {
		
		
		if(vitesse > vitesseMax) {
			vitesse = vitesseMax; 
		}else if (vitesse < 0.0) {
			vitesse = 0; 
			
		}else {
			vitesse +=(float) (acceleration * 1); 
		}
		
		
	}
	
	public void divideVitesse() {
		this.vitesse = (int)(this.vitesse/2);
	}
	
	
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