package Modele;

import java.awt.Point;
import java.util.ArrayList;

import Controleur.Controleur;

import java.lang.Math; 


public class Route {
	
	private Controleur monControleur;
	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Integer> posInitPoints = new ArrayList<Integer>();

	
	public Route(Controleur monControleur){
		this.monControleur = monControleur;
		this.monControleur.setMaRoute(this);
		
		
		Point p1 = new Point((int)(this.monControleur.getMonAffichage().getLarg()/2+50), this.monControleur.getMonAffichage().getHaut());
		this.posInitPoints.add((int)(this.monControleur.getMonAffichage().getLarg()/2) - p1.x );
		
		Point p2 = new Point(((int)(this.monControleur.getMonAffichage().getLarg()/2+100)), this.monControleur.getMonAffichage().getHorizon());
		this.posInitPoints.add((int)(this.monControleur.getMonAffichage().getLarg()/2) - p2.x );

		
		points.add(p1);
		points.add(p2);

	}
	
	
	
	public ArrayList<Point> getRoute() {
	
	 	
		//System.out.println(points);
		
		for(int i=0; i<this.points.size();i++) { //Parcours de l'array de la classe
			//System.out.println("Valeur de i :"+i);
			
			
			if(this.points.get(i).y + monControleur.getMonEtat().getDistance()> monControleur.getMonAffichage().HAUT*2) { //Sinon on ajoute le point dans l'arrayList
				
				this.points.remove(i);
				this.posInitPoints.remove(i);
				
			}
			
			
			//Si le dernier point n'apppartient pas a la fenetre, on en créer un nouveau 
			if(i==this.points.size()-1 && this.points.get(i).y + monControleur.getMonEtat().getDistance() >= monControleur.getMonAffichage().getHorizon()){
				
				
				int x = this.monControleur.getMonAffichage().getRouteSUP_InterMin() + (int)(Math.random() * ((this.monControleur.getMonAffichage().getRouteSUP_InterMax() - this.monControleur.getMonAffichage().getRouteSUP_InterMin()) + 1));
				//int x = this.monControleur.getMonAffichage().getRouteSUP_InterMin() + (int)(Math.random() * ((this.monControleur.getMonAffichage().getRouteSUP_InterMax() - this.monControleur.getMonAffichage().getRouteSUP_InterMin())+1));
				int y =  points.get(points.size()-1).y - 160 ; 
				
				
				Point pointOut = new Point(x,y);
				this.points.add(pointOut);
				this.posInitPoints.add((int)(this.monControleur.getMonAffichage().getLarg()/2) - pointOut.x);

			}
			
		}
		
		return points;
	}
	
	
	
	public int getPosInitPoints(int i) {
		return this.posInitPoints.get(i);
	}
	
	
	
	
}
