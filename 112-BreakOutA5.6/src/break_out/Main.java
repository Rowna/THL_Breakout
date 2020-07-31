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
		// Das Controller-Objekt erzeugen und das Ansicht-Objekt �bergeben
		new Controller(view);
	}
}

 // und pushen tust du erst, wenn du z.B. für heute feierabend machst.
// dann werden alle kleinen commits des tages nach github hochgeladen. also gepusht.
// auch klar?
// ja alles klar .
// ABER: Beim Pushen kann es zu überraschungen kommen!!
// Wenn rona in der gleichen zeit wie du AN DER GLEICHEN DATEI gearbeitet hat, 
// kann es zu einem Konflikt kommen!
// weil ihr beide die gleichen Zeilen geändert habt.
// Dann verweigert Git den Push! Und ihr müsst den Konflikt zusammen klären!
// vorher kann keiner von euch Pushen.
// auch klar? 

// ja alles klar. Dann müss jeder von uns voher Bescheid sagen.

//(// vorm Pushen meine ich .)
// Das ist nicht immer nötig. Aber Wenn Git Konflikt-Alarm gibt, DANN müsst ihr euch in verbindung setzen.
// wenn Rona an Datei A, B, C und du an Datei X, Y, Z arbeitest, gibt es keine konflikte.
// logisch oder?
// ja genau . verstanden.
// und bei jedem Push führt Git automatisch  einen Download der aktuellsten Version auf Github durch.
// sonst könnte es ja keine konflikte finden.
// deshalb bekommst du immer die aktuelle Github-Version, wenn du einen Push machst.
// klar?
//ja alles klar
// gut. dann löschen wir mal den oberen teil dieses chats hier und machen einen commit.
