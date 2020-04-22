package Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modele.CheckPoint;
import Modele.Etat;
import Modele.Route;
import Vue.Affichage;

public class Controleur implements KeyListener {

	private Etat monEtat;
	private Affichage monAffichage; 
	private Route maRoute;
	private CheckPoint monCheckPoint;
	private Avancer threadAvancer;

	
	
	public Avancer getThreadAvancer() {
		return threadAvancer;
	}

	public void setThreadAvancer(Avancer threadAvancer) {
		this.threadAvancer = threadAvancer;
	}

	public Controleur() {
		
	}

	public CheckPoint getMonCheckPoint() {
		return monCheckPoint;
	}

	public void setMonCheckPoint(CheckPoint monCheckPoint) {
		this.monCheckPoint = monCheckPoint;
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
		
		
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		
		case KeyEvent.VK_UP: monEtat.setUP(true); 
		
			break;
			
		case KeyEvent.VK_DOWN: monEtat.setDOWN(true);
			
			break;
			
		case KeyEvent.VK_LEFT: monEtat.setLEFT(true);
			break;
			
		case KeyEvent.VK_RIGHT: monEtat.setRIGHT(true);
			break;
			
		case KeyEvent.VK_SPACE : monEtat.setStart(true); 
			
		}
		
				
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		
		
		switch(keyCode) {
		
			case KeyEvent.VK_UP:  monEtat.setUP(false); //monEtat.goUp();
			
				break;
				
			case KeyEvent.VK_DOWN: monEtat.setDOWN(false); // monEtat.goDown();
				
				break;
				
			case KeyEvent.VK_LEFT: monEtat.setLEFT(false); // monEtat.goLeft(); 
				break;
				
			case KeyEvent.VK_RIGHT: monEtat.setRIGHT(false); // monEtat.goRight();
				break;
			
		}
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}