package Modele;
import java.awt.*;
import javax.swing.JPanel;
import Controleur.Controleur;
	
public class Obstacle {
	
	private int posX = 0; //position x de l'obstacle
	private int posXInitiale = 0; // position initiale x de l'obstacle
	private int posY = 0; // position y de l'obstacle
	private int dimensionX = 50; // dimension largeur de l'obstacle
	private int dimensionY = 50; // hauteur
	
	private Controleur monControleur;
	
	private int dimensionXMax = 40; //dimension max largeur
	private int dimensionYMax = 80; // dimension max hauteur 
	private int coefVitesseDescente = 1; //Vitesse de déplacement dans l'interface 


	/*
	 * CONSTRUCTEUR de classe 
	 * initialise les valeur de l'obstacle comme la position etc..
	 * @param Controleur
	 */
	public Obstacle(Controleur monControleur) {
		
		this.monControleur = monControleur;
		this.monControleur.getMonEtat().setApparitionNext( 50 + (int)(Math.random() * (200 - 40)));

		
		int alea = (int)(Math.random() * (2 - 0 ));
		
		if(alea == 0) {
			this.posX = this.monControleur.getMonAffichage().getCheckPointG_InterMinSUP() + (int)(Math.random() * ((this.monControleur.getMonAffichage().getCheckPointG_InterMaxSUP() - this.monControleur.getMonAffichage().getCheckPointG_InterMinSUP()))-1);
			
		}else if (alea == 1) {
			this.posX = this.monControleur.getMonAffichage().getCheckPointD_InterMinSUP() + (int)(Math.random() * ((this.monControleur.getMonAffichage().getCheckPointD_InterMaxSUP() - this.monControleur.getMonAffichage().getCheckPointD_InterMinSUP()))-1);
			
		}
		
		this.posXInitiale = this.posX;
		
		this.posY = this.monControleur.getMonAffichage().getHorizon() - dimensionX -  this.monControleur.getMonEtat().getDistance();
		
		//System.out.println("Ytest : " + alea);
		
	}
	
	
	//---------------------------------------------------------------------------------
	// GETTERS & SETTERS
	//---------------------------------------------------------------------------------
	
	
	public int getDimensionXMax() {
		return dimensionXMax;
	}

	public void setDimensionXMax(int dimensionXMax) {
		this.dimensionXMax = dimensionXMax;
	}

	public int getDimensionYMax() {
		return dimensionYMax;
	}

	public void setDimensionYMax(int dimensionYMax) {
		this.dimensionYMax = dimensionYMax;
	}
	
	
	public int getPosXInitiale() {
		return posXInitiale;
	}

	public void setPosXInitiale(int posXInitiale) {
		this.posXInitiale = posXInitiale;
	}

	public void checkPoint_pass(Graphics g) {
		 this.monControleur.getMonEtat().setHorloge(20);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getDimensionX() {
		return dimensionX;
	}

	public void setDimensionX(int dimensionX) {
		this.dimensionX = dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public void setDimensionY(int dimensionY) {
		this.dimensionY = dimensionY;
	}

	public Controleur getMonControleur() {
		return monControleur;
	}

	public void setMonControleur(Controleur monControleur) {
		this.monControleur = monControleur;
	}
	
	public int getCoefVitesseDescente() {
		return coefVitesseDescente;
	}



	public void setCoefVitesseDescente(int coefVitesseDescente) {
		this.coefVitesseDescente = coefVitesseDescente;
	}

	
	
}
