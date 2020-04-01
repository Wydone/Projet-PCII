import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import Controleur.Avancer;
import Controleur.Controleur;
import Controleur.Repaint;
import Modele.Etat;
import Modele.Route;
import Vue.Affichage;
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//import sun.audio.*;




public class Main {
	
	
	/*
	public static void playMusic(String filepath)
	{
		
		InputStream music;
		try {
			
			music = new FileInputStream (new File(filepath));
			AudioStream audios = new AudioStream(music);
			AudioPlayer.player.start(audios);
			
		}catch(Exception e) {
			
			JOptionPane.showMessageDialog(null,"Error");
		}
		
		
	}*/
	
	public static void main(String [] args) {

		Controleur c = new Controleur();
		
		
		Affichage view = new Affichage(c);
		Etat e = new Etat(c);
		Route r = new Route(c);
		Repaint t =  new Repaint(c); 
		new Thread(t).start(); 

		view.addKeyListener(c);
		
		//playMusic("Musique/alt-236-soundtracks-blue-books.wav");
		
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


	

