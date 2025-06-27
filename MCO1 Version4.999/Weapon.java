/**
 * Class file for the Weapon class, this class will be updated later on for MCO2.
 * <p>
 *     This class contains mainly getter methods as most attributes are declared upon construction.
 * </p>
 * <p>
 *     This class also contains a static attribute that keeps count of the total Weapon objects generated.
 * </p>
 * @author Stefan_Martin
 */

public class Weapon {
    
    //Private Attributes

    //String attributes
    private String name;
    private String type;

    //Stat attributes
    private int attack; //Amount of damage the weapon can deal
    private int speedPenalty; //Speed decrease when using the weapon

    //Static attributes
    private static int totalWeapons = 0; //Stores the amount of Weapon instances

    /**
     * Constructor for the Weapon class
     * @param name The weapon's unique name, assumes all names passed are valid
     * @param type Indicates the weapon's type, assumes all types passed are valid
     * @param attack The weapon's atk value, assumes any value as valid
     * @param speedPenalty The weapon's speed penalty, assumes any value as valid
     */
    //Method Constructor
    public Weapon(String name, String type, int attack, int speedPenalty){

        this.name = name;
        this.type = type;
        this.attack = attack;
        this.speedPenalty = speedPenalty;
        totalWeapons++; //Increment counter

    }


    //Getters

    /**
     * Getter method for the weapon name
     * @return the name of the weapon object
     */
    public String getName(){

        return name;

    }

    /**
     * Getter method for the weapon type
     * @return the type of the weapon object
     */
    public String getType(){

        return type;

    }

    /**
     * Getter method for the weapon's atk value
     * @return the atk value of the weapon object
     */
    public int getAttack(){

        return attack;

    }

    /**
     * Getter method for the weapon's speed penalty
     * @return speed penalty of the weapon object
     */
    public int getSpeedPenalty(){

        return speedPenalty;

    }

    /**
     * Static getter method for total weapon count
     * @return current total number of weapon objects constructed
     */
    //Returns total amount of Weapon instances 
    public static int getTotalWeapons(){

        return totalWeapons;
        
    }
}
