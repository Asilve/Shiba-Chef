import javax.swing.*;
import java.awt.*;

/**
 * Window that allows users to make their own meal.
 *
 * @author Anthony Silvester
 * @version v1.1
 */
public class NewMeal extends ShibaWindow{
    // Variable setup
    private final JPanel mealPanel;
    private final JTextField mealName = new JTextField();
    private final JTextField numDays = new JTextField();
    private final ButtonGroup meatGroup = new ButtonGroup();
    private JRadioButton meatButton;
    private JRadioButton tunaButton;
    private JTextField meatGrams = new JTextField();
    private JLabel gramsLabel = new JLabel("Grams: ");
    private final ButtonGroup carbGroup = new ButtonGroup();
    private JRadioButton pastaButton;
    private JRadioButton riceButton;
    private JRadioButton noodleButton;
    private JTextField carbGrams = new JTextField();
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
        // Frame and Image setup
        super(new ImageIcon("assets/Art/Background-NewMeal.png").getImage());
        Image menuButton = new ImageIcon("assets/Art/Button2.png").getImage().getScaledInstance(92, 30, Image.SCALE_SMOOTH);

        // Menu button Configuration.
        JButton homeButton = new JButton("Home", new ImageIcon(menuButton));
        ShibaChef.buttonSetup(homeButton, 580, 80, 92, 30);
        JButton submitButton = new JButton("Submit", new ImageIcon(menuButton));
        ShibaChef.buttonSetup(submitButton,580, 200, 92,30);

        // JPanel Configuration
        mealPanel = new JPanel(null);
        ShibaChef.panelSetup(mealPanel,45,45, 460, 290);
        pageSetup();

        //Action listeners for menu buttons.
        homeButton.addActionListener(e -> {super.dispose(); new MainMenu();});

        // Button and panel adding
        this.add(mealPanel);
        this.add(homeButton);
        this.add(submitButton);

        // Pack and display frame.
        mealPanel.setVisible(true);
        this.pack();
        this.setVisible(true);
        setLocationRelativeTo(null);
    }

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

}
