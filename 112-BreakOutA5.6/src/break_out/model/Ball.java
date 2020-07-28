package break_out.model;

import break_out.Constants;
import break_out.model.Position;
import java.awt.Rectangle;

/**
 * Diese Klasse enthält alle Daten über die Eigenschaften und Actionen des Balls
 * 
 * @author
 *
 */
public class Ball {

	/**
	 * Position auf dem Spielfeld
	 */
	private Position position;
	
	/**
	 * Bewegungsrichtung des Balls
	 */
	private Vector2D direction;
	
	
	/**
	 * MatrixPosition: Wo wurde der letzte Stein getroffen?
	 */
	private MatrixPosition hitStonePosition;
	
	/**
	 *  Konstruktor
	 *  
	 *  Initialisiert Position und Bewegungsrichtung des Balls
	 *  
	 */
	public Ball() {
		this.position = new Position(Constants.SCREEN_WIDTH / 2 - Constants.BALL_DIAMETER / 2  , 
				Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER - Constants.PADDLE_HEIGHT);	
		this.direction = new Vector2D(5,-5);
		
		// Neueinstellung der Bewegungsrichtung und Anpassung der Geschwindigkeit
		direction.rescale();
	}
	
	/**
	 * Getter für die aktuelle Position
	 *  
	 * @return position Die aktuelle Position des Balls 
	 * 					
	 */
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * Getter für die Bewegungsrichtung
	 * 
	 * @return direction Die aktuelle Bewegungsrichtung des Balls 
	 * 					
	 */
	public Vector2D getDirection() {
		return this.direction;
	}
	
	/**
	 * Getter für die Position des Balls innerhalb der Stein-Matrix
	 * @return hitStonePosition 
	 */
	public MatrixPosition getHitStonePosition() {
		return this.hitStonePosition;
	}
	
	
	
	/**
	 * Methode um die neue Position des Balls zu ermitteln
	 * (unter Benutzung der alten Position und der Richtungsaenderung)
	 */
	public void updatePosition() {
		// Ermittelt die neue X-Koordinate des Balls durch Aufruf der setX()-Methode
		// Ihr wird die alte Ballposition auf der X-Achse (getX) 
		// und die horizontale Bewegungsrichtung uebergeben
  	  	position.setX (position.getX() + direction.getDx());
		// Ermittelt die neue Y-Koordinate des Balls durch Aufruf der setY()-Methode
		// Ihr wird die alte Ballposition auf der Y-Achse (getY) 
		// und die vertikale Bewegungsrichtung uebergeben
  	  	position.setY (position.getY() + direction.getDy());
	}
	
