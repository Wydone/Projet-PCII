package Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modele.Etat;
import Modele.Route;
import Vue.Affichage;

public class Controleur implements KeyListener {

	private Etat monEtat;
	private Affichage monAffichage; 
	private Route maRoute;
	
	
	public Controleur() {
		

		
	}

	
	

	public Etat getMonEtat() {
		return monEtat;
	}
	
	public Route getMaRoute() {
		return maRoute;
	}
	
	public void setMaRoute(Route maRoute) {
		this.maRoute = maRoute;
	}


	public void setMonEtat(Etat monEtat) {
		this.monEtat = monEtat;
	}

	public Affichage getMonAffichage() {
		return monAffichage;
	}

	public void setMonAffichage(Affichage monAffichage) {
		this.monAffichage = monAffichage;
	}





	@Override
	public void keyPressed(KeyEvent e) {
		
		System.out.println("KeyPressed");
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		
		case KeyEvent.VK_UP: monEtat.goUp();
			break;
			
		case KeyEvent.VK_DOWN: monEtat.goDown(); 
			break;
			
		case KeyEvent.VK_LEFT: monEtat.goLeft(); 
			break;
			
		case KeyEvent.VK_RIGHT: monEtat.goRight(); 
			break;
			
		}
		
				
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
