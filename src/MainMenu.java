import javax.swing.*;
import java.awt.*;

/**
 * Main Menu Window.
 *
 * @author Anthony Silvester
 * @version v1.1
 */
public class MainMenu extends ShibaWindow{

    /**
     * Constructor
     */
    MainMenu(){
        // Generates a new frame and creates image variable for the button.
        super(new ImageIcon("assets/Art/Background-Main.png").getImage());
        Image buttonImage = new ImageIcon("assets/Art/Button1.png").getImage().getScaledInstance(185, 60, Image.SCALE_SMOOTH);

        // Button Setup.
        JButton mealsButton = new JButton("Generate Meals", new ImageIcon(buttonImage));
        buttonSetup(mealsButton, 130);
        JButton newMealButton = new JButton("New Meal", new ImageIcon(buttonImage));
        buttonSetup(newMealButton, 210);
        JButton quitButton = new JButton("Quit", new ImageIcon(buttonImage));
        buttonSetup(quitButton, 290);

        // Goes to meal generation pages.
        mealsButton.addActionListener(e -> {
            if(e.getSource()==mealsButton){
                super.dispose();
                new Days();
            }
        });

        // Goes to new meal adder page.
        newMealButton.addActionListener(e -> {
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

    /**
     * Sets up the button configurations for the Main Menu.
     *
     * @param button The button we are setting up.
     * @param yCoord The y coordinate that the button will be placed.
     */
    private void buttonSetup(JButton button, int yCoord){
        button.setBounds(67, yCoord, 185, 60);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }
}
