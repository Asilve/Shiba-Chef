import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Window that displays all meals that have been generated.
 * Allows user to also display the shopping list.
 *
 * @author Anthony Silvester
 * @version v1.2
 */
public class GeneratedMeals extends ShibaWindow{
    // Variable setup.
    private static final Image mealsBackground = new ImageIcon("assets/Art/Background-Meals.png").getImage();
    private static final Image shoppingBackground = new ImageIcon("assets/Art/Background-Shopping.png").getImage();
    private ArrayList<Meal> meals;
    private boolean mealPage = true;
    private JButton shoppingButton;
    private JButton mealsButton;
    private JPanel[] panelArray;
    private JButton[] deselectedButtons;
    private JButton[] selectedButtons;

    /**
     * Constructor
     *
     * @param selectedMeals Meals we have generated to be made.
     */
    GeneratedMeals(ArrayList<Meal> selectedMeals){
        // Frame and image setup.
        super(mealsBackground);
        meals = selectedMeals;
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

        // JPanel Configuration
        JPanel mealOne = new JPanel(null);
        ShibaChef.panelSetup(mealOne,45,45, 460, 290);
        JPanel mealTwo = new JPanel(null);
        ShibaChef.panelSetup(mealTwo,45,45, 460, 290);
        JPanel mealThree = new JPanel(null);
        ShibaChef.panelSetup(mealThree,45,45, 460, 290);
        JPanel shoppingPanel = new JPanel(null);
        ShibaChef.panelSetup(shoppingPanel, 45, 45, 460, 290);
        panelArray = new JPanel[]{mealOne, mealTwo, mealThree, shoppingPanel};
        for(JPanel p : panelArray){this.add(p);}

        // Meal button Configuration
        JButton mealOneD = new JButton("Meal 1", new ImageIcon(deselectedButton));
        ShibaChef.buttonSetup(mealOneD, 36,352,108,34);
        JButton mealOneS = new JButton("Meal 1", new ImageIcon(selectedButton));
        ShibaChef.buttonSetup(mealOneS, 36,352,108,34);
        JButton mealTwoD = new JButton("Meal 2", new ImageIcon(deselectedButton));
        ShibaChef.buttonSetup(mealTwoD, 144,352,108,34);
        JButton mealTwoS = new JButton("Meal 2", new ImageIcon(selectedButton));
        ShibaChef.buttonSetup(mealTwoS, 144,352,108,34);
        JButton mealThreeD = new JButton("Meal 3", new ImageIcon(deselectedButton));
        ShibaChef.buttonSetup(mealThreeD, 252,352,108,34);
        JButton mealThreeS = new JButton("Meal 3", new ImageIcon(selectedButton));
        ShibaChef.buttonSetup(mealThreeS, 252,352,108,34);
        deselectedButtons = new JButton[]{mealOneD,mealTwoD,mealThreeD};
        for (JButton dB : deselectedButtons){this.add(dB); dB.setVisible(false);}
        selectedButtons = new JButton[]{mealOneS, mealTwoS, mealThreeS};
        for (JButton sB : selectedButtons){this.add(sB); sB.setVisible(false);}


        // Action listeners for menu buttons.
        homeButton.addActionListener(e -> {super.dispose(); new MainMenu();});
        backButton.addActionListener(e -> {super.dispose(); new Days();});
        shoppingButton.addActionListener(e -> changePage());
        mealsButton.addActionListener(e -> changePage());

        // Action listeners for meal buttons.
        mealOneD.addActionListener(e -> panelSwap(0));
        mealTwoD.addActionListener(e -> panelSwap(1));
        mealThreeD.addActionListener(e -> panelSwap(2));

        // Start up method calling
        panelStartSetup();
        buttonStartSetup();

        // Button adding.
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
            panelSwap(3);
            mealPage = false;
        }

