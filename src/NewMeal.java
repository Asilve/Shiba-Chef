import javax.swing.*;
import java.awt.*;

/**
 * Window that allows users to make their own meal.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
public class NewMeal extends ShibaWindow{

    NewMeal(){
        super(new ImageIcon("assets/Art/Background-NewMeal.png").getImage());
        Image menuButton = new ImageIcon("assets/Art/Button2.png").getImage().getScaledInstance(92, 30, Image.SCALE_SMOOTH);

        // Menu button Configuration.
        JButton homeButton = new JButton("Home", new ImageIcon(menuButton));
        ShibaChef.buttonSetup(homeButton, 580, 80, 92, 30);
        JButton submitButton = new JButton("Submit", new ImageIcon(menuButton.getScaledInstance(135,40, Image.SCALE_SMOOTH)));
        ShibaChef.buttonSetup(submitButton,380, 355, 135,40);

        //Action listeners for menu buttons.
        homeButton.addActionListener(e -> {super.dispose(); new MainMenu();});

        // Button adding
        this.add(homeButton);
        this.add(submitButton);

        // Pack and display frame.
        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
