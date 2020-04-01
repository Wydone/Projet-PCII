package Vue;
import java.awt.*;

import javax.swing.JPanel;
import java.util.ArrayList;

import Controleur.Controleur;
import Modele.*;


public class Affichage extends JPanel{
	
	private Controleur monControleur;

	public static final int LARG = 800;
	public static final int HAUT = 600;
	private int horizonY = (int)(HAUT/3);
	private boolean checkPointExist = false;
	private boolean checkPointAffiche = false;
	private CheckPoint checkPoint;
	private int coefPerspective = 15;


	
	private int dimensiontMotoLARG = 50;
	private int dimensiontMotoHAUT = 50;
	private int positionMotoCentre = (int) (LARG/2) - (int)(dimensiontMotoLARG/2);
	
	
	private int cpt; //compteur de temps
	
	private int borneMaxoute = 80;
	
	//Largeur de la route en haut et en bas de l'ecran
	private int largeur_routeMax = 50;
	private int largeur_routeMin = (int)(largeur_routeMax/coefPerspective);
	
	
	//Borne a au bas de l'�cran d'apparition de la route
	private int RouteINF_InterMin = (int) (LARG/2) - borneMaxoute;
	private int RouteINF_InterMax = (int) (LARG/2) + borneMaxoute;
	
	//Borne a en haut de l'�cran d'apparition de la route
	private int RouteSUP_InterMin = (int) (LARG/2) - (int)(borneMaxoute/coefPerspective);
	private int RouteSUP_InterMax = (int) (LARG/2) + (int)(borneMaxoute/coefPerspective);
	
	
	//Borne a au bas de l'�cran d'apparition des checkpoints a Gauche
	private int CheckPointG_InterMaxINF = RouteINF_InterMin - largeur_routeMax;
	private int CheckPointG_InterMinINF = 0;
	
	//Borne l'horizon d'apparition des checkpoints a Gauche
	private int CheckPointG_InterMaxSUP = RouteSUP_InterMin - largeur_routeMin;
	private int CheckPointG_InterMinSUP = CheckPointG_InterMaxSUP - (int)((CheckPointG_InterMaxINF- CheckPointG_InterMinINF) / coefPerspective);
	
	
	
	
	//Borne au bas de l'�cran d'apparition des checkpoints a Droite
	private int CheckPointD_InterMinINF =  RouteINF_InterMax + largeur_routeMax;
	private int CheckPointD_InterMaxINF = LARG;
	
	//Borne l'horizon d'apparition des checkpoints � Droite
	private int CheckPointD_InterMinSUP = RouteSUP_InterMax + largeur_routeMin;
	private int CheckPointD_InterMaxSUP = CheckPointD_InterMinSUP + (int)((CheckPointD_InterMaxINF- CheckPointD_InterMinINF) / coefPerspective); ;
	

	public Affichage(Controleur monControleur) {
		this.setPreferredSize(new Dimension (LARG,HAUT));
		this.monControleur= monControleur;
		this.monControleur.setMonAffichage(this);
		this.setFocusable(true);
		this.requestFocus();
		
		this.cpt = 0; 
	}
	

	@Override
	public void paint(Graphics g) {
		
		if(monControleur.getMonEtat().getStart()) {
			cpt ++; 
		}
		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0,0,LARG,HAUT);

		g2d.drawLine(0, horizonY, LARG, horizonY); //Horizon 
		
		
		
	/*	g2d.drawLine(0, HAUT, CheckPointG_InterMinSUP, horizonY); // 
			g2d.drawLine(CheckPointG_InterMaxINF, HAUT, CheckPointG_InterMaxSUP, horizonY); // 
			
			g2d.drawLine(CheckPointD_InterMinINF, HAUT,CheckPointD_InterMinSUP , horizonY); // 
			g2d.drawLine(LARG, HAUT, CheckPointD_InterMaxSUP, horizonY); // 
*/
		
		g2d.drawOval(monControleur.getMonEtat().getPositionX(), monControleur.getMonEtat().getPositionY(), dimensiontMotoLARG, dimensiontMotoHAUT);
		
