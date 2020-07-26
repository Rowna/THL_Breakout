package break_out.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * Dieses Panel dient als Hintergrund für besondere Teilbereiche in dieser App
 * 
 * @author 
 * 
 */
public class SectionPanel extends JPanel {

    /**
     * automatisch generierte serial version UID
     */
    private static final long serialVersionUID = -7773487090869704154L;

    /**
     * Farbe des Panels
     */
    private Color color;

    /**
     * Randbreite
     */
    protected int strokeSize = 1;

    /**
     * Shadow-Farbe
     */
    protected Color shadowColor = new Color(50, 50, 50);

    /**
     * Shadow-Schalter
     */
    protected boolean shady = true;

    /**
     * Wert für vertikale Rundung
     */
    protected Dimension arcs = new Dimension(10, 10);

    /**
     * Abstand Panel <-> Rahmen
     */
    protected int shadowGap = 3;

    /**
     * Offset für den Shadow
     */
    protected int shadowOffset = 3;

    /**
     * Alpha-Wert für den Shadow (durchsichtig oder nicht?) 
     */
    protected int shadowAlpha = 200;

    
    /**
     * Konstruktor
     */
    public SectionPanel() {
        super();
        setOpaque(false);

        // Standard-Farbe des Hintergrunds
        this.color = new Color(220, 220, 220);
    }

    /**
     * Ein Konstruktor, mit dem man die Hintergrundfarbe selbst auswählen kann
     * 
     * @param background Die gewählte Hintergrundfarbe
     */
    public SectionPanel(Color background) {
        super();
        setOpaque(false);

        // Hintergrund einstellen
        this.color = background;
    }

    
    @Override
    public void setBackground(Color bg) {
        color = bg;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;

        Color shadowColorA = new Color(shadowColor.getRed(),
                shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D g2 = (Graphics2D) g;

        // Anti-Aliasing einstellen
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Shadow anzeigen, falls vorhanden
        if (shady) {
            g2.setColor(shadowColorA);
            g2.fillRoundRect(shadowOffset, // X position
                    shadowOffset, // Y position
                    width - strokeSize - shadowOffset, // width
                    height - strokeSize - shadowOffset, // height
                    arcs.width, arcs.height); // arc Dimension
        } else
            shadowGap = 1;

        // Erzeugt ein rundes, durchsichtiges Panel mit Rändern
        Color c1 = color;
        int nr = (color.getRed() + 40) > 255 ? 255 : (color.getRed() + 40);
        int ng = (color.getGreen() + 40) > 255 ? 255 : (color.getGreen() + 40);
        int nb = (color.getBlue() + 40) > 255 ? 255 : (color.getBlue() + 40);
        Color c2 = new Color(nr, ng, nb);
        GradientPaint gradient = new GradientPaint(0, 0, c1, getWidth(),
                getHeight(), c2, true);
        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, width - shadowGap, height - shadowGap,
                arcs.width, arcs.height);

        g2.setColor(new Color(120, 120, 120));
        g2.setStroke(new BasicStroke(strokeSize));
        g2.drawRoundRect(0, 0, width - shadowGap, height - shadowGap,
                arcs.width, arcs.height);

        // Setzt das Aussehen des Rands auf den Standardwert (ist besser)
        g2.setStroke(new BasicStroke());
    }
}
