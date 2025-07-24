/**
 * Class file for the abstract Weapon class.
 * <p>
 *     This class contains mainly getter methods as most attributes are declared upon construction and do not change.
 * </p>
 * <p>
 *     This class also contains a static attribute that keeps count of the total Weapon objects generated.
 * </p>
 * @author Stefan_Martin
 */

public abstract class Weapon{
    
    //Private Attributes
    private String name;
    protected String type;
    private int attack; //Amount of damage the weapon can deal
    private int speedPenalty; //Speed decrease when using the weapon
    private Ability abilityType;

    //Static attributes
    protected static int totalWeapons = 0; //Stores the amount of Weapon instances

    /**
     * Constructor for the Weapon class
     * @param name The weapon's unique name, assumes all names passed are valid
     * @param attack The weapon's atk value, assumes any value as valid
     * @param speedPenalty The weapon's speed penalty, assumes any value as valid
     */
    //Method Constructor
    public Weapon(String name, int attack, int speedPenalty){

        this.name = name;
        this.attack = attack;
        this.speedPenalty = speedPenalty;
        
        totalWeapons++; //Increment counter

    }

    //Setters

    /**
     * Assign an ability/ passive to the weapon
     * @param abilityType ability to be assigned to the weapon
     */
    protected void setAbility(Ability abilityType){

        this.abilityType = abilityType;

    }

    //Getters

    /**
     * Getter method for the weapon name
     * @return the name of the weapon object
     */
    protected String getName(){

        return name;

    }


    /**
     * Getter method for the weapon's atk value
     * @return the atk value of the weapon object
     */
    protected int getAttack(){

        return attack;

    }

    /**
     * Getter method for the weapon's speed penalty
     * @return speed penalty of the weapon object
     */
    protected int getSpeedPenalty(){

        return speedPenalty;

    }


    /**
     * Getter method for the weapon type
     * @return the type of the weapon object
     */
    protected String getType(){

        return type;

    }

    /**
     * Getter method for the Ability type
     * @return the Ability contained in the weapon object
     */
    protected Ability getAbility(){

        return abilityType;

    }

    /**
     * Static getter method for total weapon count
     * @return current total number of weapon objects constructed
     */
    //Returns total amount of Weapon instances 
    protected static int getTotalWeapons(){

        return totalWeapons;
        
    }

    /**
     * Method to activate the current weapon's equipped ability/ passive
     * @param character Character object that has the weapon equipped
     * @param target Character object which is the target of the weapon's ability
     */
    public void usePassiveAbility(Character character, Character target){

        abilityType.activateAbility(character, target);
        
    }

}
