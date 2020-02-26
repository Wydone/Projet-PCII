package Modele;

import java.awt.Point;
import java.util.ArrayList;

import Controleur.Controleur;

import java.lang.Math; 


public class Route {
	
	private Controleur monControleur;
	private ArrayList<Point> points = new ArrayList<Point>();
	
	public Route(Controleur monControleur){
		this.monControleur = monControleur;
		this.monControleur.setMaRoute(this);
		
		
		Point p0 = new Point((int)(this.monControleur.getMonAffichage().getLarg()/2), this.monControleur.getMonAffichage().getHaut()+500);
		Point p1 = new Point((int)(this.monControleur.getMonAffichage().getLarg()/2), this.monControleur.getMonAffichage().getHaut());
		Point p2 = new Point((int)(this.monControleur.getMonAffichage().getLarg()/2), this.monControleur.getMonAffichage().getHorizon());
		points.add(p1);
		points.add(p2);

	}
	
	
	
	public ArrayList<Point> getRoute() {
	
	 	
		//System.out.println(points);
		
		for(int i=0; i<this.points.size();i++) { //Parcours de l'array de la classe
			//System.out.println("Valeur de i :"+i);
			
			
			if(this.points.get(i).y + monControleur.getMonEtat().getDistance()> monControleur.getMonAffichage().HAUT*2) { //Sinon on ajoute le point dans l'arrayList
				
				points.remove(i);
				System.out.println("Drop points");
				
			}
			
			
			//Si le dernier point n'apppartient pas a la fenetre, on en créer un nouveau 
			if(i==this.points.size()-1 && this.points.get(i).y + monControleur.getMonEtat().getDistance() >= monControleur.getMonAffichage().getHorizon()){
				
				System.out.println("New point");
				
				int Min = 200; 
				int Max = 600;  
				
				int x = Min + (int)(Math.random() * ((Max - Min)+1));
				int y =  points.get(points.size()-1).y - 100 ; 
				
				
				Point pointOut = new Point(x,y);
				this.points.add(pointOut);
				//arrayTemp.add(pointOut);
				
			
			}
			
		}
		
		return points;
	}
	
	
	
	
}
