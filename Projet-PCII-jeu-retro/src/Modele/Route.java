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
		
		Point p1 = new Point((int)(this.monControleur.getMonAffichage().getLarg()/2), this.monControleur.getMonAffichage().getHaut());
		Point p2 = new Point((int)(this.monControleur.getMonAffichage().getLarg()/2), this.monControleur.getMonAffichage().getHorizon());
		points.add(p1);
		points.add(p2);

	}
	
	
	
	public ArrayList<Point> getRoute() {
		return points;
	}
	
	
}
