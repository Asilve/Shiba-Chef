import javax.swing.*;

/**
 * Window that Displays a shopping list to the user.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
public class ShoppingList extends ShibaWindow{

    ShoppingList(){
        super(new ImageIcon("assets/Art/Background-Shopping.png").getImage(), new ImageIcon("assets/Art/Hat-Icon.png").getImage());

        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
