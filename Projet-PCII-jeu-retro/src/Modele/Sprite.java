package Modele;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.io.IOException;




public class Sprite{

	private BufferedImage image = null;
	
	public Sprite() {
		
		try{
			
		image = ImageIO.read(new File("Image/test-moto.png"));
		
		 } catch(IOException e){
	            e.printStackTrace();
	        }
	}
	
	public void update(float deltaTime) {
		
	}
	
	
	public void render(Graphics g, int x, int y) {
		
		if (image == null) {
			return;
		}

		g.drawImage(image, x, y,image.getWidth(),image.getHeight(), null);
		
	}
	
	
}

