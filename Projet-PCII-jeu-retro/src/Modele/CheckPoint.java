package Modele;
import java.awt.*;
import javax.swing.JPanel;
import Controleur.Controleur;

public class CheckPoint {

	private int posX = 0; //position x du checkpoint
	private int posXInitiale = 0; // position x initiale du checkpoint 
	private int posY = 0; //position y du checkpoint
	private int dimensionX = 0; //les dimentions largeur
	private int dimensionY = 0; // hauteur
	
	//les dimensions max qui seront utilisée dans le calcul des proportion pour la perspective
	private int dimensionXMax = 80; 
	private int dimensionYMax = 40; 
	
	private int valeurDescente = 0; //vitesse de chute 

	private Controleur monControleur; 
	
	/*
	 * CONSTRUCTEUR de classe
	 * 
	 * Créer un checkpoint ayant une positon aléatoire (mais borné)
	 * 
	 * @param monControleur
	 */
	public CheckPoint(Controleur monControleur) {
		this.monControleur = monControleur;
			
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
	
	public int getValeurDescente() {
		return valeurDescente;
	}

	public void setValeurDescente(int valeurDescente) {
		this.valeurDescente += valeurDescente;
	}

	
	
}