package Modele;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.io.IOException;




public class Sprite{

	private BufferedImage image = null; // Buffer contenant l'image à charger
	
	/*
	 * CONSTRUCTEUR de classe 
	 * Initialise le path de l'image du sprite
	 */
	public Sprite() {
		
		try{
			
		image = ImageIO.read(new File("Image/test-moto.png"));
		
		 } catch(IOException e){
	            e.printStackTrace();
	        }
	}
	
	/*
	 * Methode qui draw l'image dans un canvas à une position donnée en parametre
	 * 
	 * @param Graphics g, positon_x , position_y
	 */
	public void render(Graphics g, int x, int y) {
		
		if (image == null) {
			return;
		}
		g.drawImage(image, x, y,image.getWidth(),image.getHeight(), null); // draw l'image sur le canvas
		
	}
	
	public void update(float deltaTime) {}
	
	
}

