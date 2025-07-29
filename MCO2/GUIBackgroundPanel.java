import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class GUIBackgroundPanel extends JPanel{
    
    private Image bgImage;


    GUIBackgroundPanel(Image bgImage){

        this.bgImage = bgImage;
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);

        
    }
}
