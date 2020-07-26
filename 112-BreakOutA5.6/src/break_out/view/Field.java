package break_out.view;
import break_out.model.Stone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import break_out.Constants;
import net.miginfocom.swing.MigLayout;

/**
 *  Der Spiel-Hintergrund. Alle Komponenten befinden sich auf diesem Hintergrund
 *  
 * @author  
 */
public class Field extends JPanel {

	/**
	 * automatisch generierte serial version UID
	 */
	private static final long serialVersionUID = 2434478741721823327L;

	/**
	 * Das View-Objekt, das mit diesem Hintergrund verknüpft ist.
	 */
	private View view;

	/**
	 * Die Hintergrundfarbe
	 */
	private Color background;

	/**
	 * Der Konstruktor
	 * 
	 * @param view Das View-Objekt, das mit dem Field-Objekt verknüpft werden soll
	 */
	public Field(View view) {
		super();

		this.view = view;
		this.background = new Color(177, 92, 107);

		setFocusable(true);

		// Einstellungen initialisieren
		initialize();
	}

	/**
	 * Initializes the settings for the board
	 */
	private void initialize() {
		// erzeugt ein Layout
		setLayout(new MigLayout("", "0[grow, fill]0", "0[grow, fill]0"));
	}

	/**
	 * ändert die Hintergrundfarbe
	 * 
	 * @param color die neue Hintergrundfarbe
	 */
	public void changeBackground(Color color) {
		background = color;
		repaint();
	}
	
	/**
	 * baut das Spielfeld neu auf 
	 * 
	 * @param g Das Graphics-Object
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		double w = Constants.SCREEN_WIDTH;
		double h = Constants.SCREEN_HEIGHT;

		// Die Abmessungen des Spielfelds festlegen
		setPreferredSize(new Dimension((int) w, (int) h));
		setMaximumSize(new Dimension((int) w, (int) h));
		setMinimumSize(new Dimension((int) w, (int) h));

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Hintergrundfarbe festlegen
		g2.setColor(background);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// die Farbe für die folgenden Komponenten festlegen
		g2.setColor(new Color(200, 200, 200));
		
		// Ruft die Methode zum Erstellen des Balls auf
		drawBall(g2);
		
		// Ruft die Methode zum Zeichnen des Paddles auf
		drawPaddle(g2);
		
		// Ruft die Methode zum Zeichnen des Rasters auf
		drawGrid(g2);
		
		// Ruft die Methode zum Zeichnen der Steine auf
		drawStones(g2);
		
		// Ruft die Methode zum Zeichnen der score wert auf
		drawScore(g2);
		
		// Ruft die Methode zum Zeichnen der leben wert auf
		drawLives(g2);
		
	}

	/**
	 * Erzeugt den Ball
	 * 
	 * @param g2 Das Graphics2D-Objekt, das als Grundlage dient
	 */
	private void drawBall(Graphics2D g2) {
		g2.fillOval((int) view.getGame().getLevel().getBall().getPosition().getX(),
				(int) view.getGame().getLevel().getBall().getPosition().getY(),
				Constants.BALL_DIAMETER.intValue(),
				Constants.BALL_DIAMETER.intValue());
	}
	
	/**
	 * Erzeugt das Paddle
	 * Greift dabei zurück auf das ihm bekannte view-Objekt auf das zugehoerige Game-Objekt und
	 * darueber auf das Level-Objekt, danach auf das Paddle-Objekt und im Anschluss darauf
	 * auf das Positions-Objekt, um um dort die Methode zum Lesen der Koordinate zu nutzen
	 *  
	 * @param g2 Das Graphics2D-Objekt, das als Grundlage dient
	 */
	private void drawPaddle(Graphics2D g2) {
		g2.fillRoundRect((int) view.getGame().getLevel().getPaddle().getPosition().getX(),
				(int) view.getGame().getLevel().getPaddle().getPosition().getY(),
				 (Constants.PADDLE_WIDTH.intValue()),
				 (Constants.PADDLE_HEIGHT).intValue(), 10,10);
		
	}
	