		ArrayList<Point> list_point = this.monControleur.getMaRoute().getRoute();
	 
		for(int i = 0; i < list_point.size()-1; i++) {
   		 
			Point p =  list_point.get(i);	
			Point p2 =  list_point.get(i+1);	
		 

			//Modifie les virage en fonction de la perspective
			int ecartInitPoint = this.monControleur.getMaRoute().getPosInitPoints(i);

			if(ecartInitPoint >= 0 && (list_point.get(i).y+ monControleur.getMonEtat().getDistance() >= 900)) {
				int ecart_Final = map(ecartInitPoint, 0, (int) (LARG/2) - RouteSUP_InterMin, 0 ,(int) (LARG/2) - RouteINF_InterMin);
				int ecart_current = map(list_point.get(i).y + monControleur.getMonEtat().getDistance(), horizonY, HAUT ,ecartInitPoint, ecart_Final);
			 
			/*	 System.out.println("horizonY : " + horizonY);
				 System.out.println("HAUT : " + HAUT);	 
				 System.out.println("list_point.get(i).y : " + (list_point.get(i).y+ monControleur.getMonEtat().getDistance()));	 
	
				 
				 System.out.println("ecartInitPoint : " + ecartInitPoint);
				 System.out.println("ecart_current : " + ecart_current);	 
				 System.out.println("ecart_Final : " + ecart_Final);	 */
				 
				 Point pTransition = new Point((int)(LARG/2) - ecart_current,p.y); //
				 list_point.set(i, pTransition);
	
			}

			//Modifie la largeur de la route en fonction de la perspective
			int perspectiveRoute1 = map(p.y + monControleur.getMonEtat().getDistance(), horizonY, HAUT,largeur_routeMin ,largeur_routeMax); 
			int perspectiveRoute2 = map(p2.y + monControleur.getMonEtat().getDistance(), horizonY, HAUT,largeur_routeMin ,largeur_routeMax); 
		
		 
			//ligne de gauche 
			g.drawLine(list_point.get(i).x- perspectiveRoute1, list_point.get(i).y  + monControleur.getMonEtat().getDistance(), list_point.get(i+1).x -perspectiveRoute2, list_point.get(i+1).y+monControleur.getMonEtat().getDistance());

			//ligne de droite 
			g.drawLine(  list_point.get(i).x +perspectiveRoute1,  list_point.get(i).y+ monControleur.getMonEtat().getDistance(), list_point.get(i+1).x+perspectiveRoute2, list_point.get(i+1).y+monControleur.getMonEtat().getDistance());
			g.drawLine(  list_point.get(i).x,  list_point.get(i).y+ monControleur.getMonEtat().getDistance(), list_point.get(i+1).x, list_point.get(i+1).y+monControleur.getMonEtat().getDistance());

		}
   	 
   	 
	    if(!checkPointExist) {
	    	CheckPoint newCheckPoint = new CheckPoint(this.monControleur);
	    	this.checkPoint = newCheckPoint;
	    	checkPointExist=true;
	    	checkPointAffiche=true;
	
	    }
    
	    ArrayList<CheckPoint> mesCheckPoints = new ArrayList<CheckPoint>();
	    mesCheckPoints = this.monControleur.getMonEtat().getMesCheckPoint();
	   	
