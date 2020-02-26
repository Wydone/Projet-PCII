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

	
	public Affichage(Controleur monControleur) {
		this.setPreferredSize(new Dimension (LARG,HAUT));
		this.monControleur= monControleur;
		this.monControleur.setMonAffichage(this);
		this.setFocusable(true);
		this.requestFocus();
	}
	

	@Override
	public void paint(Graphics g) {
		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0,0,LARG,HAUT);

		g2d.drawLine(0, horizonY, LARG, horizonY); //Horizon 
		
		g2d.drawOval(monControleur.getMonEtat().getPositionX(), monControleur.getMonEtat().getPositionY(), dimensiontMotoLARG, dimensiontMotoHAUT);
		


	 ArrayList<Point> list_point = this.monControleur.getMaRoute().getRoute();
	 
   	 for(int i = 0; i < list_point.size()-1; i++) {
		 
		 Point p =  list_point.get(i);	
		 
    	 g.drawLine(list_point.get(i).x, list_point.get(i).y + monControleur.getMonEtat().getDistance(), list_point.get(i+1).x, list_point.get(i+1).y+monControleur.getMonEtat().getDistance());

	 }
		
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
