import javax.swing.*;

/**
 * Window that allows users to make their own meal.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
public class NewMeal extends ShibaWindow{

    NewMeal(){
        super(new ImageIcon("assets/Art/Background-NewMeal.png").getImage(), new ImageIcon("assets/Art/Hat-Icon.png").getImage());

        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
