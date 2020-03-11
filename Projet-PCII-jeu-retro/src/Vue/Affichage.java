package Vue;
import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;

import Controleur.Controleur;

public class Affichage extends JPanel{
	
	private Controleur monControleur;

	public static final int LARG = 800;
	public static final int HAUT = 600;
	private int horizonY = (int)(HAUT/3);
	
	private int dimensiontMotoLARG = 50;
	private int dimensiontMotoHAUT = 50;
	private int positionMotoCentre = (int) (LARG/2) - (int)(dimensiontMotoLARG/2);
	
	private int largeurRouteDemiRoute = 50 ; 
	
	private int cpt; //compteur de temps
	private int horloge ; 
	

	
	public Affichage(Controleur monControleur) {
		this.setPreferredSize(new Dimension (LARG,HAUT));
		this.monControleur= monControleur;
		this.monControleur.setMonAffichage(this);
		this.setFocusable(true);
		this.requestFocus();
		
		this.cpt = 0; 
		this.horloge = 20; 
	}
	

	@Override
	public void paint(Graphics g) {
		
		cpt ++; 
		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0,0,LARG,HAUT);

		g2d.drawLine(0, horizonY, LARG, horizonY); //Horizon 
		
		g2d.drawOval(monControleur.getMonEtat().getPositionX(), monControleur.getMonEtat().getPositionY(), dimensiontMotoLARG, dimensiontMotoHAUT);
		

		
	 ArrayList<Point> list_point = this.monControleur.getMaRoute().getRoute();
	 
	 
	 
   	 for(int i = 0; i < list_point.size()-1; i++) {
   		 
		 Point p =  list_point.get(i);	
		 Point p2 =  list_point.get(i+1);	
		 
		
		 
		 int perspectiveRoute1 = map(p.y + monControleur.getMonEtat().getDistance(), horizonY, HAUT,5 ,60); 
		 int perspectiveRoute2 = map(p2.y + monControleur.getMonEtat().getDistance(), horizonY, HAUT,5 ,60); 
		 //System.out.println("Map : " + largeurRouteDemiRoute);
		
		 
		 //ligne de gauche 
    	 g.drawLine(list_point.get(i).x- perspectiveRoute1,list_point.get(i).y  + monControleur.getMonEtat().getDistance(), list_point.get(i+1).x -perspectiveRoute2, list_point.get(i+1).y+monControleur.getMonEtat().getDistance());

    	 //ligne de droite 
    	 g.drawLine(  list_point.get(i).x +perspectiveRoute1,  list_point.get(i).y+ monControleur.getMonEtat().getDistance(), list_point.get(i+1).x+perspectiveRoute2, list_point.get(i+1).y+monControleur.getMonEtat().getDistance());

	 }
   	 
   	 g2d.setColor(new Color(255, 255, 255));
     g2d.fillRect(0, 0, LARG, horizonY);
     
 	 g2d.setColor(new Color(0, 0, 0));
     g2d.drawString("Vitesse : " +(int) monControleur.getMonEtat().getVitesse() + " km/h", 20, 20); 

     g2d.drawString("Score : " +(int) monControleur.getMonEtat().getDistance(), LARG -120 , 20); 

     g2d.drawString("Temps restant : " + horloge, LARG -150, HAUT -50); 
     
     
     //chrono
     if (cpt == 24) {
    	 horloge --; 
    	 cpt = 0; 
     }
     
		
	}
	
	
	public int map(int x, int in_min, int in_max, int out_min, int out_max) {
		
		return (((x-in_min)* (out_max - out_min)) / (in_max - in_min))  + out_min ; 
	}
	
	
	
	public Controleur getMonControleur() {
		return monControleur;
	}
	public int getDimensionMotoHAUT() {
		return dimensiontMotoHAUT;
	}

	public void setMonControleur(Controleur monControleur) {
		this.monControleur = monControleur;
	}

	public int getPositionMotoCentre() {
		return positionMotoCentre;
	}

	public void setPositionMotoCentre(int positionMotoCentre) {
		this.positionMotoCentre = positionMotoCentre;
	}

	public static int getLarg() {
		return LARG;
	}

	public static int getHaut() {
		return HAUT;
	}

	public int getHorizon() {
		return horizonY;
	}
	
	


}
