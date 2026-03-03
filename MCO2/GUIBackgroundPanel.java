import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Class for managing the current background image to be displayed
 * @author Martin
 */
public class GUIBackgroundPanel extends JPanel{
    
    private Image bgImage;

    /**
     * Constructor for the background image class
     * @param bgImage Image to be made the background image
     */
    public GUIBackgroundPanel(Image bgImage){

        this.bgImage = bgImage;
        this.setOpaque(false);
    }

    /**
     * Sets the chosen background image to the panel
     * @param g the <code>Graphics</code> object to protect
     */
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);

        
    }
}
