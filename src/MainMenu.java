import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Main Menu Window.
 *
 * @author Anthony Silvester
 * @version v1.2
 */
public class MainMenu extends ShibaWindow{

    /**
     * Constructor
     */
    MainMenu(){
        // Generates a new frame and creates image / sound variables for the button.
        super(new ImageIcon("assets/Art/Background-Main.png").getImage());
        Image buttonImage = new ImageIcon("assets/Art/Button1.png").getImage().getScaledInstance(185, 60, Image.SCALE_SMOOTH);
        File buttonSound = new File("assets/Sounds/clicking.wav");

        // Button Setup.
        JButton mealsButton = new JButton("Generate Meals", new ImageIcon(buttonImage));
        ShibaChef.buttonSetup(mealsButton, 67,130, 185, 60);
        JButton newMealButton = new JButton("New Meal", new ImageIcon(buttonImage));
        ShibaChef.buttonSetup(newMealButton, 67,210,185, 60);
        JButton quitButton = new JButton("Quit", new ImageIcon(buttonImage));
        ShibaChef.buttonSetup(quitButton, 67,290,185, 60);

        // Goes to meal generation pages.
        mealsButton.addActionListener(e -> {
            if(e.getSource()==mealsButton){
                ShibaChef.playSound(buttonSound);
                super.dispose();
                new Days();
            }
        });

        // Goes to new meal adder page.
        newMealButton.addActionListener(e -> {
            ShibaChef.playSound(buttonSound);
            super.dispose();
            new NewMeal();
        });

        // Quits the program.
        quitButton.addActionListener(e -> System.exit(0));

        // Adding the buttons to the Panel.
        this.add(mealsButton);
        this.add(newMealButton);
        this.add(quitButton);

        // Packs and displays the frame.
        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
}
