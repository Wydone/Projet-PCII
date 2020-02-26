package Modele;

import java.awt.Point;
import java.util.ArrayList;

import Controleur.Controleur;

public class Etat {
	
	private int positionX ;
	private int positionY ; 
	
	private int score;
	
	private int deplacement = 10; 
	
	private Controleur monControleur; 
	
	private static int distanceLimiteAcceleration = 100;
	
	private int distanceMotoCentreRoute ; 
	
	private int distance_parcourue ;  //Disance parcourue par la moto 
	
	private int vitesse ;
	
	private int acceleration ; 

	
	
	
	public Etat(Controleur monControleur) {
		this.monControleur = monControleur; 
		this.monControleur.setMonEtat(this);
		
		this.positionX = monControleur.getMonAffichage().getPositionMotoCentre(); 
		this.positionY = monControleur.getMonAffichage().getHaut()- monControleur.getMonAffichage().getDimensionMotoHAUT(); 
	//	this.distanceLimiteAcceleration = 0; 
		
		this.distance_parcourue = 0 ; 
		
		this.score = 0;
		this.vitesse = 0 ;
		this.acceleration = 1; 
		
		this.distanceMotoCentreRoute = 0 ; 
		
	}
	
	
	public void goUp() {
		
		calculDistanceMotoCentreRoute();
		if((positionY - deplacement) > 0) {
			
			
			System.out.println("if ok : " + positionX);
			positionY -= deplacement;
		}
		
		System.out.println("Après  : " + positionX);

		
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
		
		int coef = (distanceLimiteAcceleration - distanceMotoCentreRoute) / distanceLimiteAcceleration; 
		
		acceleration = coef * acceleration; 
	}
	
	public void calculDistanceMotoCentreRoute() {
		
		
		ArrayList<Point> arraySelectionPoint = new ArrayList<Point>();
		arraySelectionPoint = monControleur.getMaRoute().getRoute();
		
		System.out.println("TEST");
		
		System.out.println(arraySelectionPoint);
		
		int i = 0; 
		
		while(arraySelectionPoint.get(i).y >= monControleur.getMonAffichage().HAUT) {
			System.out.println("Valeur de i : "+ i);
			System.out.println("Valeur de y du point : "+ arraySelectionPoint.get(i).y);

			i++; 
		}
		System.out.println("Valeur de i : "+ i);
		
		Point A = arraySelectionPoint.get(i-1); //Init du point antérieur à la HAUT
		Point B = arraySelectionPoint.get(i);
		
		System.out.println("A : "+ A);
		System.out.println("B : "+ B);

		
		float coef_pente_AB = (B.y - A.y)/(B.x - A.x);
		
		int current_x_pos = (int) (coef_pente_AB * (monControleur.getMonAffichage().HAUT - A.y))+ A.x; 
		
		System.out.println("Milieu route X : " + current_x_pos);
		
		
		
		
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
	
	
	

}
