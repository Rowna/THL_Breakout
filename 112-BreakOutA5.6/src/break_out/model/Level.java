package break_out.model;

import break_out.Constants;
import break_out.controller.JSONReader;

/**
 * This class contains information about the running game
 * 
 * @author dmlux
 * @author I. Schumacher
 * modified by Saman Shayanfar, Bashar Alsamar
 */
public class Level extends Thread {

    /**
     * The game to which the level belongs 
     */
    private Game game;
	 
    /**
   	 * The number of the level
   	 */
    private int levelnr;
       
    /**
	 * The score of the level
	 */
    private int score;
    
    /**
     * The ball of the level
     */
    private Ball ball;
    
    /**
     * Instantsvariable paddle mit dem Datentyp Paddle
     * Der Paddle des Levels
     */
    private Paddle paddle;
    
    /**
     * Instanzvariable stones vom Datentyp Stone (Nutzen eines Arrays)
     */
    private Stone[][] stones;
    
    /**
     * Instantsvariable lifeCnt
     */
    private int lifeCnt;
    
    
    
    
    /**
     * Instanzvariable finished vom Datentyp boolean
     * zum Ueberpruefen, ob das Spiel abgebrochen wurde
     */
    private boolean finished = false;
    
    /**
     * Flag that shows if the ball was started
     */
    private boolean ballWasStarted = false;
   
    /**
     * The constructor creates a new level object and needs the current game object, 
     * the number of the level to be created and the current score
     * @param game The game object
     * @param levelnr The number of the new level object
     * @param score The score
     */
    public Level(Game game, int levelnr, int score) {
    	this.game = game;
    	this.levelnr = levelnr;
    	this.score = score;
        this.ball = new Ball();
        // Paddle objekt erzeugen, das mittig an der Unterkante ausgerichtet ist
        this.paddle = new Paddle();
        
        loadLevelData(levelnr);
    }

    /**
     * The getter for the ball object
     * @return ball The ball of the level      
     * 
     */
    public Ball getBall() {
    	return this.ball;
    }
    
    /**
     * getter aufrufen, damit Zugriff auf Paddleobjekt moeglich ist
     * @return paddle Mit der Information der Position des Paddles
     */
    public Paddle getPaddle() {
    	return this.paddle;
    }
    
    /**
     * Sets ballWasStarted to true, the ball is moving
     */
    public void startBall() {
        ballWasStarted = true;
    }

    /**
     * Sets ballWasStarted to false, the ball is stopped
     */
    public void stopBall() {
        ballWasStarted = false;
    }
    
    /**
     * Returns if the ball is moving or stopped
     * @return ballWasStarted True: the ball is moving; false: the ball is stopped
     */
    public boolean ballWasStarted() {
        return ballWasStarted;
    }
    
    /**
     * setter um das Spiel zu beenden
     * @param finished Uebergabe, beenden des Spiels
     */
    public void setFinished(boolean finished) {
    	this.finished = finished;
    }
    
    /**
     * getter fuer den Steinematrix
     * @return stones
     */
	public Stone[][] getStones(){
		
		return this.stones;
	}
	
	/**
	 * getter fuer den score
	 * @return score aktueller punktestand
	 */
	public int getScore() {
		return this.score;
		
	}
	
	/**
	 * getter fuer den lifecounter
	 * @return lifeCnt aktueller leben
	 */
	public int getLifeCnt() {
		
		return this.lifeCnt;
	}
	