	/**
	 * Methode, um das Verhalten des Balls bei Wand- oder Steinberuehrung zu steuern
	 */
	public void reactOnBorder() {
    	// rechte Wandberuehrung pruefen
    	if (position.getX() >= Constants.SCREEN_WIDTH - Constants.BALL_DIAMETER ) {
    		// -> Neue X-Position
    		position.setX(Constants.SCREEN_WIDTH - Constants.BALL_DIAMETER);
    		// -> Neue X-Bewegungsrichtung (Einfallswinkel == Ausfallswinkel)
    		direction.setDx(-direction.getDx());
    	}
    	
    	// linke Wandberuehrung pruefen
    	if (position.getX() <= 0) {
    		// Neue X-Position festlegen
    		position.setX(0);
    		// Richtung negieren (Einfallswinkel == Ausfallswinkel)
    		direction.setDx(-direction.getDx());
    	}
    	
    	// obere Wandberuehrung pruefen
    	if (position.getY() <= 0) {
    		// Neue Y-Position festlegen
    		position.setY(0);
    		// Richtung negieren (Einfallswinkel == Ausfallswinkel)
    		direction.setDy(-direction.getDy());
    	}
    	
    	// untere Wandberuehrung pruefen
    	if (position.getY() >= Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER) {
    		// Neue Y-Position festlegen
    		position.setY(Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER);
    		// Richtung negieren (Einfallswinkel == Ausfallswinkel)
    		direction.setDy(-direction.getDy());
    	}
    	
	}
	
	
	/**
	 * Methode um zu testen, ob der Ball die obere Seite des Paddles beruehrt
	 * @param p Eine Variable des Datentyps Paddle
	 * @return paddle getroffen (true) oder nicht getroffen (false)
	 */
	public boolean hitsPaddle (Paddle p) {
		
		if ((position.getY() + Constants.BALL_DIAMETER) >= p.getPosition().getY() &&
				
				(position.getX() + Constants.BALL_DIAMETER) >= p.getPosition().getX() && 
				
			position.getX() <= (p.getPosition().getX() + Constants.PADDLE_WIDTH))
		{
			return true;
		}
		// Paddle wurde nicht getroffen
		else {
			return false;
		}
	}
	
	
	/**
	 * Methode um die Reaktion des Balls beim Auftreffen auf das Paddle festzulegen
	 * @param p Eine Variable des Datentyp Paddle
	 */
	public void reflectOnPaddle(Paddle p) {
		
		// wenn das paddle getrofen wurde
		if (hitsPaddle(p)) {
			
		// Ball hat die Mitte an ihrem Endpunkt getroffen.
		Position ballMittel = new Position (position.getX() + Constants.BALL_DIAMETER / 2,
				position.getY() + Constants.BALL_DIAMETER / 2);
		
		// Ball hat die Mitte an ihrem Startpunkt getroffen.
		Position paddleMittel = new Position(p.getPosition().getX() + Constants.PADDLE_WIDTH / 2,
				p.getPosition().getY() + Constants.REFLECTION_OFFSET);
		
		// berechnet neue Bewegungsrichtung des Balls,
		// nachdem er auf das Paddel trifft;
		// benutzt dafuer paddleMittelpunkt und ballMittelpunkt
		Vector2D newDirection = new Vector2D(paddleMittel, ballMittel);
		direction = newDirection;
		// Richtung einstellen und Geschwindigkeit anpassen
		direction.rescale();
		}
	}
	
	/**
	 * Methode um zu uberpruefen, ob der Ball einen Stein in der Matrix getroffen hat
	 * @param stones uebergeben eines Steinarrays
	 * @return true wenn ein stein getroffen wird
	 */
	public boolean hitsStone(Stone[][] stones) {
		
		Rectangle ballR = new Rectangle ( (int) position.getX(), (int) position.getY(),
				Constants.BALL_DIAMETER.intValue(), Constants.BALL_DIAMETER.intValue());
		
		for (int i = 0; i < stones.length; i++) {
			for (int j = 0; j < stones[i].length; j++) {
				
				Rectangle stoneR = new Rectangle((int) stones[i][j].getPos().getX() + 1,
						(int) stones[i][j].getPos().getY() + 1,
						(int) (Constants.SCREEN_WIDTH / Constants.SQUARES_X) - 2,
						(int) (Constants.SCREEN_HEIGHT / Constants.SQUARES_Y) - 2);
				
				if (stoneR.intersects(ballR) && stones[i][j].getType() > 0) {
					
					hitStonePosition = new MatrixPosition(i,j);
					reflectOnStone(ballR, stoneR);
					
					return true;
				}
			}
		}
		
		return false;
		
	}
	/**
	 * Methode um die Reaktion des Balls beim Auftreffen auf die Steine festzulegen
	 * @param ballRect Eine Variable des Datentyps Rectangle
	 * @param stoneRect Eine Variable des Datentyps Rectangle
	 */
	private void reflectOnStone(Rectangle ballRect, Rectangle stoneRect) {
		
		double uberschneidungBreite = ballRect.intersection(stoneRect).getWidth();
		double uberschneidungHoehe  = ballRect.intersection(stoneRect).getHeight();
		
		if (uberschneidungBreite == uberschneidungHoehe) {
			
			direction.setDx(-direction.getDx());
		}
		else {
			direction.setDy(-direction.getDy());
		}
	}
	
	/**
	 * prueft, ob ob der Ball verloren geht 
	 * @return true wenn der Ball verloren geht 
	 */
	public boolean ballLost() {
		if (position.getY() >= Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER) {
			
			return true;
		}
			return false;
	}
	
	
}
