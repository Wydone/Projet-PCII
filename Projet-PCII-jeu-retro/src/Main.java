import javax.swing.JFrame;

import Vue.*;
import Modele.*;
import Controleur.*;



public class Main {
	
	public static void main(String [] args) {

		Controleur c = new Controleur();
		
		Affichage view = new Affichage(c);
		Etat e = new Etat(c);
		Route r = new Route(c);
		Repaint t =  new Repaint(c); 
		new Thread(t).start(); 

		view.addKeyListener(c);
		
		Avancer a = new Avancer(c); 
		
		new Thread(a).start();
		
		JFrame fenetre = new JFrame("Projet IHM");
		fenetre.addKeyListener(c);
		fenetre.add(view);
		fenetre.pack();
		fenetre.setVisible(true);
		
		
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
