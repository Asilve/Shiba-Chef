import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Window that allows users to make their own meal.
 *
 * @author Anthony Silvester
 * @version v1.3
 */
public class NewMeal extends ShibaWindow{
    // Variable setup
    private final JPanel mealPanel;
    private final JTextField mealName = new JTextField();
    private final JTextField numDays = new JTextField();
    private final ButtonGroup meatGroup = new ButtonGroup();
    private JRadioButton meatButton;
    private JRadioButton tunaButton;
    private final JTextField meatGrams = new JTextField();
    private final JLabel gramsLabel = new JLabel("Grams: ");
    private final ButtonGroup carbGroup = new ButtonGroup();
    private JRadioButton pastaButton;
    private JRadioButton riceButton;
    private JRadioButton noodleButton;
    private final JTextField carbGrams = new JTextField();
    private JCheckBox onionButton;
    private JCheckBox bellButton;
    private JCheckBox garlicButton;
    private JCheckBox chopTomButton;
    private JCheckBox cherryTomButton;
    private JCheckBox eggButton;
    private JCheckBox cheeseButton;
    private JCheckBox mayoButton;
    private JCheckBox sCreamButton;
    private JCheckBox dCreamButton;
    private JCheckBox pastaSauceButton;
    private JCheckBox currySauceButton;
    private JCheckBox soySauceButton;
    private JCheckBox coconutButton;

