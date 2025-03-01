/**
 * Meal class is used to record and obtain meal data used in ShibaChef.
 *
 * @author Anthony Silvester
 * @version v1.0
 */

public class Meal {
    // Attribute setup
    private String meal_name;
    private int numOfDays;
    private boolean cannedFish;
    private boolean meat;
    private int meatGrams;
    private boolean pasta;
    private int pastaGrams;
    private boolean rice;
    private int riceGrams;
    private boolean noodles;
    private int noodlesGrams;
    private boolean onion;
    private boolean bellPepper;
    private boolean garlic;
    private boolean choppedTomatoes;
    private boolean cherryTomatoes;
    private boolean eggs;
    private boolean cheese;
    private boolean mayo;
    private boolean sourCream;
    private boolean doubleCream;
    private boolean pastaSauce;
    private boolean currySauce;
    private boolean soySauce;
    private boolean coconutMilk;

    /**
     * Constructor
     *
     * @param name Meal name
     * @param profile Meal profile to be deciphered
     */
    public Meal(String name, String profile){
        this.meal_name = name;
        this.numOfDays = (int) profile.charAt(0);
        this.cannedFish = (int) profile.charAt(2) != 0;
        this.meat = (int) profile.charAt(4) != 0;
        this.meatGrams = this.meat ? (Integer.parseInt(profile.substring(5,8))) : 0;
        this.pasta = (int) profile.charAt(9) != 0;
        this.pastaGrams = this.pasta ? (Integer.parseInt(profile.substring(10,13))) : 0;
        this.rice = (int) profile.charAt(14) != 0;
        this.riceGrams = this.rice ? (Integer.parseInt(profile.substring(15,18))) : 0;
        this.noodles = (int) profile.charAt(19) != 0;
        this.noodlesGrams = this.noodles ? (Integer.parseInt(profile.substring(20,23))) : 0;
        this.onion = (int) profile.charAt(24) != 0;
        this.bellPepper = (int) profile.charAt(25) != 0;
        this.garlic = (int) profile.charAt(26) != 0;
        this.choppedTomatoes = (int) profile.charAt(27) != 0;
        this.cherryTomatoes = (int) profile.charAt(28) != 0;
        this.eggs = (int) profile.charAt(30) != 0;
        this.cheese = (int) profile.charAt(31) != 0;
        this.mayo = (int) profile.charAt(32) != 0;
        this.sourCream = (int) profile.charAt(33) != 0;
        this.doubleCream = (int) profile.charAt(34) != 0;
        this.pastaSauce = (int) profile.charAt(35) != 0;
        this.currySauce = (int) profile.charAt(36) != 0;
        this.soySauce = (int) profile.charAt(37) != 0;
        this.coconutMilk = (int) profile.charAt(38) != 0;


    }

    /**
     * This method returns the name of the meal when we want to call the object.
     *
     * @return the name of the meal.
     */
    public String toString(){
        return this.meal_name;
    }

    /**
     * Gets the number of days the meal can be made for.
     *
     * @return the number of days the meal can be made for.
     */
    public int getNumOfDays(){
        return numOfDays;
    }

    /**
     * Outputs if a can of fish is used in the meal.
     *
     * @return 0 if canned fish isn't used and 1 if it is used.
     */
    public int getCannedFish(){
        if(this.cannedFish){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Gets the amount of meat the meal requires.
     *
     * @return how many grams of meat is required. (0 if no meat is required)
     */
    public int getMeatGrams(){
        return meatGrams;
    }

    /**
     * Gets the amount of pasta the meal requires.
     *
     * @return the amount of pasta is required. (0 if no pasta required)
     */
    public int getPastaGrams(){
        return pastaGrams;
    }

    /**
     * Gets the amount of rice the meal requires.
     *
     * @return the amount of rice used in the meal. (returns 0 if no rice required)
     */
    public int getRiceGrams(){
        return riceGrams;
    }

    /**
     * Gets the amount of noodles the meal requires.
     *
     * @return the amount of noodles the meal requires. (returns 0 if no noodles required)
     */
    public int getNoodlesGrams(){
        return this.noodlesGrams;
    }

    /**
     * Outputs if an onion is used in the meal.
     *
     * @return 0 if no onion required or 1 if an onion is required.
     */
    public int getOnion(){
        if(this.onion){
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Outputs if a bell pepper is used in the meal.
     *
     * @return 0 if a bell pepper is not required or 1 if a bell pepper is required.
     */
    public int getBellPepper(){
        if(this.bellPepper){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if garlic is used in the meal.
     *
     * @return 0 if garlic is not required or 1 if garlic is required.
     */
    public int getGarlic(){
        if(this.garlic){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if Chopped Tomatoes are used in the meal.
     *
     * @return 0 if chopped tomatoes are not required or 1 if they are required.
     */
    public int getChoppedTomatoes(){
        if(this.choppedTomatoes){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if Cherry Tomatoes are used in the meal.
     *
     * @return 0 if no cherry tomatoes are required or 1 if they are required.
     */
    public int getCherryTomatoes(){
        if(this.cherryTomatoes){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if eggs are used in the meal.
     *
     * @return 0 if no eggs are required or 1 if they are required.
     */
    public int getEggs(){
        if(this.eggs){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if cheese is used in the meal.
     *
     * @return 0 if cheese is not required or 1 if it is required.
     */
    public int getCheese(){
        if(this.cheese){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if mayo is used in the meal.
     *
     * @return 0 if mayo is not required or 1 if it is required.
     */
    public int getMayo(){
        if(this.mayo){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if Sour cream is used in the meal.
     *
     * @return 0 if sour cream is not required or 1 if it is required.
     */
    public int getSourCream(){
        if(this.sourCream){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if double cream is used in the meal.
     *
     * @return 0 if double cream is not required or 1 if it is required.
     */
    public int getDoubleCream(){
        if(this.doubleCream){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if pasta sauce is used in the meal.
     *
     * @return 0 if pasta sauce is not required or 1 if it is required.
     */
    public int getPastaSauce(){
        if(this.pastaSauce){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if Curry sauce is used in the meal.
     *
     * @return 0 if Curry sauce is not required or 1 if it is required.
     */
    public int getCurrySauce(){
        if(this.currySauce){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if Soy Sauce is used in the meal.
     *
     * @return  0 if soy sauce is not required or 1 if it is required.
     */
    public int getSoySauce(){
        if(this.soySauce){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Outputs if coconut milk is used in the meal.
     *
     * @return 0 if coconut milk is not required or 1 if it is required.
     */
    public int getCoconutMilk(){
        if(this.coconutMilk){
            return 1;
        }
        else{
            return 0;
        }
    }



}
