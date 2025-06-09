import javax.swing.*;
import java.awt.*;

/**
 * Window for obtaining how many days to batch cook.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
public class Days extends ShibaWindow{
    // Create text field for user input
    private final JTextField textField;

    /**
     * Constructor
     */
    Days(){
        // Setup new frame, obtain images for buttons
        super(new ImageIcon("assets/Art/Background-Days.png").getImage(), new ImageIcon("assets/Art/Hat-Icon.png").getImage());
        Image buttonImage = new ImageIcon("assets/Art/Button5.png").getImage().getScaledInstance(125, 45, Image.SCALE_SMOOTH);
        Image homeImage = new ImageIcon("assets/Art/Home.png").getImage().getScaledInstance(45, 40, Image.SCALE_SMOOTH);

        // setup for the submit button.
        JButton submitButton = new JButton("Submit", new ImageIcon(buttonImage));
        submitButton.setBounds(284, 265, 125, 45);
        submitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        submitButton.setVerticalTextPosition(SwingConstants.CENTER);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);

        // setup for the home button.
        JButton homeButton = new JButton(new ImageIcon(homeImage));
        homeButton.setBounds(20, 20, 45, 40);
        homeButton.setContentAreaFilled(false);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);

        // setup for the text field.
        textField = new JTextField();
        textField.setBounds(225, 140, 240, 105);
        textField.setFont(new Font("SansSerif", Font.BOLD, 42));
        textField.setForeground(new Color(216, 195, 167));
        textField.setCaretColor(Color.WHITE);
        textField.setOpaque(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBorder(BorderFactory.createEmptyBorder());

        // Action listeners for button and text field.
        submitButton.addActionListener(e -> submitDays());
        textField.addActionListener(e -> submitDays());
        homeButton.addActionListener(e -> {
            super.dispose();
            new MainMenu();
        });

        // Add button and text field to the frame.
        this.add(submitButton);
        this.add(textField);
        this.add(homeButton);

        // Packs and displays the frame.
        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Checks whether the input given is valid. Gives error window if invalid. Moves to next page if valid.
     */
    private void submitDays(){
        String temp = textField.getText().trim();
        try{
            int days = Integer.parseInt(temp);
            if(days > 1 && days < 8){
                super.dispose();
                new GeneratedMeals();
            }
            else {
                JOptionPane.showMessageDialog(null, "Please enter a number between 2 and 7!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (NumberFormatException er){
            JOptionPane.showMessageDialog(null, "Please enter a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
