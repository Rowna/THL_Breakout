package break_out.model;
import java.awt.Color;

/**
 * Diese Klasse enthaelt Informationen zu Eigenschaften der Steine
 * 
 * 
 * 
 * @author Gruppe 112 : Saman Shayanfar, Bashar Alsamar
 */


public class Stone {
	
	/**
	 * Objektvariable Typ (Haltbarkeit) eines Steins
	 */
	private int type;
	
	
	/**
	 * Objektvariable, wert zum Score ermitteln
	 */
	private int value;
	
	
	/**
	 * Objektvariable, Farbe eines Steins
	 */
	private Color color;
	
	/**
	 * Objektvariable pos, Position eines Steins
	 */
	private Position pos;
	
	
	
	/**
	 * Konstruktor mit Position des Steins und typ des Steins
	 * 
	 * @param type Typ der steine auf dem spielfeld
	 * @param position Position der steine auf dem spielfeld
	 */
	public Stone (int type, Position position) {
		
		this.type = type;
		this.pos = position;
		
		// Switch-case Bedingung, zeigt farbe der steine in abhaengigkeit von typ und score werte der steine (auch abhaengig)
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
	 * setter fuer den score wert
	 * @param value Der neue Score wert
	 */
	public void setValue (int value) {
		this.value = value;
	}
	
	/**
	 * getter fuer den score wert
	 * @return value aktueller score wert
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * setter fuer die farbe eines Steins
	 * @param color neue farbe der Steine
	 */
	public void setColor (Color color) {
		this.color = color;
	}
	
	
	/**
	 * getter fuer die Farbe eines Steins
	 * @return color Farbwert eines Steins
	 */
	public Color getColor() {
		return this.color;
	}
	
	/**
	 * setter fuer die Position eines Steins
	 * @param pos position der Steine
	 */
	public void setPos (Position pos) {
		this.pos = pos;
	}
	
	/**
	 * getter fuer die Position eines Steins
	 * @return pos
	 */
	public Position getPos() {
		return this.pos;
	}
	
	
	/**
	 * setter fuer den Typ (Haltbarkeit) eines Steins
	 * @param type typ der steine
	 */
	public void setType(int type) {
		this.type = type;
		switch (type) {
		// initialisiert die farbe der steine und den Wert abhaengig von seinem Typ
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
	 * getter fuer den Typ (Haltbarkeit) eines Steins
	 * @return typ
	 */
	public int getType() {
		return this.type;
	}
	
}
