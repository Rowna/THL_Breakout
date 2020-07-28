package break_out.model;

import break_out.Constants;

/**
 * Diese Klasse enthaelt Daten zu Eigenschaften und verhalten des Paddels
 * 
 * @author
 *
 */

public class Paddle {
	
	/**
	 * Paddel-Position auf dem spielfeld
	 */
	private Position position;
	
	/**
	 * Aktuelle Bewegungsrichtung des Paddels
	 */
	private int direction;
	
	/**
	 * Konstruktor. Initialisiert die Position des Paddels
	 */
	public Paddle() {
		// Paddle-Objekt bekommt die Position in der Mitte 
		// unten am Spielfeldrand zugewiesen
		this.position = new Position(Constants.SCREEN_WIDTH / 2 - Constants.PADDLE_WIDTH / 2, 
				Constants.SCREEN_HEIGHT - Constants.PADDLE_HEIGHT);
	}
	
	
	/**
	 * Getter fuer die Paddel-Position
	 * @return position Die aktuelle Position des Paddels
	 */
	public Position getPosition() {
		return position;
	}	
	
	/**
	 * Getter fuer die Bewegung des Paddels
	 * @return direction Die Bewegungsrichtung des Paddels
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Setter fuer die Bewegung des Paddels
	 * @param direction Die neue Bewegungsrichtung des Paddels
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	/**
	 * Methode um die neue Position des Paddels zu bestimmen
	 * mit Hilfe der vorherigen Position und der Bewegungsrichtung
	 * (Nur X-Koordinate, da sich das Paddel nur am unteren Spielrand bewegt)
	 */
	public void updatePosition() {
		// Bewegung nach rechts
		if (getDirection() == 1) {
			position.setX(position.getX() + Constants.DX_MOVEMENT);
		}
		// Bewegung nach links
		if (getDirection() == -1) {
			position.setX(position.getX() - Constants.DX_MOVEMENT);
		}
		
		// rechte Wandberuehrung pruefen
		if (position.getX() >= Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH) {
			// Setze auf Maximalposition rechts
  	  		position.setX(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH);  	  		
  	  	}
		
		// linke Wandberuehrung pruefen
  	  	if (position.getX() <= 0) {
  	  		// Setze auf Maximalposition links
  	  		position.setX(0);  	  		
  	  	}
	}
	
}
