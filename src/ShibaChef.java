import java.io.*;
import java.util.ArrayList;

/**
 * ShibaChef is a personal meal planner and shopping list generator.
 * 
 * @author Anthony Silvester
 * @version 0.1.4
 */
public class ShibaChef {
    // Variables used in ShibaChef.
    static String file = "tests/test.txt";
    static int numberOfMeals = 0;
    static ArrayList<String> mealNames = new ArrayList<>();
    static ArrayList<String> mealProfiles = new ArrayList<>();

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
     * meal names and corresponding meal profiles in alternating lines.
     * <p>
     * If an I/O error occurs during reading, an error message is printed to the console.
     *
     */
    private static void readFile(){
        int i = 0;
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
    }

    public static void main(String[] args){

    }
}