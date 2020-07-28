package break_out.model;

import java.util.List;
import java.util.ArrayList;

import break_out.controller.Controller;
import break_out.view.View;

/**
 * Diese Model-Klasse enthält alle Daten über das Game-Objekt.
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
	 * Der Controller für dieses Spiel
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
	 * Getter für den Controller
	 * 
	 * @return controller Der Controller für dieses Spiel
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Getter für den aktuellen Spiel-Level
	 * 
	 * @return level der aktuelle Spiel-Level
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Getter für den aktuellen Spielstand
	 * 
	 * @return score der aktuelle Spielstand
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Erzeugt den ersten Level (bei Spielbeginn) oder den "nächsten" Spiel-Level,
	 * solange die Level-Nummer kleiner oder gleich der höchstmöglichen Nummer
	 * für Spiel-Level ist.
	 * Wenn der aktuelle Level höher ist, wird automatisch der Startscreen aufgerufen.
	 * 
	 * (persönliche Idee: Statt Startscreen an dieser Stelle ein Celebration-Screen mit einem 
	 * schönen Bildchen, das einen Pokal zeigt (wie im bild gefunden). Ist nur eine Idee.
	 * dazu ein Button "Start", der wieder auf den Startscreen führt.) 
	 * 
	 * @param levelnr
	 *            Die Nummer für den nächsten Level
	 * @param score
	 *            Der Spielstand bei Erreichen des aktuellen Levels
	 */
	public void createLevel(int levelnr, int score) {
		this.score = score;
		if (levelnr <= maxLevel) {
			// erzeugt ein neues Level-Objekt
			level = new Level(this, levelnr, score);
			// lässt den neuen Level beginnen
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
