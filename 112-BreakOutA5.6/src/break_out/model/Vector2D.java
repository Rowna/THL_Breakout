package break_out.model;

import break_out.Constants;
import break_out.model.Position;

/**
 * This class represent a two dimensional vector.
 * 
 * @author I. Schumacher modified by Saman Shayanfar, Bashar Alsamar
 */
public class Vector2D {

	/**
	 * The x part of the vector
	 */
	private double dx;

	/**
	 * The y part of the vector
	 */
	private double dy;

	/**
	 * This constructor creates a new vector with the given x and y parts.
	 * 
	 * @param dx The x part of the vector
	 * @param dy The y part of the vector
	 */
	public Vector2D(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	
	/**
	 * Dieser Konstruktor wird die Koordinaten von zwei Punkten uebergeben
	 * Berechnet werden muss die Aufprallposition minus der alten Position
	 *  
	 * @param position1 Position, wenn der Ball auf das Paddle trifft (wo er herkommt / alte Position)
	 * @param position2 Position, unterhalb des Paddles (Aufprallposition)
	 */
	public Vector2D(Position position1, Position position2) {
		this.dx = position2.getX()-position1.getX();
		this.dy = position2.getY()-position1.getY();	
	}
	
	
	
	

	/**
	 * Getter for the x part
	 * 
	 * @return dx The x part of the vector
	 */
	public double getDx() {
		return dx;
	}

	/**
	 * Setter for the x part
	 * 
	 * @param dx The new x part of the vector
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}

	/**
	 * Getter for the y part
	 * 
	 * @return dy The y part of the vector
	 */
	public double getDy() {
		return dy;
	}

	/**
	 * Setter for the y part
	 * 
	 * @param dy The new y part of the vector
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	
	
	
	
	/**
	 * Methode um den Richtungsvektor zu normieren auf laenge 1 und
	 * einfluss auf die ball Geschwindigkeit bekommen
	 */
	public void rescale() {
		// Variable zur Berechnung innerhalb der Methode
		double richtungsVector;
		// normieren des Richtungsvektors
		richtungsVector = Math.sqrt(dx * dx + dy * dy);
		// Berechnen der normierten dx Koordinate unter Betrachtung der Geschwindigkeit
		dx = dx / richtungsVector * Constants.BALL_SPEED;
		// Berechnen der normierten dy Koordinate unter Betrachtung der Geschwindigkeit
		dy = dy / richtungsVector * Constants.BALL_SPEED;		
	}
	
	
	

}
