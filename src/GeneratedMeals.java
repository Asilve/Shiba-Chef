import javax.swing.*;

/**
 * Window that displays all meals that have been generated.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
public class GeneratedMeals extends ShibaWindow{

    GeneratedMeals(){
        super(new ImageIcon("assets/Art/Background-Meals.png").getImage(), new ImageIcon("assets/Art/Hat-Icon.png").getImage());

        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