    NewMeal(){
        // Frame, Image and sound setup
        super(new ImageIcon("assets/Art/Background-NewMeal.png").getImage());
        Image menuButton = new ImageIcon("assets/Art/Button2.png").getImage().getScaledInstance(92, 30, Image.SCALE_SMOOTH);
        File buttonSound = new File("assets/Sounds/clicking.wav");

        // Menu button Configuration.
        JButton homeButton = new JButton("Home", new ImageIcon(menuButton));
        ShibaChef.buttonSetup(homeButton, 580, 80, 92, 30);
        JButton submitButton = new JButton("Submit", new ImageIcon(menuButton));
        ShibaChef.buttonSetup(submitButton,580, 200, 92,30);

        // JPanel Configuration.
        mealPanel = new JPanel(null);
        ShibaChef.panelSetup(mealPanel,45,45, 460, 290);
        pageSetup();

        //Action listeners for buttons.
        homeButton.addActionListener(e -> {ShibaChef.playSound(buttonSound);super.dispose(); new MainMenu();});
        submitButton.addActionListener(e -> {ShibaChef.playSound(buttonSound);submitButtonAction();});
        tunaButton.addActionListener(e -> {gramsLabel.setVisible(false); meatGrams.setVisible(false);});
        meatButton.addActionListener(e -> {gramsLabel.setVisible(true); meatGrams.setVisible(true);});

        // Button and panel adding.
        this.add(mealPanel);
        this.add(homeButton);
        this.add(submitButton);

        // Pack and display frame.
        mealPanel.setVisible(true);
        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Sets up the JRadiobuttons, JCheckBoxes and JTextFields so the user can input their new meal.
     */
    private void pageSetup(){
        // Meal name setup
        mealPanel.add(ShibaChef.setTextLabel(new JLabel("Meal Name:"), 2, 0, 400, 60,22));
        mealPanel.add(ShibaChef.textFieldSetup(mealName, 132, 22, 325, 22, 14));

        // Days setup
        mealPanel.add(ShibaChef.setTextLabel(new JLabel("Number of days:"), 5, 27, 400, 60,14));
        mealPanel.add(ShibaChef.textFieldSetup(numDays, 132, 49, 50, 20, 12));

        // Meat Setup
        mealPanel.add(ShibaChef.setTextLabel(new JLabel("Meat: "), 5, 50, 120, 50,18));
        meatButton = ShibaChef.radioButtonSetup(new JRadioButton("Meat"),5,90,80,14,12);
        meatGroup.add(meatButton);
        mealPanel.add(meatButton);
        tunaButton = ShibaChef.radioButtonSetup(new JRadioButton("Tuna"), 70, 90, 80, 14,12);
        meatGroup.add(tunaButton);
        mealPanel.add(tunaButton);
        mealPanel.add(ShibaChef.setTextLabel(gramsLabel, 7, 109, 70, 20,12));
        mealPanel.add(ShibaChef.textFieldSetup(meatGrams, 55, 112,75,16,12));

        // Carb Setup
        mealPanel.add(ShibaChef.setTextLabel(new JLabel("Carbs: "), 5, 125, 120, 50,18));
        pastaButton = ShibaChef.radioButtonSetup(new JRadioButton("Pasta"),5,165,80,14,12);
        carbGroup.add(pastaButton);
        mealPanel.add(pastaButton);
        riceButton = ShibaChef.radioButtonSetup(new JRadioButton("Rice"),70,165,80,14,12);
        carbGroup.add(riceButton);
        mealPanel.add(riceButton);
        noodleButton = ShibaChef.radioButtonSetup(new JRadioButton("Noodles"),135,165,80,14,12);
        carbGroup.add(noodleButton);
        mealPanel.add(noodleButton);
        mealPanel.add(ShibaChef.setTextLabel(new JLabel("Grams:"), 7, 184, 70, 20,12));
        mealPanel.add(ShibaChef.textFieldSetup(carbGrams,55, 187, 75, 16, 12));

        // Vegetable Setup
        mealPanel.add(ShibaChef.setTextLabel(new JLabel("Vegetables: "), 5, 200, 120, 50,18));
        onionButton = ShibaChef.checkBoxSetup(new JCheckBox("Onion"),5,240,80,14,12);
        mealPanel.add(onionButton);
        bellButton = ShibaChef.checkBoxSetup(new JCheckBox("Bell pepper"),70,240,90,14,11);
        mealPanel.add(bellButton);
        garlicButton = ShibaChef.checkBoxSetup(new JCheckBox("Garlic"),155,240,80,14,12);
        mealPanel.add(garlicButton);
        chopTomButton = ShibaChef.checkBoxSetup(new JCheckBox("Chopped Tomatoes"),5,257,120,14,10);
        mealPanel.add(chopTomButton);
        cherryTomButton = ShibaChef.checkBoxSetup(new JCheckBox("Cherry Tomatoes"),125,257,120,14,10);
        mealPanel.add(cherryTomButton);

        // Misc Setup
        mealPanel.add(ShibaChef.setTextLabel(new JLabel("Misc:"), 300, 50, 120, 55,18));
        eggButton = ShibaChef.checkBoxSetup(new JCheckBox("Eggs"),300,90,80,14,12);
        mealPanel.add(eggButton);
        cheeseButton = ShibaChef.checkBoxSetup(new JCheckBox("Cheese"),300,110,80,14,12);
        mealPanel.add(cheeseButton);
        mayoButton = ShibaChef.checkBoxSetup(new JCheckBox("Mayo"),300,130,80,14,12);
        mealPanel.add(mayoButton);
        sCreamButton = ShibaChef.checkBoxSetup(new JCheckBox("Sour Cream"),300,150,120,14,12);
        mealPanel.add(sCreamButton);
        dCreamButton = ShibaChef.checkBoxSetup(new JCheckBox("Double Cream"),300,170,120,14,12);
        mealPanel.add(dCreamButton);
        pastaSauceButton = ShibaChef.checkBoxSetup(new JCheckBox("Pasta Sauce"),300,190,120,14,12);
        mealPanel.add(pastaSauceButton);
        currySauceButton = ShibaChef.checkBoxSetup(new JCheckBox("Curry Sauce"),300,210,120,14,12);
        mealPanel.add(currySauceButton);
        soySauceButton = ShibaChef.checkBoxSetup(new JCheckBox("Soy Sauce"),300,230,120,14,12);
        mealPanel.add(soySauceButton);
        coconutButton = ShibaChef.checkBoxSetup(new JCheckBox("Coconut Milk"),300,250,120,14,12);
        mealPanel.add(coconutButton);
    }

    /**
     * Adds padding to a string when creating a meal profile.
     *
     * @param input the string input that will be padded with zeros.
     * @return the padded string.
     */
    private String setProfilePadding(String input){
        int length = input.length();

        return switch (length) {
            case 1 -> "100" + input;
            case 2 -> "10" + input;
            case 3 -> "1" + input;
            default -> "0000";
        };
    }

    /**
     * A method that implements the actions that should be taken when pressing the submit button.
     */
    private void submitButtonAction(){
        String submittedMealName = mealName.getText().trim();
        String submittedDays = numDays.getText().trim();
        String mealProfile = "";

        // Check meal name valid
        if (submittedMealName.length() < 3){
            JOptionPane.showMessageDialog(null, "Submit a valid meal name! (Greater than 3 characters.)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check number of days is valid.
        try {
            int days = Integer.parseInt(submittedDays);
            if (days <= 1 || days >= 8) {
                JOptionPane.showMessageDialog(null, "Please enter a number of days between 2 and 7!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        catch (NumberFormatException er){
            JOptionPane.showMessageDialog(null, "Please enter a number of days for the meal.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        mealProfile += submittedDays;

        // check meat input is valid
        if (!meatButton.isSelected() && !tunaButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please enter a valid meat option!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (meatButton.isSelected()){
            try {
                int inputMGrams = Integer.parseInt(meatGrams.getText().trim());
                if (inputMGrams <=0 || inputMGrams >= 1000){
                    JOptionPane.showMessageDialog(null, "Please enter a valid number of grams for the meat! (1 to 999 grams)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            catch (NumberFormatException er){
                JOptionPane.showMessageDialog(null, "Please enter a number of grams for the meat!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            mealProfile += " 0 " + setProfilePadding(meatGrams.getText().trim());
        }
        if (tunaButton.isSelected()) {mealProfile += " 1 0000";}

        // check carb input is valid
        if (!pastaButton.isSelected() && !riceButton.isSelected() && !noodleButton.isSelected()){
            JOptionPane.showMessageDialog(null, "Please enter a valid input for the carbs!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int inputCGrams = Integer.parseInt(carbGrams.getText().trim());
            if (inputCGrams <=0 || inputCGrams >= 1000){
                JOptionPane.showMessageDialog(null, "Please enter a valid number of grams for the meat! (1 to 999 grams)", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        catch (NumberFormatException er){
            JOptionPane.showMessageDialog(null, "Please enter a number of grams for the carbs!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (pastaButton.isSelected()) {mealProfile += " " + setProfilePadding(carbGrams.getText().trim()) + " 0000 0000 ";}
        if (riceButton.isSelected()) {mealProfile += " 0000 " + setProfilePadding(carbGrams.getText().trim()) + " 0000 ";}
        if (noodleButton.isSelected()) {mealProfile += " 0000 0000 " + setProfilePadding(carbGrams.getText().trim()) + " ";}

        // add Veg profile
        mealProfile += (onionButton.isSelected() ? "1" : "0");
        mealProfile += (bellButton.isSelected() ? "1" : "0");
        mealProfile += (garlicButton.isSelected() ? "1" : "0");
        mealProfile += (chopTomButton.isSelected() ? "1" : "0");
        mealProfile += (cherryTomButton.isSelected() ? "1 " : "0 ");

        // add misc profile
        mealProfile += (eggButton.isSelected() ? "1" : "0");
        mealProfile += (cheeseButton.isSelected() ? "1" : "0");
        mealProfile += (mayoButton.isSelected() ? "1" : "0");
        mealProfile += (sCreamButton.isSelected() ? "1" : "0");
        mealProfile += (dCreamButton.isSelected() ? "1" : "0");
        mealProfile += (pastaSauceButton.isSelected() ? "1" : "0");
        mealProfile += (currySauceButton.isSelected() ? "1" : "0");
        mealProfile += (soySauceButton.isSelected() ? "1" : "0");
        mealProfile += (coconutButton.isSelected() ? "1" : "0");

        int answer = JOptionPane.showConfirmDialog(null, "Is this meal Correct?", "Meal Confirmation", JOptionPane.YES_NO_OPTION);

        if (answer == 0){
            ShibaChef.addNewMeal(submittedMealName, mealProfile);
            super.dispose();
            new MainMenu();
        }
    }

}
