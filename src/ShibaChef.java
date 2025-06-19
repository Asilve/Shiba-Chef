import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * ShibaChef is a personal meal planner and shopping list generator.
 * 
 * @author Anthony Silvester
 * @version 0.3.6
 */
public class ShibaChef {
    // Variables used in ShibaChef.
    static String file = "tests/test.txt";
    static int numberOfMeals = 0;
    static ArrayList<String> mealNames = new ArrayList<>();
    static ArrayList<String> mealProfiles = new ArrayList<>();
    static ArrayList<Meal> meals = new ArrayList<>();

    /**
     * Combines two {@link ArrayList}s of {@link String}s by alternating their elements line by line.
     * <p>
     * For each index from 0 to {@code numberOfMeals} (inclusive), this method appends the element
     * at that index from {@code mealNames} followed by the corresponding element from {@code mealProfiles},
     * each separated by a newline character.
     * <p>
     * The resulting string is trimmed of trailing whitespace before being returned.
     *
     * @return a combined string with alternating elements from {@code mealNames} and {@code mealProfiles},
     *         each separated by newlines
     */
    public static String combineStringArrays(){
        String output = "";
        for(int i = 0; i < numberOfMeals; i++){
            output += mealNames.get(i) + "\n" + mealProfiles.get(i) + "\n";
        }
        return output.trim();
    }

    /**
     * Writes the specified content to a file at the given file path.
     * <p>
     * If the file does not exist, it will be created. If it does exist, its contents
     * will be overwritten. If an error occurs during writing, an appropriate error
     * message is printed to the console.
     *
     * @param fileContent the content to write to the file
     */
    public static void writeToFile(String fileContent){
        try (FileWriter writer = new FileWriter(file)){
            writer.write(fileContent);
        }
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads a file line by line and stores alternating lines into separate lists.
     * <p>
     * Even-numbered lines (starting from index 0) are added to the {@code mealNames} list
     * and increment the {@code numberOfMeals} counter. Odd-numbered lines are added
     * to the {@code mealProfiles} list. This assumes that the file contains pairs of
     * meal names and corresponding meal profiles in alternating lines. Populates {@code meals}
     * once file reading has been finished.
     * <p>
     * {@code mealNames}, {@code numberOfMeals} and {@code mealProfiles} are all reset
     * upon calling the method in case multiple calls are made to read the file during
     * runtime.
     * <p>
     * If an I/O error occurs during reading, an error message is printed to the console.
     *
     */
    public static void readFile(){
        mealNames.clear();
        mealProfiles.clear();
        numberOfMeals = 0;
        int i = 0;
        meals.clear();
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while((line = reader.readLine()) != null){
                if(i % 2 == 0){
                    mealNames.add(line);
                    numberOfMeals++;
                }
                else{
                    mealProfiles.add(line);
                }
                i++;
            }
        }
        catch (IOException e) {
            System.out.println("Error Reading file: "  + e.getMessage());
        }
        generateMeals();
    }

    /**
     * Adds a new meal to the meal list if the provided meal profile matches the expected format.
     * Updates text file and variables in the program.
     * <p>
     * The meal profile must follow the format:
     * {@code ^\d [01] [01]\d{3} [01]\d{3} [01]\d{3} [01]\d{3} [01]{5} [01]{9}$}.
     * </p>
     *
     * @param mealName    The name of the meal to be added.
     * @param mealProfile A formatted string representing the meal profile.
     *                    The expected format consists of:
     *                    <ul>
     *                        <li>One digit (0-9) - Number of days meal is made for.</li>
     *                        <li>One binary digit (0 or 1) - If the meal uses tinned Tuna/Salmon</li>
     *                        <li>Four groups of a binary digit followed by three digits (e.g., "1300") -
     *                       displays if meat, pasta, rice or noddles are used (respectively), and how many grams.</li>
     *                        <li>A five-digit binary sequence - Vegetable profile</li>
     *                        <li>A nine-digit binary sequence - Misc items profile</li>
     *                    </ul>
     *                    Example: {@code "3 0 1300 1400 0000 0000 11010 010001000"}
     * @throws IllegalArgumentException if the meal profile format is incorrect.
     */
    public static void addNewMeal(String mealName, String mealProfile){
        String regex = "^\\d [01] [01]\\d{3} [01]\\d{3} [01]\\d{3} [01]\\d{3} [01]{5} [01]{9}$";
        if(mealProfile.matches(regex)){
            mealNames.add(mealName);
            mealProfiles.add(mealProfile);
            numberOfMeals++;
            meals.add(new Meal(mealName, mealProfile));
            writeToFile(combineStringArrays());
        }
        else{
            throw new IllegalArgumentException("Profile is not in the correct format.");
        }

    }

