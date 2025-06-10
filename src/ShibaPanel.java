import javax.swing.*;
import java.awt.*;

/**
 * Class that creates a background frame for each window.
 *
 * @author Anthony Silvester
 * @version v1.1
 */
public class ShibaPanel extends JPanel {
    private Image backgroundImage;

    /**
     * Constructor
     *
     * @param background background image
     */
    ShibaPanel(Image background){
        this.backgroundImage = background;
        setLayout(null);
    }

    /**
     * Method that sets the background image of the window.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, 720, 480, this);
    }

    /**
     * Protected method that changes the background image of the panel.
     *
     * @param newBackground new background to be displayed
     */
    protected void changeBackground(Image newBackground){
        this.backgroundImage = newBackground;
    }
}
