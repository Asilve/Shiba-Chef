import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that creates a template for future windows of the app.
 *
 * @author Anthony Silvester
 * @version v1.1ss
 */
abstract class ShibaWindow extends JFrame {
    private static final Image icon = new ImageIcon("assets/Art/Hat-Icon.png").getImage();
    ShibaPanel bgPanel;

    /**
     * Constructor
     *
     * @param background background image
     */
    ShibaWindow(Image background){
        // Setting Window / Frame data
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("ShibaChef");
        this.setIconImage(icon);
        bgPanel = new ShibaPanel(background);
        bgPanel.setPreferredSize(new Dimension(720, 480));
        this.setContentPane(bgPanel);
    }

    /**
     * Changes the background image of the page.
     *
     * @param newBackground new background to be displayed.
     */
    public void changeBackground(Image newBackground){
        this.bgPanel.changeBackground(newBackground);
        this.setContentPane(this.bgPanel);
    }

}
