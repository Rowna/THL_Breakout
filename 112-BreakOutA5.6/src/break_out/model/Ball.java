package break_out.model;

import break_out.Constants;
import break_out.model.Position;
import java.awt.Rectangle;

/**
 * This class contains the information about the balls characteristics and behavior
 * 
 * @author iSchumacher modified by Saman Shayanfar, Bashar Alsamar
 *
 */
public class Ball {

	/**
	 * The balls position on the playground
	 */
	private Position position;
	
	/**
	 * The balls direction
	 */
	private Vector2D direction;
	
	
	/**
	 * Objektvariable hitStonePosition vom Datentyp MatrixPosition
	 */
	private MatrixPosition hitStonePosition;
	
	/**
	 * The constructor of a ball
	 * The balls position and direction are initialized here.
	 */
	public Ball() {
		this.position = new Position(Constants.SCREEN_WIDTH / 2 - Constants.BALL_DIAMETER / 2  , 
				Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER - Constants.PADDLE_HEIGHT);	
		this.direction = new Vector2D(5,-5);
		// Normieren der Richtung mit anpasung der Geschwindigkeit
		direction.rescale();
	}
	
	/**
	 * The getter for the balls position
	 * @return position The balls current position
	 */
	public Position getPosition() {
		return this.position;
	}
	
	/**
	 * The getter for the balls direction
	 * @return direction The balls current direction
	 */
	public Vector2D getDirection() {
		return this.direction;
	}
	
	/**
	 * Der getter fuer die Position des Balls in der Steinmatrix
	 * @return hitStonePosition 
	 */
	public MatrixPosition getHitStonePosition() {
		return this.hitStonePosition;
	}
	
	
	
	/**
	 * Methode um die neue Position des Balls zu ermitteln
	 * unter benutzung der alten Position und der Richtungsaenderung
	 */
	public void updatePosition() {
		// Ermittelt die neue x Koordinate des Balls ueber Aufruf der set Methode
		// Ihr wird die alte Ballposition (get) und die Richtung uebergeben
  	  	position.setX (position.getX() + direction.getDx());
  	  	// Ermittelt die neue y Koordinate des Balls ueber Aufruf der set Methode
  		// Ihr wird die alte Ballposition (get) und die Richtung uebergeben
  	  	position.setY (position.getY() + direction.getDy());
	}
	
	/**
	 * Methode um das verhalten des balls bei Wandberuehrung zu prufen
	 */
	public void reactOnBorder() {
    	// rechte Wandberuehrung pruefen
    	if (position.getX() >= Constants.SCREEN_WIDTH - Constants.BALL_DIAMETER ) {
    		// Setze auf Randberuehrung
    		position.setX(Constants.SCREEN_WIDTH - Constants.BALL_DIAMETER);
    		// Richtung negieren (Einfallswinkel = Ausfallswinkel)
    		direction.setDx(-direction.getDx());
    	}
    	
    	// linke Wandberuehrung pruefen
    	if (position.getX() <= 0) {
    		// Setze auf Randberuehrung
    		position.setX(0);
    		// Richtung negieren (Einfallswinkel = Ausfallswinkel)
    		direction.setDx(-direction.getDx());
    	}
    	
    	// obere Wandberuehrung pruefen
    	if (position.getY() <= 0) {
    		// Setze auf Randberuehrung
    		position.setY(0);
    		// Richtung negieren (Einfallswinkel = Ausfallswinkel)
    		direction.setDy(-direction.getDy());
    	}
    	
    	// untere Wandberuehrung pruefen
    	if (position.getY() >= Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER) {
    		// Setze auf Randberuehrung
    		position.setY(Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER);
    		// Richtung negieren (Einfallswinkel = Ausfallswinkel)
    		direction.setDy(-direction.getDy());
    	}
    	
	}
	
	
	/**
	 * Methode um zu testen ob der ball die obere seite des Paddles beruehrt
	 * @param p Eine Variable des datentyp Paddle
	 * @return true nach treffen des Paddles
	 */
	public boolean hitsPaddle (Paddle p) {
		
		if ((position.getY() + Constants.BALL_DIAMETER) >= p.getPosition().getY() &&
				
				(position.getX() + Constants.BALL_DIAMETER) >= p.getPosition().getX() && 
				
			position.getX() <= (p.getPosition().getX() + Constants.PADDLE_WIDTH))
		{
			return true;
		}
		// Ball trifft das Paddle nicht
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
			
		// Ball Mittelpunkt (Endpunkt)
		Position ballMittel = new Position (position.getX() + Constants.BALL_DIAMETER / 2,
				position.getY() + Constants.BALL_DIAMETER / 2);
		
		//Paddle Mittelpunkt (Startpunkt)
		Position paddleMittel = new Position(p.getPosition().getX() + Constants.PADDLE_WIDTH / 2,
				p.getPosition().getY() + Constants.REFLECTION_OFFSET);
		
		// berechnet die ball richtung nachdem er auf paddle trifft
		// benutzt dafuer den paddleMittelpunkt und ballMittelpunkt
		Vector2D newDirection = new Vector2D(paddleMittel, ballMittel);
		direction = newDirection;
		// Normieren der Richtung mit anpassung der Geschwindigkeit
		direction.rescale();
		}
	}
	
	/**
	 * Methode um zu uberpruefen ob der Ball einen Stein in der Matrix getroffen hat
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
	 * @param ballRect Eine Variable des Datentyp Rectangle
	 * @param stoneRect Eine Variable des Datentyp Rectangle
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
	 * prueft ob ob der ball verloren geht 
	 * @return true wenn der ball verloren geht 
	 */
	public boolean ballLost() {
		if (position.getY() >= Constants.SCREEN_HEIGHT - Constants.BALL_DIAMETER) {
			
			return true;
		}
			return false;
	}
	
	
}
