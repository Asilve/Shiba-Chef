import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that creates a template for future windows of the app.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
abstract class ShibaWindow extends JFrame {


    /**
     * Constructor
     *
     * @param background background image
     * @param icon icon image
     */
    ShibaWindow(Image background, Image icon){
        // Setting Window / Frame data
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("ShibaChef");
        this.setIconImage(icon);
        ShibaPanel bgPanel = new ShibaPanel(background);
        bgPanel.setPreferredSize(new Dimension(720, 480));
        this.setContentPane(bgPanel);
    }

}
