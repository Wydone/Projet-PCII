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
	
	
	
	public static void playMusic(String filepath)
	{
		
		//InputStream music;
		File music = new File(filepath);
		try {
			
			/*music = new FileInputStream (new File(filepath));
			AudioStream audios = new AudioStream(music);
			AudioPlayer.player.start(audios);*/
			
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(music); 
			Clip clip = AudioSystem.getClip(); 
			clip.open(audioIn);
			clip.start();
			
			
			
		}catch(Exception e) {
			
			System.out.println(e);
		}
		
		
	}
	
	public static void main(String [] args) {

		Sprite monSprite = new Sprite();
		
		Controleur c = new Controleur();
		Affichage view = new Affichage(c, monSprite);
		Etat e = new Etat(c);
		Route r = new Route(c);
		Repaint t =  new Repaint(c); 
		new Thread(t).start(); 

		view.addKeyListener(c);
		
		playMusic("Musique/alt-236-soundtracks-blue-books.wav");
		

		
		
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

