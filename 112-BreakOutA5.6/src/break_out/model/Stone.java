package break_out.model;
import java.awt.Color;

/**
 * Diese Klasse enthaelt Daten fuer die Eigenschaften der Steine
 *
 * @author 
 */

public class Stone {
	
	/**
	 * Typ (= Robustheit) eines Steins
	 */
	private int type;
	
	/**
	 * aktueller "Zustand" des Steins
	 */
	private int value;
	
	
	/**
	 * Farbe des Steins
	 */
	private Color color;
	
	/**
	 * Position des Steins
	 */
	private Position pos;
	
	
	
	/**
	 * Konstruktor. Initialisiert den Stein.
	 * 
	 * @param type Typ des Steins auf dem spielfeld
	 * @param position Position des Steins auf dem Spielfeld
	 */
	public Stone (int type, Position position) {
		
		this.type = type;
		this.pos = position;
		
		// Switch-case: Legt die Farbe des Steins fest,
		//    abhaengig von Typ und aktuellem Zustand des Steins (auch abhaengig)
		switch (type) {
		
		case 0:
			color = new Color (0, 0, 0);
			value = 0;
			break;
		case 1:
			color = new Color (191, 191, 191);
			value = 100;
			break;
		case 2:
			color = new Color (148, 148, 148);
			value = 200;
			break;
		case 3:
			color = new Color (103, 103, 103);
			value = 300;
			break;
			default:
				color = new Color (0, 0, 0);
		}
		
	}
	
	
	
	
	/**
	 * Setter fuer den Zustandswert
	 * 
	 * * @param value der neue Zustandswert
	 */
	public void setValue (int value) {
		this.value = value;
	}
	
	/**
	 * Getter fuer den Zustandswert
	 * @return value aktueller Zustandswert
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Setter fuer die Farbe eines Steins
	 * @param color neue Farbe des Steins
	 */
	public void setColor (Color color) {
		this.color = color;
	}
	
	
	/**
	 * Getter fuer die Farbe des Steins
	 * @return color Farbwert des Steins
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Setter fuer die Position des Steins
	 * @param pos neue Position des Steins
	 */
	public void setPos (Position pos) {
		this.pos = pos;
	}
	
	/**
	 * Getter fuer die Position des Steins
	 * @return pos aktuelle Position des Steins
	 */
	public Position getPos() {
		return this.pos;
	}
	
	
	/**
	 * Setter fuer den Typ (Haltbarkeit) des Steins
	 * @param type neuer Typ des Steins
	 */
	public void setType(int type) {
		this.type = type;
		switch (type) {
		// initialisiert die farbe des steine und den Zustand, abhaengig von seinem Typ
		case (0):
			color = new Color(0, 0, 0);
			value = 0;
			break;
		case (1):
			color = new Color(191, 191, 191);
			value = 100;
			break;
		case (2):
			color = new Color(148, 148, 148);
			value = 200;
			break;
		case (3):
			color = new Color(103, 103, 103);
			value = 300;
			break;
		default:
			color = new Color(0, 0, 0);
		}
	}
	
	
	
	
	/**
	 * Getter fuer den Typ (Haltbarkeit) eines Steins
	 * @return typ aktueller Typ des Steins
	 */
	public int getType() {
		return this.type;
	}
	
}
