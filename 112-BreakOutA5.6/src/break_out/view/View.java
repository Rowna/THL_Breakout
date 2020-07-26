package break_out.view;

import java.awt.CardLayout;

import javax.swing.JFrame;

import break_out.Constants;
import break_out.model.Game;

/**
 * Die Klasse "View" bringt die Komponenten innerhalb der JFrames "auf den 
 * Schirm". Dabei holt sich der View die Komponenten aus dem Game-Objekt, 
 * das mit dieser Klasse verbunden ist.
 * 
 * @author 
 * 
 */
public class View extends JFrame  {

	/**
	 * automatisch generierte ID für die Seriennummer
	 */
	private static final long serialVersionUID = -1850986636132660133L;

	/**
	 * Das Layout
	 */
	private CardLayout cardLayout;

	/**
	 * Das Game-Objekt, das angezeigt wird
	 */
	private Game game;

	/**
	 * Der Startbildschirm für diese App
	 */
	private StartScreen startScreen;

	/**
	 * Das Spielfeld
	 */
	private Field field;

	
	/**
	 * Konstruktor
	 */
	public View() {
		// Konstruktor für die Konstanten, die diese Klasse verwendet
		super(Constants.APP_TITLE);

		// Standard-Operation beim Schließen des Views
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //DISPOSE_ON_CLOSE);

		// Welches Layout verwendet dieser View?
		cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);

		// Verschiedene "Panels" für den View erstellen
		startScreen = new StartScreen(this);
		field = new Field(this);

		getContentPane().add(startScreen, StartScreen.class.getName());
		getContentPane().add(field, Field.class.getName());

		// den Startbildschirm anzeigen
		cardLayout.show(getContentPane(), StartScreen.class.getName());

		// Einstellungen für den View vornehmen
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		pack();
	}

	/**
	 * Getter für den Startbildschirm
	 * @return startScreen
	 */
	public StartScreen getStartScreen() {
		return startScreen;
	}

	/**
	 * Getter für das Spielfeld
	 * @return field
	 */
	public Field getField() {
		return field;
	}

	/**
	 * Getter für das Game-Objekt
	 * @return game
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * Setter für das Game-Objekt
	 * @param game The current game
	 */
	public void setGame(Game game) {
		// stell das Spiel als model
		this.game = game;
		game.addObserver(this);
	}
	
	/**
	 * Zeigt einen angeforderten Bildschirm an, falls er auf dem
	 * Card-Layout vorhanden ist
	 * 
	 * @param screenName The screen to be shown
	 */
	public void showScreen(String screenName) {
		cardLayout.show(getContentPane(), screenName);
	}

	/**
	 * Diese Methode wird nur von game.notifyObservers() in der
	 * run()-Methode innerhalb des Levels aufgerufen,
	 * um das Spielfeld visuell neu aufzubauen. 
	 * 
	 * Hintergrund: Im Spiel kommt es ständig zu Eregnissen: Steine werden getroffen, 
	 * der Ball geht verloren usw.
	 * Der View muss auf diese Ereignisse reagieren und das Spielfeld neu aufbauen
	 * -- je nachdem, welches Ereignis ihn aus dem Spiel erreicht,
	 * und die Verbindung von Spielereignis und View, das ist diese Methode!
	 * 
	 * @param game Das Game-Objekt, auf dessen 'notify-Ereignisse' reagiert werden soll
	 */
	public void modelChanged(Game game) {
		this.game = game;
		
		// ruft die Methode paintComponents() im "Field"-Objekt auf.
		field.repaint();
	}
}