        // Otherwise, if on shopping list page, sets up meals page.
        else {
            super.changeBackground(mealsBackground);
            mealsButton.setVisible(false);
            shoppingButton.setVisible(true);
            panelSwap(0);
            mealPage = true;
        }
    }

    /**
     * Sets up a text JLabels to be added to panels.
     *
     * @param label the JLabel to be configured
     * @param xCoord the x Coordinates of the label
     * @param yCoord the y Coordinates of the label
     * @param width the Width of the label
     * @param height the height of the label
     * @param size the size of the font
     * @return the configured label back (for anonymous object calling).
     */
    private JLabel setTextLabel(JLabel label, int xCoord, int yCoord, int width, int height, int size){
        label.setBounds(xCoord,yCoord,width,height);
        label.setFont(new Font("SansSerif", Font.BOLD, size));
        label.setForeground(new Color(50,35,20));
        label.setOpaque(false);
        return label;
    }

    /**
     * Sets up the meal panels and shopping panels with their relevant information.
     * Hides all panels upon completing their setup
     */
    private void panelStartSetup(){
        // Set up each Meal Panel.
        for (int i = 0; i < meals.size(); i++){
            // Get panel and meal.
            JPanel panel = panelArray[i];
            Meal meal = meals.get(i);

            // Set up Titles.
            JLabel mealName = new JLabel(meal.getMeal_name());
            panel.add(setTextLabel(mealName, 0, 0, 400, 60,22));
            JLabel meatText = new JLabel("Meat:");
            panel.add(setTextLabel(meatText, 5, 50, 220, 60, 18));
            JLabel carbText = new JLabel("Carb:");
            panel.add(setTextLabel(carbText, 5, 100, 220, 60, 18));
            JLabel vegText = new JLabel("Vegetables:");
            panel.add(setTextLabel(vegText, 5, 150, 220, 60, 18));
            JLabel miscText = new JLabel("Misc:");
            panel.add(setTextLabel(miscText, 230, 50, 220, 60, 18));

            // Set meat value.
            JLabel meatValue;
            if(meal.getCannedFish() == 1){
                meatValue = new JLabel("Canned Fish.");
            }
            else {
                meatValue = new JLabel(meal.getMeatGrams() + " Grams of Meat");
            }
            panel.add(setTextLabel(meatValue,5,80,220,30, 12));

            // set carb value.
            JLabel carbValue;
            if(meal.getPastaGrams() > 0){
                carbValue = new JLabel(meal.getPastaGrams() + " Grams of Pasta");
            }
            else if (meal.getRiceGrams() > 0) {
                carbValue = new JLabel(meal.getRiceGrams() + " Grams of Rice");
            }
            else {
                carbValue = new JLabel(meal.getNoodlesGrams() + " Grams of Noodles");
            }
            panel.add(setTextLabel(carbValue, 5, 130,220,30,12));

            // Set up Vegetable values.
            int vegNum = 0;
            if(meal.getOnion() == 1){
                panel.add(setTextLabel(new JLabel("Onion"),5,180,220,30,12));
                vegNum++;
            }
            if (meal.getBellPepper() == 1){
                panel.add(setTextLabel(new JLabel("Bell Pepper"),5,180+(14*vegNum),220,30,12));
                vegNum++;
            }
            if(meal.getGarlic() == 1){
                panel.add(setTextLabel(new JLabel("Garlic"),5,180+(14*vegNum),220,30,12));
                vegNum++;
            }
            if(meal.getChoppedTomatoes() == 1){
                panel.add(setTextLabel(new JLabel("Chopped Tomatoes"),5,180+(14*vegNum),220,30,12));
                vegNum++;
            }
            if(meal.getCherryTomatoes() == 1){
                panel.add(setTextLabel(new JLabel("Cherry Tomatoes"),5,180+(14*vegNum),220,30,12));
            }

            // Set up misc values.
            int miscNum = 0;
            if(meal.getEggs() == 1){
                panel.add(setTextLabel(new JLabel("Eggs"),230,80,220,30,12));
                miscNum++;
            }
            if(meal.getCheese() == 1){
                panel.add(setTextLabel(new JLabel("Cheese"),230,80+(14*miscNum),220,30,12));
                miscNum++;
            }
            if(meal.getMayo() == 1){
                panel.add(setTextLabel(new JLabel("Mayo"),230,80+(14*miscNum),220,30,12));
                miscNum++;
            }
            if(meal.getSourCream() == 1){
                panel.add(setTextLabel(new JLabel("Sour Cream"),230,80+(14*miscNum),220,30,12));
                miscNum++;
            }
            if(meal.getDoubleCream() == 1){
                panel.add(setTextLabel(new JLabel("Double Cream"),230,80+(14*miscNum),220,30,12));
                miscNum++;
            }
            if(meal.getPastaSauce() == 1){
                panel.add(setTextLabel(new JLabel("Pasta Sauce"),230,80+(14*miscNum),220,30,12));
                miscNum++;
            }
            if(meal.getCurrySauce() == 1){
                panel.add(setTextLabel(new JLabel("Curry Sauce"),230,80+(14*miscNum),220,30,12));
                miscNum++;
            }
            if(meal.getSoySauce() == 1){
                panel.add(setTextLabel(new JLabel("Soy Sauce"),230,80+(14*miscNum),220,30,12));
                miscNum++;
            }
            if(meal.getCoconutMilk() == 1){
                panel.add(setTextLabel(new JLabel("Coconut Milk"),230,80+(14*miscNum),220,30,12));
            }
            panel.setVisible(false);

        }
        panelArray[0].setVisible(true);

        // Set up Shopping Panel.
        JPanel sPanel = panelArray[3];
        boolean tuna = false;
        int meat = 0;
        int pasta = 0;
        int rice = 0;
        int noodles = 0;
        int onion = 0;
        int bell = 0;
        boolean garlic = false;
        int chopTom = 0;
        boolean cherryTom = false;
        boolean egg = false;
        boolean cheese = false;
        boolean mayo = false;
        boolean sCream = false;
        boolean dCream = false;
        int pastaSauce = 0;
        int currySauce = 0;
        boolean soySauce = false;
        boolean coconutMilk = false;

        // get totals for every meal.
        for(Meal meal : meals){
            if (!tuna && meal.getCannedFish() == 1){tuna = true;}
            meat += meal.getMeatGrams();
            pasta += meal.getPastaGrams();
            rice += meal.getRiceGrams();
            noodles += meal.getNoodlesGrams();
            onion += meal.getOnion();
            bell += meal.getBellPepper();
            if (!garlic && meal.getGarlic() == 1){garlic = true;}
            chopTom += meal.getChoppedTomatoes();
            if (!cherryTom && meal.getCherryTomatoes() == 1){cherryTom = true;}
            if (!egg && meal.getEggs() == 1){egg = true;}
            if (!cheese && meal.getCheese() == 1){cheese = true;}
            if (!mayo && meal.getMayo() == 1){mayo = true;}
            if (!sCream && meal.getSourCream() == 1){sCream = true;}
            if (!dCream && meal.getDoubleCream() == 1){dCream = true;}
            pastaSauce += meal.getPastaSauce();
            currySauce += meal.getCurrySauce();
            if (!soySauce && meal.getSoySauce() == 1){soySauce = true;}
            if (!coconutMilk && meal.getCoconutMilk() == 1){coconutMilk = true;}
        }

        // Set up the Shopping List
        JLabel shoppingTitle = new JLabel("Shopping List");
        setTextLabel(shoppingTitle,0, 0, 220, 60,18);
        sPanel.add(shoppingTitle);
        int numItems = 0;
        int xExpansion = 0;
        if(tuna){sPanel.add(setTextLabel(new JLabel("Canned Fish"), 5,50,100,20,12)); numItems++;}
        if(meat > 0){sPanel.add(setTextLabel(new JLabel(meat + " Grams of Meat"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(pasta>0){sPanel.add(setTextLabel(new JLabel(pasta + " Grams of Pasta"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(rice>0){sPanel.add(setTextLabel(new JLabel(rice + " Grams of Rice"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(noodles>0){sPanel.add(setTextLabel(new JLabel(noodles + " Grams of Noodles"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(onion>0){sPanel.add(setTextLabel(new JLabel(onion + " Onion(s)"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(bell>0){sPanel.add(setTextLabel(new JLabel(bell + " Bell Pepper(s)"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(garlic){sPanel.add(setTextLabel(new JLabel("Garlic"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(chopTom>0){sPanel.add(setTextLabel(new JLabel(chopTom + " Can(s) of Chopped Tomatoes"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(cherryTom){sPanel.add(setTextLabel(new JLabel("Cherry Tomatoes"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(egg){sPanel.add(setTextLabel(new JLabel("Eggs"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(cheese){sPanel.add(setTextLabel(new JLabel("Cheese"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(mayo){sPanel.add(setTextLabel(new JLabel("Mayo"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if(sCream){sPanel.add(setTextLabel(new JLabel("Sour Cream"), 5,50+(numItems*14),200,20,12)); numItems++;}
        if (numItems >=14 ){numItems = 0; xExpansion++;}
        if(dCream){sPanel.add(setTextLabel(new JLabel("Double Cream"), 5 + (xExpansion*230),50+(numItems*14),200,20,12)); numItems++;}
        if (numItems >=14 ){numItems = 0; xExpansion++;}
        if(pastaSauce>0){sPanel.add(setTextLabel(new JLabel(pastaSauce + " Jar(s) of Pasta Sauce"), 5 + (xExpansion*230),50+(numItems*14),200,20,12)); numItems++;}
        if (numItems >=14 ){numItems = 0; xExpansion++;}
        if (currySauce>0){sPanel.add(setTextLabel(new JLabel(currySauce + " Jar(s) of Curry Sauce"), 5 + (xExpansion*230),50+(numItems*14),200,20,12)); numItems++;}
        if (numItems >=14 ){numItems = 0; xExpansion++;}
        if(soySauce){sPanel.add(setTextLabel(new JLabel("Soy Sauce"), 5 + (xExpansion*230),50+(numItems*14),200,20,12)); numItems++;}
        if (numItems >=14 ){numItems = 0; xExpansion++;}
        if(coconutMilk){sPanel.add(setTextLabel(new JLabel("Coconut Milk"), 5 + (xExpansion*230),50+(numItems*14),200,20,12)); numItems++;}

        sPanel.setVisible(false);
    }

    private void buttonStartSetup(){
        selectedButtons[0].setVisible(true);
        for(int i = 1; i < meals.size(); i++){
            deselectedButtons[i].setVisible(true);
        }

    }

    /**
     * swaps panels and buttons based on which button was clicked.
     *
     * @param value the value of the button that was pressed.
     */
    private void panelSwap(int value){
        panelArray[3].setVisible(false);
        for (int i = 0; i < meals.size(); i++){
            panelArray[i].setVisible(false);
            selectedButtons[i].setVisible(false);
            deselectedButtons[i].setVisible(true);
        }
        panelArray[value].setVisible(true);
        if(value < 3){
            selectedButtons[value].setVisible(true);
            deselectedButtons[value].setVisible(false);
        }
        if (value == 3){
            deselectedButtons[0].setVisible(false);
            deselectedButtons[1].setVisible(false);
            deselectedButtons[2].setVisible(false);
        }
    }
}
