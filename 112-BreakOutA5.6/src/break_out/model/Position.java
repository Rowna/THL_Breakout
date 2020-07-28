package break_out.model;


/**
 * 
 * Diese Klasse beinhaltet eine Position auf dem Spielfeld in Pixeln 
 *
 * @author
 * 
 */
public class Position {

	/**
	 * X-Position
	 */
	private double x;

	/**
	 * Y-Position
	 */
	private double y;
	
	/**
	 * Konstruktor
	 * 
	 * @param x Die X-Position des betreffenden Objekts
	 * @param y Die Y-Position des betreffenden Objekts
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter für die X-Position
	 * 
	 * @return x Der aktuelle X-Wert
	 */
	public double getX() {
		return x;
	}

	/**
	 * Setter für die X-Position
	 * @param x die neue X-Position
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Getter für die Y-Position
	 * 
	 * @return y Der aktuelle Y-Wert
	 */
	public double getY() {
		return y;
	}

	/**
	 * Setter für die Y-Position
	 * @param y die neue Y-Position
	 */
	public void setY(double y) {
		this.y = y;
	}
	
}
