package break_out.model;

import break_out.Constants;
import break_out.controller.JSONReader;

/**
 * Diese Klasse enthält Daten über das laufende Spiel
 *  
 * @author
 */
public class Level extends Thread {

    /**
     * Das Spiel, zu dem dieser Level gehört
     */
    private Game game;
	 
    /**
   	 * Welches Level läuft gerade?
   	 */
    private int levelnr;
       
    /**
	 * Der Spielstand des aktuellen Levels
	 */
    private int score;
    
    /**
     * Der Ball im aktuellen Level
     */
    private Ball ball;
    
    /**
     * Das Paddel, das mit diesem Level verknüpft ist
     */
    private Paddle paddle;
    
    /**
     * Die Spielsteine des aktuellen Levels
     */
    private Stone[][] stones;
    
    /**
     * Anzahl der "Leben", die der Spieler noch hat
     */
    private int lifeCnt;
    
    /**
     * Instanzvariable 'finished' vom Datentyp boolean
     * zum Ueberpruefen, ob das Spiel abgebrochen wurde
     */
    private boolean finished = false;
    
    /**
     * 
     * Schalter, der anzeigt, ob der Ball sich gerade bewegt
     */
    private boolean ballWasStarted = false;
   
    /**
     * Konstruktor. Er erzeugt ein neues Level-Objekt.
     * 
     * @param game das Game-Objekt des aktuellen Spiels
     * @param levelnr die Level-Nummer des zu erzeugenden Levels
     * @param score der aktuelle Spielstand
     */
    public Level(Game game, int levelnr, int score) {
    	this.game = game;
    	this.levelnr = levelnr;
    	this.score = score;
        this.ball = new Ball();
        // Paddle-Objekt erzeugen, das mittig an der Unterkante ausgerichtet ist
        this.paddle = new Paddle();
        
        loadLevelData(levelnr);
    }

    /**
     *  Getter für das Ball-Objekt
     * @return ball der Ball des aktuellen Levels      
     * 
     */
    public Ball getBall() {
    	return this.ball;
    }
    
    /**
     * Getter für das aktuelle Paddle-Objekt
     * @return paddle das aktuelle Paddle-Objekt
     */
    public Paddle getPaddle() {
    	return this.paddle;
    }
    
    /**
     * setzt den Ball in Bewegung
     */
    public void startBall() {
        ballWasStarted = true;
    }

    /**
     * hält den Ball an Ort und Stelle fest
     */
    public void stopBall() {
        ballWasStarted = false;
    }
    
    /**
     * gibt an, ob der Ball gerade in Bewegung ist (true) oder nicht (false)
     * 
     * @return ballWasStarted True: Ball ist in Bewegung; false: Ball wurde angehalten
     */
    public boolean ballWasStarted() {
        return ballWasStarted;
    }
    
    /**
     * setter um das Spiel zu beenden
     * @param finished Uebergabewert: beendet oder nicht?
     */
    public void setFinished(boolean finished) {
    	this.finished = finished;
    }
    
    /**
     * getter fuer die Steinematrix
     * @return die Steinematrix
     */
	public Stone[][] getStones(){
		
		return this.stones;
	}
	
	/**
	 * getter fuer den aktuellen Spielstand
	 * @return score aktueller Spielstand
	 */
	public int getScore() {
		return this.score;
		
	}
	
	/**
	 * getter fuer die Anzahl der noch vorhandenen Leben
	 * @return lifeCnt die noch vorhandenen Leben
	 */
	public int getLifeCnt() {
		
		return this.lifeCnt;
	}
	

    /**
     * Methode, die den Level-Thread steuert
     */
    public void run() {	
    	game.notifyObservers();
    		
    	// endlosschlefe
    	while (!finished && !allStonesBroken() ) {
    		// wenn der Ball in Bewegung ist ...
	        if (ballWasStarted) {
	                
	            // Den Ball auf Berührung der "Wände" reagieren lassen
	        	ball.reactOnBorder();
	           
	            		            	
	            // die akuelle Position (immer wieder) neu bestimmen
	        	ball.updatePosition();
	        	
	                               
	            // den Observer (von neuem) anweisen, auf Ereignisse zu reagieren 
	            game.notifyObservers();    
	            
	            // Ball-Reaktion nach Beruehrung mit dem Paddel
	            ball.reflectOnPaddle(paddle);
	            
	            // neue Paddleposition (nach Betaetigen von Pfeiltasten)
	            paddle.updatePosition();
	            
	            // wenn ein Stein vom Ball getroffen wurde
	            if (ball.hitsStone(stones)) {
	            	
	            	// Zustand des Steins auf neuesten Stand bringen
	            	// Spielstand auf neuesten Stand bringen
	            	updateStonesAndScore();
	            }
	            
	            // wenn der Ball verloren geht
	            if (ball.ballLost()) {
	            	
	            	// -> ein Leben weniger
	            	decreaseLives();
	            }
	                
	        }
	        // Der Thread versucht, 4 millisekunden anzuhalten 
	        try {
	            Thread.sleep(4);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
    	}   
    }
    
    /**
     * Lädt die Daten den aktuellen Level aus einer JSON-Datei im /res/ Ordner des Projekts
    * 
    * @param levelnr die Nummer des Levels, der geladen werden soll.
    */
    private void loadLevelData(int levelnr) {
    	
    	// Laden der JSON-Datei mit dem entsprechenden Level
    	JSONReader reader = new JSONReader("res/Level" + levelnr + ".json");
    	
        // // befuellen des 2D-Stone-Arrays mit den Werten aus der JSON-Datei
    	int[][] stoneTypes = reader.getStones2DArray();
    	
    	// Setzten der Anzahl an zur Verfuegung stehender Leben aus der JSON-Datei
    	lifeCnt = reader.getLifeCounter();
    	
    	// Erzeugen ein neues Objekt der Klasse Stone
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
     * prueft ob auf dem Spielfeld noch Steine vorhanden sind (true) oder nicht (false)
     * 
     * @return Steine vorhanden (true) oder nicht (false)
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
     * Methode ein Leben abzieht, wenn der ball verloren geht
     * und zum Startscreen geht, wenn der Lebensstand bei 0 steht.
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
