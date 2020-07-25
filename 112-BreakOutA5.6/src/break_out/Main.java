package break_out;

import break_out.controller.Controller;
import break_out.view.View;

/**
 * Hier beginnt die gesamte App. Das Spiel beginnt hier,
 * und alle Komponenten werden hier initialisiert.
 * 
 * @author 
 * 
 */
public class Main {

	/**
	 * Die main()-Methode
	 * 
	 * @param args Kommandozeilen-Argumente
	 */
	public static void main(String[] args) {
		// Das Ansicht-Objekt erzeugen
		View view = new View();
		// Das Controller-Objekt erzeugen und das Ansicht-Objekt übergeben
		new Controller(view);
	}
}