	/**
	 * Zeichnet das Raster des Spielfeldes
	 * 
	 * @param g2 Das Graphics2D-Objekt, das als Grundlage dient
	 */
	private void drawGrid(Graphics2D g2) {
		// Horizontale linien des Rasters
		for (int i = 1; i < Constants.SQUARES_Y; i++) {
			g2.drawLine( 0,(i * Constants.SCREEN_HEIGHT.intValue() / Constants.SQUARES_Y),
					 Constants.SCREEN_WIDTH.intValue(), (i * Constants.SCREEN_HEIGHT.intValue() / Constants.SQUARES_Y));
		}
		
		// Vertikale linien des Rasters
		for (int i = 1; i < Constants.SQUARES_X; i++) {
			g2.drawLine((i * Constants.SCREEN_WIDTH.intValue() / Constants.SQUARES_X), 0, 
					(i * Constants.SCREEN_WIDTH.intValue() / Constants.SQUARES_X), Constants.SCREEN_HEIGHT.intValue());
		}
	}
	
	
	/**
	 * Erzeugt die einzelnen Steine in einer Stein-Matrix
	 * 
	 * @param g2 Das Graphics2D-Objekt, das als Grundlage dient
	 */
	private void drawStones(Graphics2D g2) {
		
		// Breite eines Steins
		double blockBreite = Constants.SCREEN_WIDTH / Constants.SQUARES_X;
		
		// Hoehe eines Steins
		double blockHoehe = Constants.SCREEN_HEIGHT / Constants.SQUARES_Y;
		
		// Zugriff auf das Stone-Objekt im Level-Objekt
		Stone [][] s2D = view.getGame().getLevel().getStones();
		
		// Zeile der Stein-Matrix
		for (int zeile = 0; zeile < s2D.length; zeile++) {
			
			// Spalte der Stein-Matrix
			for (int spalte = 0; spalte < s2D[zeile].length; spalte++) {
				
				// pruefen ob die Stein-Matrix nicht leer ist
				if (s2D[zeile][spalte].getType() != 0) {
					
					// Farbe des Steins ermitteln
					g2.setColor(s2D[zeile][spalte].getColor());
					
					// Steine zeichnen, etwas kleiner als das eigentliche Feld der Matrix
					g2.fillRoundRect((int) s2D[zeile][spalte].getPos().getX() + 2, (int) s2D[zeile][spalte].getPos().getY() + 2, 
							(int) blockBreite - 2, (int) blockHoehe - 2, 10, 10);
				}
			}
		}
	}
	
	/**
	 * Anzeigen des Punktestandes auf dem Spielfeld
	 * @param g2 Das Graphics2D-Objekt, das als Grundlage dient
	 */
	private void drawScore(Graphics2D g2) {
		
		// Farbe der Anzeige: Schwarz
		g2.setColor(new Color(0, 0, 0));
		
		// Schriftart
		g2.setFont(new Font("Arial", Font.BOLD,20));
		
		// aktuellen Punktestand anzeigen
		String scoreText = "Spielstand: " + view.getGame().getLevel().getScore();
		g2.drawString(scoreText, 5, 20);
	}
	
	/**
	 * Anzeigen der Leben auf dem Spielfeld
	 * @param g2 Das Graphics2D-Objekt, das als Grundlage dient
	 */
	private void drawLives(Graphics2D g2) {
		
		// Farbe der Anzeige: Schwarz
		g2.setColor(new Color(0, 0, 0));
		
		// Schriftart
		g2.setFont(new Font("Arial", Font.BOLD,20));
		
		// aktuelle Anzahl der Leben anzeigen
		String liveText = "Life : " + view.getGame().getLevel().getLifeCnt();
		g2.drawString(liveText, 5, 45);
	}

}
