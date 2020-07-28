package break_out.model;


/**
 * Diese Klasse enthaelt Informationen zur Position eines Steins in der Spielfeld-Matrix
 *
 * @author
 *
 */

public class MatrixPosition {
	
	/**
	 * Zeilenposition des Steins
	 */
	private int line;
	
	/**
	 * Spaltenposition des Steins
	 */
	private int column;
	
	/**
	 * Konstruktor 
	 * @param line zeile, in der der Stein stehen soll 
	 * @param column spalte, in der der Stein stehen soll 
	 */
	public MatrixPosition(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	/**
	 * Getter fuer die zeile
	 * @return line 
	 */
	public int getLine() {
		return line;
	}
	
	/**
	 * Getter fuer die Spalte
	 * @return column
	 */
	public int getColumn() {
		return column;
	}
}
