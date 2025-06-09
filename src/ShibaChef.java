import java.io.*;
import java.util.*;

/**
 * ShibaChef is a personal meal planner and shopping list generator.
 * 
 * @author Anthony Silvester
 * @version 0.3.1
 */
public class ShibaChef {
    // Variables used in ShibaChef.
    static String file = "tests/test.txt";
    static int numberOfMeals = 0;
    static ArrayList<String> mealNames = new ArrayList<>();
    static ArrayList<String> mealProfiles = new ArrayList<>();
    static ArrayList<Meal> meals = new ArrayList<>();
    static ArrayList<Meal> selectedMeals = new ArrayList<>();

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
    private static String combineStringArrays(){
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
    private static void writeToFile(String fileContent){
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
    private static void readFile(){
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
    private static void addNewMeal(String mealName, String mealProfile){
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
    private static void generateMeals(){
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
    private static int[] getNumberOfDays(int numberOfDays){
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
    private static void generateRandomMeals(int[] mealSplit){
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
            if(!foundMeal){
                System.out.println("Could not find valid meal to batch cook for " + mealNum + " Days.");
            }
            }
        }
    }

    public static void main(String[] args){
        new Days();
    }
}