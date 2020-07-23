package break_out.model;

import break_out.Constants;

/**
 * Diese Klasse enthaelt Informationen zu Eigenschaften und verhalten des Paddles
 * 
 * 
 * 
 * @author Gruppe 112 : Saman Shayanfar, Bashar Alsamar
 *
 */

public class Paddle {
	
	/**
	 * Objektvariable position vom Datentyp Position
	 * Paddle position auf dem spielfeld
	 */
	private Position position;
	
	/**
	 * Objektvariable direction
	 * Sie beinhaltet die Bewegungsrichtung des Paddle
	 */
	private int direction;
	
	
	
	/**
	 * Konstruktor des Paddles
	 * Die Position des Paddles wird hier initialisiert.
	 */
	public Paddle() {
		// Paddleobjekt wird die Position mittig am unteren Spielrand zugewiesen
		this.position = new Position(Constants.SCREEN_WIDTH / 2 - Constants.PADDLE_WIDTH / 2, 
				Constants.SCREEN_HEIGHT - Constants.PADDLE_HEIGHT);
	}
	
	
	/**
	 * Der getter fuer die Paddel position
	 * @return position Die aktuelle position des Paddles
	 */
	public Position getPosition() {
		return position;
	}
	
	
	
	/**
	 * getter fuer die Bewegung des Paddles
	 * @return direction Die Bewegungsrichtung des Paddles
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * setter fuer die Bewegung des Paddles
	 * @param direction Die neue Bewegungsrichtung des Paddles
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
	
	/**
	 * Methode um die neue Position des Paddles zu setzten
	 * unter Nutzung der alten Position und der Bewegungsrichtung
	 * (Nur x-Koordinate, da sich das Paddle nur am unteren Spielrand bewegt)
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
			// Setzte auf Randberuehrung
  	  		position.setX(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH);  	  		
  	  	}
		
		// linke Wandberuehrung pruefen
  	  	if (position.getX() <= 0) {
  	  		// Setze auf Randberuehrung
  	  		position.setX(0);  	  		
  	  	}
	}
	
}
