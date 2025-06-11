import javax.swing.*;
import java.awt.*;

/**
 * Window that displays all meals that have been generated.
 * Allows user to also display the shopping list.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
public class GeneratedMeals extends ShibaWindow{
    // Variable setup.
    private static final Image mealsBackground = new ImageIcon("assets/Art/Background-Meals.png").getImage();
    private static final Image shoppingBackground = new ImageIcon("assets/Art/Background-Shopping.png").getImage();
    private boolean mealPage = true;
    private JButton shoppingButton;
    private JButton mealsButton;

    /**
     * Constructor
     */
    GeneratedMeals(){
        // Frame and image setup.
        super(mealsBackground);
        Image menuButton = new ImageIcon("assets/Art/Button2.png").getImage().getScaledInstance(92, 30, Image.SCALE_SMOOTH);
        Image selectedButton = new ImageIcon("assets/Art/Button3.png").getImage().getScaledInstance(108, 34, Image.SCALE_SMOOTH);
        Image deselectedButton = new ImageIcon("assets/Art/Button4.png").getImage().getScaledInstance(108, 34, Image.SCALE_SMOOTH);

        // Menu button Configuration.
        JButton homeButton = new JButton("Home", new ImageIcon(menuButton));
        ShibaChef.buttonSetup(homeButton, 580, 80, 92, 30);
        JButton backButton = new JButton("Back", new ImageIcon(menuButton));
        ShibaChef.buttonSetup(backButton, 580, 130, 92, 30);
        shoppingButton = new JButton("Shopping", new ImageIcon(menuButton));
        ShibaChef.buttonSetup(shoppingButton, 580, 180, 92, 30);
        shoppingButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        mealsButton = new JButton("Meals", new ImageIcon(menuButton));
        ShibaChef.buttonSetup(mealsButton, 580, 180, 92, 30);

        //Action listeners for menu buttons.
        homeButton.addActionListener(e -> {super.dispose(); new MainMenu();});
        backButton.addActionListener(e -> {super.dispose(); new Days();});
        shoppingButton.addActionListener(e -> changePage());
        mealsButton.addActionListener(e -> changePage());

        // Button adding
        this.add(homeButton);
        this.add(backButton);
        this.add(shoppingButton);
        this.add(mealsButton);

        // Pack and display frame. hides irrelevant buttons.
        mealsButton.setVisible(false);
        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Changes the page between generated meals and shopping list.
     */
    private void changePage(){
        // If on meals page, sets up shopping list.
        if(mealPage){
            super.changeBackground(shoppingBackground);
            shoppingButton.setVisible(false);
            mealsButton.setVisible(true);
            mealPage = false;
        }

        // Otherwise, if on shopping list page, sets up meals page.
        else {
            super.changeBackground(mealsBackground);
            mealsButton.setVisible(false);
            shoppingButton.setVisible(true);
            mealPage = true;
        }
    }

}
