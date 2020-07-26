package break_out.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import break_out.Constants;
import net.miginfocom.swing.MigLayout;

/**
 * Dieser Bildschirm setzt die Einstellungen des Spieles um.
 * 
 * @author 
 * 
 */
public class StartScreen extends JPanel {

	/**
	 * automatisch generierte serial version UID
	 */
	private static final long serialVersionUID = -131505828069382345L;

	/**
	 * Der Game-Starten-Button
	 */
	private JButton startGame;

	/**
	 * Das View-Objekt, mit dem das Game-Objekt verbunden ist
	 */
	private View view;

	/**
	 * Der "Beenden"-Button
	 */
	private JButton quitGame;
	/**
	 * Der "Schwierigkeit"-Button
	 */
	private JButton modiGame;
	
	/**
	 * Eingabefeld für den Spielernamen
	 */
	private JTextField playersName;

	/**
	 * Das Label für Fehlermeldungen
	 */
	private JLabel error;

	
	/**
	 * Der Konstruktor braucht ein View-Objekt. 
	 * 
	 * @param view Das View-Objekt, das den Startbildschirm anzeigen soll
	 */
	public StartScreen(View view) {
		super();
		this.view = view;
		double w = Constants.SCREEN_WIDTH;
		double h = Constants.SCREEN_HEIGHT;

		setPreferredSize(new Dimension((int) w, (int) h));
		setMaximumSize(new Dimension((int) w, (int) h));
		setMinimumSize(new Dimension((int) w, (int) h));

		initialize();
	}

	
	/**
	 * initialisiert die Einstellungen für den Start-Bildschirm
	 */
	private void initialize() {
		// layout
		setLayout(new MigLayout("",
				"10[35%, center, grow, fill][65%, center, grow, fill]10",
				"10[center, grow, fill]10"));

		// Hintergrundfarbe
		setBackground(Constants.BACKGROUND);

		// Menüs initialisieren
		initializeLeftMenu();
		initializeScoreMenu();
	}

	/**
	 * initialisiert das Menü auf der linken Seite
	 */
	private void initializeLeftMenu() {
		// the layout
		SectionPanel leftMenu = new SectionPanel();
		leftMenu.shady = false;
		leftMenu.setLayout(new MigLayout("", "10[center, grow, fill]10",
				"10[center]30[center]5[center]20[center]5[center]0"));

		// Komponenten dem Layout hinzufügen
		startGame = new JButton("Spiel starten");
		quitGame = new JButton("Spiel beenden");
		modiGame = new JButton("Schwierigkeit");
		
		playersName = new JTextField();

		error = new JLabel("");
		error.setForeground(new Color(204, 0, 0));
		error.setHorizontalAlignment(SwingConstants.CENTER);
		// Spiel Titel
		JLabel menuLabel = new JLabel(Constants.APP_TITLE + getPlayersName());
		menuLabel.setFont(new Font("Sans-serif", Font.PLAIN, 16));
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);

		leftMenu.add(menuLabel, "cell 0 0, growx");
		leftMenu.add(new JLabel("Spielername:"), "cell 0 1, growx, gapleft 5");
		leftMenu.add(playersName, "cell 0 2, growx");
		leftMenu.add(startGame, "cell 0 3, growx");
		leftMenu.add(quitGame, "cell 0 4, growx");
		leftMenu.add(modiGame, "cell 0 6, growx");
		leftMenu.add(error, "cell 0 5, growx");
		add(leftMenu, "cell 0 0");
	}

	/**
	 * initialisiert das Menü auf der rechten Seite
	 */
	private void initializeScoreMenu() {
		// Layout
		SectionPanel scoreMenu = new SectionPanel(Color.WHITE);
		scoreMenu.shady = false;
		scoreMenu.setLayout(new MigLayout("", "10[center, grow, fill]10",
				"5[center]5"));

		// die Komponenten dem Layout hinzufügen
		JLabel headline = new JLabel("Scores");
		headline.setFont(new Font("Sans-serif", Font.PLAIN, 16));
		headline.setHorizontalAlignment(SwingConstants.CENTER);
		scoreMenu.add(headline, "cell 0 0, gaptop 5");
		
		add(scoreMenu, "cell 1 0, gapleft 5");
	}

	/**
	 * setzt einen Action-Listener auf diesen Knopf an
	 * 
	 * @param l der Action-Listener
	 */
	public void addActionListenerToStartButton(ActionListener l) {
		startGame.addActionListener(l);
	}

	/**
	 * Getter für den Start-Button
	 * 
	 * @return startGame Der Button, mit dem man das Spiel startet
	 */
	public JButton getStartButton() {
		return startGame;
	}

	/**
	 * Setzt einen Action-Listener auf den "Beenden"-Button an
	 * @param l Der Action-Listener
	 */
	public void addActionListenerToQuitButton(ActionListener l) {
		quitGame.addActionListener(l);
	}

	/**
	 * Getter für den "Beenden"-Button
	 * 
	 * @return quitGame Der Button, mit dem man das Spiel beendet.
	 */
	public JButton getQuitButton() {
		return quitGame;
	}

	/**
	 * Getter für den Spielernamen
	 * 
	 * @return Der Name des Spieler, der im entsprechenden Feld steht
	 */
	public String getPlayersName() {
		return playersName.getText();
	}

	/**
	 * Zeigt im Menü eine Fehlermeldung an
	 * 
	 * @param message Die Fehlermeldung, die angezeigt werden soll
	 */
	public void showError(String message) {
		error.setText(message);
	}

	/**
	 * Entfernt Fehlermeldungen vom Bildschirm
	 */
	public void hideError() {
		error.setText("");
	}
	
}
