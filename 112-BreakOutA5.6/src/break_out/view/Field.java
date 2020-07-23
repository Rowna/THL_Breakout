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
 * The field represents the board of the game. All components are on the board
 * 
 * @author  
 * 
 * 
 */
public class Field extends JPanel {

	/**
	 * Automatic generated serial version UID
	 */
	private static final long serialVersionUID = 2434478741721823327L;

	/**
	 * The connected view object
	 */
	private View view;

	/**
	 * The background color
	 */
	private Color background;

	/**
	 * The constructor needs a view
	 * 
	 * @param view The view of this board
	 */
	public Field(View view) {
		super();

		this.view = view;
		this.background = new Color(177, 92, 107);

		setFocusable(true);

		// Load settings
		initialize();
	}

	/**
	 * Initializes the settings for the board
	 */
	private void initialize() {
		// creates a layout
		setLayout(new MigLayout("", "0[grow, fill]0", "0[grow, fill]0"));
	}

	/**
	 * Change the background color
	 * @param color The new color
	 */
	public void changeBackground(Color color) {
		background = color;
		repaint();
	}
	
	/**
	 * This method is called when painting/repainting the playground
	 * @param g the graphics object
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		double w = Constants.SCREEN_WIDTH;
		double h = Constants.SCREEN_HEIGHT;

		// Setting the dimensions of the playground
		setPreferredSize(new Dimension((int) w, (int) h));
		setMaximumSize(new Dimension((int) w, (int) h));
		setMinimumSize(new Dimension((int) w, (int) h));

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Setting the background color
		g2.setColor(background);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// Setting the color for the following components
		g2.setColor(new Color(200, 200, 200));
		
		// Calls the method for drawing the ball
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
	 * Draws the ball
	 * @param g2 The graphics object
	 */
	private void drawBall(Graphics2D g2) {
		g2.fillOval((int) view.getGame().getLevel().getBall().getPosition().getX(),
				(int) view.getGame().getLevel().getBall().getPosition().getY(),
				Constants.BALL_DIAMETER.intValue(),
				Constants.BALL_DIAMETER.intValue());
	}
	
	/**
	 * Zeichnt den Paddle
	 * Greift dabei auf das ihm bekannte view-Objekt auf das zugehoerige Game-Objekt und
	 * darueber auf das Level-Objekt, danach auf das Paddle-Objekt und im Anschluss
	 * auf das Positions-Objekt zu, um dortige Methode (lesen der Koordinate) zu nutzen 
	 * @param g2 Das Grafikobjekt
	 */
	private void drawPaddle(Graphics2D g2) {
		g2.fillRoundRect((int) view.getGame().getLevel().getPaddle().getPosition().getX(),
				(int) view.getGame().getLevel().getPaddle().getPosition().getY(),
				 (Constants.PADDLE_WIDTH.intValue()),
				 (Constants.PADDLE_HEIGHT).intValue(), 10,10);
		
	}
	
	/**
	 * Zeichnet den Raster des Spielfeldes
	 * @param g2 Das Grafikobjekt
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
	 * Zeichnet die einzelnen Steine in einer Steinmatrix
	 * @param g2 Variable fuer Grafik 2D Objekt
	 */
	private void drawStones(Graphics2D g2) {
		
		// Breite eines Steins
		double blockBreite = Constants.SCREEN_WIDTH / Constants.SQUARES_X;
		
		// Hoehe eines Steins
		double blockHoehe = Constants.SCREEN_HEIGHT / Constants.SQUARES_Y;
		
		// Zugriff auf das Steinobjekt in der Level Klasse
		Stone [][] s2D = view.getGame().getLevel().getStones();
		
		// Zeile der Steinmatrix
		for (int zeile = 0; zeile < s2D.length; zeile++) {
			
			// Spalte der Steinmatrix
			for (int spalte = 0; spalte < s2D[zeile].length; spalte++) {
				
				// pruefen ob das Steinmatrix nicht leer ist
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
	 * @param g2 Variable fuer Grafik 2D objekt
	 */
	private void drawScore(Graphics2D g2) {
		
		// Farbe der Anzeige Schwarz
		g2.setColor(new Color(0, 0, 0));
		
		// Schriftart
		g2.setFont(new Font("Arial", Font.BOLD,20));
		
		// aktueller Scorewert anzeigen
		String scoreText = "Score: " + view.getGame().getLevel().getScore();
		g2.drawString(scoreText, 5, 20);
	}
	
	/**
	 * Anzeigen der leben auf dem Spielfeld
	 * @param g2 Variable fuer Grafik 2D objekt
	 */
	private void drawLives(Graphics2D g2) {
		
		// Farbe der Anzeige Schwarz
		g2.setColor(new Color(0, 0, 0));
		
		// Schriftart
		g2.setFont(new Font("Arial", Font.BOLD,20));
		
		// aktueller leben anzeigen
		String liveText = "Life : " + view.getGame().getLevel().getLifeCnt();
		g2.drawString(liveText, 5, 45);
	}

}