    /**
     * Generates a list of {@code Meal} objects from stored meal names and meal profiles.
     * <p>
     * Each meal profile must match the expected format:
     * {@code ^\d [01] [01]\d{3} [01]\d{3} [01]\d{3} [01]\d{3} [01]{5} [01]{9}$}.
     * If a meal profile does not conform to this format, an {@code IllegalArgumentException} is thrown.
     * </p>
     *
     * @throws IllegalArgumentException if any meal profile does not match the expected format.
     */
    public static void generateMeals(){
        meals.clear();
        String regex = "^\\d [01] [01]\\d{3} [01]\\d{3} [01]\\d{3} [01]\\d{3} [01]{5} [01]{9}$";
        for (int i = 0; i < numberOfMeals; i++){
            if(mealProfiles.get(i).matches(regex)){
                meals.add(new Meal(mealNames.get(i), mealProfiles.get(i)));
            }
            else {
                throw new IllegalArgumentException("Profile is not in the correct format.");
            }
        }
    }

    /**
     * Splits a given total number of days into smaller segments for meal planning.
     * <p>
     * The method returns an array of integers representing how the days should be grouped.
     * For example, if {@code numberOfDays} is 4, the method returns {@code [2, 2]}.
     * </p>
     * <p>
     * For 6 days, the split is randomized between {@code [2, 2, 2]} and {@code [3, 3]}.
     * </p>
     *
     * @param numberOfDays the total number of days to be split (accepted values: 2–7)
     * @return an array of integers representing how the days are split
     * @throws NoSuchElementException if the provided number of days is not supported
     */
    public static int[] getNumberOfDays(int numberOfDays){
        int[] daySplit;

        switch (numberOfDays){
            case 2:
                daySplit = new int[]{2};
                return daySplit;
            case 3:
                daySplit = new int[]{3};
                return daySplit;
            case 4:
                daySplit = new int[]{2,2};
                return daySplit;
            case 5:
                daySplit = new int[]{2,3};
                return daySplit;
            case 7:
                daySplit = new int[]{2,2,3};
                return daySplit;
            case 6:
                Random random = new Random();
                int number = random.nextInt(0,2);

                if(number == 0){
                    daySplit = new int[]{2,2,2};
                }
                else {
                    daySplit = new int[]{3,3};
                }
                return daySplit;
            default:
                throw new NoSuchElementException("No valid number was given. Try again.");
        }
    }

