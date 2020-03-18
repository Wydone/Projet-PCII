package Modele;

import java.awt.Point;
import java.util.ArrayList;

import Controleur.Controleur;

public class Etat {
	
	private int positionX ; //postion x de la moto
	private int positionY ; //position y de la moto
	
	private int score;
	
	private int deplacement = 20; 
	
	private Controleur monControleur; 
	
	private static int distanceLimiteAcceleration ;
	
	private double distanceMotoCentreRoute ; 
	
	private int distance_parcourue ;  //Disance parcourue par la moto 
	
	private float vitesse ;
	
	private float vitesseMax = 200 ;
	
	private float acceleration ; 

	private boolean start ; 
	
	
	
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
		
		this.start = true ; 
		
	}
	
	
	public void goUp() {
		
		calculCentreRoute();
		if((positionY - deplacement) > 0) {
			
			positionY -= deplacement;
		}
	
		
	}
	
	public void goDown() {
			
			if(positionY + deplacement < monControleur.getMonAffichage().HAUT) {
				positionY += deplacement;
				
			}
		}
	
	
	public void goLeft() {

		System.out.println("Go left");
		positionX -= deplacement;
		
	}
	
	public void goRight() {
		System.out.println("Go right");
		positionX += deplacement;
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
		System.out.println("Distance parcourue : "+ distance_parcourue);
		while((arraySelectionPoint.get(i).y + distance_parcourue) >= monControleur.getMonAffichage().HAUT) {
			//System.out.println("Valeur de i : "+ i);
		//	System.out.println("Valeur de y du point : "+ arraySelectionPoint.get(i).y);
		//	System.out.println("TEST");
			i++; 
		}
		
		//System.out.println("Fin du while");
		//System.out.println("Valeur de i : "+ i);
		
		Point A = arraySelectionPoint.get(i-1); //Init du point antérieur à la HAUT
		Point B = arraySelectionPoint.get(i);
		
		//System.out.println("A : "+ A);
		//System.out.println("B : "+ B);

		
		float coef_pente_AB = (float) ((B.x) - (A.x))/(B.y - A.y);
		
		int current_x_centre_route = (int) (coef_pente_AB * ((A.y + distance_parcourue) - monControleur.getMonAffichage().HAUT ))+ A.x; 
		System.out.println("coef : "+coef_pente_AB);

		
		System.out.println("Milieu route X : " + current_x_centre_route);
		

		return  current_x_centre_route; 
	}
	
	public void calculDistanceMotoCentreRoute() {
		int x_route = calculCentreRoute() ; 
		
		double pythagoreDistance =((positionX - x_route)*(positionX - x_route) )+( (monControleur.getMonAffichage().HAUT- positionY)*(monControleur.getMonAffichage().HAUT- positionY)) ;
		
		distanceMotoCentreRoute = Math.sqrt(pythagoreDistance); 
		
		System.out.println("distance centre et moto : "+ distanceMotoCentreRoute);
	}
	
	public void calculVitesse() {
		
		System.out.println("Acceleration coef : "+ acceleration);
		
		
		if(vitesse > vitesseMax) {
			vitesse = vitesseMax; 
		}else if (vitesse < 0.0) {
			vitesse = 0; 
			
		}else {
			vitesse +=(float) (acceleration * 1); 
		}
		
		
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
	
	
	

}