	    //Affiche tous les check point
	    for(int cpt = 0; cpt < mesCheckPoints.size(); cpt++) {
			
	    	CheckPoint monCheckPoint = mesCheckPoints.get(cpt);	
			
	    	//calcul de la dimensions des check points en fonction de la perspective
	    	int perspectiveCheckPointX = map(monCheckPoint.getPosY() + monControleur.getMonEtat().getDistance(), horizonY, HAUT,(int)(monCheckPoint.getDimensionXMax()/coefPerspective) ,monCheckPoint.getDimensionXMax()); 
	    	int perspectiveCheckPointY = map(monCheckPoint.getPosY() + monControleur.getMonEtat().getDistance(), horizonY, HAUT,(int)(monCheckPoint.getDimensionYMax()/coefPerspective) ,monCheckPoint.getDimensionYMax()); 
	    	int checkPointY =monCheckPoint.getPosY() + monControleur.getMonEtat().getDistance();
			
			int d = 0;
			int posFinal = 0;
			int d_current = 0;
			int distaceInitialFinal = 0;
			
			//Calcul de la position des checkpoints en fonction de la perspective
			if (LARG/2 > monCheckPoint.getPosX()) {
				posFinal = map(monCheckPoint.getPosXInitiale(), CheckPointG_InterMinSUP, CheckPointG_InterMaxSUP ,CheckPointG_InterMinINF, CheckPointG_InterMaxINF);	
				distaceInitialFinal = monCheckPoint.getPosXInitiale() - posFinal;
				d_current = map(monCheckPoint.getPosY() + monControleur.getMonEtat().getDistance(), horizonY, HAUT, 0  ,distaceInitialFinal);
				d=-d_current;
				
			}else {
				posFinal = map(monCheckPoint.getPosXInitiale(), CheckPointD_InterMinSUP, CheckPointD_InterMaxSUP ,CheckPointD_InterMinINF, CheckPointD_InterMaxINF);	
				distaceInitialFinal = monCheckPoint.getPosXInitiale() - posFinal;
				d_current = map(monCheckPoint.getPosY() + monControleur.getMonEtat().getDistance(), horizonY, HAUT, 0  ,distaceInitialFinal);
				d=-d_current;	
			}

			monCheckPoint.setDimensionX(perspectiveCheckPointX);
			monCheckPoint.setDimensionY(perspectiveCheckPointY);
			monCheckPoint.setPosX(monCheckPoint.getPosXInitiale() + d);

			g2d.drawRect (monCheckPoint.getPosX(),checkPointY , perspectiveCheckPointX,  perspectiveCheckPointY);  
			this.monControleur.getMonEtat().collision_motoCheckPoint(cpt, monCheckPoint.getPosX(),checkPointY, monCheckPoint.getDimensionX() , monCheckPoint.getDimensionY());

	    }

		ArrayList<Obstacle> mesObstacles = new ArrayList<Obstacle>();
		mesObstacles = this.monControleur.getMonEtat().getMesObstacles();
		
	   	//Affiche tous les obstacles
		for(int cpt = 0; cpt < mesObstacles.size(); cpt++) {
			
			Obstacle monObstacle = mesObstacles.get(cpt);
				
			//calcul de la dimensions des check points en fonction de la perspective
			int perspectiveObstacleX = map(monObstacle.getPosY() + monControleur.getMonEtat().getDistance(), horizonY, HAUT,(int)(monObstacle.getDimensionXMax()/coefPerspective) ,monObstacle.getDimensionXMax()); 
			int perspectiveObstacleY = map(monObstacle.getPosY() + monControleur.getMonEtat().getDistance(), horizonY, HAUT,(int)(monObstacle.getDimensionYMax()/coefPerspective) , monObstacle.getDimensionYMax()); 
			int obstacleY =monObstacle.getPosY() + monControleur.getMonEtat().getDistance();
			
			int d = 0;
			int posFinal = 0;
			int d_current = 0;
			int distaceInitialFinal = 0;
				
			//Calcul de la position des obstacles en fonction de la perspective
			if (LARG/2 > monObstacle.getPosX()) {
				posFinal = map(monObstacle.getPosXInitiale(), CheckPointG_InterMinSUP, CheckPointG_InterMaxSUP ,CheckPointG_InterMinINF, CheckPointG_InterMaxINF);	
				distaceInitialFinal = monObstacle.getPosXInitiale() - posFinal;
				d_current = map(monObstacle.getPosY() + monControleur.getMonEtat().getDistance(), horizonY, HAUT, 0  ,distaceInitialFinal);
				d=-d_current;
					
			}else {
				posFinal = map(monObstacle.getPosXInitiale(), CheckPointD_InterMinSUP, CheckPointD_InterMaxSUP ,CheckPointD_InterMinINF, CheckPointD_InterMaxINF);	
				distaceInitialFinal = monObstacle.getPosXInitiale() - posFinal;
				d_current = map(monObstacle.getPosY() + monControleur.getMonEtat().getDistance(), horizonY, HAUT, 0  ,distaceInitialFinal);
				d=-d_current;	
			}

			monObstacle.setDimensionX(perspectiveObstacleX);
			monObstacle.setDimensionY(perspectiveObstacleY);
			monObstacle.setPosX(monObstacle.getPosXInitiale() + d);


			g2d.drawRect (monObstacle.getPosX(),obstacleY , perspectiveObstacleX,  perspectiveObstacleY); 
			g2d.setColor(new Color(0, 0, 0));
			g2d.fillRect(monObstacle.getPosX(), obstacleY, perspectiveObstacleX, perspectiveObstacleY);
			this.monControleur.getMonEtat().collision_motoObstacle(cpt, monObstacle.getPosX(), obstacleY, monObstacle.getDimensionX() , monObstacle.getDimensionY());

		}
  