    /**
     * Randomly selects meals from the list of available meals based on the provided day split.
     * <p>
     * For each entry in the {@code mealSplit} array, the method looks for a {@code Meal} object
     * that matches the required number of batch-cook days. It ensures no duplicate meals are selected.
     * </p>
     * <p>
     * The list of meals is shuffled before selection to ensure randomness.
     * If no valid meal is found for a given day split, a message is printed to the console.
     * </p>
     *
     * @param mealSplit an array of integers representing how many days each selected meal should cover
     */
    public static ArrayList<Meal> generateRandomMeals(int[] mealSplit){
        ArrayList<Meal> selectedMeals = new ArrayList<>();
        for(int mealNum : mealSplit){
            int index = 0;
            boolean foundMeal = false;
            Collections.shuffle(meals);

            while(!foundMeal && index < numberOfMeals){
                Meal meal = meals.get(index);
                if(meal.getNumOfDays() == mealNum && !selectedMeals.contains(meal)){
                    foundMeal = true;
                    selectedMeals.add(meal);
                }
                else{
                    index++;
                }
            }
            if(!foundMeal){
                JOptionPane.showMessageDialog(null, "Could not find valid meal to batch cook for " + mealNum + " Days.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return selectedMeals;
    }

    /**
     * Sets up the button configurations for GUI.
     *
     * @param button The button we are setting up.
     * @param xCoord The x coordinate that the button will be placed.
     * @param yCoord The y coordinate that the button will be placed.
     * @param width The width of the button.
     * @param height The height of the button.
     */
    public static void buttonSetup(JButton button, int xCoord, int yCoord, int width, int height){
        button.setBounds(xCoord, yCoord, width, height);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(new Color(216, 195, 167));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    /**
     * Sets up a JPanel for GUI
     *
     * @param panel panel we are setting up
     * @param xCoord x coordinate of the panel
     * @param yCoord y coordinate of the panel
     * @param width width of the panel
     * @param height height of the panel
     */
    public static void panelSetup(JPanel panel, int xCoord, int yCoord, int width, int height){
        panel.setBounds(xCoord, yCoord, width, height);
        panel.setOpaque(false);
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
    public static JLabel setTextLabel(JLabel label, int xCoord, int yCoord, int width, int height, int size){
        label.setBounds(xCoord,yCoord,width,height);
        label.setFont(new Font("SansSerif", Font.BOLD, size));
        label.setForeground(new Color(50,35,20));
        label.setOpaque(false);
        return label;
    }

    /**
     * Sets up a text field to be added to panels.
     *
     * @param textField the field to be configured
     * @param xCoord the x Coordinates of the field
     * @param yCoord the y Coordinates of the field
     * @param width the Width of the field
     * @param height the height of the field
     * @param size the size of the field
     * @return the configured text field back (for anonymous object calling).
     */
    public static JTextField textFieldSetup(JTextField textField, int xCoord, int yCoord, int width, int height, int size){
        textField.setBounds(xCoord, yCoord, width, height);
        textField.setFont(new Font("SansSerif", Font.BOLD, size));
        textField.setForeground(new Color(50,35,20));
        return textField;
    }

    /**
     * Sets up a radio button to be added to panels.
     *
     * @param button the JRadioButton to be configured
     * @param xCoord the x Coordinates of the button
     * @param yCoord the y Coordinates of the button
     * @param width the Width of the button
     * @param height the height of the button
     * @param size the size of the font
     * @return the configured button back (for anonymous object calling).
     */
    public static JRadioButton radioButtonSetup(JRadioButton button, int xCoord, int yCoord, int width, int height, int size){
        button.setBounds(xCoord,yCoord,width,height);
        button.setOpaque(false);
        button.setFont(new Font("SansSerif", Font.BOLD, size));
        button.setForeground(new Color(50,35,20));
        return button;
    }

    /**
     * Sets up a checkbox to be added to panels.
     *
     * @param button the JCheckBox to be configured
     * @param xCoord the x Coordinates of the box
     * @param yCoord the y Coordinates of the box
     * @param width the Width of the box
     * @param height the height of the box
     * @param size the size of the font
     * @return the configured box back (for anonymous object calling).
     */
    public static JCheckBox checkBoxSetup(JCheckBox button, int xCoord, int yCoord, int width, int height, int size) {
        button.setBounds(xCoord, yCoord, width, height);
        button.setOpaque(false);
        button.setFont(new Font("SansSerif", Font.BOLD, size));
        button.setForeground(new Color(50, 35, 20));
        return button;
    }

    public static void main(String[] args){
        readFile();
        new MainMenu();
    }
}