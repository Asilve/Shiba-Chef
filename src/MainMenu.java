import javax.swing.*;

/**
 * Main Menu Window.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
public class MainMenu extends ShibaWindow{

    MainMenu(){
        super(new ImageIcon("assets/Art/Background-Main.png").getImage(), new ImageIcon("assets/Art/Hat-Icon.png").getImage());

        this.pack();
        this.setVisible(true);
    }
}
