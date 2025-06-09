import javax.swing.*;
import java.awt.*;

/**
 * Window for obtaining how many days to batch cook.
 *
 * @author Anthony Silvester
 * @version v1.0
 */
public class Days extends ShibaWindow{
    private final JTextField textField;

    Days(){
        super(new ImageIcon("assets/Art/Background-Days.png").getImage(), new ImageIcon("assets/Art/Hat-Icon.png").getImage());
        Image buttonImage = new ImageIcon("assets/Art/Button5.png").getImage().getScaledInstance(125, 45, Image.SCALE_SMOOTH);

        JButton button = new JButton("Submit", new ImageIcon(buttonImage));
        button.setBounds(284, 265, 125, 45);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        textField = new JTextField();
        textField.setBounds(225, 140, 240, 105);
        textField.setFont(new Font("SansSerif", Font.BOLD, 42));
        textField.setForeground(new Color(216, 195, 167));
        textField.setCaretColor(Color.WHITE);
        textField.setOpaque(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setBorder(BorderFactory.createEmptyBorder());

        button.addActionListener(e -> submitDays());
        textField.addActionListener(e -> submitDays());

        this.add(button);
        this.add(textField);

        this.pack();
        this.setVisible(true);
    }

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