	   	g2d.setColor(new Color(255, 255, 255));
	    g2d.fillRect(0, 0, LARG, horizonY);
	     
	 	g2d.setColor(new Color(0, 0, 0));
	    g2d.drawString("Vitesse : " +(int) monControleur.getMonEtat().getVitesse() + " km/h", 20, 20); 
	
	    g2d.drawString("Score : " +(int) monControleur.getMonEtat().getDistance(), LARG -120 , 20); 
	
	    g2d.drawString("Temps restant : " + this.monControleur.getMonEtat().getHorloge(), LARG -150, HAUT -50); 
	     
     
	    //chrono
	    if (cpt == 24) {
	    	this.monControleur.getMonEtat().actualiserHorloge(); 
	    	cpt = 0; 
	    }
     
     	//SECTION DE PANEL DU DEBUT
     
	    if(!monControleur.getMonEtat().getStart()) {
	    	Font myFont = new Font ("Courier New", 1, 40);
	    	g2d.setFont(myFont);
		  	g2d.setColor(new Color(0, 0, 0));
		  	g2d.drawString("PRESS 'SPACE' TO START", LARG/2 - 280, HAUT/2 + 60); 
	    }
     
     	// Section de dessin du panel de FIN
     	if( monControleur.getMonEtat().getLoose()) { //Si le jeu est bien dans l'état "Loose"
 		
	  		Font myFont = new Font ("Courier New", 1, 40);
	  		g2d.setFont(myFont);
	  		g2d.setColor(new Color(0, 0, 0));
	  		g2d.drawString("YOU LOOSE", LARG/2 - 140, HAUT/2); 
	  		g2d.drawString("YOUR SCORE IS : " + monControleur.getMonEtat().getDistance(), LARG/2 - 230, HAUT/2 + 100); 
	  		
  		}
     
	  //Section de dessin du decors
	 	g2d.drawLine(0,190, 120,50);
	 	g2d.drawLine(120,50 , 230,180);
	 	
	 	g2d.drawLine(230,180 , 420,100);
	 	g2d.drawLine(420,100 , 630,180);
			
	 	g2d.drawLine(610, 195, 800, 70);
	 	
