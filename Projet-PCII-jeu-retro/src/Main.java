import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Controleur.Avancer;
import Controleur.Controleur;
import Controleur.Repaint;
import Modele.Etat;
import Modele.Route;
import Vue.Affichage;

import Modele.Sprite;

public class Main {
	
	public static void main(String [] args) {
		
		
		Sprite monSprite = new Sprite(); //Création de l'objet Sprite qui est l'image de notre moto
		
		//Création des différentes classes du modèle MVC
		Controleur c = new Controleur(); //Controleur 
		Affichage view = new Affichage(c, monSprite); //Vue 
		Etat e = new Etat(c); // Modele
		Route r = new Route(c); // Route
		
		Repaint t =  new Repaint(c); //Initialisation du thread Repaint pour actualiser l'affichage
		new Thread(t).start(); //Lancement du thread

		view.addKeyListener(c); // Binder les touches du clavier
		
		playMusic("Musique/alt-236-soundtracks-blue-books.wav"); //Lancement de la musique de fond au lancement de l'application 
		
		Avancer a = new Avancer(c); // Initialisation du thread de déplacement 
		new Thread(a).start(); //Lancement du thread de déplacement
		
		//Création de la fenetre et ajout des elements 
		JFrame fenetre = new JFrame("Projet IHM");
		fenetre.addKeyListener(c); 
		fenetre.add(view);
		fenetre.pack();
		fenetre.setVisible(true);		
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * Methode qui prend en paramètre le path d'un fichier audio et lance la musique 
	 * 
	 * @param String FilePath
	 */
	public static void playMusic(String filepath)
	{
		
		File music = new File(filepath); //File 
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(music);  // extraction de l'audio 
			Clip clip = AudioSystem.getClip(); 
			clip.open(audioIn);
			clip.start(); //Lancement de la lecture
						
		}catch(Exception e) {
			
			System.out.println(e); // Gestion d'erreurs
		}
		
		
	}
}

