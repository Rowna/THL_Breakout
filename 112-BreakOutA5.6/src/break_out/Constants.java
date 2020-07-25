package break_out;

import java.awt.Color;

/**
 * Eine Klasse, die alle Konstanten enthält, die das Spiel konfigurieren
 * 
 * @author 
 * 
 */
public class Constants {

    /**
     * Die Anzeigebreite in Pixeln
     */
    public static final Double SCREEN_WIDTH = 800.0;
    
    /**
     * Die Anzeigehöhe in Pixeln
     */
    public static final Double SCREEN_HEIGHT = 625.0;
    
    /**
     * Der App-Name
     */
    public static final String APP_TITLE = "BreakOut Game";

    /**
     * Debug-Schalter für besondere Render-Hinweise
     */
    public static final boolean DEBUG_MODE = false;

    /**
     * Die Hintergrundfarbe für das Menue
     */
    public static final Color BACKGROUND = new Color(52, 152, 219);

    /**
     * Wie viele Spalten hat ein Block?
     */
    public static final Integer SQUARES_X = 20;

    /**
     * Wie viele Zeilen soll es geben?
     */
    public static final Integer SQUARES_Y = 25;
    
    /**
     * Wie breit soll das Paddel sein (in Pixeln)
     * => ändert sich mit der Schwierigkeit des Spiels.
     */
    public static final Double PADDLE_WIDTH = 70.0;
    
    /**
     * Wie hoch soll das Paddel sein? (in Pixeln)
     */
    public static final Double PADDLE_HEIGHT = 15.0;
    
    /**
     * Der Abstand zwischen Paddel und dem unteren Reflection Offset
     */
    public static final Double REFLECTION_OFFSET = 25.0;
    
    /**
     * Der Durchmesser des Balls in Pixels
     */
    public static final Double BALL_DIAMETER = 15.0;
       
    /**
     * Wie schnell bewegt sich das Paddel?
     */
    public static final Double DX_MOVEMENT = 4.5;
    
    /**
     * Wie schnell bewegt sich der Ball?
     */
    public static final Double BALL_SPEED = 1.20;
    
}