    /**
     * The method of the level thread
     */
    public void run() {	
    	game.notifyObservers();
    		
    	// endless loop 
    	while (!finished && !allStonesBroken() ) {
    		// if ballWasStarted is true, the ball is moving
	        if (ballWasStarted) {
	                
	            // Call here the balls method for reacting on the borders of the playground
	        	ball.reactOnBorder();
	           
	            		            	
	            // Call here the balls method for updating his position on the playground
	        	ball.updatePosition();
	        	
	                               
	            // Tells the observer to repaint the components on the playground
	            game.notifyObservers();    
	            
	            // Reaktion des Balls nach der Beruehrung mit dem Paddle
	            ball.reflectOnPaddle(paddle);
	            
	            // neue Paddleposition nach Betaetigen von Pfeiltasten
	            // Paddle auf Wandberuehrung pruefen
	            paddle.updatePosition();
	            
	            // wenn ein Stein vom Ball getroffen wurde
	            if (ball.hitsStone(stones)) {
	            	
	            	// erneuern der Eigenschaften vom Stein und aktualisieren des Punktestandes
	            	updateStonesAndScore();
	            }
	            
	            // wenn der Ball verloren geht
	            if (ball.ballLost()) {
	            	
	            	// veringert leben
	            	decreaseLives();
	            }
	                
	        }
	        // The thread pauses for a short time 
	        try {
	            Thread.sleep(4);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
    	}   
    }
    
    /**
    * Loads the information for the level from a json-file located in the folder /res of the project
    * @param levelnr The number X for the LevelX.json file
    */
    private void loadLevelData(int levelnr) {
    	
    	// Laden der JSON-Datei mit dem entsprechenden Level
    	JSONReader reader = new JSONReader("res/Level" + levelnr + ".json");
    	
        // // befuellen der 2D-Stein-Array mit den Werten aus der JSON-Datei
    	int[][] stoneTypes = reader.getStones2DArray();
    	
    	// Setzten der Anzahl an zur Verfuegung stehender Leben aus der JSON-Datei
    	lifeCnt = reader.getLifeCounter();
    	
    	// Erzeugen ein neues Objekt der Klasse Stein
    	// mit der Matrixgroesse aus der JSON-Datei
    	stones = new Stone [Constants.SQUARES_Y][Constants.SQUARES_X];
    	
    	// Zeile der Steinmatrix
    	for (int zeile = 0; zeile < stoneTypes.length; zeile++) {
    		
    		// Spalte der Steinmatrix
    		for (int spalte = 0; spalte < stoneTypes[0].length; spalte++) {
    			
    			int type = stoneTypes[zeile][spalte];
    			
    			Position position = new Position((Constants.SCREEN_WIDTH / stoneTypes[zeile].length) * spalte,
    					(Constants.SCREEN_HEIGHT / stoneTypes.length) * zeile);
    			
    			stones[zeile][spalte] = new Stone(type, position);
    			
    		}
    		
    	}
    			
    }
    
    /**
     * Methode um die Anzeige der Steine und den Punktestand zu aktualisieren
     * 
     */
    private void updateStonesAndScore() {
    	
    	Stone curStone = stones[ball.getHitStonePosition().getLine()][ball.getHitStonePosition().getColumn()];
    	
    	score = score + curStone.getValue();
    	
    	curStone.setType(curStone.getType() - 1);
    	
    }
    
    /**
     * prueft ob auf dem Spielfeld noch Steine vorhanden sind
     * @return true wenn spielfeld leer
     */
    private boolean allStonesBroken() {
    	// Zeile der Steinmatrix
    	for (int i = 0; i < stones.length; i++) {
    		// Spalte der Steinmatrix
    		for (int j = 0; j < stones[i].length; j++) {
    			// wenn es nicht leer ist
    			if(stones[i][j].getType() != 0) {
    				
    				return false;
    			}
    		}
    	}
    	game.getController().toStartScreen();
    	return true;
    }
    
    
    
    /**
     * Methode die den leben verringert wenn der ball verloren geht
     * und am ende wenn keine leben mehr vorhanden ist zum Startscreen geht
     */
    private void decreaseLives() {
    	
    	if (ball.ballLost()) {
    		lifeCnt--;
    	}
    	
    	if (lifeCnt >= 1) {
    		
    		ball.getPosition().setX((Constants.SCREEN_WIDTH - Constants.BALL_DIAMETER) / 2);
    		ball.getPosition().setY(Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER - Constants.PADDLE_HEIGHT);
    		ballWasStarted = false;
    		paddle.getPosition().setX((Constants.SCREEN_WIDTH / 2) - (Constants.PADDLE_WIDTH / 2));
    		paddle.getPosition().setY(Constants.SCREEN_HEIGHT - Constants.PADDLE_HEIGHT);
    		
    	}
    	else {
    		finished = true;
    		game.getController().toStartScreen();
    	}
    }
    
    
    
}
    


	
