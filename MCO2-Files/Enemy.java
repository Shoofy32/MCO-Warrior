/**
 * Class file for the abstract Enemy Class, this is a subclass of the abstract Character class.
 * <p>
 *     This class serves as the Player's opponent in battle.
 * </p>
 * <p>
 *     This class now implements faux AI, each subclass implements a different attack pattern dictated upon initialization
 * </p>
 * <p>
 *     This class also contains a static attribute to keep track of the total Enemy objects generated.
 * </p>
 * @see Character
 * @author Stefan_Martin
 */
public abstract class Enemy extends Character{
  
    private String type;
    private String pattern;
    private int counter = 0;
    public static int totalEnemies = 0;

    /**
     * Constructor for the enemy class
     * @param name name of the enemy, all names are assumed valid
     * @param type type of the enemy, all types are assumed valid
     * @param hitPoints base HP of the enemy, all values are assumed valid
     * @param attack attack value of the enemy, all values are assumed valid
     * @param defense defense value of the enemy, all values are assumed valid
     * @param speed speed value of the enemy, all values are assumed valid
     * @param pattern attack pattern of the enemy
     */
    public Enemy(String name, String type, int hitPoints, int attack, int defense, int speed, String pattern){

        super(name, hitPoints, attack, defense, speed);
        this.type = type;
        this.pattern = pattern;
        setTurnInputAction(pattern.charAt(counter));

    }

    //Getters

    /**
     * Getter method to retrieve the enemy's type
     * @return enemy's type
     */
    public String getType(){

        return type;

    }

    /**
     * Getter method to retrieve how far the enemy is into their attack pattern
     * @return current position of the enemy's attack pattern
     */
    public int getCounter(){

        return counter;

    }

    /**
     * Static getter method for the total enemy objects constructed
     * @return the current amount of enemy objects constructed
     */
    public static int getTotalEnemies(){

        return totalEnemies;

    }

    /**
     * Method to iterate through the enemy's attack pattern dictating its action in the turn
     */
    public void updateTurnAction(){
            
        counter++;

        if(counter == pattern.length())
            counter = 0;

        if((pattern.charAt(counter) == 'U' && getConsumable().getChargesLeft() == 0) || 
        (pattern.charAt(counter) == 'F' && (getWeapon() == null || !(getWeapon() instanceof EnchantedWeapon) 
        || !(((EnchantedWeapon) getWeapon()).getWeaponSkill().getHasMetConditions()))))

            setTurnInputAction('A');
        else
            setTurnInputAction(pattern.charAt(counter));
    
    }

    /**
     * Resets the position of the enemy's attack pattern
     */
    public void resetTurnAction(){

        if(counter != 0)
            counter = 0;
    
    }

}
