package break_out.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import break_out.model.Game;
import break_out.view.Field;
import break_out.view.StartScreen;
import break_out.view.View;

/**
 * Der Controller kümmert sich um Eingabe-Events und reagiert darauf,
 * indem er die Ansicht und das Datenmodell auf einen neuen Stand bringt.
 * 
 * @author 
 * 
 */
public class Controller implements ActionListener, KeyListener {

	/**
	 * Das Spiel als Daten-Modell, das mit diesem Controller verknüpft ist
	 */
	private Game game;

	/**
	 * Die Ansicht, die mit diesem Controller verknüpft ist.
	 */
	private View view;

	/**
	 * Der Konstruktor benötigt eine Ansicht, um sich selbst zu "bauen"
	 * 
	 * @param view
	 *            Die Ansicht, die mit diesem Controller verbunden ist.
	 */
	public Controller(View view) {
		this.view = view;

		// Die Listener zuweisen
		assignActionListener();
		assignKeyListener();
	}

	/**
	 * Mit Hilfe dieser Methode erhält der Controller alle Buttons, die
	 * in der Ansicht definiert sind, und macht diesen Controller für sie alle
	 * zuständig. Jedes Mal, wenn der Benutzer einen Button aus der Ansicht
	 * anklickt, reagiert sein ActionListener (also dieser Controller) mit
	 * einem ActionEvent.  
	 * 
	 */
	private void assignActionListener() {
		// Get the start screen to add this controller as action
		// listener to the buttons.
		view.getStartScreen().addActionListenerToStartButton(this);
		view.getStartScreen().addActionListenerToQuitButton(this);
	}

	/**
	 * Mit Hilfe dieser Methode macht sich der Controller zuständig als KeyListener.
	 * Jedes Mall, wenn der Benutzer eine Taste drückt, wertet der KeyListener
	 * (also dieser Controller) diesen KeyEvent aus.
	 * 
	 */
	private void assignKeyListener() {
		// Get the field to add this controller as KeyListener
		view.getField().addKeyListener(this);
	}

	/**
	 * Sobald der Benutzer irgendeinen Button klickt, wird dieser ActionListener aktiv.
	 * Die Methode bekommt einen ActionEvent 'e' übergeben, den sie dann als Event-Quelle
	 * auswertet.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Hiermit bekommen wir die StartScreen
		StartScreen startScreen = view.getStartScreen();

		// Die StartScreen-Ansicht hat ein paar Buttons. Wir vergleichen die 
		// Event-Quelle 'e' mit dem StartButton, um sicher zu sein, dass
		// tatsächlich diser Button die Event-Quelle war.
		// Oder einfach gesagt: Dass der Benutzer tatsächlich den StartButton
		// geklickt hat.
		
		if (startScreen.getStartButton().equals(e.getSource())) {
			// Der Name des Spielers im Eingabefeld des Startfensters
			String playersName = startScreen.getPlayersName();
			playersName = playersName.trim();
			if (playersName.length() < 1) {
				// Wenn der Benutzername zu kurz ist, werden wir das nicht akzeptieren
				// und eine Fehlermeldung ausgeben.
				startScreen.showError("Der Name ist ungueltig. Versuchen Sie es nochmal!");
			} else {
				// Wenn alles OK ist, können wir ein neues Spiel starten ... 
				game = new Game(this);
				// ... und der Ansicht diese neue Spiel-Instanz übergeben.
				view.setGame(game);
			}
		}
		// Wenn die Event-Quelle der "Quit"-Button war, beenden wir die komplette App.
		else if (startScreen.getQuitButton().equals(e.getSource())) {
			System.exit(0);
		}
	}

	/**
	 * Diese Methode wird aufgerufen, nachdem eine Taste gedrückt wurde. Das heißt,
	 * dass die Taste heruntergedrückt und wieder losgelassen wurde; vorher kommt
	 * der Event nicht bei dieser Methode an.
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Diese Methode wird aufgerufen, nachdem die taste gedrückt (aber noch nicht
	 * losgelassen) wurde.
	 * 
	 * @param e Das Ereignisobjekt
	 */
	@Override
	public void keyPressed(KeyEvent e) {
    	// Es war die Leertaste ...
    	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    		// Zustand des Balls pruefen. Wenn der Ball sich bewegt ...
    		if (game.getLevel().ballWasStarted()) {
    			// ... Ball anhalten
    			game.getLevel().stopBall();
    		}
    		else {
    			// ... andernfalls: Ball weiterlaufen lassen.
    			game.getLevel().startBall();
    		}
    	}
    	
    	//betaetigen der linken Pfeiltaste
    	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    		// Bewegung nach links
    		game.getLevel().getPaddle().setDirection( -1 );
    		    		
    	}
    	
    	// betaetigen der rechten Pfeiltaste
    	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		// Bewegung nach rechts
    		game.getLevel().getPaddle().setDirection( +1 );
    	}
    	
    	// betaetigen der ESC-Taste
    	if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
    		// "Spiel beendet" auf "wahr" setzen
    		game.getLevel().setFinished(true);
    		// Startbildschirm aufrufen
    		toStartScreen();   		
    	}
		
	}

	/**
	 * Diese Methode wird aufgerufen, wenn eine Taste gedrückt ist
	 * und wieder losgelassen wird.

	 * @param e Das Ereignis-Objekt
	 */
	@Override
	public void keyReleased(KeyEvent e) {
    	// beim Loslassen der Pfeiltasten soll sich das Paddle nicht mehr bewegen
    	// linke Pfeiltaste
    	if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
    		game.getLevel().getPaddle().setDirection(0);
    	}
    	// rechts Pfeiltaste
    	if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
    		game.getLevel().getPaddle().setDirection(0);
    	}
		
	}

	/**
	 * Diese Methode schaltet um auf den Start Screen.
	 */
	public void toStartScreen() {
		view.showScreen(StartScreen.class.getName());
		view.getStartScreen().requestFocusInWindow();
	}

	/**
	 * Diese Methode schaltet um auf den Field View
	 * (der das Spielfeld anzeigt)
	 */
	public void toPlayground() {
		view.showScreen(Field.class.getName());
		view.getField().requestFocusInWindow();
	}

}
