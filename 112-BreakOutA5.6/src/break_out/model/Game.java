package break_out.model;

import java.util.List;
import java.util.ArrayList;

import break_out.controller.Controller;
import break_out.view.View;

/**
 * Diese Model-Klasse enth�lt alle Daten �ber das Game-Objekt.
 * 
 * @author 
 * 
 */
public class Game {

	/**
	 * Eine Observer-Liste
	 */
	private List<View> observers = new ArrayList<View>();

	/**
	 * Der Controller f�r dieses Spiel
	 */
	private Controller controller;

	/**
	 * Der aktuelle Spiel-Level
	 */
	private Level level;

	/**
	 * Die Nummer des ersten Levels
	 */
	private int firstLevel = 1;

	/**
	 * Die Nummer des letzten Levels
	 */
	private int maxLevel = 1;

	/**
	 * Der aktuelle Spielstand
	 */
	private int score = 0;

	/**
	 * Konstruktor
	 * 
	 * @param controller
	 *            Controller-Instanz, um das Spiel zu steuern (MVC-pattern)
	 */
	public Game(Controller controller) {
		this.controller = controller;
		createLevel(firstLevel, 0);
	}

	// Die drei Methoden des MVC-Patterns ----------------
	public void addObserver(View observer) {
		observers.add(observer);
	}

	public void removeObserver(View observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		for (View observer : observers)
			observer.modelChanged(this);
	}
	// -------------------------------------------------------

	/**
	 * Getter f�r den Controller
	 * 
	 * @return controller Der Controller f�r dieses Spiel
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Getter f�r den aktuellen Spiel-Level
	 * 
	 * @return level der aktuelle Spiel-Level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Getter f�r den aktuellen Spielstand
	 * 
	 * @return score der aktuelle Spielstand
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Erzeugt den ersten Level (bei Spielbeginn) oder den "n�chsten" Spiel-Level,
	 * solange die Level-Nummer kleiner oder gleich der h�chstm�glichen Nummer
	 * f�r Spiel-Level ist.
	 * Wenn der aktuelle Level h�her ist, wird automatisch der Startscreen aufgerufen.
	 * 
	 * (pers�nliche Idee: Statt Startscreen an dieser Stelle ein Celebration-Screen mit einem 
	 * sch�nen Bildchen, das einen Pokal zeigt (wie im bild gefunden). Ist nur eine Idee.
	 * dazu ein Button "Start", der wieder auf den Startscreen f�hrt.) 
	 * 
	 * @param levelnr
	 *            Die Nummer f�r den n�chsten Level
	 * @param score
	 *            Der Spielstand bei Erreichen des aktuellen Levels
	 */
	public void createLevel(int levelnr, int score) {
		this.score = score;
		if (levelnr <= maxLevel) {
			// erzeugt ein neues Level-Objekt
			level = new Level(this, levelnr, score);
			// l�sst den neuen Level beginnen
			level.start();
			// fordert den Controller auf, auf den Field-View zu wechseln;
			// => zeigt das Spielfeld wieder an.
			controller.toPlayground();
		} else {
			// fordert den Conntoller auf, den Start-Bildschirm wieder aufzubauen
			controller.toStartScreen();

		}

	}

}
