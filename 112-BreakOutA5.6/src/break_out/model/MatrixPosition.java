package break_out.model;


/**
 * Diese Klasse enthaelt Informationen zu Zeile und Spalte der Steine in einer Matrix
 * 
 * 
 * 
 * @author Gruppe 112 : Saman Shayanfar, Bashar Alsamar
 *
 */

public class MatrixPosition {
	
	/**
	 * Instanzvariable line 
	 * Sie beinhaltet die zeile der Steine in einer matrix
	 */
	private int line;
	
	/**
	 * Instanzvariable column
	 * Sie beinhaltet die spalten der Steine in einer matrix
	 */
	private int column;
	
	/**
	 * Konstruktor mit zeile und spalte der steine
	 * @param line zeile 
	 * @param column spalte 
	 */
	public MatrixPosition(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	/**
	 * getter fuer die zeile
	 * @return line 
	 */
	public int getLine() {
		return line;
	}
	
	/**
	 * getter fuer die spalte
	 * @return column
	 */
	public int getColumn() {
		return column;
	}
	

}
