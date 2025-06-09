import javax.swing.*;

/**
 * Window for obtaining how many days to batch cook.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
public class Days extends ShibaWindow{

    Days(){
        super(new ImageIcon("assets/Art/Background-Days.png").getImage(), new ImageIcon("assets/Art/Hat-Icon.png").getImage());

        this.pack();
        this.setVisible(true);
    }
}