	 	g2d.drawLine(575, 160, 640, 110);
	 	g2d.drawLine(640, 110, 690, 145); 

		
		
	}
	
	
	public boolean isCheckPointExist() {
		return checkPointExist;
	}


	public void setCheckPointExist(boolean checkPointExist) {
		this.checkPointExist = checkPointExist;
	}


	public int map(int x, int in_min, int in_max, int out_min, int out_max) {
		
		return (((x-in_min)* (out_max - out_min)) / (in_max - in_min))  + out_min ; 
	}
	
	
	
	public Controleur getMonControleur() {
		return monControleur;
	}
	public int getDimensionMotoHAUT() {
		return dimensiontMotoHAUT;
	}

	public int getDimensiontMotoLARG() {
		return dimensiontMotoLARG;
	}


	public void setDimensiontMotoLARG(int dimensiontMotoLARG) {
		this.dimensiontMotoLARG = dimensiontMotoLARG;
	}


	public boolean isCheckPointAffiche() {
		return checkPointAffiche;
	}


	public void setCheckPointAffiche(boolean checkPointAffiche) {
		this.checkPointAffiche = checkPointAffiche;
	}


	public int getDimensiontMotoHAUT() {
		return dimensiontMotoHAUT;
	}


	public void setDimensiontMotoHAUT(int dimensiontMotoHAUT) {
		this.dimensiontMotoHAUT = dimensiontMotoHAUT;
	}


	public void setMonControleur(Controleur monControleur) {
		this.monControleur = monControleur;
	}

	public int getPositionMotoCentre() {
		return positionMotoCentre;
	}

	public void setPositionMotoCentre(int positionMotoCentre) {
		this.positionMotoCentre = positionMotoCentre;
	}

	public static int getLarg() {
		return LARG;
	}

	public static int getHaut() {
		return HAUT;
	}

	public int getHorizon() {
		return horizonY;
	}
	
	



	public void setLargeur_routeMax(int largeur_routeMax) {
		this.largeur_routeMax = largeur_routeMax;
	}


	public int getCheckPointG_InterMaxSUP() {
		return CheckPointG_InterMaxSUP;
	}


	public void setCheckPointG_InterMaxSUP(int checkPointG_InterMaxSUP) {
		CheckPointG_InterMaxSUP = checkPointG_InterMaxSUP;
	}


	public int getCheckPointG_InterMinSUP() {
		return CheckPointG_InterMinSUP;
	}


	public void setCheckPointG_InterMinSUP(int checkPointG_InterMinSUP) {
		CheckPointG_InterMinSUP = checkPointG_InterMinSUP;
	}


	public int getCheckPointD_InterMaxSUP() {
		return CheckPointD_InterMaxSUP;
	}


	public void setCheckPointD_InterMaxSUP(int checkPointD_InterMaxSUP) {
		CheckPointD_InterMaxSUP = checkPointD_InterMaxSUP;
	}


	public int getCheckPointD_InterMinSUP() {
		return CheckPointD_InterMinSUP;
	}


	public void setCheckPointD_InterMinSUP(int checkPointD_InterMinSUP) {
		CheckPointD_InterMinSUP = checkPointD_InterMinSUP;
	}


	public int getCheckPointG_InterMaxINF() {
		return CheckPointG_InterMaxINF;
	}


	public void setCheckPointG_InterMaxINF(int checkPointG_InterMaxINF) {
		CheckPointG_InterMaxINF = checkPointG_InterMaxINF;
	}


	public int getCheckPointG_InterMinINF() {
		return CheckPointG_InterMinINF;
	}


	public void setCheckPointG_InterMinINF(int checkPointG_InterMinINF) {
		CheckPointG_InterMinINF = checkPointG_InterMinINF;
	}


	public int getCheckPointD_InterMaxINF() {
		return CheckPointD_InterMaxINF;
	}


	public void setCheckPointD_InterMaxINF(int checkPointD_InterMaxINF) {
		CheckPointD_InterMaxINF = checkPointD_InterMaxINF;
	}


	public int getCheckPointD_InterMinINF() {
		return CheckPointD_InterMinINF;
	}


	public void setCheckPointD_InterMinINF(int checkPointD_InterMinINF) {
		CheckPointD_InterMinINF = checkPointD_InterMinINF;
	}
	
	
	public int getRouteSUP_InterMin() {
		return RouteSUP_InterMin;
	}


	public void setRouteSUP_InterMin(int routeSUP_InterMin) {
		RouteSUP_InterMin = routeSUP_InterMin;
	}


	public int getRouteSUP_InterMax() {
		return RouteSUP_InterMax;
	}


	public void setRouteSUP_InterMax(int routeSUP_InterMax) {
		RouteSUP_InterMax = routeSUP_InterMax;
	}


	public int getRouteINF_InterMin() {
		return RouteINF_InterMin;
	}


	public void setRouteINF_InterMin(int routeINF_InterMin) {
		RouteINF_InterMin = routeINF_InterMin;
	}


	public int getRouteINF_InterMax() {
		return RouteINF_InterMax;
	}


	public void setRouteINF_InterMax(int routeINF_InterMax) {
		RouteINF_InterMax = routeINF_InterMax;
	}
	
	public int getLargeur_routeMax() {
		return largeur_routeMax;
	}

}
